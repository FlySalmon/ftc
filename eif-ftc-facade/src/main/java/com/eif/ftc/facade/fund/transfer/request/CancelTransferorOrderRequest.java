package com.eif.ftc.facade.fund.transfer.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 转让单撤销请求类
 * 
 * @author jiangweifeng
 *
 */
public class CancelTransferorOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 转让交易业务单号
     */
    private String fundTransferorOrderNo;

    /**
     * OFC订单业务项号
     */
    private String businessOrderItemNo;

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
	 * @return the fundTransferorOrderNo
	 */
	public String getFundTransferorOrderNo() {
		return fundTransferorOrderNo;
	}

    /**
     * @param fundTransferorOrderNo 转让交易业务单号
     * @occurs required
     */
	public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
		this.fundTransferorOrderNo = fundTransferorOrderNo;
	}

	/**
	 * @return the businessOrderItemNo
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

    /**
     * @param businessOrderItemNo OFC订单业务项号
     * @occurs required
     */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
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
