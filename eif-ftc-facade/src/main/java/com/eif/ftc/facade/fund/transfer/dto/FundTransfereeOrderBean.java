package com.eif.ftc.facade.fund.transfer.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.framework.common.utils.pkg.BaseDTO;

/**
 * 受让单Bean
 * 
 * @author jiangweifeng
 *
 */
public class FundTransfereeOrderBean extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易单号
	 */
	private String fundTransfereeOrderNo;
	
	/**
	 * 业务单号
	 */
	private String businessOrderItemNo;
	
	/**
	 * 关联转让交易订单号
	 */
	private String refFundTransferorOrderNo;
	
	/**
     * 关联最初转让交易订单号，最初发起转让的交易订单号
     */
    private String refOriginFundTransferorOrderNo;
    
    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 资产账户号
     */
    private String assetAccountNo;

    /**
     * 基金交易账号
     */
    private String transAccountNo;

    /**
     * 转让协议号
     */
    private String transferAgreementNo;
    
    /**
     * 二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     * 受让价格，即为转让定价价格
     */
    private BigDecimal fundTransferAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 支付方式，json表示
     */
    private String payMethod;

    /**
     * 支付方式描述
     */
    private String payMethodDesc;

    /**
     * 币种
     */
    private String currencyType;

    /**
     * 库存冻结号
     */
    private String frozen_token;

    /**
     * 交易核心受让交易单流水号
     */
    private String transcoreTransNo;

    /**
     * 交易核心转账交易单流水号
     */
    private String transferTransNo;

    /**
     * 合同流水号
     */
    private String contractNo;

    /**
     * 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    private Integer bizChannel;

    /**
     * 业务单状态
     */
    private Integer status;

    /**
     * 业务扩展字段，保存json字串
     */
    private String extField;

    /**
     * 防止幂等, 全局跟踪
     */
    private String trackingNo;

    /**
     * 业务单支付时间
     */
    private Date payTime;

    /**
     * 业务单整体完成时间
     */
    private Date finishTime;

    /**
     * 订单总有效期
     */
    private Date expiryTime;

    /**
     * 当前状态过期时间
     */
    private Date statusExpiryTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人号
     */
    private String operatorNo;

    /**
     * @return 受让交易单号
     * @occurs required
     */
	public String getFundTransfereeOrderNo() {
		return fundTransfereeOrderNo;
	}

	/**
	 * @param fundTransfereeOrderNo the fundTransfereeOrderNo to set
	 */
	public void setFundTransfereeOrderNo(String fundTransfereeOrderNo) {
		this.fundTransfereeOrderNo = fundTransfereeOrderNo;
	}

    /**
     * @return 业务单号
     * @occurs required
     */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	/**
	 * @param businessOrderItemNo the businessOrderItemNo to set
	 */
	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}

    /**
     * @return 关联转让交易订单号
     * @occurs required
     */
	public String getRefFundTransferorOrderNo() {
		return refFundTransferorOrderNo;
	}

	/**
	 * @param refFundTransferorOrderNo the refFundTransferorOrderNo to set
	 */
	public void setRefFundTransferorOrderNo(String refFundTransferorOrderNo) {
		this.refFundTransferorOrderNo = refFundTransferorOrderNo;
	}

	/**
     * @return 关联最初转让交易订单号，最初发起转让的交易订单号
     * @occurs required
     */
	public String getRefOriginFundTransferorOrderNo() {
		return refOriginFundTransferorOrderNo;
	}

	/**
	 * @param refOriginFundTransferorOrderNo the refOriginFundTransferorOrderNo to set
	 */
	public void setRefOriginFundTransferorOrderNo(String refOriginFundTransferorOrderNo) {
		this.refOriginFundTransferorOrderNo = refOriginFundTransferorOrderNo;
	}

	/**
     * @return 会员号
     * @occurs required
     */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

    /**
     * @return 资产账户号
     * @occurs required
     */
	public String getAssetAccountNo() {
		return assetAccountNo;
	}

	/**
	 * @param assetAccountNo the assetAccountNo to set
	 */
	public void setAssetAccountNo(String assetAccountNo) {
		this.assetAccountNo = assetAccountNo;
	}

    /**
     * @return 基金交易账号
     * @occurs required
     */
	public String getTransAccountNo() {
		return transAccountNo;
	}

	/**
	 * @param transAccountNo the transAccountNo to set
	 */
	public void setTransAccountNo(String transAccountNo) {
		this.transAccountNo = transAccountNo;
	}

    /**
	 * @return 转让协议号
     * @occurs required
	 */
	public String getTransferAgreementNo() {
		return transferAgreementNo;
	}

	/**
	 * @param transferAgreementNo the transferAgreementNo to set
	 */
	public void setTransferAgreementNo(String transferAgreementNo) {
		this.transferAgreementNo = transferAgreementNo;
	}

    /**
     * @return 二级市场用户产品ID
     * @occurs required
     */
	public Long getMktProductId() {
		return mktProductId;
	}

	/**
	 * @param mktProductId the mktProductId to set
	 */
	public void setMktProductId(Long mktProductId) {
		this.mktProductId = mktProductId;
	}

    /**
     * @return 受让价格，即为转让定价价格
     * @occurs required
     */
	public BigDecimal getFundTransferAmount() {
		return fundTransferAmount;
	}

	/**
	 * @param fundTransferAmount the fundTransferAmount to set
	 */
	public void setFundTransferAmount(BigDecimal fundTransferAmount) {
		this.fundTransferAmount = fundTransferAmount;
	}

    /**
     * @return 优惠金额
     * @occurs required
     */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

    /**
     * @return 手续费
     * @occurs required
     */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

    /**
     * @return 支付方式，json表示
     * @occurs required
     */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

    /**
     * @return 支付方式描述
     * @occurs required
     */
	public String getPayMethodDesc() {
		return payMethodDesc;
	}

	/**
	 * @param payMethodDesc the payMethodDesc to set
	 */
	public void setPayMethodDesc(String payMethodDesc) {
		this.payMethodDesc = payMethodDesc;
	}

    /**
     * @return 币种
     * @occurs required
     */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

    /**
     * @return 库存冻结号
     * @occurs required
     */
	public String getFrozen_token() {
		return frozen_token;
	}

	/**
	 * @param frozen_token the frozen_token to set
	 */
	public void setFrozen_token(String frozen_token) {
		this.frozen_token = frozen_token;
	}

    /**
     * @return 交易核心受让交易单流水号
     * @occurs required
     */
	public String getTranscoreTransNo() {
		return transcoreTransNo;
	}

	/**
	 * @param transcoreTransNo the transcoreTransNo to set
	 */
	public void setTranscoreTransNo(String transcoreTransNo) {
		this.transcoreTransNo = transcoreTransNo;
	}

    /**
     * @return 交易核心转账交易单流水号
     * @occurs required
     */
	public String getTransferTransNo() {
		return transferTransNo;
	}

	/**
	 * @param transferTransNo the transferTransNo to set
	 */
	public void setTransferTransNo(String transferTransNo) {
		this.transferTransNo = transferTransNo;
	}

    /**
     * @return 合同流水号
     * @occurs required
     */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

    /**
     * @return 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     * @occurs required
     */
	public Integer getBizChannel() {
		return bizChannel;
	}

	/**
	 * @param bizChannel the bizChannel to set
	 */
	public void setBizChannel(Integer bizChannel) {
		this.bizChannel = bizChannel;
	}

    /**
     * @return 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
     * @occurs required
     */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

    /**
     * @return 业务扩展字段，保存json字串
     * @occurs required
     */
	public String getExtField() {
		return extField;
	}

	/**
	 * @param extField the extField to set
	 */
	public void setExtField(String extField) {
		this.extField = extField;
	}

    /**
     * @return 防止幂等
     * @occurs required
     */
	public String getTrackingNo() {
		return trackingNo;
	}

	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

    /**
     * @return 业务单支付时间
     * @occurs required
     */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
     * @return 业务单交易完成时间
     * @occurs required
     */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

    /**
     * @return 订单有效期时间
     * @occurs required
     */
	public Date getExpiryTime() {
		return expiryTime;
	}

	/**
	 * @param expiryTime the expiryTime to set
	 */
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

    /**
     * @return 当前状态过期时间
     * @occurs required
     */
	public Date getStatusExpiryTime() {
		return statusExpiryTime;
	}

	/**
	 * @param statusExpiryTime the statusExpiryTime to set
	 */
	public void setStatusExpiryTime(Date statusExpiryTime) {
		this.statusExpiryTime = statusExpiryTime;
	}

    /**
     * @return 创建时间
     * @occurs required
     */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    /**
     * @return 更新时间
     * @occurs required
     */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    /**
     * @return 备注
     * @occurs required
     */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    /**
     * @return 操作人
     * @occurs required
     */
	public String getOperatorNo() {
		return operatorNo;
	}

	/**
	 * @param operatorNo the operatorNo to set
	 */
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

}
