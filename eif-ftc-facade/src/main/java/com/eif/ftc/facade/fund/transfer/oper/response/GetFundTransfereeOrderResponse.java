package com.eif.ftc.facade.fund.transfer.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.transfer.dto.FundTransfereeOrderBean;

public class GetFundTransfereeOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让单信息
	 */
	FundTransfereeOrderBean transfereeOrderBean;

    /**
     * @return 受让单信息
     * @occurs required
     */
	public FundTransfereeOrderBean getTransfereeOrderBean() {
		return transfereeOrderBean;
	}

	/**
	 * @param transfereeOrderBean the transfereeOrderBean to set
	 */
	public void setTransfereeOrderBean(FundTransfereeOrderBean transfereeOrderBean) {
		this.transfereeOrderBean = transfereeOrderBean;
	}
	
}
