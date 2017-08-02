package com.eif.ftc.facade.fund.transfer.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * 受让单OTP支付结果类
 * 
 * @author jiangweifeng
 *
 */
public class ResumePayTransfereeOrderResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 交易核心交易单号
	 */
    private String transNo;
    
    /**
     * 支付状态
     */
    private Integer transStatus;

	/**
	 * @return 支付凭证号
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
