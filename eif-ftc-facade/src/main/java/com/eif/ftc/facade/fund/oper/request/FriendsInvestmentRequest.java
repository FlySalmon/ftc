package com.eif.ftc.facade.fund.oper.request;

import java.util.Date;
import java.util.List;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class FriendsInvestmentRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private Date startDateTime;

	private Date endDateTime;

	private List<String> friendMemberNos;

	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * @param startDateTime 开始日期
	 * @occurs required
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	/**
	 * @param endDateTime 结束日期
	 * @occurs required
	 */
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}


	public List<String> getFriendMemberNos() {
		return friendMemberNos;
	}

	/**
	 * @param friendMemberNos 好友会员号列表
	 * @occurs required
	 */
	public void setFriendMemberNos(List<String> friendMemberNos) {
		this.friendMemberNos = friendMemberNos;
	}
	
}
