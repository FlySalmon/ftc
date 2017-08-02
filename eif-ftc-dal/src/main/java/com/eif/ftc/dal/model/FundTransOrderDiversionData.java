package com.eif.ftc.dal.model;

import java.math.BigDecimal;

public class FundTransOrderDiversionData {

	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 投资总金额
	 */
	private BigDecimal totalAmount;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
