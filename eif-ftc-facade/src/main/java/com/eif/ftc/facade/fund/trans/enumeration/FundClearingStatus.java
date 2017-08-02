package com.eif.ftc.facade.fund.trans.enumeration;

public enum FundClearingStatus {

	//清盘单状态
	OFC_CLEAR_ORDER_SUCC(1),			//OFC下清盘单成功
	TC_REDEEM_ORDER_SUCC(2), 			//交易核心下赎回单成功
	TC_REDEEM_ORDER_FAILED(3), 			//交易核心下赎回单失败
	TC_REDEEM_ORDER_UPDATE_SUCC(4), 	//交易核心赎回单更新成功
	TC_REDEEM_ORDER_UPDATE_FAILED(5), 	//交易核心赎回单更新失败
	TC_PROMOTION_ORDER_SUCC(6),			//交易核心下活动贴现单成功
	TC_PROMOTION_ORDER_FAILED(7),		//交易核心下活动贴现单失败
	TC_WITHDRAW_ORDER_SUCC(8),			//交易核心下提现单成功
	TC_WITHDRAW_ORDER_FAILED(9),		//交易核心下提现单失败
	TC_WITHDRAW_SUCC(10),				//交易核心提现成功
	TC_WITHDRAW_FAILED(11),				//交易核心提现失败
	TC_PURCHASE_ORDER_SUCC(12),			//交易核心下申购单成功
	TC_PURCHASE_ORDER_FAILED(13),		//交易核心下申购单失败
	AMC_CLEAR_SUCC(14), 				//资产账户清盘完毕
	AMC_CLEAR_FAILED(15), 				//资产账户清盘失败
	TC_GROUPON_ORDER_SUCC(16),			//交易核心下团购贴现单成功
	TC_GROUPON_ORDER_FAILED(17);		//交易核心下团购贴现单失败


	private int value;
	
	private FundClearingStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
