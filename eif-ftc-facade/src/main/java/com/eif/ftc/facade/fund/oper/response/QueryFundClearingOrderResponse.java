package com.eif.ftc.facade.fund.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FundClearingOrderBean;

public class QueryFundClearingOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FundClearingOrderBean fundClearingOrderBean;

    /**
     * @return 兑付单信息
     * @occurs required
     */
	public FundClearingOrderBean getFundClearingOrderBean() {
		return fundClearingOrderBean;
	}

	/**
	 * @param fundClearingOrderBean the fundClearingOrderBean to set
	 */
	public void setFundClearingOrderBean(FundClearingOrderBean fundClearingOrderBean) {
		this.fundClearingOrderBean = fundClearingOrderBean;
	}
	
}
