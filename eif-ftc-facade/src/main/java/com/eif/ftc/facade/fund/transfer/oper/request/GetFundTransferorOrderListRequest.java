package com.eif.ftc.facade.fund.transfer.oper.request;

import java.util.List;
import java.util.Set;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 转让单列表请求类（若传递会员号，则至少还需传递用户产品ID、或二级市场用户产品ID、或业务单状态）
 * 
 * @author jiangweifeng
 *
 */
public class GetFundTransferorOrderListRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让交易单号列表
	 */
	private List<String> fundTransferorOrderNoList;

	/**
	 * 转让业务订单号列表
	 */
	private List<String> businessOrderItemNoList;
	
    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 用户产品ID
     */
    private Long productId;
    
    /**
     * 用户产品Id，必须和memberNo一起用
     */
    private Set<Long> productIds;

    /**
     * 二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     * 二级市场用户产品ID
     */
    private Set<Long> mktProductIds;
    
    /**
     * 业务单状态
     */
    private Integer status;

	/**
	 * @return the fundTransferorOrderNoList
	 */
	public List<String> getFundTransferorOrderNoList() {
		return fundTransferorOrderNoList;
	}

    /**
     * @param fundTransferorOrderNoList 转让交易单号列表
     * @occurs required
     */
	public void setFundTransferorOrderNoList(List<String> fundTransferorOrderNoList) {
		this.fundTransferorOrderNoList = fundTransferorOrderNoList;
	}

	/**
	 * @return the businessOrderItemNoList
	 */
	public List<String> getBusinessOrderItemNoList() {
		return businessOrderItemNoList;
	}

    /**
     * @param fundTransferorOrderNoList 转让业务订单号列表
     * @occurs required
     */
	public void setBusinessOrderItemNoList(List<String> businessOrderItemNoList) {
		this.businessOrderItemNoList = businessOrderItemNoList;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

    /**
     * @param memberNo 会员号
     * @occurs optional 
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
     * @occurs optional
     */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productIds
	 */
	public Set<Long> getProductIds() {
		return productIds;
	}

	/**
	 * @param productIds 用户产品号
     * @occurs optional 需要和memberNo同时使用
	 */
	public void setProductIds(Set<Long> productIds) {
		this.productIds = productIds;
	}

	/**
	 * @return the mktProductId
	 */
	public Long getMktProductId() {
		return mktProductId;
	}

    /**
     * @param mktProductId 二级市场用户产品号
     * @occurs optional
     */
	public void setMktProductId(Long mktProductId) {
		this.mktProductId = mktProductId;
	}

	/**
	 * @return the mktProductIds
	 */
	public Set<Long> getMktProductIds() {
		return mktProductIds;
	}

	/**
	 * @param mktProductIds 二级市场用户产品号
     * @occurs optional
	 */
	public void setMktProductIds(Set<Long> mktProductIds) {
		this.mktProductIds = mktProductIds;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

    /**
     * @param status 订单状态
     * @occurs optional 需要与memberNo或者mktProductId同时传递
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
