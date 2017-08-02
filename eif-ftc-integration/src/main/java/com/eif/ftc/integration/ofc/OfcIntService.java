package com.eif.ftc.integration.ofc;

import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.mq.dto.MQBusinessOrderItemMessage;
import com.eif.ofc.facade.dto.bean.CreateBusinessOrderItemBean;
import com.eif.ofc.facade.response.BatchCreateOrderItemResponse;
import com.eif.ofc.facade.response.CreateOrderItemResponse;

import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
public interface OfcIntService {
    CreateOrderItemResponse createBusinessOrderItem(FundTransOrder fundTransOrder,
                                                           int businessOrderItemType, String extField, String marketField, String productName);

    void updateBusinessOrderItem(FundTransOrder targetOrder, int businessOrderItemType, int status, String productName);
    void updateBusinessOrderItemStatusAsync(FundTransOrder targetOrder, int fundTransOrderStatus, String productName);
    void updateBusinessOrderItem(FundTransOrder targetOrder, int businessOrderItemType, int status, String productName, String reasonCode);
    void updateBusinessOrderItemStatusAsync(FundTransOrder targetOrder, int fundTransOrderStatus, String productName, String reasonCode);
    BatchCreateOrderItemResponse batchCreateBusinessOrderItems(List<CreateBusinessOrderItemBean> createBusinessOrderItemBeanList);
    
    /**************/
    /**二级市场接口 **/
    /**************/
    //创建、更新转让单
    public String createBusinessOrderItem(FundTransferorOrder fundTransferorOder, String productName);
    public void updateBusinessOrderItemAsync(FundTransferorOrder fundTransferorOder, String productClearAccount);
    public MQBusinessOrderItemMessage buildBusinessOrderItemMessage(
    		FundTransferorOrder fundTransferorOder, String productClearAccount);

    //创建、更新受让单
    public String createBusinessOrderItem(FundTransfereeOrder fundTransfereeOder, String productName);
    public void updateBusinessOrderItemAsync(FundTransfereeOrder fundTransfereeOder);
    public MQBusinessOrderItemMessage buildBusinessOrderItemMessage(FundTransfereeOrder fundTransfereeOder);
    //批量更新业务单
    public void batchUpdateBusinessOrderItemAsync(List<MQBusinessOrderItemMessage> businessOrderItemList);
    
    
}
