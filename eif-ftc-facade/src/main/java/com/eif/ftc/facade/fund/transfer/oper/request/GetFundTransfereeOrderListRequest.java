package com.eif.ftc.facade.fund.transfer.oper.request;

import java.util.List;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 受让单列表请求类（若传递会员号，则至少还需传递用户产品ID、或二级市场用户产品ID）
 * 
 * @author jiangweifeng
 *
 */
public class GetFundTransfereeOrderListRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易单号列表
	 */
	private List<String> fundTransfereeOrderNoList;

	/**
	 * 受让业务订单号列表
	 */
	private List<String> businessOrderItemNoList;
	
    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     * 业务单状态
     */
    private Integer status;

	/**
	 * @return the fundTransfereeOrderNoList
	 */
	public List<String> getFundTransfereeOrderNoList() {
		return fundTransfereeOrderNoList;
	}

    /**
     * @param fundTransfereeOrderNoList 受让交易单号列表
     * @occurs required
     */
	public void setFundTransfereeOrderNoList(List<String> fundTransfereeOrderNoList) {
		this.fundTransfereeOrderNoList = fundTransfereeOrderNoList;
	}

	/**
	 * @return the businessOrderItemNoList
	 */
	public List<String> getBusinessOrderItemNoList() {
		return businessOrderItemNoList;
	}

    /**
     * @param businessOrderItemNoList 业务订单号列表
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
     * @occurs required
     */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
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

}
