package com.eif.ftc.facade.fund.transfer.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.transfer.dto.FundTransferorOrderBean;

public class GetFundTransferorOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让单信息
	 */
	FundTransferorOrderBean fundTransferorOrderBean;

    /**
     * @return 转让单信息
     * @occurs required
     */
	public FundTransferorOrderBean getFundTransferorOrderBean() {
		return fundTransferorOrderBean;
	}

	/**
	 * @param fundTransferorOrderBean the fundTransferorOrderBean to set
	 */
	public void setFundTransferorOrderBean(FundTransferorOrderBean fundTransferorOrderBean) {
		this.fundTransferorOrderBean = fundTransferorOrderBean;
	}
	
}
