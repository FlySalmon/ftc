package com.eif.ftc.facade.fund.oper.response;

import java.util.List;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.LargeInvestmentClientResponseBean;

/**
 * 
 * @author jiangweifeng
 *
 */
public class LargeInvestmentClientsResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LargeInvestmentClientResponseBean> largeInvestmentClientsResponse;


	/**
	 * @return 大额投资列表
	 * @occurs required
	 */
	public List<LargeInvestmentClientResponseBean> getLargeInvestmentClientsResponse() {
		return largeInvestmentClientsResponse;
	}

	public void setLargeInvestmentClientsResponse(List<LargeInvestmentClientResponseBean> largeInvestmentClientsResponse) {
		this.largeInvestmentClientsResponse = largeInvestmentClientsResponse;
	}
	
}
