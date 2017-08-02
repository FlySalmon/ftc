package com.eif.ftc.facade.fund.trans.enumeration;

/**
 * @author RanboJiang
 * 
 * 基金业务交易单明细状态
 *
 */
public class FundTransOrderStatusDetail {
	
	/**
	 * 订单（销售系统、TA）确认成功
	 */
	public final static int CONFIRM_SUCC = 60;
	/**
	 * 订单（TA）确认失败（包括TA处理失败、TA确认份额与申请份额不一致）
	 */
	public final static int CONFIRM_FAIL = 61;

	/**
	 * 更新交易核心申购单状态成功
	 */
	public final static int TC_UPDATE_PURCHASE_STATUS_SUCC = 62;
	/**
	 * 更新交易核心申购单状态失败
	 */
	public final static int TC_UPDATE_PURCHASE_STATUS_FAIL = 63;

	/**
	 * 更新交易核心认购单状态成功
	 */
	public final static int TC_UPDATE_SUBSCRIBE_STATUS_SUCC = 64;
	/**
	 * 更新交易核心认购单状态失败
	 */
	public final static int TC_UPDATE_SUBSCRIBE_STATUS_FAIL = 65;

	/**
	 * 签合同成功
	 */
	public final static int SIGN_CONTRACT_SUCC = 66;
	/**
	 * 签合同失败
	 */
	public final static int SIGN_CONTRACT_FAIL = 67;

	/**
	 * 更新赎回单状态成功
	 */
	public final static int TC_UPDATE_REDEEM_STATUS_SUCC = 68;
	/**
	 * 更新赎回单状态失败
	 */
	public final static int TC_UPDATE_REDEEM_STATUS_FAIL = 69;

	/**
	 * 更新资产成功
	 */
	public final static int UPDATE_ASSET_SUCC = 70;
	/**
	 * 更新资产失败
	 */
	public final static int UPDATE_ASSET_FAIL = 71;

	/**
	 * 创建提现单成功
	 */
	public final static int TC_CREATE_WITHDRAW_SUCC = 72;
	/**
	 * 创建提现单失败
	 */
	public final static int TC_CREATE_WITHDRAW_FAIL = 73;

}
