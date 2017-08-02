package com.eif.ftc.service.trans.action.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.request.ftc.DeductionMonetaryInstrumentBalanceReq;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.action.FundRedeemActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/27/16.
 */
@Service("fundRedeemActionService")
public class FundRedeemActionServiceImpl implements FundRedeemActionService {

    static Logger logger = LoggerFactory.getLogger(FundRedeemActionServiceImpl.class);

    @Resource
    RiskIntService riskIntService;

    @Autowired
    FundAccountService fundAccountService;

    @Resource
    FisIntService fisIntService;

    @Autowired
    FundTransDataService fundTransDataService;

    @Resource
    OfcIntService ofcIntService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundCommTransActionService fundCommTransActionService;

    // 赎回限额判断
    public void doRedeemOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                          QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 基础限制判断
//        doBaseQuotaLimitedCheck(queryProdTransInfoResp);

        // 金额产品限额判断
        doBaseProdQuotaLimitedCheck(curDate, fundTransAmount, queryProdTransInfoResp);

        // 风控限额非并发判断
        doRiskQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);
    }


    public void doBaseQuotaLimitedCheck(QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 有条件开放式为开放式
        if (queryProdTransInfoResp.getCloseType() == ProductCloseType.CLOSE) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_REDEEM_CLOSED.getMessage(),
                    FTCRespCode.ERR_FUND_TRANS_REDEEM_CLOSED.getCode());
        }

    }

    public void doBaseProdQuotaLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryProdTransInfoV2Resp queryProdTransInfoResp) {

        BigDecimal minSellAmt;
        BigDecimal maxSellAmt;
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            minSellAmt = queryProdTransInfoResp.getNormalProdTransInfo().getMinSellAmt();
            maxSellAmt = queryProdTransInfoResp.getNormalProdTransInfo().getMaxSellAmt();
        }else{
            minSellAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getMinSellAmt();
            maxSellAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getMaxSellAmt();
        }

        // 小于单笔赎回下限
        if (fundTransAmount.compareTo(minSellAmt) == -1 && minSellAmt.compareTo(new BigDecimal(-1)) != 0) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_MIN_REDEEM_AMT_EXCEEDED.getMessage(),
                    FTCRespCode.ERR_FUND_TRANS_MIN_REDEEM_AMT_EXCEEDED.getCode());
        }
        // 超过单笔赎回上限
        if (fundTransAmount.compareTo(maxSellAmt) == 1 && maxSellAmt.compareTo(new BigDecimal(-1)) != 0) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_MAX_REDEEM_AMT_EXCEEDED.getMessage(),
                    FTCRespCode.ERR_FUND_TRANS_MAX_REDEEM_AMT_EXCEEDED.getCode());
        }
    }

    public void doRiskQuotaLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                        QueryProdTransInfoV2Resp queryProdTransInfoResp) {


        BigDecimal perUdsLimitAmt;
        Long productId;
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            productId = queryProdTransInfoResp.getNormalProdTransInfo().getId();
            perUdsLimitAmt = queryProdTransInfoResp.getNormalProdTransInfo().getPerUdsLimitAmt();
        }else{
            productId = queryProdTransInfoResp.getCurrentProdTransInfo().getId();
            perUdsLimitAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getPerUdsLimitAmt();
        }
        // 单户单日赎回最高金额/份额：用户赎回产品的单日最高限额
        if (perUdsLimitAmt.compareTo(new BigDecimal(-1)) != 0) {
            if (fundTransAmount.compareTo(perUdsLimitAmt) == -1 ||
                    fundTransAmount.compareTo(perUdsLimitAmt) == 0) {
                BigDecimal originalSharesOrAmount = riskIntService.queryDayAmount(productId,
                        memberResp.getUserInfoBean().getMemberNo(), FundTransType.REDEEMING, curDate, null).getDayAmount();

                if (originalSharesOrAmount.add(fundTransAmount).compareTo(perUdsLimitAmt) == 1) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT.getCode());
                }
            } else {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT.getCode());
            }
        }

    }


    //--------- Compensable -----------//
    // 判断是否触发流动性
    public void deductionMonetaryInstrumentBalance(String deductionToken,
                                                      BigDecimal fundTransAmount, Long productId) {
        DeductionMonetaryInstrumentBalanceReq dreq = new DeductionMonetaryInstrumentBalanceReq();
        dreq.setToken(deductionToken);
        dreq.setAmount(fundTransAmount);
        dreq.setProductId(productId);
        fisIntService.deductionMonetaryInstrumentBalance(dreq);
    }

    public void deductionMonetaryInstrumentBalanceCompensable(String deductionToken,
                                                         BigDecimal fundTransAmount, Long productId) {
        DeductionMonetaryInstrumentBalanceReq dreq = new DeductionMonetaryInstrumentBalanceReq();
        dreq.setToken(deductionToken);
        dreq.setProductId(productId);

        try {
            fisIntService.deductionMonetaryInstrumentBalance(dreq);
        } catch (Exception e) {
            logger.error("deductionMonetaryInstrumentBalanceCompensable failed! ", e);
            // 异步回滚
            fisIntService.deductionMonetaryInstrumentBalanceAsync(dreq);
        }
    }

    public void addDayQuota(FundTransOrder order, TagRules tagRules) {
        riskIntService.addDayStat(order, tagRules);
    }

    public void addDayQuotaCompensable(FundTransOrder order, TagRules tagRules) {
        try {
            // 先同步回滚
            riskIntService.deductDayStat(order, tagRules);
        } catch (Exception e) {
            logger.error("addDayQuotaCompensable failed! ", e);
            // 异步回滚
            riskIntService.deductDayStatAsync(order, tagRules);
        }
    }

    @Transactional
    public Boolean insertFundTransOrderTransaction(FundTransOrder order, Date curDate) {
        //控制ofc发消息
        Boolean result = Boolean.FALSE;
        try {
            fundTransOrderMapper.insertSelective(order);
        } catch (DuplicateKeyException ex) {
            logger.error("dup insert by " + order.getFundTransOrderNo());

            FundTransOrder updateOrder = new FundTransOrder();
            updateOrder.setStatus(FundTransOrderStatus.REDEEM_FAILED);
            updateOrder.setFinishTime(curDate);
            FundTransOrderExample example = new FundTransOrderExample();
            FundTransOrderExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(order.getId());
            fundTransOrderMapper.updateByExampleSelective(updateOrder, example);
            order.setStatus(updateOrder.getStatus());
            order.setFinishTime(curDate);
            result = Boolean.TRUE;

        }
        // 更新基金交易状态表
        fundTransDataService.createFundTransOrderStatusInfo(curDate, order.getStatus(), order.getId());
        return result;
    }

    public void insertFundTransOrderForException(FundTransOrder transOrder, Date curDate, Throwable throwable, String productName,Integer closeType) {
        transOrder.setStatus(FundTransOrderStatus.REDEEM_FAILED);
        transOrder.setFinishTime(curDate);

        String reasonCode = null;
        if (throwable instanceof BusinessException) {
            reasonCode = ((BusinessException) throwable).getCode();
            BusinessException be = (BusinessException) throwable;
            if (be.getCode().equals(FTCRespCode.ERR_FUND_RISK_REJECTED.getCode())) {
                transOrder.setStatus(FundTransOrderStatus.REJECTED_BY_RISK);
            } else if (be.getCode().equals(FTCRespCode.ERR_FUND_AMC_CALL_QUERY.getCode()) ||
                    be.getCode().equals(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode())) {
                return;
            } else if (be.getCode().equals(FTCRespCode.ERR_FUND_TRANS_REDEEM_AMT_HUGE_REFUSE.getCode())) {
                transOrder.setStatus(FundTransOrderStatus.HUGE_REDEEM_REFUSED);
            }
        }

        fundTransDataService.logFundTransOrderException(transOrder.getFundTransOrderNo(), throwable, curDate);
        Boolean needNotifyOFC = insertFundTransOrderTransaction(transOrder, curDate);

        if(needNotifyOFC)
        {
            ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName, reasonCode);
        }

        if (StringUtils.isEmpty(transOrder.getBusinessOrderItemNo())) {
            // reason? TODO
            fundCommTransActionService.buildForBusinessOrderItem(transOrder, productName, null,closeType);
        } else {
            ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(),productName, reasonCode);
        }

    }

}
