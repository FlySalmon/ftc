package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class QueryFundClearingOrderRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String businessOrderItemNo;

	private String fundClearingOrderNo;
	
	private Long productId;
	
	private String memberNo;

	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	/**
	 * @param businessOrderItemNo 业务订单号
	 * @occurs optional
	 */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

	public String getFundClearingOrderNo() {
		return fundClearingOrderNo;
	}

	/**
	 * @param fundClearingOrderNo 清盘业务单号
	 * @occurs optional
	 */
	public void setFundClearingOrderNo(String fundClearingOrderNo) {
		this.fundClearingOrderNo = fundClearingOrderNo;
	}

	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId 业务产品号
	 * @occurs required
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo 会员号
	 * @occurs required
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

}
