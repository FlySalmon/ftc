package com.eif.ftc.service.trans.action;

import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/23/16.
 */
public interface FundBuyingPayActionService {
    RoutePaymentProviderInfoResponse doPaymentRouting(
            List<TransactionPaymentOptionBean> paymentOptions, Integer smsSendControl,String memberNo);
    boolean checkDCPPaymentOption(List<TransactionPaymentOptionBean> paymentOptions);

    void updatePayFundTransOrderTransaction(FundTransOrder order, List<Integer> prevStatusList, Date curDate);

    void riskPayingCheck(FundTransOrder contextFundTransOrder, String deviceInfo, String devId, String ip, String productName);

    void addDayQuota(FundTransOrder order, TagRules tagRules);

    void freezeProdInventory(int closeType, String frozenCode, FundTransOrder transOrder);

    void updateRookie(String memberNo, boolean isNovicePacks);

    void updateRookieCompensable(String memberNo);

    void freezeProdInventoryCompensable(FundTransOrder order, String frozenCode,int closeType);

    void addDayQuotaCompensable(FundTransOrder order, TagRules tagRules);

    void resumePayTrans(String transNo, String pin, FundTransOrder transOrder, Date curDate, Long orderExpireTime, String productName);
    void payWithoutOTP(FundTransOrder transOrder, RoutePaymentProviderInfoResponse paymentRoutingResp,
                       List<TransactionPaymentOptionBean> paymentOptions, Date curDate, Long orderExpireTime, String productName, String extField);

    void payingControl(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp, QueryMemberInfoResponse memberResp,
                       Date curDate, int prevStatus, String deviceInfo, String devId, String ip );

    void updateFundTransOrderForException(FundTransOrder transOrder, Date curDate, Throwable throwable, List<Integer> prevStatusList, String productName);

    void freezeGrouponInventory(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp);
    void freezeGrouponInventoryCompensable(FundTransOrder transOrder);

}
