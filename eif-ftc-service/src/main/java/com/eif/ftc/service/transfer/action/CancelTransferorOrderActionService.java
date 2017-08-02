package com.eif.ftc.service.transfer.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.eif.ftc.dal.model.FundTransferorOrder;

public interface CancelTransferorOrderActionService {

	/**
	 * 更新转让单
	 * @param businessOrderItemNo
	 * @param curDateTime
	 * @param status
	 * @param reason
	 * @return
	 */
	FundTransferorOrder updateFundTransferorOrder(String businessOrderItemNo, 
			Date curDateTime, Integer status, String reason);
	
	/**
	 * 撤销二级市场产品
	 * @param mktProductId
	 */
	void cancelMarketProduct(Long mktProductId);
	
	/**
	 * 解冻（取消）转让的资产
	 * @param memberNo
	 * @param productId
	 */
	void unfreezeMemberProductAsset(String memberNo, Long productId);
	
	/**
	 * 更新OFC转让订单状态
	 * @param transferorOrder
	 * @param productName
	 */
	void updateBusinessOrderItem(FundTransferorOrder transferorOrder);
	
	/**
	 * 站内信通知
	 * @param businessId
	 * @param transferorOrder
	 */
	void notifyCancelInboxMessage(int businessId, FundTransferorOrder transferorOrder);
	
	/**
	 * 站内信通知
	 * @param businessId
	 * @param transferorOrderList
	 */
	void notifyCancelInboxMessages(int businessId, List<FundTransferorOrder> transferorOrderList);
	
	/**
	 * 更新转让单
	 * @param memberNos
	 * @param curDateTime
	 * @param status
	 * @param reason
	 * @return
	 */
	List<FundTransferorOrder> updateFundTransferorOrders(List<String> memberNos, 
			Date curDateTime, Integer status, String reason);
	
	/**
	 * 撤销二级市场产品
	 * @param mktProductIds
	 */
	void cancelMarketProducts(Set<Long> mktProductIds);

	/**
	 * 解冻（取消）资产份额
	 * @param memberProductSet
	 */
	void unfreezeMemberProductAsset(Set<String> memberProductSet);
	
	/**
	 * 更新业务单信息
	 * @param transferorOrderList
	 */
	void updateBusinessOrderItems(List<FundTransferorOrder> transferorOrderList);
	
	/**
	 * 短信通知用户
	 * @param businessId
	 * @param transferorOrderList
	 * @param productIds
	 * @param memberNos
	 */
	void notifyCancelSMSMessage(int businessId, List<FundTransferorOrder> transferorOrderList,
			Set<Long> productIds, Set<String> memberNos);
	
}
