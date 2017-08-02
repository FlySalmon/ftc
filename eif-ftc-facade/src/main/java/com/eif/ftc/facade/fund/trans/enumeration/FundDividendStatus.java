package com.eif.ftc.facade.fund.trans.enumeration;

public enum FundDividendStatus {

	//红利单状态
	OFC_DIVIDEND_ORDER_SUCC(1),			//OFC下红利单成功
	TC_INCOME_ORDER_SUCC(2), 			//交易核心下计提单成功
	TC_INCOME_ORDER_FAILED(3), 			//交易核心下计提单失败
	TC_INCOME_ORDER_UPDATE_SUCC(4), 	//交易核心计提单单更新成功
	TC_INCOME_ORDER_UPDATE_FAILED(5), 	//交易核心计提单更新失败
	TC_REINVEST_ORDER_SUCC(6),			//交易核心下复投单成功
	TC_REINVEST_ORDER_FAILED(7),		//交易核心下复投单失败
	TC_REINVEST_ORDER_UPDATE_SUCC(8),	//交易核心复投单更新成功
	TC_REINVEST_ORDER_UPDATE_FAILED(9),	//交易核心复投单更新失败
	AMC_UPDATE_SUCC(10), 				//资产账户更新完毕
	AMC_UPDATE_FAILED(11); 				//资产账户更新失败
	
	private int value;
	
	private FundDividendStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
