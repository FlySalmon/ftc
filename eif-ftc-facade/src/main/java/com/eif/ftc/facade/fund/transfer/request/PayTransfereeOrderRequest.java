package com.eif.ftc.facade.fund.transfer.request;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 受让单支付请求类
 * 
 * @author jiangweifeng
 *
 */
public class PayTransfereeOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 受让交易订单号
	 */
	private String fundTransfereeOrderNo;

	/**
	 * 支付工具/方式（JSON字符串）
	 */
    private String payMethod;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * OTP验证标识
     */
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
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}

    /**
     * @param payMethod 支付方式，json表示
     * @occurs required
     */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

    /**
     * @param discountAmount 优惠金额
     * @occurs required
     */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the smsControl
	 */
	public Integer getSmsControl() {
		return smsControl;
	}

    /**
     * @param smsControl OTP标识
     * @occurs required
     */
	public void setSmsControl(Integer smsControl) {
		this.smsControl = smsControl;
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
