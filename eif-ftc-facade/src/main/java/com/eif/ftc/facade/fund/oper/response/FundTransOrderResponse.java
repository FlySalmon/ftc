package com.eif.ftc.facade.fund.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

import java.math.BigDecimal;
import java.util.Date;

public class FundTransOrderResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

    private Long id;

    private String fundTransOrderNo;

    private String memberNo;

    private String assetAccountNo;

    private String refFundAccountNo;

    private String transAccountNo;

    private String parentFundTransOrderNo;

    private String refFundTransOrderNo;

    private String transcoreTransNo;

    private Boolean isAdjusted;

    private Integer triggerReason;

    private String businessOrderItemNo;

    private String orderNo;

    private String trackingNo;

    private Long productId;

    private String merMemberNo;

    private String contractNo;

    private Integer fundTransType;

    private BigDecimal fundTransAmount;

    private BigDecimal fundTransShares;

    private BigDecimal discountAmount;

    private String currencyType;

    private String refFundCode;

    private String refFundSubCode;

    private String refFundBusinessCode;

    private Integer bizChannel;

    private Integer status;

    private Integer fundInteractType;

    private String payMethodDesc;

    private String frozenCode;

    private Date totalExpiryTime;

    private Date curStatusExpiryTime;

    private String refAppSheetSerialNo;

    private String extInfoOrderNo;

    private String remark;

    private String extField;

    private Date transTime;

    private Date createTime;

    private Date updateTime;

    private Date finishTime;

    private Date refundTime;

    private Date cancelTime;

    private BigDecimal refundAmountLimit;

    private BigDecimal refundAmount;

    private BigDecimal refundFeeAmount;

    private BigDecimal toFee;

    private BigDecimal fromFee;

    private Integer feeMethod;

    private String operatorNo;

    private String payMethod;

    private String memberName;

    private String productName;


    /**
     * @return 产品名称
     * @occurs required
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return 用户名
     * @occurs required
     */
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * @return 主键
     * @occurs required
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 基金交易业务单号
     * @occurs required
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    /**
     * @return 会员号
     * @occurs required
     */
    public String getMemberNo() {
        return memberNo;
    }

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

    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }

    /**
     * @return 投资人TA交易号
     * @occurs required
     */
    public String getRefFundAccountNo() {
        return refFundAccountNo;
    }

    public void setRefFundAccountNo(String refFundAccountNo) {
        this.refFundAccountNo = refFundAccountNo;
    }

    /**
     * @return 投资人基金交易账号
     * @occurs required
     */
    public String getTransAccountNo() {
        return transAccountNo;
    }

    public void setTransAccountNo(String transAccountNo) {
        this.transAccountNo = transAccountNo;
    }

    /**
     * @return 组合赎回母单号
     * @occurs optional
     */
    public String getParentFundTransOrderNo() {
        return parentFundTransOrderNo;
    }

    public void setParentFundTransOrderNo(String parentFundTransOrderNo) {
        this.parentFundTransOrderNo = parentFundTransOrderNo;
    }

     /**
     * @return 当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;
     * @occurs optional
     */
    public String getRefFundTransOrderNo() {
        return refFundTransOrderNo;
    }

    public void setRefFundTransOrderNo(String refFundTransOrderNo) {
        this.refFundTransOrderNo = refFundTransOrderNo;
    }

    /**
     * @return 交易核心交易单流水号
     * @occurs required
     */
    public String getTranscoreTransNo() {
        return transcoreTransNo;
    }

    public void setTranscoreTransNo(String transcoreTransNo) {
        this.transcoreTransNo = transcoreTransNo;
    }

    /**
     * @return 是否补单
     * @occurs required
     */
    public Boolean getIsAdjusted() {
        return isAdjusted;
    }

    public void setIsAdjusted(Boolean isAdjusted) {
        this.isAdjusted = isAdjusted;
    }

    /**
     * @return 触发原因 0 - 正常; 1 - 充值后触发
     * @occurs required
     */
    public Integer getTriggerReason() {
        return triggerReason;
    }

    public void setTriggerReason(Integer triggerReason) {
        this.triggerReason = triggerReason;
    }

    /**
     * @return 业务订单项流水号
     * @occurs required
     */
    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }

    /**
     * @return 业务单号
     * @occurs required
     */
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return 幂等键
     * @occurs required
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    /**
     * @return 用户产品ID
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return 商户号
     * @occurs required
     */
    public String getMerMemberNo() {
        return merMemberNo;
    }

    public void setMerMemberNo(String merMemberNo) {
        this.merMemberNo = merMemberNo;
    }

    /**
     * @return 合同流水号
     * @occurs required
     */
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }


    /**
     * @return 基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回
     * @occurs required
     */
    public Integer getFundTransType() {
        return fundTransType;
    }

    public void setFundTransType(Integer fundTransType) {
        this.fundTransType = fundTransType;
    }

    /**
     * @return 交易金额
     * @occurs required
     */
    public BigDecimal getFundTransAmount() {
        return fundTransAmount;
    }

    public void setFundTransAmount(BigDecimal fundTransAmount) {
        this.fundTransAmount = fundTransAmount;
    }

    /**
     * @return 交易份额
     * @occurs required
     */
    public BigDecimal getFundTransShares() {
        return fundTransShares;
    }

    public void setFundTransShares(BigDecimal fundTransShares) {
        this.fundTransShares = fundTransShares;
    }

    /**
     * @return 优惠金额
     * @occurs required
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return 币种
     * @occurs required
     */
    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * @return TA基金代码
     * @occurs required
     */
    public String getRefFundCode() {
        return refFundCode;
    }

    public void setRefFundCode(String refFundCode) {
        this.refFundCode = refFundCode;
    }

    /**
     * @return 外部基金分组编号
     * @occurs required
     */
    public String getRefFundSubCode() {
        return refFundSubCode;
    }

    public void setRefFundSubCode(String refFundSubCode) {
        this.refFundSubCode = refFundSubCode;
    }

    /**
     * @return TA基金业务代码
     * @occurs required
     */
    public String getRefFundBusinessCode() {
        return refFundBusinessCode;
    }

    public void setRefFundBusinessCode(String refFundBusinessCode) {
        this.refFundBusinessCode = refFundBusinessCode;
    }

     /**
     * @return 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     * @occurs required
     */
    public Integer getBizChannel() {
        return bizChannel;
    }

    public void setBizChannel(Integer bizChannel) {
        this.bizChannel = bizChannel;
    }

    /**
     * @return 业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败
     * @occurs required
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 基金提交方式：1 - 实时; 2 - 文件
     * @occurs required
     */
    public Integer getFundInteractType() {
        return fundInteractType;
    }


    public void setFundInteractType(Integer fundInteractType) {
        this.fundInteractType = fundInteractType;
    }

    /**
     * @return 支付方式描述
     * @occurs required
     */
    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

    /**
     * @return 库存冻结号
     * @occurs required
     */
    public String getFrozenCode() {
        return frozenCode;
    }

    public void setFrozenCode(String frozenCode) {
        this.frozenCode = frozenCode;
    }

    /**
     * @return 单据总有效期
     * @occurs required
     */
    public Date getTotalExpiryTime() {
        return totalExpiryTime;
    }

    public void setTotalExpiryTime(Date totalExpiryTime) {
        this.totalExpiryTime = totalExpiryTime;
    }

    /**
     * @return 当前状态的过期时间
     * @occurs required
     */
    public Date getCurStatusExpiryTime() {
        return curStatusExpiryTime;
    }

    public void setCurStatusExpiryTime(Date curStatusExpiryTime) {
        this.curStatusExpiryTime = curStatusExpiryTime;
    }

    /**
     * @return TA申请单号
     * @occurs required
     */
    public String getRefAppSheetSerialNo() {
        return refAppSheetSerialNo;
    }

    public void setRefAppSheetSerialNo(String refAppSheetSerialNo) {
        this.refAppSheetSerialNo = refAppSheetSerialNo;
    }

    /**
     * @return 扩展关联单号
     * @occurs required
     */
    public String getExtInfoOrderNo() {
        return extInfoOrderNo;
    }

    public void setExtInfoOrderNo(String extInfoOrderNo) {
        this.extInfoOrderNo = extInfoOrderNo;
    }

    /**
     * @return 单据备注
     * @occurs required
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 业务扩展字段，json表示
     * @occurs required
     */
    public String getExtField() {
        return extField;
    }

    public void setExtField(String extField) {
        this.extField = extField;
    }

    /**
     * @return 交易时间
     * @occurs required
     */
    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * @return 业务单创建时间
     * @occurs required
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 业务单更新时间
     * @occurs required
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 业务单完成时间
     * @occurs required
     */
    public Date getFinishTime() {
        return finishTime;
    }


    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return 退款时间
     * @occurs required
     */
    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * @return 撤单时间
     * @occurs required
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * @return 可退款金额
     * @occurs required
     */
    public BigDecimal getRefundAmountLimit() {
        return refundAmountLimit;
    }

    public void setRefundAmountLimit(BigDecimal refundAmountLimit) {
        this.refundAmountLimit = refundAmountLimit;
    }

    /**
     * @return 退款金额
     * @occurs required
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * @return 退款手续费
     * @occurs required
     */
    public BigDecimal getRefundFeeAmount() {
        return refundFeeAmount;
    }

    public void setRefundFeeAmount(BigDecimal refundFeeAmount) {
        this.refundFeeAmount = refundFeeAmount;
    }

    /**
     * @return 付款方手续费
     * @occurs required
     */
    public BigDecimal getToFee() {
        return toFee;
    }

    public void setToFee(BigDecimal toFee) {
        this.toFee = toFee;
    }

    /**
     * @return 收款方手续费
     * @occurs required
     */
    public BigDecimal getFromFee() {
        return fromFee;
    }

    public void setFromFee(BigDecimal fromFee) {
        this.fromFee = fromFee;
    }

    /**
     * @return 收费类型
     * @occurs required
     */
    public Integer getFeeMethod() {
        return feeMethod;
    }


    public void setFeeMethod(Integer feeMethod) {
        this.feeMethod = feeMethod;
    }

    /**
     * @return 操作人号
     * @occurs required
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    /**
     * @return 支付方式，json表示
     * @occurs required
     */
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
