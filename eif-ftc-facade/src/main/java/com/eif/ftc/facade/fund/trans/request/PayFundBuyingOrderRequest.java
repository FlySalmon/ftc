package com.eif.ftc.facade.fund.trans.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.math.BigDecimal;

/**
 * Created by bohan on 1/7/16.
 */
public class PayFundBuyingOrderRequest extends BaseRequest {
    private String fundTransOrderNo;

    private String payMethod;

    private BigDecimal discountAmount;

    private BigDecimal expectedProfitAmount;

    private Integer smsControl = 0;

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

    public BigDecimal getExpectedProfitAmount() {
        return expectedProfitAmount;
    }

    public void setExpectedProfitAmount(BigDecimal expectedProfitAmount) {
        this.expectedProfitAmount = expectedProfitAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     *
     * @occurs optional
     * @param discountAmount 优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @occurs required
     * @param payMethod 支付方法
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    /**
     * @occurs required
     * @param fundTransOrderNo 基金交易单
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    public Integer getSmsControl() {
        return smsControl;
    }

    /**
     * @occurs required
     * @param smsControl 是否发送短信
     */
    public void setSmsControl(Integer smsControl) {
        this.smsControl = smsControl;
    }
}
