package com.eif.ftc.facade.fund.acct.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.page.PageableRequest;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;

public class TATransJobPageableRequest extends PageableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date startDt;	//起始时间
	
	private Date endDt;		//终止时间

	private int fundTransType; //交易类型
	
	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public int getFundTransType() {
		return fundTransType;
	}

	public void setFundTransType(int fundTransType) {
		this.fundTransType = fundTransType;
	}
}
