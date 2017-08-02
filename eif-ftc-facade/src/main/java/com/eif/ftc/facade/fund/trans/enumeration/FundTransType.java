package com.eif.ftc.facade.fund.trans.enumeration;

import java.io.Serializable;

/**
 * @author bohan
 * 
 * 基金交易类型
 * 1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回
 * 9 - 无需确认的赎回; 10 - 清盘; 11 - 红利 ; 12 - 转让; 13 - 受让
 */
public final class FundTransType {
	
	/**
	 * 认购
	 */
	public static final int SUBSCRIBING = 1;
	
	/**
	 * 申购
	 */
	public static final int PURCHASING = 2;
	
	/**
	 * 正常赎回
	 */
	public static final int REDEEMING = 3;
	
	/**
	 * 申购退款，商户发起
	 */
	public static final int REFUNDING = 4;
	
	/**
	 * 撤销
	 */
	public static final int CANCELING = 5;
	
	/**
	 * 当日赎回，用户发起
	 */
	public static final int IMMEDIATELY_REDEEMING = 6;
	
	/**
	 * 认购退款
	 */
	public static final int SUBCRIBED_REFUNDING = 7;
	
	/**
	 * 组合赎回
	 */
	public static final int COMBO_REDEEMING = 8;
	
	/**
	 * 无需确认的赎回
	 */
	public static final int NOT_CONFIRMED_REDEEMING = 9;

	/**
	 * 清盘
	 */
	public static final int WINDUP = 10;
	
	/**
	 * 分红
	 */
	public static final int DIVIDEND = 11;

	/**
	 * 转让
	 */
	public static final int TRANSFEROR = 12;
	
	/**
	 * 受让
	 */
	public static final int TRANSFEREE = 13;
	
}
