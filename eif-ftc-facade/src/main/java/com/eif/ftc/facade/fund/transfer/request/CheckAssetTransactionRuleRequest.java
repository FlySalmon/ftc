package com.eif.ftc.facade.fund.transfer.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 用户产品资产份额可转让校验请求类
 * 
 * @author jiangweifeng
 *
 */
public class CheckAssetTransactionRuleRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员号
	 */
	private String memberNo;
	
	/**
	 * 产品Id
	 */
	private Long productId;

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
	
}
