package com.eif.ftc.facade.fund.transfer.oper.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

public class GetFundTransfereeOrderByPageRequest extends PageableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 用户产品ID
     */
    private Long productId;

    /**
     * 二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     * 业务单状态
     */
    private Integer status;

    /**
     * 起始时间
     */
    private Date createStartTime;

    /**
     * 终止时间
     */
    private Date createEndTime;

    /**
     * 关联转让交易单号
     */
    private String refTransferorOrderNo;
    
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

	/**
	 * @return the mktProductId
	 */
	public Long getMktProductId() {
		return mktProductId;
	}

    /**
     * @param mktProductId 二级市场用户产品号
     * @occurs required
     */
	public void setMktProductId(Long mktProductId) {
		this.mktProductId = mktProductId;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

    /**
     * @param status 订单状态
     * @occurs required
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createStartTime
	 */
	public Date getCreateStartTime() {
		return createStartTime;
	}

    /**
     * @param createStartTime 订单创建起始时间
     * @occurs required
     */
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	/**
	 * @return the createEndTime
	 */
	public Date getCreateEndTime() {
		return createEndTime;
	}

    /**
     * @param createStartTime 订单创建终止时间
     * @occurs required
     */
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	/**
	 * @return the refTransferorOrderNo
	 */
	public String getRefTransferorOrderNo() {
		return refTransferorOrderNo;
	}

    /**
     * @param refTransferorOrderNo 关联转让交易单号（仅供OMC用）
     * @occurs 
     */
	public void setRefTransferorOrderNo(String refTransferorOrderNo) {
		this.refTransferorOrderNo = refTransferorOrderNo;
	}

}
