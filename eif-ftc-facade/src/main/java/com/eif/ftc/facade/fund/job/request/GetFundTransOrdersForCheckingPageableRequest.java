package com.eif.ftc.facade.fund.job.request;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetFundTransOrdersForCheckingPageableRequest extends PageableRequest {

	private static final long serialVersionUID = 1L;
	

	private Date startDate;
	

	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate 开始时间
	 * @occurs required
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate 结束时间
	 * @occurs required
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
