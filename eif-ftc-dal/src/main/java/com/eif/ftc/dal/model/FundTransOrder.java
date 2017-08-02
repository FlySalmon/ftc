package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_trans_order")
public class FundTransOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金交易业务单号
     */
    @Column(name = "fund_trans_order_no")
    private String fundTransOrderNo;

    /**
     * 会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 资产账户号
     */
    @Column(name = "asset_account_no")
    private String assetAccountNo;

    /**
     * 对应TA投资人基金账号
     */
    @Column(name = "ref_fund_account_no")
    private String refFundAccountNo;

    /**
     * 投资人基金交易账号
     */
    @Column(name = "trans_account_no")
    private String transAccountNo;

    /**
     * 当此单为组合赎回的子单时对应的母单
     */
    @Column(name = "parent_fund_trans_order_no")
    private String parentFundTransOrderNo;

    /**
     * 当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     */
    @Column(name = "ref_fund_trans_order_no")
    private String refFundTransOrderNo;

    /**
     * 交易核心交易单流水号
     */
    @Column(name = "transcore_trans_no")
    private String transcoreTransNo;

    /**
     * 是否为补单
     */
    @Column(name = "is_adjusted")
    private Boolean isAdjusted;

    /**
     * 0 - 正常; 1 - 充值后触发
     */
    @Column(name = "trigger_reason")
    private Integer triggerReason;

    /**
     * 业务订单项流水号
     */
    @Column(name = "business_order_item_no")
    private String businessOrderItemNo;

    /**
     * 用户订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 防止幂等, 全局跟踪
     */
    @Column(name = "tracking_no")
    private String trackingNo;

    /**
     * 用户产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 商户号
     */
    @Column(name = "mer_member_no")
    private String merMemberNo;

    /**
     * 合同流水号, 申购后自动生成
     */
    @Column(name = "contract_no")
    private String contractNo;

    /**
     * 基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     */
    @Column(name = "fund_trans_type")
    private Integer fundTransType;

    /**
     * 交易金额
     */
    @Column(name = "fund_trans_amount")
    private BigDecimal fundTransAmount;

    /**
     * 交易份额
     */
    @Column(name = "fund_trans_shares")
    private BigDecimal fundTransShares;

    /**
     * 优惠金额
     */
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    /**
     * 币种
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * ta基金代码
     */
    @Column(name = "ref_fund_code")
    private String refFundCode;

    /**
     * 外部基金分组编号
     */
    @Column(name = "ref_fund_sub_code")
    private String refFundSubCode;

    /**
     * ta基金业务代码
     */
    @Column(name = "ref_fund_business_code")
    private String refFundBusinessCode;

    /**
     * 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    @Column(name = "biz_channel")
    private Integer bizChannel;

    /**
     * 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     */
    private Integer status;

    /**
     * 基金提交方式：1 - 实时; 2 - 文件
     */
    @Column(name = "fund_interact_type")
    private Integer fundInteractType;

    /**
     * 库存冻结号
     */
    @Column(name = "frozen_code")
    private String frozenCode;

    /**
     * 总有效期
     */
    @Column(name = "total_expiry_time")
    private Date totalExpiryTime;

    /**
     * 当前状态过期时间
     */
    @Column(name = "cur_status_expiry_time")
    private Date curStatusExpiryTime;

    /**
     * ta申请单号, 可关联请求，确认，结果
     */
    @Column(name = "ref_app_sheet_serial_no")
    private String refAppSheetSerialNo;

    /**
     * reserved
     */
    @Column(name = "ext_info_order_no")
    private String extInfoOrderNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务扩展字段，保存json字串
     */
    @Column(name = "ext_field")
    private String extField;

    /**
     * 交易时间
     */
    @Column(name = "trans_time")
    private Date transTime;

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
     * 业务单整体完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 退款时间，退款发生在已经下单支付但是TA还未处理的时候
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 业务单撤销时间，撤销发生在已经下单但是还未支付的时候
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 可退款金额
     */
    @Column(name = "refund_amount_limit")
    private BigDecimal refundAmountLimit;

    /**
     * 已退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 已退款手续费
     */
    @Column(name = "refund_fee_amount")
    private BigDecimal refundFeeAmount;

    /**
     * 付款方手续费
     */
    @Column(name = "to_fee")
    private BigDecimal toFee;

    /**
     * 收款方手续费
     */
    @Column(name = "from_fee")
    private BigDecimal fromFee;

    /**
     * reserved
     */
    @Column(name = "fee_method")
    private Integer feeMethod;

    /**
     * 操作人号
     */
    @Column(name = "operator_no")
    private String operatorNo;

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
     * 获取基金交易业务单号
     *
     * @return fund_trans_order_no - 基金交易业务单号
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    /**
     * 设置基金交易业务单号
     *
     * @param fundTransOrderNo 基金交易业务单号
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    /**
     * 获取会员号
     *
     * @return member_no - 会员号
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置会员号
     *
     * @param memberNo 会员号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取资产账户号
     *
     * @return asset_account_no - 资产账户号
     */
    public String getAssetAccountNo() {
        return assetAccountNo;
    }

    /**
     * 设置资产账户号
     *
     * @param assetAccountNo 资产账户号
     */
    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }

    /**
     * 获取对应TA投资人基金账号
     *
     * @return ref_fund_account_no - 对应TA投资人基金账号
     */
    public String getRefFundAccountNo() {
        return refFundAccountNo;
    }

    /**
     * 设置对应TA投资人基金账号
     *
     * @param refFundAccountNo 对应TA投资人基金账号
     */
    public void setRefFundAccountNo(String refFundAccountNo) {
        this.refFundAccountNo = refFundAccountNo;
    }

    /**
     * 获取投资人基金交易账号
     *
     * @return trans_account_no - 投资人基金交易账号
     */
    public String getTransAccountNo() {
        return transAccountNo;
    }

    /**
     * 设置投资人基金交易账号
     *
     * @param transAccountNo 投资人基金交易账号
     */
    public void setTransAccountNo(String transAccountNo) {
        this.transAccountNo = transAccountNo;
    }

    /**
     * 获取当此单为组合赎回的子单时对应的母单
     *
     * @return parent_fund_trans_order_no - 当此单为组合赎回的子单时对应的母单
     */
    public String getParentFundTransOrderNo() {
        return parentFundTransOrderNo;
    }

    /**
     * 设置当此单为组合赎回的子单时对应的母单
     *
     * @param parentFundTransOrderNo 当此单为组合赎回的子单时对应的母单
     */
    public void setParentFundTransOrderNo(String parentFundTransOrderNo) {
        this.parentFundTransOrderNo = parentFundTransOrderNo;
    }

    /**
     * 获取当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     *
     * @return ref_fund_trans_order_no - 当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     */
    public String getRefFundTransOrderNo() {
        return refFundTransOrderNo;
    }

    /**
     * 设置当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     *
     * @param refFundTransOrderNo 当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     */
    public void setRefFundTransOrderNo(String refFundTransOrderNo) {
        this.refFundTransOrderNo = refFundTransOrderNo;
    }

    /**
     * 获取交易核心交易单流水号
     *
     * @return transcore_trans_no - 交易核心交易单流水号
     */
    public String getTranscoreTransNo() {
        return transcoreTransNo;
    }

    /**
     * 设置交易核心交易单流水号
     *
     * @param transcoreTransNo 交易核心交易单流水号
     */
    public void setTranscoreTransNo(String transcoreTransNo) {
        this.transcoreTransNo = transcoreTransNo;
    }

    /**
     * 获取是否为补单
     *
     * @return is_adjusted - 是否为补单
     */
    public Boolean getIsAdjusted() {
        return isAdjusted;
    }

    /**
     * 设置是否为补单
     *
     * @param isAdjusted 是否为补单
     */
    public void setIsAdjusted(Boolean isAdjusted) {
        this.isAdjusted = isAdjusted;
    }

    /**
     * 获取0 - 正常; 1 - 充值后触发
     *
     * @return trigger_reason - 0 - 正常; 1 - 充值后触发
     */
    public Integer getTriggerReason() {
        return triggerReason;
    }

    /**
     * 设置0 - 正常; 1 - 充值后触发
     *
     * @param triggerReason 0 - 正常; 1 - 充值后触发
     */
    public void setTriggerReason(Integer triggerReason) {
        this.triggerReason = triggerReason;
    }

    /**
     * 获取业务订单项流水号
     *
     * @return business_order_item_no - 业务订单项流水号
     */
    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    /**
     * 设置业务订单项流水号
     *
     * @param businessOrderItemNo 业务订单项流水号
     */
    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }

    /**
     * 获取用户订单号
     *
     * @return order_no - 用户订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置用户订单号
     *
     * @param orderNo 用户订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取用户产品ID
     *
     * @return product_id - 用户产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置用户产品ID
     *
     * @param productId 用户产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取商户号
     *
     * @return mer_member_no - 商户号
     */
    public String getMerMemberNo() {
        return merMemberNo;
    }

    /**
     * 设置商户号
     *
     * @param merMemberNo 商户号
     */
    public void setMerMemberNo(String merMemberNo) {
        this.merMemberNo = merMemberNo;
    }

    /**
     * 获取合同流水号, 申购后自动生成
     *
     * @return contract_no - 合同流水号, 申购后自动生成
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置合同流水号, 申购后自动生成
     *
     * @param contractNo 合同流水号, 申购后自动生成
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * 获取基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     *
     * @return fund_trans_type - 基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     */
    public Integer getFundTransType() {
        return fundTransType;
    }

    /**
     * 设置基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     *
     * @param fundTransType 基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     */
    public void setFundTransType(Integer fundTransType) {
        this.fundTransType = fundTransType;
    }

    /**
     * 获取交易金额
     *
     * @return fund_trans_amount - 交易金额
     */
    public BigDecimal getFundTransAmount() {
        return fundTransAmount;
    }

    /**
     * 设置交易金额
     *
     * @param fundTransAmount 交易金额
     */
    public void setFundTransAmount(BigDecimal fundTransAmount) {
        this.fundTransAmount = fundTransAmount;
    }

    /**
     * 获取交易份额
     *
     * @return fund_trans_shares - 交易份额
     */
    public BigDecimal getFundTransShares() {
        return fundTransShares;
    }

    /**
     * 设置交易份额
     *
     * @param fundTransShares 交易份额
     */
    public void setFundTransShares(BigDecimal fundTransShares) {
        this.fundTransShares = fundTransShares;
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
     * 获取ta基金代码
     *
     * @return ref_fund_code - ta基金代码
     */
    public String getRefFundCode() {
        return refFundCode;
    }

    /**
     * 设置ta基金代码
     *
     * @param refFundCode ta基金代码
     */
    public void setRefFundCode(String refFundCode) {
        this.refFundCode = refFundCode;
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
     * 获取ta基金业务代码
     *
     * @return ref_fund_business_code - ta基金业务代码
     */
    public String getRefFundBusinessCode() {
        return refFundBusinessCode;
    }

    /**
     * 设置ta基金业务代码
     *
     * @param refFundBusinessCode ta基金业务代码
     */
    public void setRefFundBusinessCode(String refFundBusinessCode) {
        this.refFundBusinessCode = refFundBusinessCode;
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
     * 获取业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     *
     * @return status - 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     *
     * @param status 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取基金提交方式：1 - 实时; 2 - 文件
     *
     * @return fund_interact_type - 基金提交方式：1 - 实时; 2 - 文件
     */
    public Integer getFundInteractType() {
        return fundInteractType;
    }

    /**
     * 设置基金提交方式：1 - 实时; 2 - 文件
     *
     * @param fundInteractType 基金提交方式：1 - 实时; 2 - 文件
     */
    public void setFundInteractType(Integer fundInteractType) {
        this.fundInteractType = fundInteractType;
    }

    /**
     * 获取库存冻结号
     *
     * @return frozen_code - 库存冻结号
     */
    public String getFrozenCode() {
        return frozenCode;
    }

    /**
     * 设置库存冻结号
     *
     * @param frozenCode 库存冻结号
     */
    public void setFrozenCode(String frozenCode) {
        this.frozenCode = frozenCode;
    }

    /**
     * 获取总有效期
     *
     * @return total_expiry_time - 总有效期
     */
    public Date getTotalExpiryTime() {
        return totalExpiryTime;
    }

    /**
     * 设置总有效期
     *
     * @param totalExpiryTime 总有效期
     */
    public void setTotalExpiryTime(Date totalExpiryTime) {
        this.totalExpiryTime = totalExpiryTime;
    }

    /**
     * 获取当前状态过期时间
     *
     * @return cur_status_expiry_time - 当前状态过期时间
     */
    public Date getCurStatusExpiryTime() {
        return curStatusExpiryTime;
    }

    /**
     * 设置当前状态过期时间
     *
     * @param curStatusExpiryTime 当前状态过期时间
     */
    public void setCurStatusExpiryTime(Date curStatusExpiryTime) {
        this.curStatusExpiryTime = curStatusExpiryTime;
    }

    /**
     * 获取ta申请单号, 可关联请求，确认，结果
     *
     * @return ref_app_sheet_serial_no - ta申请单号, 可关联请求，确认，结果
     */
    public String getRefAppSheetSerialNo() {
        return refAppSheetSerialNo;
    }

    /**
     * 设置ta申请单号, 可关联请求，确认，结果
     *
     * @param refAppSheetSerialNo ta申请单号, 可关联请求，确认，结果
     */
    public void setRefAppSheetSerialNo(String refAppSheetSerialNo) {
        this.refAppSheetSerialNo = refAppSheetSerialNo;
    }

    /**
     * 获取reserved
     *
     * @return ext_info_order_no - reserved
     */
    public String getExtInfoOrderNo() {
        return extInfoOrderNo;
    }

    /**
     * 设置reserved
     *
     * @param extInfoOrderNo reserved
     */
    public void setExtInfoOrderNo(String extInfoOrderNo) {
        this.extInfoOrderNo = extInfoOrderNo;
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
     * 获取交易时间
     *
     * @return trans_time - 交易时间
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * 设置交易时间
     *
     * @param transTime 交易时间
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
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
     * 获取退款时间，退款发生在已经下单支付但是TA还未处理的时候
     *
     * @return refund_time - 退款时间，退款发生在已经下单支付但是TA还未处理的时候
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 设置退款时间，退款发生在已经下单支付但是TA还未处理的时候
     *
     * @param refundTime 退款时间，退款发生在已经下单支付但是TA还未处理的时候
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 获取业务单撤销时间，撤销发生在已经下单但是还未支付的时候
     *
     * @return cancel_time - 业务单撤销时间，撤销发生在已经下单但是还未支付的时候
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置业务单撤销时间，撤销发生在已经下单但是还未支付的时候
     *
     * @param cancelTime 业务单撤销时间，撤销发生在已经下单但是还未支付的时候
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取可退款金额
     *
     * @return refund_amount_limit - 可退款金额
     */
    public BigDecimal getRefundAmountLimit() {
        return refundAmountLimit;
    }

    /**
     * 设置可退款金额
     *
     * @param refundAmountLimit 可退款金额
     */
    public void setRefundAmountLimit(BigDecimal refundAmountLimit) {
        this.refundAmountLimit = refundAmountLimit;
    }

    /**
     * 获取已退款金额
     *
     * @return refund_amount - 已退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置已退款金额
     *
     * @param refundAmount 已退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取已退款手续费
     *
     * @return refund_fee_amount - 已退款手续费
     */
    public BigDecimal getRefundFeeAmount() {
        return refundFeeAmount;
    }

    /**
     * 设置已退款手续费
     *
     * @param refundFeeAmount 已退款手续费
     */
    public void setRefundFeeAmount(BigDecimal refundFeeAmount) {
        this.refundFeeAmount = refundFeeAmount;
    }

    /**
     * 获取付款方手续费
     *
     * @return to_fee - 付款方手续费
     */
    public BigDecimal getToFee() {
        return toFee;
    }

    /**
     * 设置付款方手续费
     *
     * @param toFee 付款方手续费
     */
    public void setToFee(BigDecimal toFee) {
        this.toFee = toFee;
    }

    /**
     * 获取收款方手续费
     *
     * @return from_fee - 收款方手续费
     */
    public BigDecimal getFromFee() {
        return fromFee;
    }

    /**
     * 设置收款方手续费
     *
     * @param fromFee 收款方手续费
     */
    public void setFromFee(BigDecimal fromFee) {
        this.fromFee = fromFee;
    }

    /**
     * 获取reserved
     *
     * @return fee_method - reserved
     */
    public Integer getFeeMethod() {
        return feeMethod;
    }

    /**
     * 设置reserved
     *
     * @param feeMethod reserved
     */
    public void setFeeMethod(Integer feeMethod) {
        this.feeMethod = feeMethod;
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
}