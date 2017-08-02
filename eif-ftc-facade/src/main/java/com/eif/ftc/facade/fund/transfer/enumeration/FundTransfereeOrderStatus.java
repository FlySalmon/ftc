package com.eif.ftc.facade.fund.transfer.enumeration;

/**
 * 受让交易单状态
 * 
 * @author jiangweifeng
 *
 */
public final class FundTransfereeOrderStatus {

	/**
	 * 待支付
	 */
	public final static int NEED_PAY = 4;
	
	/**
	 * 支付中
	 */
	public final static int PAYING = 5;
	
	/**
	 * 支付成功
	 */
	public final static int PAY_SUC = 6;
	
	/**
	 * 支付失败
	 */
	public final static int PAY_FAILED = 7;
	
	/**
	 * 交易（转入）成功
	 */
	public final static int TRANS_SUC = 9;
	
	/**
	 * 交易（转入）失败
	 */
	public final static int TRANS_FAILED = 10;
	
	/**
	 * 退款中
	 */
	public final static int REFUNDING = 12;
	
	/**
	 * 退款成功
	 */
	public final static int REFUND_SUC = 13;
	
	/**
	 * 退款失败
	 */
	public final static int REFUND_FAILED = 14;
	
	/**
	 * 过期关闭
	 */
	public final static int CLOSE_BY_EXPIRY = 18;
	
	/**
	 * 等待短信验证
	 */
	public final static int AUTH_PENDING = 24;

	/**
	 * 风控挂起
	 */
	public final static int SUSPEND_BY_RISK = 25;

	/**
	 * 风控拒绝
	 */
	public final static int REJECTED_BY_RISK = 26;

}
