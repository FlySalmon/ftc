package com.eif.ftc.facade.fund.trans.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.math.BigDecimal;

/**
 * Created by bohan on 1/7/16.
 */
public class CreateFundBuyingOrderRequest extends BaseRequest {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *  会员号
     */
    private String memberNo;

    /**
     *  商户号
     */
    private String merMemberNo;

    /**
     *  是否为补单
     */
    private Boolean isAdjusted;

    /**
     *  0 - 正常; 1 - 充值后触发
     */
    private Integer triggerReason;

    /**
     *  防止幂等, 全局跟踪
     */
    private String trackingNo;

    /**
     *  用户产品ID
     */
    private long productId;

    /**
     *  交易金额
     */
    private BigDecimal fundTransAmount;

    /**
     *  渠道类型： 0 - pc; 1 - ios; 2 - android; 3 - wx; 4 - h5
     */
    private Integer bizChannel;

    /**
     *  支付方式，json表示
     */
    private String payMethod;

    /**
     *  支付方式描述
     */
    private String payMethodDesc;

    /**
     *  备注
     */
    private String remark;

    /**
     *  操作人号
     */
    private String operatorNo;

    /**
     *  优惠金额
     */
    private BigDecimal discountAmount;

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

    /**
     * 外部系统号
     */
    private String outerSysNo;

    /**
     * 外部订单号
     */
    private String outerOrderNo;

    /**
     * 营销扩展字段
     */
    private String marketField;
    
    public String getOuterSysNo() {
        return outerSysNo;
    }

    public void setOuterSysNo(String outerSysNo) {
        this.outerSysNo = outerSysNo;
    }

    public String getOuterOrderNo() {
        return outerOrderNo;
    }

    public void setOuterOrderNo(String outerOrderNo) {
        this.outerOrderNo = outerOrderNo;
    }

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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 优惠金额
     *
     * @occurs required
     * @param discountAmount
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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
     * @param isAdjusted 是否为补单
     */
    public void setIsAdjusted(Boolean isAdjusted) {
        this.isAdjusted = isAdjusted;
    }

    public Integer getTriggerReason() {
        return triggerReason;
    }

    /**
     * @occurs required
     * @param triggerReason 触发原因: 0 - 正常; 1 - 充值后触发
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

    public long getProductId() {
        return productId;
    }

    /**
     * @occurs required
     * @param productId 用户产品ID
     */
    public void setProductId(long productId) {
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

    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @occurs optional
     * @param payMethod 支付方式，json表示
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
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

    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    /**
     * @occurs optional
     * @param payMethodDesc 支付方式描述
     */
    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
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

	/**
	 * @return the marketField
	 */
	public String getMarketField() {
		return marketField;
	}

	/**
	 * @param marketField the marketField to set
	 */
	public void setMarketField(String marketField) {
		this.marketField = marketField;
	}
}
