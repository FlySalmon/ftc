package com.eif.ftc.facade.fund.oper.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

public class FundDividendOrderRequest extends PageableRequest {

	private static final long serialVersionUID = 1L;

	private String businessOrderItemNo;

	private String fundDividendOrderNo;
	
	private Long productId;
	
	private String memberNo;

	private String transactionAccountId;
	
	private Integer status;
	
	private Date startDateTime;
	
	private Date endDateTime;

	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	/**
	 * @param businessOrderItemNo 业务订单号
	 * @occurs required
	 */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

	public String getFundDividendOrderNo() {
		return fundDividendOrderNo;
	}

	/**
	 * @param fundDividendOrderNo 分红单业务单号
	 * @occurs required
	 */
	public void setFundDividendOrderNo(String fundDividendOrderNo) {
		this.fundDividendOrderNo = fundDividendOrderNo;
	}

	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId 产品Id
	 * @occurs required
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo 会员号
	 * @occurs required
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getTransactionAccountId() {
		return transactionAccountId;
	}

	/**
	 * @param transactionAccountId 基金交易账号
	 * @occurs required
	 */
	public void setTransactionAccountId(String transactionAccountId) {
		this.transactionAccountId = transactionAccountId;
	}

	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status 单据状态
	 * @occurs required
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * @param startDateTime 开始时间
	 * @occurs required
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	/**
	 * @param endDateTime 结束时间
	 * @occurs required
	 */
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

}
