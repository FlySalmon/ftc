package com.eif.ftc.facade.fund.transfer.enumeration;

/**
 * 转让交易单状态
 * 
 * @author jiangweifeng
 *
 */
public final class FundTransferorOrderStatus {

	/**
	 * 待转让
	 */
	public final static int NEED_TRANSFER = 4;
	
	/**
	 * 转让中
	 */
	public final static int TRANSFERING = 5;
	
	/**
	 * 转让成功
	 */
	public final static int TRANSFER_SUC = 6;
	
	/**
	 * 转让失败
	 */
	public final static int TRANSFER_FAILED = 7;
	
	/**
	 * 转让取消
	 */
	public final static int CANCEL = 17;
	
	/**
	 * 过期关闭
	 */
	public final static int CLOSE_BY_EXPIRY = 18;
	
	/**
	 * 风控取消
	 */
	public final static int CANCEL_BY_RISK = 26;

}
