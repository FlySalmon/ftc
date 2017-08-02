package com.eif.ftc.facade.fund.oper.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

public class FundClearingOrderRequest extends PageableRequest {

	private static final long serialVersionUID = 1L;

	private String businessOrderItemNo;

	private String fundClearingOrderNo;
	
	private Long productId;
	
	private String memberNo;

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

	public String getFundClearingOrderNo() {
		return fundClearingOrderNo;
	}



	/**
	 * @param fundClearingOrderNo 清盘业务单号
	 * @occurs required
	 */
	public void setFundClearingOrderNo(String fundClearingOrderNo) {
		this.fundClearingOrderNo = fundClearingOrderNo;
	}

	public Long getProductId() {
		return productId;
	}



	/**
	 * @param productId 业务产品号
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
