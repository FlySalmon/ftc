package com.eif.ftc.facade.fund.transfer.request;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * 受让单创建请求类
 * 
 * @author jiangweifeng
 *
 */
public class CreateTransfereeOrderRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     *  会员号
     */
    private String memberNo;

    /**
     *  二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     *  受让交易金额
     */
    private BigDecimal fundTransferAmount;

    /**
     *  优惠金额
     */
    private BigDecimal discountAmount;

    /**
     *  支付方式，json表示
     */
    private String payMethod;

    /**
     *  支付方式描述
     */
    private String payMethodDesc;

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
	 * @return the mktProductId
	 */
	public Long getMktProductId() {
		return mktProductId;
	}

    /**
     * @param mktProductId 二级市场用户产品号
     * @occurs required
     */
	public void setMktProductId(Long mktProductId) {
		this.mktProductId = mktProductId;
	}

	/**
	 * @return the fundTransferAmount
	 */
	public BigDecimal getFundTransferAmount() {
		return fundTransferAmount;
	}

    /**
     * @param fundTransferAmount 受让价格，即为转让定价价格
     * @occurs required
     */
	public void setFundTransferAmount(BigDecimal fundTransferAmount) {
		this.fundTransferAmount = fundTransferAmount;
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
	 * @return the payMethodDesc
	 */
	public String getPayMethodDesc() {
		return payMethodDesc;
	}

    /**
     * @param payMethodDesc 支付方式描述
     * @occurs required
     */
	public void setPayMethodDesc(String payMethodDesc) {
		this.payMethodDesc = payMethodDesc;
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
