package com.eif.ftc.service.trans.action;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.model.FundTransOrder;

import java.util.Date;

/**
 * Created by bohan on 9/27/16.
 */
public interface FundChargeActionService {

    void notifyCustomerWhenInvestSuc(FundTransOrder transOrder, Integer closeType, String productName, String mobile);

    void doRefund(FundTransOrder transOrder, String productName, String mobile);

    void refundFundTransOrder(Date curDate, FundTransOrder transOrder, int targetStatus);

    void compensateProdInventory(int closeType,FundTransOrder transOrder);

    void unfreezeAndDeductGrouponInventory(FundTransOrder transOrder);

    void unfreezeAndDeductProdInventory(int closeType,String frozenCode);

    void notifyMarketWhenInvestSuc(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp);
    
}
