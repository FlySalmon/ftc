package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

public class MemberCurrentAssetBean {

	/**
	 * 产品Id
	 */
	private Long productId;
	
	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 基金交易账号
	 */
	private String transactionAccountId;

	/**
	 * TA账号
	 */
	private String taAccountId;
	
	/**
	 * 确认的总资产份额
	 */
	private BigDecimal confirmedAddShare;
	
	/**
	 * 未确认扣减份额
	 */
	private BigDecimal unconfirmedSubShare;
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the transactionAccountId
	 */
	public String getTransactionAccountId() {
		return transactionAccountId;
	}

	/**
	 * @param transactionAccountId the transactionAccountId to set
	 */
	public void setTransactionAccountId(String transactionAccountId) {
		this.transactionAccountId = transactionAccountId;
	}

	/**
	 * @return the taAccountId
	 */
	public String getTaAccountId() {
		return taAccountId;
	}

	/**
	 * @param taAccountId the taAccountId to set
	 */
	public void setTaAccountId(String taAccountId) {
		this.taAccountId = taAccountId;
	}

	/**
	 * @return the confirmedAddShare
	 */
	public BigDecimal getConfirmedAddShare() {
		return confirmedAddShare;
	}

	/**
	 * @param confirmedAddShare the confirmedAddShare to set
	 */
	public void setConfirmedAddShare(BigDecimal confirmedAddShare) {
		this.confirmedAddShare = confirmedAddShare;
	}

	/**
	 * @return the unconfirmedSubShare
	 */
	public BigDecimal getUnconfirmedSubShare() {
		return unconfirmedSubShare;
	}

	/**
	 * @param unconfirmedSubShare the unconfirmedSubShare to set
	 */
	public void setUnconfirmedSubShare(BigDecimal unconfirmedSubShare) {
		this.unconfirmedSubShare = unconfirmedSubShare;
	}

}
