package com.eif.ftc.facade.fund.trans.request;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class DirectlyTransactionRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 交易类型：参照com.eif.ftc.facade.fund.trans.enumeration.DirectlyTransactionType
	 */
	private Integer transType;
	
	/**
	 * 用户产品Id
	 */
	private Long productId;
	
	/**
	 * 基金代码
	 */
	private String fundCode;
	
	/**
	 * 投资人基金交易帐号
	 */
	private String transactionAccountId;
	
	/**
	 * 投资人基金帐号 
	 */
	private String taAccountId;

	/**
	 * 申请金额 (申购用)
	 */
	private BigDecimal applicationAmount;

	/**
	 * 申请份额（赎回用）
	 */
	private BigDecimal applicationVol;

    /**
     * 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    private String shareClass;

    /**
     * 收费类型:0-折扣率方式 1-指定费率，2-指定费用
     */
    private String chargeType;

	/**
	 * 交易时间戳
	 */
	private Date transTime;

	/**
	 * 上期利息
	 */
	private BigDecimal lastDividend;
	
	/**
	 * 利率
	 */
	private BigDecimal rate;

	/**
	 * 个人/机构标志（com.eif.ftc.job.service.bean.fund.ta.IndividualOrInstitution,1-个人；0-机构）
	 */
	private Integer individualOrInstitution;
	
	/**
     * 个人证件类型
            0-身份证，1-护照
            2-军官证，3-士兵证
            4-港澳居民来往内地通行证，5-户口本
            6-外国护照，7-其它
            8-文职证，9-警官证
            A-台胞证
            
            机构证件类型
            0-组织机构代码证
            1-营业执照，2-行政机关
            3-社会团体，4-军队
            5-武警
            6-下属机构（具有主管单位批文号）
            7-基金会，8-其它
     */
	private String certificateType;
	
	/**
	 * 客户姓名（个人用）
	 */
	private String memberName;
	
	/**
	 * 客户全称（机构用）
	 */
	private String memberFullName;

	/**
	 * 证件编号
	 */
	private String certificateNo;

	/**
	 * 风险承受级别（个人用）
	 */
	private String riskLevel;

	/**
	 * 操作人
	 */
	private String operator;

	/**
	 * @return the transType
	 */
	public Integer getTransType() {
		return transType;
	}

	/**
	 * @param transType the transType to set
	 */
	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the fundCode
	 */
	public String getFundCode() {
		return fundCode;
	}

	/**
	 * @param fundCode the fundCode to set
	 */
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	/**
	 * @return the transactionAccountId
	 */
	public String getTransactionAccountId() {
		return transactionAccountId;
	}

	/**
	 * @param transactionAccountId the transactionAccountId to set
	 */
	public void setTransactionAccountId(String transactionAccountId) {
		this.transactionAccountId = transactionAccountId;
	}

	/**
	 * @return the taAccountId
	 */
	public String getTaAccountId() {
		return taAccountId;
	}

	/**
	 * @param taAccountId the taAccountId to set
	 */
	public void setTaAccountId(String taAccountId) {
		this.taAccountId = taAccountId;
	}

	/**
	 * @return the applicationAmount
	 */
	public BigDecimal getApplicationAmount() {
		return applicationAmount;
	}

	/**
	 * @param applicationAmount the applicationAmount to set
	 */
	public void setApplicationAmount(BigDecimal applicationAmount) {
		this.applicationAmount = applicationAmount;
	}

	/**
	 * @return the applicationVol
	 */
	public BigDecimal getApplicationVol() {
		return applicationVol;
	}

	/**
	 * @param applicationVol the applicationVol to set
	 */
	public void setApplicationVol(BigDecimal applicationVol) {
		this.applicationVol = applicationVol;
	}

	/**
	 * @return the shareClass
	 */
	public String getShareClass() {
		return shareClass;
	}

	/**
	 * @param shareClass the shareClass to set
	 */
	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}

	/**
	 * @return the chargeType
	 */
	public String getChargeType() {
		return chargeType;
	}

	/**
	 * @param chargeType the chargeType to set
	 */
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	/**
	 * @return the transTime
	 */
	public Date getTransTime() {
		return transTime;
	}

	/**
	 * @param transTime the transTime to set
	 */
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	/**
	 * @return the lastDividend
	 */
	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * @return the individualOrInstitution
	 */
	public Integer getIndividualOrInstitution() {
		return individualOrInstitution;
	}

	/**
	 * @param individualOrInstitution the individualOrInstitution to set
	 */
	public void setIndividualOrInstitution(Integer individualOrInstitution) {
		this.individualOrInstitution = individualOrInstitution;
	}

	/**
	 * @return the certificateType
	 */
	public String getCertificateType() {
		return certificateType;
	}

	/**
	 * @param certificateType the certificateType to set
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberFullName
	 */
	public String getMemberFullName() {
		return memberFullName;
	}

	/**
	 * @param memberFullName the memberFullName to set
	 */
	public void setMemberFullName(String memberFullName) {
		this.memberFullName = memberFullName;
	}

	/**
	 * @return the certificateNo
	 */
	public String getCertificateNo() {
		return certificateNo;
	}

	/**
	 * @param certificateNo the certificateNo to set
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	/**
	 * @return the riskLevel
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * @param riskLevel the riskLevel to set
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
