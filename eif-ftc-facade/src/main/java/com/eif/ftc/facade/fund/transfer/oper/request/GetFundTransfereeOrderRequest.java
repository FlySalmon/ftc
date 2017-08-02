package com.eif.ftc.facade.fund.transfer.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class GetFundTransfereeOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易单号
	 */
	private String fundTransfereeOrderNo;
	
	/**
	 * 业务单号
	 */
	private String businessOrderItemNo;

	/**
	 * @return the fundTransfereeOrderNo
	 */
	public String getFundTransfereeOrderNo() {
		return fundTransfereeOrderNo;
	}

    /**
     * @param fundTransfereeOrderNo 受让交易单号
     * @occurs required
     */
	public void setFundTransfereeOrderNo(String fundTransfereeOrderNo) {
		this.fundTransfereeOrderNo = fundTransfereeOrderNo;
	}

	/**
	 * @return the businessOrderItemNo
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

    /**
     * @param businessOrderItemNo 业务单号
     * @occurs required
     */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}
	
}
