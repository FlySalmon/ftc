package com.eif.ftc.service.trans.action;

import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.fis.facade.response.ftc.DeductionMonetaryInstrumentBalanceResp;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoResp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bohan on 9/27/16.
 */
public interface FundRedeemActionService {
    void doRedeemOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                   QueryProdTransInfoV2Resp queryProdTransInfoResp);

    void deductionMonetaryInstrumentBalance(String deductionToken, BigDecimal fundTransAmount, Long productId);
    void deductionMonetaryInstrumentBalanceCompensable(String deductionToken, BigDecimal fundTransAmount, Long productId);

    void addDayQuota(FundTransOrder order, TagRules tagRules);
    void addDayQuotaCompensable(FundTransOrder order, TagRules tagRules);

    void insertFundTransOrderForException(FundTransOrder transOrder, Date curDate, Throwable throwable, String productName,Integer closeType);
    void doBaseQuotaLimitedCheck(QueryProdTransInfoV2Resp queryProdTransInfoResp);

}