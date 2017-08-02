package com.eif.ftc.facade.fund.oper.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

public class QueryFundTransOrderRequest extends PageableRequest {

	private static final long serialVersionUID = 1L;

	private String memberPhoneNumber;

	private String memberNo;

	private String fundTransOrderNo;

	private String productName;
	
	private Long productID;
	
	private int orderStatus;
	
	private Date startDate;
	
	private Date endDate;

	private int transType;

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	/**
	 * @occurs optional
	 * @param memberPhoneNumber 用户手机
     */
	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getProductName() {
		return productName;
	}

	/**
	 * @occurs optional
	 * @param productName 产品名称
     */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductID() {
		return productID;
	}

	/**
	 * @occurs optional
	 * @param productID 产品id
     */
	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @occurs required
	 * @param orderStatus 交易状态
     */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @occurs required
	 * @param startDate 开始时间
     */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @occurs required
	 * @param endDate 结束时间
     */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTransType() {
		return transType;
	}

	/**
	 * @occurs required
	 * @param transType 交易类型
     */
	public void setTransType(int transType) {
		this.transType = transType;
	}

	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @occurs optional
	 * @param memberNo 用户编号
     */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getFundTransOrderNo() {
		return fundTransOrderNo;
	}

	/**
	 * @occurs optional
	 * @param fundTransOrderNo 基金交易单号
     */
	public void setFundTransOrderNo(String fundTransOrderNo) {
		this.fundTransOrderNo = fundTransOrderNo;
	}

}
