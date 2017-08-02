package com.eif.ftc.facade.mq.dto;

import com.eif.ftc.facade.code.FTCRespCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bohan on 1/6/16.
 */
public class MQBusinessOrderItemMessage implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String trackingNo;
    private Boolean isRegulated;
    private Integer businessOrderItemType;
    private String businessOrderItemNo;
    private Integer bizChannel;
    private String bizOrderNo;
    private Integer bizOrderStatus;
    private String bizSysCode;
    private String currency;
    private String merMemberNo;
    private Long bizProductId;
    private String bizProductNo;
    private String bizProductName;
    private String bizProductDesc;
    private BigDecimal amount;
    private BigDecimal discountAmount;
    private String memberNo;
    private String memberPhone;
    private String memberAlias;
    private String notifyPhone;
    private String notifyEmail;
    private String extField;
    private Short operatorNo;
    private String paymentMethods;
    private String reasonCode;

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public Boolean getRegulated() {
        return isRegulated;
    }

    public void setRegulated(Boolean regulated) {
        isRegulated = regulated;
    }

    public Integer getBusinessOrderItemType() {
        return businessOrderItemType;
    }

    public void setBusinessOrderItemType(Integer businessOrderItemType) {
        this.businessOrderItemType = businessOrderItemType;
    }

    public Boolean getIsRegulated() {
        return isRegulated;
    }

    public void setIsRegulated(Boolean isRegulated) {
        this.isRegulated = isRegulated;
    }

    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }

    public Integer getBizChannel() {
        return bizChannel;
    }

    public void setBizChannel(Integer bizChannel) {
        this.bizChannel = bizChannel;
    }

    public String getBizOrderNo() {
        return bizOrderNo;
    }

    public void setBizOrderNo(String bizOrderNo) {
        this.bizOrderNo = bizOrderNo;
    }

    public Integer getBizOrderStatus() {
        return bizOrderStatus;
    }

    public void setBizOrderStatus(Integer bizOrderStatus) {
        this.bizOrderStatus = bizOrderStatus;
    }

    public String getBizSysCode() {
        return bizSysCode;
    }

    public void setBizSysCode(String bizSysCode) {
        this.bizSysCode = bizSysCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerMemberNo() {
        return merMemberNo;
    }

    public void setMerMemberNo(String merMemberNo) {
        this.merMemberNo = merMemberNo;
    }

    public Long getBizProductId() {
        return bizProductId;
    }

    public void setBizProductId(Long bizProductId) {
        this.bizProductId = bizProductId;
    }

    public String getBizProductNo() {
        return bizProductNo;
    }

    public void setBizProductNo(String bizProductNo) {
        this.bizProductNo = bizProductNo;
    }

    public String getBizProductName() {
        return bizProductName;
    }

    public void setBizProductName(String bizProductName) {
        this.bizProductName = bizProductName;
    }

    public String getBizProductDesc() {
        return bizProductDesc;
    }

    public void setBizProductDesc(String bizProductDesc) {
        this.bizProductDesc = bizProductDesc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberAlias() {
        return memberAlias;
    }

    public void setMemberAlias(String memberAlias) {
        this.memberAlias = memberAlias;
    }

    public String getNotifyPhone() {
        return notifyPhone;
    }

    public void setNotifyPhone(String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getExtField() {
        return extField;
    }

    public void setExtField(String extField) {
        this.extField = extField;
    }

    public Short getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(Short operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
}
