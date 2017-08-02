package com.eif.ftc.facade.fund.trans.enumeration;

/**
 * @author bohan
 * 
 * 基金业务交易单状态
 *
 */
public final class FundTransOrderStatus {

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
	 * 基金交易处理中
	 */
	public final static int TA_TRANS_PROCESSING = 8;
	/**
	 * 基金交易处理成功
	 */
	public final static int TA_TRANS_SUC = 9;
	/**
	 * 基金交易处理失败
	 */
	public final static int TA_TRANS_FAILED = 10;
	/**
	 * 基金交易确认
	 */
	public final static int TA_TRANS_CFM = 11;
	/**
	 * 退款中
	 */
	public final static int REFUNDING = 12;
	/**
	 * 全额退款成功
	 */
	public final static int REFUND_SUC = 13;
	/**
	 * 退款失败
	 */
	public final static int REFUND_FAILED = 14;
	/**
	 * 赎回限额风控挂起
	 */
	public final static int SUSPEND_BY_REDEEM_RISK = 15;
	/**
	 * 取消
	 */
	public final static int CANCEL = 17;
	/**
	 * 过期关闭
	 */
	public final static int CLOSE_BY_EXPIRY = 18;
	
	/**
	 * 部分退款成功
	 */
	public final static int PARTIAL_REFUND_SUC = 19;
	/**
	 * 提现中
	 */
	public final static int WITHDRAWING = 20;

	/**
	 * 提现成功
	 */
	public final static int WITHDRAW_SUC = 21;

	/**
	 * 提现失败
	 */
	public final static int WITHDRAW_FAIL = 22;
	
	/**
	 * 赎回提交成功
	 */
	public final static int REDEEMING_APPLIED = 23;

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

	/**
	 * 当日赎回成功
	 */
	public final static int IMM_REDEEM_SUC = 27;
	/**
	 * 赎回失败
	 */
	public final static int REDEEM_FAILED = 28;


	/**
	 * 大额赎回挂起
	 */

	public final static int SUSPEND_BY_HUGE_REDEEM = 29;


	/**
	 * 大额赎回拒绝
	 */
	public final static int HUGE_REDEEM_REFUSED = 30;



}
