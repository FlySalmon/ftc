package com.eif.ftc.service.trans.impl;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.CurrencyISOCode;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.ftc.dal.dao.FundPersonalRedeemDailyVolMapper;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.facade.fund.trans.enumeration.FundInteractType;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.request.RedeemFundRequest;
import com.eif.ftc.facade.fund.trans.response.RedeemFundResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.paycore.PaycoreIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.data.fund.FundAmcDataService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.action.FundRedeemActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.risk.facade.constant.CheckPointList;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundRedeemService")
public class FundRedeemServiceImpl implements FundRedeemService {

    private static Logger logger = LoggerFactory.getLogger(FundRedeemServiceImpl.class);

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    MemberAssetService memberAssetService;

    @Resource
    OfcIntService ofcIntService;

    @Resource
    TranscoreIntService transcoreIntService;

    @Resource
    PaycoreIntService paycoreIntService;

    @Resource
    FisIntService fisIntService;

    @Autowired
    FundTransDataService fundTransDataService;

    @Autowired
    FundAmcDataService fundAmcDataService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundPersonalRedeemDailyVolMapper fundPersonalRedeemDailyVolMapper;

    @Autowired
    MQMessageSender mqMessageSender;

    @Resource
    SequenceGenerator sequenceGenerator;

    @Resource
    GoutongIntService goutongIntService;

    @Resource
    RiskIntService riskIntService;

    @Resource
    MemberIntService memberIntService;

    @Autowired
    FundCommTransActionService fundCommTransActionService;

    @Autowired
    FundRedeemActionService fundRedeemActionService;

    @Resource
    RedisConcurrentLock redisConcurrentLock;

    static int KEY_EXPIRED_TIME_IN_SECOND = 30 * 60; // 30分钟

    public void redeemFund(RedeemFundRequest redeemFundRequest, RedeemFundResponse resp) {

        if (redeemFundRequest.getFundTransAmount().compareTo(BigDecimal.ZERO) == -1) {
            throw new BusinessException(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage(),
                    FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
        }

        // 基础信息获取
        Date curDate = new Date();
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService
                .queryProdTransInfoV2(redeemFundRequest.getProductId());

        fundRedeemActionService.doBaseQuotaLimitedCheck(queryProdTransInfoResp);

        String productName;
        Boolean isNovicePacks;
        TagRules tagRules;
        Long groupBuyId = Long.valueOf(-1);
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
            isNovicePacks = queryProdTransInfoResp.getNormalProdTransInfo().getIsNovicePacks();
            tagRules = queryProdTransInfoResp.getNormalProdTransInfo().getTagRules();
            groupBuyId = queryProdTransInfoResp.getNormalProdTransInfo().getGroupBuyId();
        }else
        {
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
            isNovicePacks = queryProdTransInfoResp.getCurrentProdTransInfo().getIsNovicePacks();
            tagRules = queryProdTransInfoResp.getCurrentProdTransInfo().getTagRules();
        }

        QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByMemberNo(redeemFundRequest.getMemberNo());

        // 构建基础业务订单信息
        int fundTransType = FundTransType.REDEEMING;
        FundTransOrder transOrder = new FundTransOrder();
        mapperFacade.map(redeemFundRequest, transOrder);
        //赎回资金走向处理
        if (redeemFundRequest.getPaymentInstrumentType() != null) {
            Map<String, Object> extField = new HashMap<>();
            extField.put(TransCoreConstant.PAYMENT_INSTRMENT_TYPE, redeemFundRequest.getPaymentInstrumentType());
            if (!StringUtil.isEmpty(redeemFundRequest.getPaymentInstrumentNo())) {
                extField.put(TransCoreConstant.PAYMENT_INSTRMENT_NO, redeemFundRequest.getPaymentInstrumentNo());
            }

            transOrder.setExtField(JSON.toJSONString(extField));
        }
        transOrder.setFundTransOrderNo(sequenceGenerator.transOrderGen(fundTransType));
        transOrder.setUpdateTime(curDate);
        transOrder.setCreateTime(curDate);
        transOrder.setTransTime(curDate);
        transOrder.setCurrencyType(CurrencyISOCode.CHINA);
        transOrder.setDiscountAmount(BigDecimal.ZERO);
        transOrder.setFundInteractType(FundInteractType.FILE);
        transOrder.setFundTransType(fundTransType);
        // 判断是否巨额赎回
        String deductionToken = UUIDGenerator.gen();
        transOrder.setFrozenCode(deductionToken);


        // 并发控制
        redisConcurrentLock.acquire(redeemFundRequest.getTrackingNo(), KEY_EXPIRED_TIME_IN_SECOND);

        try {
            // 判断amc账户状态, 是否交易禁止, 并获取用户信息
            FundAccountBean resultBean = fundCommTransActionService.checkAndBuildAmcAccount(transOrder, memberResp);

            // 没有amc账号, 抛出异常
            if (resultBean == null) {
                throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_QUERY.getMessage(), FTCRespCode.ERR_FUND_AMC_CALL_QUERY.getCode());
            }

            FundTotalAssetRequestBean assetBean = new FundTotalAssetRequestBean();
            assetBean.setMemberNo(redeemFundRequest.getMemberNo());
            assetBean.setProductId(redeemFundRequest.getProductId());

            // 获取amc资产，确认的和未确认的
            FundTotalAssetBean fundTotalAssetBean = memberAssetService.getFundTotalAsset(assetBean);

            if (fundTotalAssetBean == null)
                throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                        FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());

