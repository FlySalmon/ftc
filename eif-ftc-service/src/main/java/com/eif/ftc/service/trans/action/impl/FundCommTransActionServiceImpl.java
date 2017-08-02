package com.eif.ftc.service.trans.action.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.dao.OuterOrderRelMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.OuterOrderRel;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.fund.acct.FundAcctOrderService;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.ofc.facade.dto.enumeration.BusinessOrderItemType;
import com.eif.ofc.facade.response.CreateOrderItemResponse;
import com.eif.risk.facade.constant.RiskMemberStatus;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by bohan on 9/26/16.
 */
@Service("fundCommTransActionService")
public class FundCommTransActionServiceImpl implements FundCommTransActionService {

    static Logger logger = LoggerFactory.getLogger(FundBuyingPayActionServiceImpl.class);

    @Resource
    HadesClient hadesClient;

    @Autowired
    FundAccountService fundAccountService;

    @Resource
    OfcIntService ofcIntService;
    
    @Resource
    RiskIntService riskIntService;

    @Autowired
    FundAcctOrderService fundAcctOrderService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundTransDataService fundTransDataService;

    @Autowired
    OuterOrderRelMapper outerOrderRelMapper;

    public FundAccountBean checkAndBuildAmcAccount(FundTransOrder transOrder, QueryMemberInfoResponse memberInfoResponse) {
        FundAccountBean resultBean = fundAccountService.getFundAccountByMemberNo(memberInfoResponse.getUserInfoBean().getMemberNo());
        if (resultBean == null)
            return null;
        int fundAcctStatus = resultBean.getAccountStatus();

        transOrder.setAssetAccountNo(resultBean.getFundAccountNo());
        transOrder.setRefFundAccountNo(resultBean.getTaAccountId());
        transOrder.setTransAccountNo(resultBean.getTransactionAccount());

        if (fundAcctStatus == FundAcctStatus.FROZEN || fundAcctStatus == FundAcctStatus.TA_FAILED
                || fundAcctStatus == FundAcctStatus.TA_FINISH) {
            throw new BusinessException(String.format("基金资产账号状态错误, 状态为: %s", fundAcctStatus),
                    FTCRespCode.ERR_FUND_ACCT_STATUS.getCode());
        }

        if (riskIntService.getRiskUserLock(transOrder.getMemberNo()).getTransLock() == RiskMemberStatus.FORBIDDEN) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_TRANS_FORBIDDEN.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_TRANS_FORBIDDEN.getCode());
        }

        return resultBean;
    }

    public void buildForBusinessOrderItem(FundTransOrder contextFundTransOrder, String productName, String marketField,Integer closeType) {
        int businessOrderItemType;
        if (contextFundTransOrder.getFundTransType() == FundTransType.SUBSCRIBING) { // 只有封闭式认购
            businessOrderItemType = BusinessOrderItemType.SUBSCRIBE;
        } else if (contextFundTransOrder.getFundTransType() == FundTransType.PURCHASING){
            if(closeType.equals(ProductCloseType.HALF_OPEN) || closeType.equals(ProductCloseType.CLOSE_OPEN)){
                businessOrderItemType = BusinessOrderItemType.SUBSCRIBE;
            } else {
                businessOrderItemType = BusinessOrderItemType.PURCHASE;
            }
        }
        else {
            businessOrderItemType = BusinessOrderItemType.REDEEM;
        }

        // 创建ofc业务项单项, ofc控制幂等
        try {
            CreateOrderItemResponse createOrderItemResponse = ofcIntService.createBusinessOrderItem(contextFundTransOrder, businessOrderItemType, null, marketField,productName);
            contextFundTransOrder.setBusinessOrderItemNo(createOrderItemResponse.getResult().getBusinessOrderItemNo());
        }
        catch (Exception ex) {
            // 如果创建ofc用户订单失败,则异步通知用户订单失败(要求ofc能够处理: 用户订单未创建, 但是有更新消息的情况)
            int failedStatus = FundTransOrderStatus.PAY_FAILED;
            if (businessOrderItemType != BusinessOrderItemType.REDEEM) {
                failedStatus = FundTransOrderStatus.REDEEM_FAILED;
            }
            ofcIntService.updateBusinessOrderItemStatusAsync(contextFundTransOrder, failedStatus, productName);
            throw ex;
        }
    }

    @Transactional
    public void doCreateFundTransTransaction(FundTransOrder contextFundTransOrder, Date curDate, boolean isMemberExist, String outerSysNo, String outerOrderNo) {

        // 是否需要开资产账户
        if (!isMemberExist) {
            FundAccountBean resultBean = fundAcctOrderService.openFundAccount(contextFundTransOrder.getBizChannel(), contextFundTransOrder.getMemberNo(), contextFundTransOrder.getProductId());
            contextFundTransOrder.setAssetAccountNo(resultBean.getFundAccountNo());
            contextFundTransOrder.setRefFundAccountNo(resultBean.getTaAccountId());
            contextFundTransOrder.setTransAccountNo(resultBean.getTransactionAccount());
        }

        // 插入业务单
        fundTransOrderMapper.insertSelective(contextFundTransOrder); // 幂等控制
        fundTransDataService.createFundTransOrderStatusInfo(curDate, contextFundTransOrder.getStatus(), contextFundTransOrder.getId());

        // 业务单关联外部订单
        if (!StringUtils.isEmpty(outerSysNo) && !StringUtils.isEmpty(outerOrderNo)) {
            OuterOrderRel outerOrderRel = new OuterOrderRel();
            outerOrderRel.setOuterOrderNo(outerOrderNo);
            outerOrderRel.setOuterSysNo(outerSysNo);
            outerOrderRel.setBizSysNo(BizSysCode.FTC_SYSTEM);
            outerOrderRel.setBizOrderNo(contextFundTransOrder.getFundTransOrderNo());

            outerOrderRelMapper.insertSelective(outerOrderRel);
        }
    }

}
