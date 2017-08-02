package com.eif.ftc.facade.fund.oper.request;

import java.math.BigDecimal;
import java.util.Set;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class LargeInvestmentClientsRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private BigDecimal threshold;
	
	private Integer fundTransType;

	private Set<String> memberNoSet;

	public BigDecimal getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold 大额投资阈值
	 * @occurs required
	 */
	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public Integer getFundTransType() {
		return fundTransType;
	}

	/**
	 * @param fundTransType 交易类型（com.eif.ftc.facade.fund.trans.enumeration.FundTransType, 1 - 定期认购; 2 - 活期申购;）
	 * @occurs optional
	 */
	public void setFundTransType(Integer fundTransType) {
		this.fundTransType = fundTransType;
	}

	public Set<String> getMemberNoSet() {
		return memberNoSet;
	}

	/**
	 * @param memberNoSet 会员号列表
	 * @occurs required
	 */
	public void setMemberNoSet(Set<String> memberNoSet) {
		this.memberNoSet = memberNoSet;
	}
	
}
