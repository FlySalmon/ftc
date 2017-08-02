package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * 会员投资额统计bean
 * @author jiangweifeng
 *
 */
public class MemberInvestmentBean {

	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 交易类型
	 */
	private Integer fundTransType;
	
	/**
	 * 投资金额（定期）
	 */
	private BigDecimal amount;

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
	 * @return the fundTransType
	 */
	public Integer getFundTransType() {
		return fundTransType;
	}

	/**
	 * @param fundTransType the fundTransType to set
	 */
	public void setFundTransType(Integer fundTransType) {
		this.fundTransType = fundTransType;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
