package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_transferee_order")
public class FundTransfereeOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 受让交易订单号
     */
    @Column(name = "fund_transferee_order_no")
    private String fundTransfereeOrderNo;

    /**
     * 业务订单流水号
     */
    @Column(name = "business_order_item_no")
    private String businessOrderItemNo;

    /**
     * 关联转让交易订单号
     */
    @Column(name = "ref_fund_transferor_order_no")
    private String refFundTransferorOrderNo;

    /**
     * 关联最初转让交易订单号，最初发起转让的交易订单号
     */
    @Column(name = "ref_origin_fund_transferor_order_no")
    private String refOriginFundTransferorOrderNo;

    /**
     * 受让发起人会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 受让发起人资产账户号
     */
    @Column(name = "asset_account_no")
    private String assetAccountNo;

    /**
     * 受让发起人基金交易账号
     */
    @Column(name = "trans_account_no")
    private String transAccountNo;

    /**
     * 转让协议号
     */
    @Column(name = "transfer_agreement_no")
    private String transferAgreementNo;

    /**
     * 二级市场所属产品ID
     */
    @Column(name = "mkt_product_id")
    private Long mktProductId;

    /**
     * 受让价格，即为转让单中的转让定价价格
     */
    @Column(name = "fund_transfer_amount")
    private BigDecimal fundTransferAmount;

    /**
     * 优惠金额
     */
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 支付方式，json表示
     */
    @Column(name = "pay_method")
    private String payMethod;

    /**
     * 支付方式描述
     */
    @Column(name = "pay_method_desc")
    private String payMethodDesc;

    /**
     * 币种
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 库存冻结号
     */
    @Column(name = "frozen_token")
    private String frozenToken;

    /**
     * 交易核心受让交易单流水号
     */
    @Column(name = "transcore_trans_no")
    private String transcoreTransNo;

    /**
     * 交易核心转账交易单流水号
     */
    @Column(name = "transfer_trans_no")
    private String transferTransNo;

    /**
     * 合同流水号, 受让成功后自动生成
     */
    @Column(name = "contract_no")
    private String contractNo;

    /**
     * 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    @Column(name = "biz_channel")
    private Integer bizChannel;

    /**
     * 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
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
     * 业务单支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 业务单交易完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 订单有效期时间
     */
    @Column(name = "expiry_time")
    private Date expiryTime;

    /**
     * 当前状态过期时间
     */
    @Column(name = "status_expiry_time")
    private Date statusExpiryTime;

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
     * 获取受让交易订单号
     *
     * @return fund_transferee_order_no - 受让交易订单号
     */
    public String getFundTransfereeOrderNo() {
        return fundTransfereeOrderNo;
    }

    /**
     * 设置受让交易订单号
     *
     * @param fundTransfereeOrderNo 受让交易订单号
     */
    public void setFundTransfereeOrderNo(String fundTransfereeOrderNo) {
        this.fundTransfereeOrderNo = fundTransfereeOrderNo;
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
     * 获取关联转让交易订单号
     *
     * @return ref_fund_transferor_order_no - 关联转让交易订单号
     */
    public String getRefFundTransferorOrderNo() {
        return refFundTransferorOrderNo;
    }

    /**
     * 设置关联转让交易订单号
     *
     * @param refFundTransferorOrderNo 关联转让交易订单号
     */
    public void setRefFundTransferorOrderNo(String refFundTransferorOrderNo) {
        this.refFundTransferorOrderNo = refFundTransferorOrderNo;
    }

    /**
     * 获取关联最初转让交易订单号，最初发起转让的交易订单号
     *
     * @return ref_origin_fund_transferor_order_no - 关联最初转让交易订单号，最初发起转让的交易订单号
     */
    public String getRefOriginFundTransferorOrderNo() {
        return refOriginFundTransferorOrderNo;
    }

    /**
     * 设置关联最初转让交易订单号，最初发起转让的交易订单号
     *
     * @param refOriginFundTransferorOrderNo 关联最初转让交易订单号，最初发起转让的交易订单号
     */
    public void setRefOriginFundTransferorOrderNo(String refOriginFundTransferorOrderNo) {
        this.refOriginFundTransferorOrderNo = refOriginFundTransferorOrderNo;
    }

    /**
     * 获取受让发起人会员号
     *
     * @return member_no - 受让发起人会员号
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置受让发起人会员号
     *
     * @param memberNo 受让发起人会员号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取受让发起人资产账户号
     *
     * @return asset_account_no - 受让发起人资产账户号
     */
    public String getAssetAccountNo() {
        return assetAccountNo;
    }

    /**
     * 设置受让发起人资产账户号
     *
     * @param assetAccountNo 受让发起人资产账户号
     */
    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }

    /**
     * 获取受让发起人基金交易账号
     *
     * @return trans_account_no - 受让发起人基金交易账号
     */
    public String getTransAccountNo() {
        return transAccountNo;
    }

    /**
     * 设置受让发起人基金交易账号
     *
     * @param transAccountNo 受让发起人基金交易账号
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
     * 获取受让价格，即为转让单中的转让定价价格
     *
     * @return fund_transfer_amount - 受让价格，即为转让单中的转让定价价格
     */
    public BigDecimal getFundTransferAmount() {
        return fundTransferAmount;
    }

    /**
     * 设置受让价格，即为转让单中的转让定价价格
     *
     * @param fundTransferAmount 受让价格，即为转让单中的转让定价价格
     */
    public void setFundTransferAmount(BigDecimal fundTransferAmount) {
        this.fundTransferAmount = fundTransferAmount;
    }

    /**
     * 获取优惠金额
     *
     * @return discount_amount - 优惠金额
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置优惠金额
     *
     * @param discountAmount 优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 获取手续费
     *
     * @return fee - 手续费
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置手续费
     *
     * @param fee 手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获取支付方式，json表示
     *
     * @return pay_method - 支付方式，json表示
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * 设置支付方式，json表示
     *
     * @param payMethod 支付方式，json表示
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取支付方式描述
     *
     * @return pay_method_desc - 支付方式描述
     */
    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    /**
     * 设置支付方式描述
     *
     * @param payMethodDesc 支付方式描述
     */
    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

    /**
     * 获取币种
     *
     * @return currency_type - 币种
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置币种
     *
     * @param currencyType 币种
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * 获取库存冻结号
     *
     * @return frozen_token - 库存冻结号
     */
    public String getFrozenToken() {
        return frozenToken;
    }

    /**
     * 设置库存冻结号
     *
     * @param frozenToken 库存冻结号
     */
    public void setFrozenToken(String frozenToken) {
        this.frozenToken = frozenToken;
    }

    /**
     * 获取交易核心受让交易单流水号
     *
     * @return transcore_trans_no - 交易核心受让交易单流水号
     */
    public String getTranscoreTransNo() {
        return transcoreTransNo;
    }

    /**
     * 设置交易核心受让交易单流水号
     *
     * @param transcoreTransNo 交易核心受让交易单流水号
     */
    public void setTranscoreTransNo(String transcoreTransNo) {
        this.transcoreTransNo = transcoreTransNo;
    }

    /**
     * 获取交易核心转账交易单流水号
     *
     * @return transfer_trans_no - 交易核心转账交易单流水号
     */
    public String getTransferTransNo() {
        return transferTransNo;
    }

    /**
     * 设置交易核心转账交易单流水号
     *
     * @param transferTransNo 交易核心转账交易单流水号
     */
    public void setTransferTransNo(String transferTransNo) {
        this.transferTransNo = transferTransNo;
    }

    /**
     * 获取合同流水号, 受让成功后自动生成
     *
     * @return contract_no - 合同流水号, 受让成功后自动生成
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置合同流水号, 受让成功后自动生成
     *
     * @param contractNo 合同流水号, 受让成功后自动生成
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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
     * 获取业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
     *
     * @return status - 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
     *
     * @param status 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;
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
     * 获取业务单支付时间
     *
     * @return pay_time - 业务单支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置业务单支付时间
     *
     * @param payTime 业务单支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取业务单交易完成时间
     *
     * @return finish_time - 业务单交易完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置业务单交易完成时间
     *
     * @param finishTime 业务单交易完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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
     * 获取当前状态过期时间
     *
     * @return status_expiry_time - 当前状态过期时间
     */
    public Date getStatusExpiryTime() {
        return statusExpiryTime;
    }

    /**
     * 设置当前状态过期时间
     *
     * @param statusExpiryTime 当前状态过期时间
     */
    public void setStatusExpiryTime(Date statusExpiryTime) {
        this.statusExpiryTime = statusExpiryTime;
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