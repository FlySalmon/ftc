package com.eif.ftc.facade.fund.transfer.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 受让单OTP支付请求类
 * 
 * @author jiangweifeng
 *
 */
public class ResumePayTransfereeOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 交易核心受让交易单号
	 */
    private String transNo;

    /**
     * 支付用PIN码
     */
    private String pin;
    
	/**
	 * 受让交易订单号
	 */
	private String fundTransfereeOrderNo;

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
	 * @return the transNo
	 */
	public String getTransNo() {
		return transNo;
	}

    /**
     * @param transNo 交易核心受让交易单号
     * @occurs required
     */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

    /**
     * @param pin 支付用PIN码
     * @occurs required
     */
	public void setPin(String pin) {
		this.pin = pin;
	}

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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

    /**
     * @param ip ip
     * @occurs required
     */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the devId
	 */
	public String getDevId() {
		return devId;
	}

    /**
     * @param devId 机器设备ID
     * @occurs required
     */
	public void setDevId(String devId) {
		this.devId = devId;
	}

	/**
	 * @return the deviceInfo
	 */
	public String getDeviceInfo() {
		return deviceInfo;
	}

    /**
     * @param deviceInfo 机器设备信息
     * @occurs required
     */
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

}
