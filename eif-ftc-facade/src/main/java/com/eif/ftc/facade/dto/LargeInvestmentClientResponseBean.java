package com.eif.ftc.facade.dto;

import com.eif.framework.common.utils.pkg.BaseDTO;

public class LargeInvestmentClientResponseBean extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String memberNo;

	private boolean isLargeInvestmentClient;

	/**
	 * @return 会员号
	 * @occurs required
	 */
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return 是否大额投资客户
	 * @occurs required
	 */
	public boolean isLargeInvestmentClient() {
		return isLargeInvestmentClient;
	}

	public void setLargeInvestmentClient(boolean isLargeInvestmentClient) {
		this.isLargeInvestmentClient = isLargeInvestmentClient;
	}
	
}
