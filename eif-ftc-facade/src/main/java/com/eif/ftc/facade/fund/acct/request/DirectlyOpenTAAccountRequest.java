package com.eif.ftc.facade.fund.acct.request;

import java.util.Date;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class DirectlyOpenTAAccountRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
     * 投资人证件号码
     */
    private String certificateNo;

    /**
     * 投资人户名
     */
    private String investorName;

    /**
     * 个人/机构标志（com.eif.ftc.job.service.bean.fund.ta.IndividualOrInstitution,1-个人；0-机构）
     */
    private Integer individualOrInstitution;

    /**
     * 投资人基金交易账号
     */
    private String transactionAccountId;

    /**
     * 交易发生时间戳
     */
    private Date transTime;

	/**
	 * 操作人
	 */
	private String operator;
	
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
	 * @return the investorName
	 */
	public String getInvestorName() {
		return investorName;
	}

	/**
	 * @param investorName the investorName to set
	 */
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
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
