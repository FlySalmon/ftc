package com.eif.ftc.facade.fund.transfer.request;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 转让申请（转让单创建）请求类
 * 
 * @author jiangweifeng
 *
 */
public class CreateTransferorOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     *  会员号
     */
    private String memberNo;

    /**
     *  一级市场用户产品ID
     */
    private Long productId;

    /**
     * 转让方式（1 - 用户指定转让定价; 2 - 用户指定转让收益率;）
     * @see com.eif.ftc.facade.fund.transfer.enumeration.TransferMode
     */
    private Integer transferMode;
    
    /**
     * 转让交易模式（1 - 一口价模式; 2 - 竞价模式;）
     * @see com.eif.ftc.facade.fund.transfer.enumeration.TransferTransactionMode
     */
    private Integer transferTransactionMode;

    /**
     * 转让定价金额（用户指定转让定价方式 传入）
     */
    private BigDecimal fundTransferAmount;
    
    /**
     * 转让收益率（用户指定转让收益率方式 传入）
     */
    private BigDecimal rate;

	/**
	 * 预计到帐金额（转让定价金额-手续费）,校验订单有效性用
	 */
	private BigDecimal expectedAmount;
	
	/**
	 * 预计手续费,校验订单有效性用
	 */
	private BigDecimal expectedFee;

    /**
     *  渠道类型： 0 - pc; 1 - ios; 2 - android; 3 - wx; 4 - h5
     */
    private Integer bizChannel;

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
     * 扩展信息: JSON字符串
     */
    private String extField;

    /**
     *  防止幂等, 全局跟踪
     */
    private String trackingNo;

    /**
     *  备注
     */
    private String remark;

    /**
     *  操作人号
     */
    private String operatorNo;

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

    /**
     * @param memberNo 会员号
     * @occurs required
     */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

    /**
     * @param productId 用户产品Id
     * @occurs required
     */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the transferMode
	 */
	public Integer getTransferMode() {
		return transferMode;
	}

    /**
     * @param transferMode 转让方式（1 - 用户指定转让定价; 2 - 用户指定转让收益率;）
     * @occurs required
     */
	public void setTransferMode(Integer transferMode) {
		this.transferMode = transferMode;
	}

	/**
	 * @return the transferTransactionMode
	 */
	public Integer getTransferTransactionMode() {
		return transferTransactionMode;
	}

    /**
     * @param transferTransactionMode 转让交易模式（1 - 一口价模式; 2 - 竞价模式;）
     * @occurs required
     */
	public void setTransferTransactionMode(Integer transferTransactionMode) {
		this.transferTransactionMode = transferTransactionMode;
	}

	/**
	 * @return the fundTransferAmount
	 */
	public BigDecimal getFundTransferAmount() {
		return fundTransferAmount;
	}

    /**
     * @param fundTransferAmount 转让定价价格
     * @occurs required
     */
	public void setFundTransferAmount(BigDecimal fundTransferAmount) {
		this.fundTransferAmount = fundTransferAmount;
	}

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

    /**
     * @param rate 转让预期收益率
     * @occurs required
     */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	/**
	 * @return the expectedAmount
	 */
	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

    /**
     * @param expectedAmount 预计到帐金额（本金+收益-手续费）
     * @occurs required
     */
	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	/**
	 * @return the expectedFee
	 */
	public BigDecimal getExpectedFee() {
		return expectedFee;
	}

    /**
     * @param expectedFee 预计手续费
     * @occurs required
     */
	public void setExpectedFee(BigDecimal expectedFee) {
		this.expectedFee = expectedFee;
	}

	/**
	 * @return the bizChannel
	 */
	public Integer getBizChannel() {
		return bizChannel;
	}

    /**
     * @param bizChannel 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     * @occurs required
     */
	public void setBizChannel(Integer bizChannel) {
		this.bizChannel = bizChannel;
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

	/**
	 * @return the extField
	 */
	public String getExtField() {
		return extField;
	}

    /**
     * @param extField 扩展业务字段
     * @occurs required
     */
	public void setExtField(String extField) {
		this.extField = extField;
	}

	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return trackingNo;
	}

    /**
     * @param trackingNo 幂等号
     * @occurs required
     */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

    /**
     * @param remark 备注
     * @occurs required
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the operatorNo
	 */
	public String getOperatorNo() {
		return operatorNo;
	}

    /**
     * @param operatorNo 操作人
     * @occurs required
     */
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

}
