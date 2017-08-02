package com.eif.ftc.facade.fund.transfer.request;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 转让定价预算请求类
 * 
 * @author jiangweifeng
 *
 */
public class CalculateTransferPriceAndFeeRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户产品Id
	 */
	private Long productId;

	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 预期收益率
	 */
	private BigDecimal rate;

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

    /**
     * @param productId 用户产品号
     * @occurs required
     */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the memberNo
	 */
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

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

    /**
     * @param rate 预期收益率
     * @occurs required
     */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
}
