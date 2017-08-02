package com.eif.ftc.facade.fund.job.response;

import java.util.ArrayList;
import java.util.List;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FundClearingOrderBean;

public class QueryByFundClearingOrderNosResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;


    private List<FundClearingOrderBean> fundClearingOrderBeanList = new ArrayList<>();


	/**
	 * @return 兑付单列表
	 * @occurs required
	 */
	public List<FundClearingOrderBean> getFundClearingOrderBeanList() {
		return fundClearingOrderBeanList;
	}

	public void setFundClearingOrderBeanList(List<FundClearingOrderBean> fundClearingOrderBeanList) {
		this.fundClearingOrderBeanList = fundClearingOrderBeanList;
	}
    
}
