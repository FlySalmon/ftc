package com.eif.ftc.biz.facade.impl.fund.trans;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.FundTransFacade;
import com.eif.ftc.facade.fund.trans.request.*;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.RedeemFundResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.service.trans.action.FundBuyingLimitedActionService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class FundTransFacadeImpl implements FundTransFacade {

    @Autowired
    private FundRedeemService fundRedeemingService;

    @Autowired
    private FundBuyingService fundBuyingService;

    @Autowired
    FundBuyingLimitedActionService fundBuyingLimitedActionService;

    private final static Logger logger = LoggerFactory.getLogger(FundTransFacadeImpl.class);

    public RedeemFundResponse redeemFund(RedeemFundRequest redeemFundRequest) {
        RedeemFundResponse resp = new RedeemFundResponse();
        fundRedeemingService.redeemFund(redeemFundRequest, resp);
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        return resp;
    }

    @Override
    public CreateFundBuyingOrderResponse createFundBuyingOrder(CreateFundBuyingOrderRequest createFundBuyingOrderRequest) {
        CreateFundBuyingOrderResponse resp = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(createFundBuyingOrderRequest, resp);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

    @Override
    public PayFundBuyingOrderResponse payFundBuyingOrder(PayFundBuyingOrderRequest payFundBuyingOrderRequest) {
        PayFundBuyingOrderResponse resp = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, resp);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

    @Override
    public ResumePayResponse resumePay(ResumePayRequest request) {
        ResumePayResponse resp = new ResumePayResponse();
        fundBuyingService.resumePay(request, resp);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

    @Override
    public BaseResponse commonBuyFundLimitJustify(CommonBuyFundLimitJustifyRequest commonBuyFundLimitJustifyRequest) {
        BaseResponse resp = new BaseResponse();

        fundBuyingLimitedActionService.doCreateOrderLimitedCheck(new Date(), commonBuyFundLimitJustifyRequest.getFundTransAmount(), commonBuyFundLimitJustifyRequest.getMemberNo(), commonBuyFundLimitJustifyRequest.getProductID());

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

}
