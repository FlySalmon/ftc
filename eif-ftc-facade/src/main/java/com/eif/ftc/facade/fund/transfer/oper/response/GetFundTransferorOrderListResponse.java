package com.eif.ftc.facade.fund.transfer.oper.response;

import java.util.List;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.transfer.dto.FundTransferorOrderBean;

public class GetFundTransferorOrderListResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让单列表
	 */
	List<FundTransferorOrderBean> tranferorOrderBeanList;

    /**
     * @return 转让单列表
     * @occurs required
     */
	public List<FundTransferorOrderBean> getTranferorOrderBeanList() {
		return tranferorOrderBeanList;
	}

	/**
	 * @param tranferorOrderBeanList the tranferorOrderBeanList to set
	 */
	public void setTranferorOrderBeanList(List<FundTransferorOrderBean> tranferorOrderBeanList) {
		this.tranferorOrderBeanList = tranferorOrderBeanList;
	}
	
}