            // 做可赎回判断
            BigDecimal usableAmount = fundTotalAssetBean.getConfirmedAmount();
            if (transOrder.getFundTransAmount().compareTo(usableAmount) == 1)
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_REDEEM_AMT_NOT_ENOUGH.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_REDEEM_AMT_NOT_ENOUGH.getCode());

            transOrder.setFundTransType(fundTransType);
            transOrder.setStatus(FundTransOrderStatus.REDEEMING_APPLIED);

            // 赎回限额限制
            fundRedeemActionService.doRedeemOrderLimitedCheck(curDate, transOrder.getFundTransAmount(), memberResp, queryProdTransInfoResp);
            // 风控控制
            riskIntService.riskCheckTransIng(transOrder, CheckPointList.USER_FT_ORDER_PRE.getCheckPoint(), redeemFundRequest.getDeviceInfo(), redeemFundRequest.getDevId(), redeemFundRequest.getIp());
            try {
                // 加流量
                fundRedeemActionService.addDayQuota(transOrder, tagRules);
                try {
                    //判断是否触发流动性
                    fundRedeemActionService.deductionMonetaryInstrumentBalance(deductionToken, transOrder.getFundTransAmount(), transOrder.getProductId());
                    // 创建用户订单
                    fundCommTransActionService.buildForBusinessOrderItem(transOrder, productName, null,queryProdTransInfoResp.getCloseType());
                    // 创建赎回业务单
                    fundCommTransActionService.doCreateFundTransTransaction(transOrder, curDate, true, null, null);
                    // 异步调用交易核心记账
                    List<TransactionPaymentOptionBean> paymentOptions = new ArrayList<>();
                    TransactionPaymentOptionBean tpoBean = new TransactionPaymentOptionBean();
                    tpoBean.setPaymentInstrumentType(PaymentInstrumentType.MEMBER_BALANCE);
                    tpoBean.setAmount(transOrder.getFundTransAmount());
                    tpoBean.setCreditMemberNo(redeemFundRequest.getMemberNo());
                    tpoBean.setDebitMemberNo(redeemFundRequest.getMerMemberNo());
                    paymentOptions.add(tpoBean);
                    transcoreIntService.createTransAsync(transOrder, TransCode.REDEMPTION,
                            paymentOptions, TransAttribute.FUND,productName);
                    // 调用amc扣减份额
                    fundAmcDataService.redeemAsset(curDate, transOrder.getFundTransOrderNo(), redeemFundRequest.getMemberNo(),
                            redeemFundRequest.getProductId(), transOrder.getFundTransAmount(),queryProdTransInfoResp.getCloseType());
                    // 事后风控通知, 添加ip
                    riskIntService.riskCheckTransPost(transOrder, redeemFundRequest.getDeviceInfo(), redeemFundRequest.getDevId(), redeemFundRequest.getIp());
                }
                catch (Exception e) {
                    fundRedeemActionService.deductionMonetaryInstrumentBalanceCompensable(deductionToken, transOrder.getFundTransAmount(), transOrder.getProductId());
                    throw e;
                }
            }
            catch (Exception e) {
                // 回滚流量
                fundRedeemActionService.addDayQuotaCompensable(transOrder, tagRules);
                throw e;
            }
            // 返回值设置
            resp.setBusinessOrderItemNo(transOrder.getBusinessOrderItemNo());
            resp.setFundTransOrderNo(transOrder.getFundTransOrderNo());
            resp.setRespCode(CommonRspCode.SUCCESS.getCode());
            resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        } catch (Exception e) {
            fundRedeemActionService.insertFundTransOrderForException(transOrder, curDate, e, productName,queryProdTransInfoResp.getCloseType());
            throw e;
        }
        finally {
            redisConcurrentLock.release(redeemFundRequest.getTrackingNo());
        }
    }
}
