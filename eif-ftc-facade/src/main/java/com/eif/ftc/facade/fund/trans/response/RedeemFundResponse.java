package com.eif.ftc.facade.fund.trans.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

public class RedeemFundResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private String businessOrderItemNo;

    private String fundTransOrderNo;


	/**
	 * @return OFC订单号
	 * @occurs required
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

	/**
	 * @return 交易业务单号
	 * @occurs required
	 */
	public String getFundTransOrderNo() {
		return fundTransOrderNo;
	}

	public void setFundTransOrderNo(String fundTransOrderNo) {
		this.fundTransOrderNo = fundTransOrderNo;
	}

}
