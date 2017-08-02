package com.eif.ftc.service.transfer.exchange.processor;

import com.eif.ftc.dal.model.FundTransferApplyToExchange;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.service.transfer.exchange.bean.resp.TransferApplyResultBean;

/**
 * @description 交易所产品转让申请接口类
 * 
 * @author jiangweifeng
 *
 */
public interface TransferApplyService {

	/**
	 * 转让申请（一对一）
	 * @param exchangeCode
	 * @param exchangeProdNo
	 * @param transfereeOrder
	 */
	void applyTransfer(String exchangeCode, String exchangeProdNo, FundTransfereeOrder transfereeOrder);
	
	/**
	 * 根据受让单和状态查找转让申请记录
	 * @param transfereeOrderNo
	 * @param status
	 */
	FundTransferApplyToExchange queryApplication(String transfereeOrderNo, byte status);
	
	/**
	 * 更新转让申请记录
	 * @param apply
	 * @param status
	 * @param result
	 */
	void updateApplication(FundTransferApplyToExchange apply, byte status, TransferApplyResultBean result);
	
}
