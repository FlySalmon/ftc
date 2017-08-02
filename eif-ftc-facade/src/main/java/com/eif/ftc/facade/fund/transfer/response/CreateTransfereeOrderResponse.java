package com.eif.ftc.facade.fund.transfer.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * 受让单创建结果
 * 
 * @author jiangweifeng
 *
 */
public class CreateTransfereeOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 受让交易业务单号
     */
    private String fundTransfereeOrderNo;

    /**
     * 订单业务项号
     */
    private String businessOrderItemNo;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return 受让交易业务单号
     * @occurs required
	 */
	public String getFundTransfereeOrderNo() {
		return fundTransfereeOrderNo;
	}

	/**
	 * @param fundTransfereeOrderNo the fundTransfereeOrderNo to set
	 */
	public void setFundTransfereeOrderNo(String fundTransfereeOrderNo) {
		this.fundTransfereeOrderNo = fundTransfereeOrderNo;
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
