package com.eif.ftc.service.trans.action;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundBuyingLimitedActionService {

    void doCreateOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, String memberNo,
                                   Long productId);

    void doCreateOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                   QueryProdTransInfoV2Resp queryProdTransInfoResp);

    void doPrePayOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                   QueryProdTransInfoV2Resp queryProdTransInfoResp);

    void doPayingOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                   QueryProdTransInfoV2Resp queryProdTransInfoResp);
}
