package com.eif.ftc.facade.fund.transfer.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * 转让单创建结果类
 * 
 * @author jiangweifeng
 *
 */
public class CreateTransferorOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 转交易业务单号
     */
    private String fundTransferorOrderNo;

    /**
     * 订单业务项号
     */
    private String businessOrderItemNo;

	/**
	 * @return 转交易业务单号
     * @occurs required
	 */
	public String getFundTransferorOrderNo() {
		return fundTransferorOrderNo;
	}

	/**
	 * @param fundTransferorOrderNo the fundTransferorOrderNo to set
	 */
	public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
		this.fundTransferorOrderNo = fundTransferorOrderNo;
	}

	/**
	 * @return 订单业务项号
     * @occurs required
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	/**
	 * @param businessOrderItemNo the businessOrderItemNo to set
	 */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

}
