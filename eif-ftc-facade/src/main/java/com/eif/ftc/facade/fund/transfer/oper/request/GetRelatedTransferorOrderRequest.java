package com.eif.ftc.facade.fund.transfer.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class GetRelatedTransferorOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易单号
	 */
	private String fundTransfereeOrderNo;

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
	
}
