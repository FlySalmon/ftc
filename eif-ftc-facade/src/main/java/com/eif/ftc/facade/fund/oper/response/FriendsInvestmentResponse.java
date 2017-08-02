package com.eif.ftc.facade.fund.oper.response;

import java.math.BigDecimal;
import java.util.List;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FriendInvestmentBean;

public class FriendsInvestmentResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private BigDecimal investAmount;

	private List<FriendInvestmentBean> friendInvestments;

	/**
	 * @return 投资金额
	 * @occurs required
	 */
	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	/**
	 * @return 好友投资信息列表
	 * @occurs required
	 */
	public List<FriendInvestmentBean> getFriendInvestments() {
		return friendInvestments;
	}

	public void setFriendInvestments(List<FriendInvestmentBean> friendInvestments) {
		this.friendInvestments = friendInvestments;
	}
	
}
