package com.eif.ftc.facade.fund.trans.request;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseRequest;

public class RedeemFundRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员号
	 */
    private String memberNo;

    /**
     *  是否为补单
     */
    private Boolean isAdjusted;

    /**
     *  0 - 正常; 1 - 充值后触发
     */
    private Integer triggerReason;

    /**
     *  商户号
     */
    private String merMemberNo;

    /**
     *  防止幂等, 全局跟踪
     */
    private String trackingNo;

    /**
     *  用户产品ID
     */
    private Long productId;

    /**
     *  交易金额
     */
    private BigDecimal fundTransAmount;

    /**
     * 资金走势(到银行卡、到余额)
     */
    private Integer paymentInstrumentType;
    
    /**
     * 支付工具凭证号（仅在paymentInstrumentType为MEMBER_BALANCE有效）
     */
    private String paymentInstrumentNo;
    
    /**
     *  渠道类型： 0 - pc; 1 - ios; 2 - android; 3 - wx; 4 - h5
     */
    private Integer bizChannel;

    /**
     *  备注
     */
    private String remark;

    /**
     *  操作人号
     */
    private String operatorNo;

	/**
	 * 风控使用:IP
	 */
	private String ip;

	/**
	 * 风控使用:设备号

	 */
	private String devId;

	/**
	 * 风控使用:设备信息
	 */
	private String deviceInfo;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @occurs required
	 * @param memberNo 会员号
     */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Boolean getIsAdjusted() {
		return isAdjusted;
	}

	/**
	 * @occurs required
	 * @param isAdjusted 是否补单
     */
	public void setIsAdjusted(Boolean isAdjusted) {
		this.isAdjusted = isAdjusted;
	}

	public Integer getTriggerReason() {
		return triggerReason;
	}

	/**
	 * @occurs required
	 * @param triggerReason 0 - 正常; 1 - 充值后触发
     */
	public void setTriggerReason(Integer triggerReason) {
		this.triggerReason = triggerReason;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	/**
	 * @occurs required
	 * @param trackingNo 防止幂等, 全局跟踪
     */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public Long getProductId() {
		return productId;
	}

	/**
	 * @occurs required
	 * @param productId 产品id
     */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getFundTransAmount() {
		return fundTransAmount;
	}

	/**
	 * @occurs required
	 * @param fundTransAmount 交易金额
     */
	public void setFundTransAmount(BigDecimal fundTransAmount) {
		this.fundTransAmount = fundTransAmount;
	}

	/**
	 * @return the paymentInstrumentType
	 */
	public Integer getPaymentInstrumentType() {
		return paymentInstrumentType;
	}

	/**
	 * @param paymentInstrumentType the paymentInstrumentType to set
	 */
	public void setPaymentInstrumentType(Integer paymentInstrumentType) {
		this.paymentInstrumentType = paymentInstrumentType;
	}

	/**
	 * @return the paymentInstrumentNo
	 */
	public String getPaymentInstrumentNo() {
		return paymentInstrumentNo;
	}

	/**
	 * @param paymentInstrumentNo the paymentInstrumentNo to set
	 */
	public void setPaymentInstrumentNo(String paymentInstrumentNo) {
		this.paymentInstrumentNo = paymentInstrumentNo;
	}

	public Integer getBizChannel() {
		return bizChannel;
	}

	/**
	 * @occurs required
	 * @param bizChannel 渠道类型： 0 - pc; 1 - ios; 2 - android; 3 - wx; 4 - h5
     */
	public void setBizChannel(Integer bizChannel) {
		this.bizChannel = bizChannel;
	}

	public String getRemark() {
		return remark;
	}

	/**
	 * @occurs optional
	 * @param remark 备注
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	/**
	 * @occurs optional
	 * @param operatorNo 操作人号
     */
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getMerMemberNo() {
		return merMemberNo;
	}

	/**
	 * @occurs required
	 * @param merMemberNo 商户号
     */
	public void setMerMemberNo(String merMemberNo) {
		this.merMemberNo = merMemberNo;
	}

}
