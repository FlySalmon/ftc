package com.eif.ftc.facade.fund.transfer.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * 支付结果类
 * 
 * @author jiangweifeng
 *
 */
public class PayTransfereeOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易订单号
	 */
	private String fundTransfereeOrderNo;

    /**
     * 订单业务项号
     */
    private String businessOrderItemNo;
    
	/**
	 * 交易核心交易流水号
	 */
    private String transNo;

    /**
     * 支付凭证号
     */
    private String paymentNo;

    /**
     * 支付状态
     */
    private Integer transStatus;

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

	/**
	 * @return 受让交易核心流水号
     * @occurs required
	 */
	public String getTransNo() {
		return transNo;
	}

	/**
	 * @param transNo the transNo to set
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * @return 支付凭证号
     * @occurs required
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * @param paymentNo the paymentNo to set
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * @return 支付状态
     * @occurs required
	 */
	public Integer getTransStatus() {
		return transStatus;
	}

	/**
	 * @param transStatus the transStatus to set
	 */
	public void setTransStatus(Integer transStatus) {
		this.transStatus = transStatus;
	}

}
