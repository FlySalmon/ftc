package com.eif.ftc.facade.fund.transfer.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 原始转让单查询请求类
 * 
 * @author jiangweifeng
 *
 */
public class GetOriginalTransferorOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易单号
	 */
	private String fundTransfereeOrderNo;
	
	/**
	 * 受让业务单号
	 */
	private String transfereeBusinessOrderItemNo;

	/**
	 * 转让交易单号
	 */
	private String fundTransferorOrderNo;
	
	/**
	 * 二级市场产品Id
	 */
	private Long mktProductId;
	
	/**
	 * @return the fundTransfereeOrderNo
	 */
	public String getFundTransfereeOrderNo() {
		return fundTransfereeOrderNo;
	}

    /**
     * @param fundTransfereeOrderNo 受让交易单号
     * @occurs required
     */
	public void setFundTransfereeOrderNo(String fundTransfereeOrderNo) {
		this.fundTransfereeOrderNo = fundTransfereeOrderNo;
	}

	/**
	 * @return the transfereeBusinessOrderItemNo
	 */
	public String getTransfereeBusinessOrderItemNo() {
		return transfereeBusinessOrderItemNo;
	}

    /**
     * @param transfereeBusinessOrderItemNo 受让业务单号
     * @occurs required
     */
	public void setTransfereeBusinessOrderItemNo(String transfereeBusinessOrderItemNo) {
		this.transfereeBusinessOrderItemNo = transfereeBusinessOrderItemNo;
	}

	/**
	 * @return the fundTransferorOrderNo
	 */
	public String getFundTransferorOrderNo() {
		return fundTransferorOrderNo;
	}

    /**
     * @param fundTransferorOrderNo 转让交易单号
     * @occurs required
     */
	public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
		this.fundTransferorOrderNo = fundTransferorOrderNo;
	}

	/**
	 * @return the mktProductId
	 */
	public Long getMktProductId() {
		return mktProductId;
	}

    /**
     * @param mktProductId 二级市场产品Id
     * @occurs required
     */
	public void setMktProductId(Long mktProductId) {
		this.mktProductId = mktProductId;
	}

}
