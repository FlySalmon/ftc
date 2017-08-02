package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

import java.util.Date;

public class QueryMemberFundTransOrderRequest extends PageableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户号
	 */
	String memberNo;

	/**
	 * 基金业务单
	 */
	String fundTransOrderNo;

	/**
	 * 产品名称
	 */
	String productName;
	
	/**
	 * 产品id
	 */
	Long productID;
	
	/**
	 * 订单状态
	 */
	int orderStatus;
	
	/**
	 * 开始时间
	 */
	Date startDate;
	
	/**
	 * 结束时间
	 */
	Date endDate;
	
	/**
	 * 交易类型
	 */
	int transType;

	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @occurs optional
	 * @param memberNo 用户号
     */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
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


	/**
	 *
	 * @return
     */
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
