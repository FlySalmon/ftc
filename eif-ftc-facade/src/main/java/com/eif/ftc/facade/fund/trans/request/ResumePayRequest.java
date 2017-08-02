package com.eif.ftc.facade.fund.trans.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by bohan on 1/15/16.
 */
public class ResumePayRequest extends BaseRequest {

    private String transNo;

    private String pin;

    private String fundTransOrderNo;

    private String ip;

    private String devId;

    private String deviceInfo;

    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    /**
     * @occurs optional
     * @param fundTransOrderNo 基金交易业务单号
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    public String getPin() {
        return pin;
    }


    /**
     * @occurs optional
     * @param pin pin码
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTransNo() {
        return transNo;
    }


    /**
     * @occurs optional
     * @param transNo 交易单号
     */
    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * @occurs optional
     * @param deviceInfo 设备信息
     */
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getIp() {
        return ip;
    }


    /**
     * @occurs optional
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevId() {
        return devId;
    }

    /**
     * @occurs optional
     * @param devId 设备ID
     */
    public void setDevId(String devId) {
        this.devId = devId;
    }

}

