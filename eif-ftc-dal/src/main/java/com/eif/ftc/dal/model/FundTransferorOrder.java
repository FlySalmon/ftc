package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_transferor_order")
public class FundTransferorOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     */
    @Column(name = "transfer_mode")
    private Integer transferMode;

    /**
     * 转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     */
    @Column(name = "transfer_transaction_mode")
    private Integer transferTransactionMode;

    /**
     * 转让交易订单号
     */
    @Column(name = "fund_transferor_order_no")
    private String fundTransferorOrderNo;

    /**
     * 业务订单流水号
     */
    @Column(name = "business_order_item_no")
    private String businessOrderItemNo;

    /**
     * 转让发起人会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 发起人资产账户号
     */
    @Column(name = "asset_account_no")
    private String assetAccountNo;

    /**
     * 发起人基金交易账号
     */
    @Column(name = "trans_account_no")
    private String transAccountNo;

    /**
     * 转让协议号
     */
    @Column(name = "transfer_agreement_no")
    private String transferAgreementNo;

    /**
     * 资产份额所属产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 外部基金分组编号
     */
    @Column(name = "ref_fund_sub_code")
    private String refFundSubCode;

    /**
     * 二级市场所属产品ID
     */
    @Column(name = "mkt_product_id")
    private Long mktProductId;

    /**
     * 原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
     */
    @Column(name = "original_asset_total_value")
    private BigDecimal originalAssetTotalValue;

    /**
     * 原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
     */
    @Column(name = "original_asset_surplus_value")
    private BigDecimal originalAssetSurplusValue;

    /**
     * 转让金额，即转让定价价格
     */
    @Column(name = "fund_transfer_amount")
    private BigDecimal fundTransferAmount;

    /**
     * 转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     */
    @Column(name = "fund_transfer_principal")
    private BigDecimal fundTransferPrincipal;

    /**
     * 转让价格中的利息金额，转让价格-转让价格中本金金额
     */
    @Column(name = "fund_transfer_interest")
    private BigDecimal fundTransferInterest;

    /**
     * 订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
     */
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    /**
     * 转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）
     */
    @Column(name = "annual_yield")
    private BigDecimal annualYield;

    /**
     * 转让方手续费
     */
    @Column(name = "transferor_fee")
    private BigDecimal transferorFee;

    /**
     * 受让方手续费
     */
    @Column(name = "transferee_fee")
    private BigDecimal transfereeFee;

    /**
     * 实时手续费规则，保存json字串
     */
    @Column(name = "fee_rule")
    private String feeRule;

    /**
     * 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    @Column(name = "biz_channel")
    private Integer bizChannel;

    /**
     * 业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
     */
    private Integer status;

    /**
     * 业务扩展字段，保存json字串
     */
    @Column(name = "ext_field")
    private String extField;

    /**
     * 防止幂等, 全局跟踪
     */
    @Column(name = "tracking_no")
    private String trackingNo;

    /**
     * 业务单整体完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 业务单撤销时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 订单有效期时间
     */
    @Column(name = "expiry_time")
    private Date expiryTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人号
     */
    @Column(name = "operator_no")
    private String operatorNo;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     *
     * @return transfer_mode - 转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     */
    public Integer getTransferMode() {
        return transferMode;
    }

    /**
     * 设置转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     *
     * @param transferMode 转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）
     */
    public void setTransferMode(Integer transferMode) {
        this.transferMode = transferMode;
    }

    /**
     * 获取转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     *
     * @return transfer_transaction_mode - 转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     */
    public Integer getTransferTransactionMode() {
        return transferTransactionMode;
    }

    /**
     * 设置转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     *
     * @param transferTransactionMode 转让交易模式：1 - 一口价模式; 2 - 竞价模式;
     */
    public void setTransferTransactionMode(Integer transferTransactionMode) {
        this.transferTransactionMode = transferTransactionMode;
    }

    /**
     * 获取转让交易订单号
     *
     * @return fund_transferor_order_no - 转让交易订单号
     */
    public String getFundTransferorOrderNo() {
        return fundTransferorOrderNo;
    }

    /**
     * 设置转让交易订单号
     *
     * @param fundTransferorOrderNo 转让交易订单号
     */
    public void setFundTransferorOrderNo(String fundTransferorOrderNo) {
        this.fundTransferorOrderNo = fundTransferorOrderNo;
    }

    /**
     * 获取业务订单流水号
     *
     * @return business_order_item_no - 业务订单流水号
     */
    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    /**
     * 设置业务订单流水号
     *
     * @param businessOrderItemNo 业务订单流水号
     */
    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }

    /**
     * 获取转让发起人会员号
     *
     * @return member_no - 转让发起人会员号
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置转让发起人会员号
     *
     * @param memberNo 转让发起人会员号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取发起人资产账户号
     *
     * @return asset_account_no - 发起人资产账户号
     */
    public String getAssetAccountNo() {
        return assetAccountNo;
    }

    /**
     * 设置发起人资产账户号
     *
     * @param assetAccountNo 发起人资产账户号
     */
    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }

    /**
     * 获取发起人基金交易账号
     *
     * @return trans_account_no - 发起人基金交易账号
     */
    public String getTransAccountNo() {
        return transAccountNo;
    }

    /**
     * 设置发起人基金交易账号
     *
     * @param transAccountNo 发起人基金交易账号
     */
    public void setTransAccountNo(String transAccountNo) {
        this.transAccountNo = transAccountNo;
    }

    /**
     * 获取转让协议号
     *
     * @return transfer_agreement_no - 转让协议号
     */
    public String getTransferAgreementNo() {
        return transferAgreementNo;
    }

    /**
     * 设置转让协议号
     *
     * @param transferAgreementNo 转让协议号
     */
    public void setTransferAgreementNo(String transferAgreementNo) {
        this.transferAgreementNo = transferAgreementNo;
    }

    /**
     * 获取资产份额所属产品ID
     *
     * @return product_id - 资产份额所属产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置资产份额所属产品ID
     *
     * @param productId 资产份额所属产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取外部基金分组编号
     *
     * @return ref_fund_sub_code - 外部基金分组编号
     */
    public String getRefFundSubCode() {
        return refFundSubCode;
    }

    /**
     * 设置外部基金分组编号
     *
     * @param refFundSubCode 外部基金分组编号
     */
    public void setRefFundSubCode(String refFundSubCode) {
        this.refFundSubCode = refFundSubCode;
    }

    /**
     * 获取二级市场所属产品ID
     *
     * @return mkt_product_id - 二级市场所属产品ID
     */
    public Long getMktProductId() {
        return mktProductId;
    }

    /**
     * 设置二级市场所属产品ID
     *
     * @param mktProductId 二级市场所属产品ID
     */
    public void setMktProductId(Long mktProductId) {
        this.mktProductId = mktProductId;
    }

    /**
     * 获取原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
     *
     * @return original_asset_total_value - 原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
     */
    public BigDecimal getOriginalAssetTotalValue() {
        return originalAssetTotalValue;
    }

    /**
     * 设置原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
     *
     * @param originalAssetTotalValue 原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）
     */
    public void setOriginalAssetTotalValue(BigDecimal originalAssetTotalValue) {
        this.originalAssetTotalValue = originalAssetTotalValue;
    }

    /**
     * 获取原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
     *
     * @return original_asset_surplus_value - 原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
     */
    public BigDecimal getOriginalAssetSurplusValue() {
        return originalAssetSurplusValue;
    }

    /**
     * 设置原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
     *
     * @param originalAssetSurplusValue 原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）
     */
    public void setOriginalAssetSurplusValue(BigDecimal originalAssetSurplusValue) {
        this.originalAssetSurplusValue = originalAssetSurplusValue;
    }

    /**
     * 获取转让金额，即转让定价价格
     *
     * @return fund_transfer_amount - 转让金额，即转让定价价格
     */
    public BigDecimal getFundTransferAmount() {
        return fundTransferAmount;
    }

    /**
     * 设置转让金额，即转让定价价格
     *
     * @param fundTransferAmount 转让金额，即转让定价价格
     */
    public void setFundTransferAmount(BigDecimal fundTransferAmount) {
        this.fundTransferAmount = fundTransferAmount;
    }

    /**
     * 获取转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     *
     * @return fund_transfer_principal - 转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     */
    public BigDecimal getFundTransferPrincipal() {
        return fundTransferPrincipal;
    }

    /**
     * 设置转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     *
     * @param fundTransferPrincipal 转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格
     */
    public void setFundTransferPrincipal(BigDecimal fundTransferPrincipal) {
        this.fundTransferPrincipal = fundTransferPrincipal;
    }

    /**
     * 获取转让价格中的利息金额，转让价格-转让价格中本金金额
     *
     * @return fund_transfer_interest - 转让价格中的利息金额，转让价格-转让价格中本金金额
     */
    public BigDecimal getFundTransferInterest() {
        return fundTransferInterest;
    }

    /**
     * 设置转让价格中的利息金额，转让价格-转让价格中本金金额
     *
     * @param fundTransferInterest 转让价格中的利息金额，转让价格-转让价格中本金金额
     */
    public void setFundTransferInterest(BigDecimal fundTransferInterest) {
        this.fundTransferInterest = fundTransferInterest;
    }

    /**
     * 获取订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
     *
     * @return discount_amount - 订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
     *
     * @param discountAmount 订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 获取转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）
     *
     * @return annual_yield - 转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）
     */
    public BigDecimal getAnnualYield() {
        return annualYield;
    }

    /**
     * 设置转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）
     *
     * @param annualYield 转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）
     */
    public void setAnnualYield(BigDecimal annualYield) {
        this.annualYield = annualYield;
    }

    /**
     * 获取转让方手续费
     *
     * @return transferor_fee - 转让方手续费
     */
    public BigDecimal getTransferorFee() {
        return transferorFee;
    }

    /**
     * 设置转让方手续费
     *
     * @param transferorFee 转让方手续费
     */
    public void setTransferorFee(BigDecimal transferorFee) {
        this.transferorFee = transferorFee;
    }

    /**
     * 获取受让方手续费
     *
     * @return transferee_fee - 受让方手续费
     */
    public BigDecimal getTransfereeFee() {
        return transfereeFee;
    }

    /**
     * 设置受让方手续费
     *
     * @param transfereeFee 受让方手续费
     */
    public void setTransfereeFee(BigDecimal transfereeFee) {
        this.transfereeFee = transfereeFee;
    }

    /**
     * 获取实时手续费规则，保存json字串
     *
     * @return fee_rule - 实时手续费规则，保存json字串
     */
    public String getFeeRule() {
        return feeRule;
    }

    /**
     * 设置实时手续费规则，保存json字串
     *
     * @param feeRule 实时手续费规则，保存json字串
     */
    public void setFeeRule(String feeRule) {
        this.feeRule = feeRule;
    }

    /**
     * 获取渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     *
     * @return biz_channel - 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    public Integer getBizChannel() {
        return bizChannel;
    }

    /**
     * 设置渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     *
     * @param bizChannel 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    public void setBizChannel(Integer bizChannel) {
        this.bizChannel = bizChannel;
    }

    /**
     * 获取业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
     *
     * @return status - 业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
     *
     * @param status 业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取业务扩展字段，保存json字串
     *
     * @return ext_field - 业务扩展字段，保存json字串
     */
    public String getExtField() {
        return extField;
    }

    /**
     * 设置业务扩展字段，保存json字串
     *
     * @param extField 业务扩展字段，保存json字串
     */
    public void setExtField(String extField) {
        this.extField = extField;
    }

    /**
     * 获取防止幂等, 全局跟踪
     *
     * @return tracking_no - 防止幂等, 全局跟踪
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * 设置防止幂等, 全局跟踪
     *
     * @param trackingNo 防止幂等, 全局跟踪
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    /**
     * 获取业务单整体完成时间
     *
     * @return finish_time - 业务单整体完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置业务单整体完成时间
     *
     * @param finishTime 业务单整体完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取业务单撤销时间
     *
     * @return cancel_time - 业务单撤销时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置业务单撤销时间
     *
     * @param cancelTime 业务单撤销时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取订单有效期时间
     *
     * @return expiry_time - 订单有效期时间
     */
    public Date getExpiryTime() {
        return expiryTime;
    }

    /**
     * 设置订单有效期时间
     *
     * @param expiryTime 订单有效期时间
     */
    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取操作人号
     *
     * @return operator_no - 操作人号
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * 设置操作人号
     *
     * @param operatorNo 操作人号
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }
}