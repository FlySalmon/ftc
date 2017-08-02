package com.eif.ftc.facade.fund.transfer.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.framework.common.utils.pkg.BaseDTO;

/**
 * 转让单Bean
 * 
 * @author jiangweifeng
 *
 */
public class FundTransferorOrderBean extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让方式
	 */
	private Integer transferMode;

	/**
	 * 转让交易模式
	 */
	private Integer transferTransactionMode;
	
	/**
	 * 转让交易单号
	 */
	private String fundTransferorOrderNo;
	
	/**
	 * 业务单号
	 */
	private String businessOrderItemNo;
	
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
     * 用户产品ID
     */
    private Long productId;

	/**
	 * 关联外部基金分组编号
	 */
	private String refFundSubCode;
	
    /**
     * 二级市场用户产品ID
     */
    private Long mktProductId;

    /**
     * 原资产份额总价值
     */
    private BigDecimal originalAssetTotalValue;
    
    /**
     * 原资产份额残值
     */
    private BigDecimal originalAssetSurplusValue;
    
    /**
     * 转让金额，即转让定价价格
     */
    private BigDecimal fundTransferAmount;
    
    /**
     * 转让价格中的本金金额
     */
    private BigDecimal fundTransferPrincipal;
    
    /**
     * 转让价格中的利息金额
     */
    private BigDecimal fundTransferInterest;
    
    /**
     * 订单让利金额
     */
    private BigDecimal discountAmount;
    
    /**
     * 转让年化收益率
     */
    private BigDecimal annualYield;
    
    /**
     * 转让方手续费
     */
    private BigDecimal transferorFee;
    
    /**
     * 受让方手续费
     */
    private BigDecimal transfereeFee;
    
    /**
     * 手续费规则（JSON字符串）
     */
    private String feeRule;
    
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
     * 业务单整体完成时间
     */
    private Date finishTime;

    /**
     * 业务单撤销时间
     */
    private Date cancelTime;

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
	 * @return 转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     * @occurs required
	 */
	public Integer getTransferMode() {
		return transferMode;
	}

	/**
	 * @param transferMode the transferMode to set
	 */
	public void setTransferMode(Integer transferMode) {
		this.transferMode = transferMode;
	}

    /**
	 * @return 转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     * @occurs required
	 */
	public Integer getTransferTransactionMode() {
		return transferTransactionMode;
	}

	/**
	 * @param transferTransactionMode the transferTransactionMode to set
	 */
	public void setTransferTransactionMode(Integer transferTransactionMode) {
		this.transferTransactionMode = transferTransactionMode;
	}

    /**
	 * @return 转让交易订单号
     * @occurs required
	 */
	public String getFundTransferorOrderNo() {
		return fundTransferorOrderNo;
	}

	/**
	 * @param fundTransferorOrderNo the fundTransferorOrderNo to set
	 */
	public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
		this.fundTransferorOrderNo = fundTransferorOrderNo;
	}

    /**
	 * @return 业务订单流水号
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
     * @return 用户产品ID
     * @occurs required
     */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
     * @return 外部基金分组编号（即子产品号，仅活包定或活期产品存在）
     * @occurs 
     */
	public String getRefFundSubCode() {
		return refFundSubCode;
	}

	/**
	 * @param refFundSubCode the refFundSubCode to set
	 */
	public void setRefFundSubCode(String refFundSubCode) {
		this.refFundSubCode = refFundSubCode;
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
	 * @return 原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
	 * @occurs required
	 */
	public BigDecimal getOriginalAssetTotalValue() {
		return originalAssetTotalValue;
	}

	/**
	 * @param originalAssetTotalValue the originalAssetTotalValue to set
	 */
	public void setOriginalAssetTotalValue(BigDecimal originalAssetTotalValue) {
		this.originalAssetTotalValue = originalAssetTotalValue;
	}

	/**
	 * @return 原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
	 * @occurs required
	 */
	public BigDecimal getOriginalAssetSurplusValue() {
		return originalAssetSurplusValue;
	}

	/**
	 * @param originalAssetSurplusValue the originalAssetSurplusValue to set
	 */
	public void setOriginalAssetSurplusValue(BigDecimal originalAssetSurplusValue) {
		this.originalAssetSurplusValue = originalAssetSurplusValue;
	}

	/**
	 * @return 转让金额，即转让定价价格
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
     * @return 转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     * @occurs required
     */
	public BigDecimal getFundTransferPrincipal() {
		return fundTransferPrincipal;
	}

	/**
	 * @param fundTransferPrincipal the fundTransferPrincipal to set
	 */
	public void setFundTransferPrincipal(BigDecimal fundTransferPrincipal) {
		this.fundTransferPrincipal = fundTransferPrincipal;
	}

    /**
     * @return 转让价格中的利息金额，转让价格-转让价格中本金金额
     * @occurs required
     */
	public BigDecimal getFundTransferInterest() {
		return fundTransferInterest;
	}

	/**
	 * @param fundTransferInterest the fundTransferInterest to set
	 */
	public void setFundTransferInterest(BigDecimal fundTransferInterest) {
		this.fundTransferInterest = fundTransferInterest;
	}

    /**
     * @return 订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
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
     * @return 转让年化收益率
     * @occurs required
     */
	public BigDecimal getAnnualYield() {
		return annualYield;
	}

	/**
	 * @param annualYield the annualYield to set
	 */
	public void setAnnualYield(BigDecimal annualYield) {
		this.annualYield = annualYield;
	}

    /**
     * @return 转让方手续费
     * @occurs required
     */
	public BigDecimal getTransferorFee() {
		return transferorFee;
	}

	/**
	 * @param transferorFee the transferorFee to set
	 */
	public void setTransferorFee(BigDecimal transferorFee) {
		this.transferorFee = transferorFee;
	}

    /**
     * @return 受让方手续费
     * @occurs required
     */
	public BigDecimal getTransfereeFee() {
		return transfereeFee;
	}

	/**
	 * @param transfereeFee the transfereeFee to set
	 */
	public void setTransfereeFee(BigDecimal transfereeFee) {
		this.transfereeFee = transfereeFee;
	}

    /**
     * @return 实时手续费规则，保存json字串
     * @occurs required
     */
	public String getFeeRule() {
		return feeRule;
	}

	/**
	 * @param feeRule the feeRule to set
	 */
	public void setFeeRule(String feeRule) {
		this.feeRule = feeRule;
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
     * @return 业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
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
     * @return 业务单撤销时间
     * @occurs required
     */
	public Date getCancelTime() {
		return cancelTime;
	}

	/**
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
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
