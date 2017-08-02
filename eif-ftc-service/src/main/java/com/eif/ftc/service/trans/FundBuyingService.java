package com.eif.ftc.service.trans;

import com.eif.ftc.facade.fund.trans.request.CreateFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.PayFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.ResumePayRequest;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundBuyingService {
    void createFundBuyingOrder(CreateFundBuyingOrderRequest createFundBuyingOrderRequest, CreateFundBuyingOrderResponse createFundBuyingOrderResponse);

    void payFundBuyingOrder(PayFundBuyingOrderRequest payFundBuyingOrderRequest, PayFundBuyingOrderResponse resp);

    void resumePay(ResumePayRequest request, ResumePayResponse response);
}
