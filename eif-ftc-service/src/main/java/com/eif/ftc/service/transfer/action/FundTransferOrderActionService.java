package com.eif.ftc.service.transfer.action;

import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;

public interface FundTransferOrderActionService {

	/**
	 * 插入转让单
	 * @param transferorOrder
	 */
	void insertFundTransferorOrder(FundTransferorOrder transferorOrder);

	/**
	 * 插入受让单
	 * @param transfereeOrder
	 */
	void insertFundTransfereeOrder(FundTransfereeOrder transfereeOrder);
	
	/**
	 * 更新受让单信息
	 * @param transfereeOrder
	 * @param targetStatus
	 */
	void updateFundTransfereeOrder(FundTransfereeOrder transfereeOrder, int targetStatus);
	
	/**
	 * 更新转让单信息
	 * @param transferorOrder
	 * @param targetStatus
	 */
	void updateFundTransferorOrder(FundTransferorOrder transferorOrder, int targetStatus);

	/**
	 * 更新受让单信息
	 * @param transfereeOrder
	 */
	void updateFundTransfereeOrder(FundTransfereeOrder transfereeOrder);
	
	/**
	 * 更新转让单信息
	 * @param transferorOrder
	 */
	void updateFundTransferorOrder(FundTransferorOrder transferorOrder);
	
	/**
	 * 插入受让单状态迁移信息
	 * @param transfereeOrder
	 */
	void insertFundTransfereeOrderStatus(FundTransfereeOrder transfereeOrder);
	
	/**
	 * 插入转让单状态迁移信息
	 * @param transferorOrder
	 */
	void insertFundTransferorOrderStatus(FundTransferorOrder transferorOrder);
	
	/**
	 * 校验受让单的状态
	 * @param transfereeOrder
	 * @param expectStatus
	 */
	void checkTransfereeOrderStatus(FundTransfereeOrder transfereeOrder, int expectStatus);
	
	/**
	 * 校验转让单状态
	 * @param transferorOrder
	 * @param expectStatus
	 */
	void checkTransferorOrderStatus(FundTransferorOrder transferorOrder, int expectStatus);

	/**
	 * 更新转让单二级市场产品Id
	 * @param transferorOrderNo
	 * @param mktProductId
	 */
	void updateFundTransferorOrderMktProductId(String transferorOrderNo, Long mktProductId);
	
	/**
	 * 更新转让单二级市场协议号
	 * @param transferorOrderNo
	 * @param agreementNo
	 */
	void updateFundTransferorOrderAgreementNo(String transferorOrderNo, String agreementNo);
	
}
