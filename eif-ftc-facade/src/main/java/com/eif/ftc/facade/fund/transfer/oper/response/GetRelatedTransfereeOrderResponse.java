package com.eif.ftc.facade.fund.transfer.oper.response;

import java.util.List;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.transfer.dto.FundTransfereeOrderBean;

public class GetRelatedTransfereeOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让单信息列表
	 */
	List<FundTransfereeOrderBean> transfereeOrderBeanList;

    /**
     * @return 受让单信息列表
     * @occurs required
     */
	public List<FundTransfereeOrderBean> getTransfereeOrderBeanList() {
		return transfereeOrderBeanList;
	}

	/**
	 * @param transfereeOrderBeanList the transfereeOrderBeanList to set
	 */
	public void setTransfereeOrderBeanList(List<FundTransfereeOrderBean> transfereeOrderBeanList) {
		this.transfereeOrderBeanList = transfereeOrderBeanList;
	}
	
}
