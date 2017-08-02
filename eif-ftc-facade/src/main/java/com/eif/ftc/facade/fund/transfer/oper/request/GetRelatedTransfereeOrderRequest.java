package com.eif.ftc.facade.fund.transfer.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class GetRelatedTransfereeOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让交易单号
	 */
	private String fundTransferorOrderNo;

	/**
	 * @return the fundTransferorOrderNo
	 */
	public String getFundTransferorOrderNo() {
		return fundTransferorOrderNo;
	}

    /**
     * @param fundTransferorOrderNo 转让交易单号
     * @occurs required
     */
	public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
		this.fundTransferorOrderNo = fundTransferorOrderNo;
	}
	
}
