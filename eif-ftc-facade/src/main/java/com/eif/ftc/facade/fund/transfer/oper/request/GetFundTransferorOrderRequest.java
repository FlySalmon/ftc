package com.eif.ftc.facade.fund.transfer.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 转让单查询请求类
 * 
 * @author jiangweifeng
 *
 */
public class GetFundTransferorOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让交易单号
	 */
	private String fundTransferorOrderNo;
	
	/**
	 * 业务单号
	 */
	private String businessOrderItemNo;

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
