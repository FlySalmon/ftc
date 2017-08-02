package com.eif.ftc.service.test.prepare;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.market.facade.request.groupon.FreezeGrouponInventoryReq;
import org.mockito.Mockito;

/**
 * Created by bohan on 11/7/16.
 */
public class MarketDataPrepare {

    public static void buildFreezeGrouponInventoryForSuccess(MarketIntService marketIntService) {
        Mockito.doNothing().when(marketIntService).freezeGrouponInventory(Mockito.any(FreezeGrouponInventoryReq.class));
    }

    public static void buildUnfreezeAndReturnGrouponInventoryForSuccess(MarketIntService marketIntService) {
        Mockito.doNothing().when(marketIntService).unfreezeAndReturnGrouponInventory(Mockito.anyString());
    }

    public static void buildUnfreezeAndDeductGrouponInventoryForSuccess(MarketIntService marketIntService) {
        Mockito.doNothing().when(marketIntService).unfreezeAndDeductGrouponInventory(Mockito.anyString());
    }

    public static void buildSendMemberInvestmentForSuccess(MarketIntService marketIntService) {
        Mockito.doNothing().when(marketIntService).sendMemberInvestment(Mockito.any(FundTransOrder.class), Mockito.any(QueryProdTransInfoV2Resp.class));
    }
}
