package com.eif.ftc.facade.fund.transfer.enumeration;

public class FundTransferExchangeStatus {

	/**
	 * 转让发起中
	 */
	public static final byte TRANSFERING = 1;
	
	/**
	 * 转让申请成功
	 */
	public static final byte TRANSFER_APPLY_SUCC = 2;
	
	/**
	 * 转让申请失败
	 */
	public static final byte TRANSFER_APPLY_FAIL = 3;

	/**
	 * 转让成功
	 */
	public static final byte TRANSFER_SUCC = 4;

	/**
	 * 转让失败
	 */
	public static final byte TRANSFER_FAIL = 5;
	
}
