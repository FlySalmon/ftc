package com.eif.ftc.facade.fund.amc.constant;

/**
 *  状态：1 -  基金公司开户中, 2 - 基金公司开户成功, 3 - 基金公司开户失败, 4 - 清盘 5 - 冻结, 6 - 正常, 7 - 止付,所属表字段为t_amc_fund_account.status
 */
public final class FundAcctStatus {
	public final static int TA_PENDING= 1;
	public final static int TA_SUCCESS = 2;
	public final static int TA_FAILED = 3;
	public final static int TA_FINISH = 4;
	public final static int FROZEN = 5;
	public final static int AVAILABLE = 6;
	public final static int STOP_PAYING = 7;
}
