package com.eif.ftc.service.test.prepare;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.paycore.facade.PaymentProviderRouteFacade;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.request.ResumePayTransResquest;
import com.eif.transcore.facade.request.RoutePaymentProviderInfoRequest;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.ResumePayTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA.COMM_FAILURE;

/**
 * Created by bohan on 10/7/16.
 */
public class TranscoreDataPrepare {

    public static void buildCreateTransCoreOrderForSuccess(TranscoreIntService transcoreIntService, boolean authPending) {
        CreateTransResponse resp = new CreateTransResponse();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        resp.setTransNo(UUIDGenerator.gen());
        if (authPending) {
            resp.setTransactionStatus(TransactionStatus.AUTH_PENDING);
            resp.setAuthWaitingPaymentNo(UUIDGenerator.gen());
        }
        else {
            resp.setTransactionStatus(TransactionStatus.SETTLING);
        }

        Mockito.doReturn(resp).when(transcoreIntService).createTransCoreOrder(Mockito.any(FundTransOrder.class), Mockito.anyString(), Mockito.anyList(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(RoutePaymentProviderInfoResponse.class),Mockito.anyString());
    }

    public static void buildPaymentRoutingForSuccess(TranscoreIntService transcoreIntService, boolean noSmsSend) {
        RoutePaymentProviderInfoResponse resp = new RoutePaymentProviderInfoResponse();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        if (noSmsSend) {
            resp.setSmsStrategy(SmsStrategy.NEVER_SEND_SMS);
        }
        else {
            resp.setSmsStrategy(SmsStrategy.ALWAYS_SEND_SMS);
        }
        Mockito.doReturn(resp).when(transcoreIntService).paymentRouting(Mockito.any(RoutePaymentProviderInfoRequest.class));
    }

    public static void buildResumePayTransForSuccess(TranscoreIntService transcoreIntService) {
        ResumePayTransResponse resp = new ResumePayTransResponse();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        resp.setTransNo(UUIDGenerator.gen());
        resp.setTransStatus(TransactionStatus.SETTLING);
        Mockito.doReturn(resp).when(transcoreIntService).resumePayTrans(Mockito.any(ResumePayTransResquest.class));
    }

}
