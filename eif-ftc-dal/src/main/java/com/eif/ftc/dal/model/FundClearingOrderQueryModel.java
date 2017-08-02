package com.eif.ftc.dal.model;

import java.util.Date;

/**
 * 兑付单查询条件
 * @author RanboJiang
 *
 */
public class FundClearingOrderQueryModel {

	/**
	 * 业务订单号
	 */
	private String businessOrderItemNo;

	/**
	 * 清盘单业务单号
	 */
	private String fundClearingOrderNo;
	
	/**
	 * 产品Id
	 */
	private Long productId;
	
	/**
	 * 会员号
	 */
	private String memberNo;

	/**
	 * 订单状态
	 */
	private Integer status;
	
	/**
	 * 起始时间
	 */
	private Date startDateTime;
	
	/**
	 * 终止时间
	 */
	private Date endDateTime;

	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

	public String getFundClearingOrderNo() {
		return fundClearingOrderNo;
	}

	public void setFundClearingOrderNo(String fundClearingOrderNo) {
		this.fundClearingOrderNo = fundClearingOrderNo;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

}
