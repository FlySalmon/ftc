package com.eif.ftc.facade.fund.acct.enumeration;

/**
 * @author bohan
 * 
 * 基金账户业务单状态
 *
 */
public final class FundAcctOrderStatus {
	/**
	 * 资产账户开户中
	 */
	public final static int ACCOUNT_PROCESSING = 1;
	/**
	 * 资产账户开户成功
	 */
	public final static int ACCOUNT_PROCESS_SUC = 2;
	/**
	 * 资产账户开户失败
	 */
	public final static int ACCOUNT_PROCESS_FAILED = 3;
	/**
	 * 基金公司开户中
	 */
	public final static int TA_PROCESSING = 4;
	/**
	 * 基金公司开户成功
	 */
	public final static int TA_PROCESS_SEC = 5;
	/**
	 * 基金公司开户失败
	 */
	public final static int TA_PROCESS_FAILED = 6;
}
