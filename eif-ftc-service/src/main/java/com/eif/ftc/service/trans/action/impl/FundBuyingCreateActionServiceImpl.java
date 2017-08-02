package com.eif.ftc.service.trans.action.impl;

import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.dao.OuterOrderRelMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.fund.acct.FundAcctOrderService;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.trans.action.FundBuyingCreateActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.risk.facade.constant.CheckPointList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by bohan on 9/22/16.
 */
@Service("fundBuyingCreateActionService")
public class FundBuyingCreateActionServiceImpl implements FundBuyingCreateActionService {

    static Logger logger = LoggerFactory.getLogger(FundBuyingCreateActionServiceImpl.class);

    @Resource
    RiskIntService riskIntService;

    @Autowired
    FundTransDataService fundTransDataService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Resource
    OfcIntService ofcIntService;

    @Autowired
    FundAcctOrderService fundAcctOrderService;

    @Autowired
    OuterOrderRelMapper outerOrderRelMapper;

    @Autowired
    FundAccountService fundAccountService;

    public void riskPreCheck(FundTransOrder contextFundTransOrder, String deviceInfo, String devId, String ip, String productName) {
        Date curDate = new Date();
        try {
            // 事前风控控制
            riskIntService.riskCheckTransIng(contextFundTransOrder, CheckPointList.USER_FT_ORDER_PRE.getCheckPoint(), deviceInfo, devId, ip);
        } catch (BusinessException be) {
            fundTransDataService.logFundTransOrderException(contextFundTransOrder.getFundTransOrderNo(), be, curDate);
            // 被风控拒绝则
            if (be.getCode().equals(FTCRespCode.ERR_FUND_RISK_REJECTED.getCode())) {

                // 创建风控失败单据
                contextFundTransOrder.setStatus(FundTransOrderStatus.REJECTED_BY_RISK);
                contextFundTransOrder.setFinishTime(curDate);
                contextFundTransOrder.setAssetAccountNo("");
                fundTransOrderMapper.insertSelective(contextFundTransOrder);
                fundTransDataService.createFundTransOrderStatusInfo(curDate, contextFundTransOrder.getStatus(), contextFundTransOrder.getId(), contextFundTransOrder.getRemark(), contextFundTransOrder.getOperatorNo());

                // OFC风控拒绝更新
                ofcIntService.updateBusinessOrderItemStatusAsync(contextFundTransOrder, FundTransOrderStatus.REJECTED_BY_RISK, productName);
            }
            else {
                // 比如超时, 直接置为失败
                ofcIntService.updateBusinessOrderItemStatusAsync(contextFundTransOrder, FundTransOrderStatus.PAY_FAILED, productName);
            }
            throw be;
        }
    }

}
