package com.eif.ftc.integration.market;

import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.market.facade.dto.CalcUserCouponBean;
import com.eif.market.facade.request.groupon.FreezeGrouponInventoryReq;
import com.eif.market.facade.response.couponuse.CalcUserCouponListResp;
import com.eif.market.facade.response.groupon.CalcGrouponProfitForUserResp;

import java.util.List;
import java.util.Map;

/**
 * Created by bohan on 1/11/16.
 */
public interface MarketIntService {

    CalcGrouponProfitForUserResp calcGrouponProfitForUser(String memberNo , List<Long> productIds);

    /**
     * 计算加息金额
     * @param calcUserCouponBeans
     * @return
     */
    CalcUserCouponListResp calcUserCouponList(List<CalcUserCouponBean> calcUserCouponBeans);

    /**
     * 冻结团购份额
     * @param request
     * @return
     */
    void freezeGrouponInventory(FreezeGrouponInventoryReq request);

    void unfreezeAndDeductGrouponInventory(String token);

    void unfreezeAndReturnGrouponInventory(String token);

    Map<String, String> batchUnfreezeAndDeductGrouponInventory(List<String> tokenList);

    Map<String, String> batchUnfreezeAndReturnGrouponInventory(List<String> tokenList);

    void unfreezeAndDeductGrouponInventoryAsync(FundTransOrder transOrder, String token);

    void unfreezeAndReturnGrouponInventoryAsync(FundTransOrder transOrder, String token);
    
    /**
     * 通知用户投资信息
     * @param transOrder
     * @param queryProdTransInfoResp
     */
    void sendMemberInvestment(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp);

    /**
     * 通知用户投资信息
     * @param transfereeOrder
     * @param product
     */
    void sendMemberInvestment(FundTransfereeOrder transfereeOrder, ProdInfo product);
    
}
