package com.eif.ftc.facade.dto;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseDTO;

public class FriendInvestmentBean extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 投资金额（定期）
	 */
	private BigDecimal regularAmount;
	
	/**
	 * 投资金额（活期）
	 */
	private BigDecimal currentAmount;

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
	 * @return the regularAmount
	 */
	public BigDecimal getRegularAmount() {
		return regularAmount;
	}

	/**
	 * @param regularAmount the regularAmount to set
	 */
	public void setRegularAmount(BigDecimal regularAmount) {
		this.regularAmount = regularAmount;
	}

	/**
	 * @return the currentAmount
	 */
	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * @param currentAmount the currentAmount to set
	 */
	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}
	
}
