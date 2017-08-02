package com.eif.ftc.facade.dto;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseDTO;

public class FundDividendOrderBean extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String fundDividendOrderNo;

	private String businessOrderItemNo;

	private Long productId;

	private String productName;

	private String memberNo;

	private String transactionAccountId;

	private BigDecimal basisforCalculatingDividend;

	private BigDecimal volOfDividendForReinvestment;

	private BigDecimal dividendAmount;

    private String dividentDate;

	private String registrationDate;

	private String externalSerialNo;

	private Integer status;

	/**
	 * @return 基金分红业务单号
	 * @occurs required
	 */
	public String getFundDividendOrderNo() {
		return fundDividendOrderNo;
	}

	public void setFundDividendOrderNo(String fundDividendOrderNo) {
		this.fundDividendOrderNo = fundDividendOrderNo;
	}

	/**
	 * @return 业务订单项流水号
	 * @occurs required
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

	/**
	 * @return 	用户产品Id
	 * @occurs required
	 */
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return 	产品名称
	 * @occurs required
	 */
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return 	会员号
	 * @occurs required
	 */
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return 	基金交易账号
	 * @occurs required
	 */
	public String getTransactionAccountId() {
		return transactionAccountId;
	}

	public void setTransactionAccountId(String transactionAccountId) {
		this.transactionAccountId = transactionAccountId;
	}


	/**
	 * @return 	红利/红利再投资基数,登记日基金持有人的基金份数
	 * @occurs required
	 */
	public BigDecimal getBasisforCalculatingDividend() {
		return basisforCalculatingDividend;
	}

	public void setBasisforCalculatingDividend(BigDecimal basisforCalculatingDividend) {
		this.basisforCalculatingDividend = basisforCalculatingDividend;
	}

	/**
	 * @return 基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
	 * @occurs required
	 */
	public BigDecimal getVolOfDividendForReinvestment() {
		return volOfDividendForReinvestment;
	}

	public void setVolOfDividendForReinvestment(BigDecimal volOfDividendForReinvestment) {
		this.volOfDividendForReinvestment = volOfDividendForReinvestment;
	}

	/**
	 * @return 分红金额
	 * @occurs required
	 */
	public BigDecimal getDividendAmount() {
		return dividendAmount;
	}

	public void setDividendAmount(BigDecimal dividendAmount) {
		this.dividendAmount = dividendAmount;
	}

	/**
	 * @return 权益发放日
	 * @occurs required
	 */
	public String getDividentDate() {
		return dividentDate;
	}

	public void setDividentDate(String dividentDate) {
		this.dividentDate = dividentDate;
	}

	/**
	 * @return 权益登记日期
	 * @occurs required
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return 外部系统关联流水号
	 * @occurs required
	 */
	public String getExternalSerialNo() {
		return externalSerialNo;
	}

	public void setExternalSerialNo(String externalSerialNo) {
		this.externalSerialNo = externalSerialNo;
	}

	/**
	 * @return 单据状态
	 * @occurs required
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
