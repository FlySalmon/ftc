package com.eif.ftc.service.transfer.action;

import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.ftc.dal.model.FundTransfereeOrder;

public interface FundTransferChargeActionService {

	/**
	 * 支付成功处理
	 * @param transfereeOrder
	 * @param product
	 */
	void chargeSuccess(FundTransfereeOrder transfereeOrder, ProdInfo product);
	
	/**
	 * 支付失败处理
	 * @param transfereeOrder
	 */
	void chargeFailed(FundTransfereeOrder transfereeOrder);
	
	/**
	 * 退款处理
	 * @param transfereeOrder
	 * @param productName
	 */
	void doRefund(FundTransfereeOrder transfereeOrder, String productName);
	
	/**
	 * 退款结果处理
	 * @param transfereeOrder
	 * @param targetStatus
	 */
	void handleRefundResult(FundTransfereeOrder transfereeOrder, int targetStatus);
	
}
