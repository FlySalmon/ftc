package com.eif.ftc.facade.fund.job.request;

import java.util.List;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class QueryByFundClearingOrderNosRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    

    private List<String> fundClearingOrderNoList;

	public List<String> getFundClearingOrderNoList() {
		return fundClearingOrderNoList;
	}


	/**
	 * @param fundClearingOrderNoList 清盘兑付单号列表
	 * @occurs required
	 */
	public void setFundClearingOrderNoList(List<String> fundClearingOrderNoList) {
		this.fundClearingOrderNoList = fundClearingOrderNoList;
	}
    
}
