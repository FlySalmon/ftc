package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_transfer_apply_to_exchange")
public class FundTransferApplyToExchange {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 操作流水号
     */
    @Column(name = "op_id")
    private String opId;

    /**
     * 转让交易订单号
     */
    @Column(name = "fund_transferor_order_no")
    private String fundTransferorOrderNo;

    /**
     * 受让交易订单号
     */
    @Column(name = "fund_transferee_order_no")
    private String fundTransfereeOrderNo;

    /**
     * 交易所转让申请Id
     */
    @Column(name = "exchange_transferor_id")
    private String exchangeTransferorId;

    /**
     * 交易所受让匹配Id
     */
    @Column(name = "exchange_matching_id")
    private String exchangeMatchingId;

    /**
     * 交易所受让交易Id
     */
    @Column(name = "exchange_transferee_id")
    private String exchangeTransfereeId;

    /**
     * 交易所产品代码
     */
    @Column(name = "exchange_prod_no")
    private String exchangeProdNo;

    /**
     * 原用户产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 二级市场所属产品ID
     */
    @Column(name = "mkt_product_id")
    private Long mktProductId;

    /**
     * 转让发起人会员号
     */
    @Column(name = "member_no_from")
    private String memberNoFrom;

    /**
     * 受让发起人会员号
     */
    @Column(name = "member_no_to")
    private String memberNoTo;

    /**
     * 平台转让发生时间
     */
    @Column(name = "transfer_time")
    private Date transferTime;

    /**
     * 确认转让执行时间
     */
    @Column(name = "exchange_execution_time")
    private Date exchangeExecutionTime;

    /**
     * 交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;
     */
    private Byte status;

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
     * 获取操作流水号
     *
     * @return op_id - 操作流水号
     */
    public String getOpId() {
        return opId;
    }

    /**
     * 设置操作流水号
     *
     * @param opId 操作流水号
     */
    public void setOpId(String opId) {
        this.opId = opId;
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
     * 获取交易所转让申请Id
     *
     * @return exchange_transferor_id - 交易所转让申请Id
     */
    public String getExchangeTransferorId() {
        return exchangeTransferorId;
    }

    /**
     * 设置交易所转让申请Id
     *
     * @param exchangeTransferorId 交易所转让申请Id
     */
    public void setExchangeTransferorId(String exchangeTransferorId) {
        this.exchangeTransferorId = exchangeTransferorId;
    }

    /**
     * 获取交易所受让匹配Id
     *
     * @return exchange_matching_id - 交易所受让匹配Id
     */
    public String getExchangeMatchingId() {
        return exchangeMatchingId;
    }

    /**
     * 设置交易所受让匹配Id
     *
     * @param exchangeMatchingId 交易所受让匹配Id
     */
    public void setExchangeMatchingId(String exchangeMatchingId) {
        this.exchangeMatchingId = exchangeMatchingId;
    }

    /**
     * 获取交易所受让交易Id
     *
     * @return exchange_transferee_id - 交易所受让交易Id
     */
    public String getExchangeTransfereeId() {
        return exchangeTransfereeId;
    }

    /**
     * 设置交易所受让交易Id
     *
     * @param exchangeTransfereeId 交易所受让交易Id
     */
    public void setExchangeTransfereeId(String exchangeTransfereeId) {
        this.exchangeTransfereeId = exchangeTransfereeId;
    }

    /**
     * 获取交易所产品代码
     *
     * @return exchange_prod_no - 交易所产品代码
     */
    public String getExchangeProdNo() {
        return exchangeProdNo;
    }

    /**
     * 设置交易所产品代码
     *
     * @param exchangeProdNo 交易所产品代码
     */
    public void setExchangeProdNo(String exchangeProdNo) {
        this.exchangeProdNo = exchangeProdNo;
    }

    /**
     * 获取原用户产品ID
     *
     * @return product_id - 原用户产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置原用户产品ID
     *
     * @param productId 原用户产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
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
     * 获取转让发起人会员号
     *
     * @return member_no_from - 转让发起人会员号
     */
    public String getMemberNoFrom() {
        return memberNoFrom;
    }

    /**
     * 设置转让发起人会员号
     *
     * @param memberNoFrom 转让发起人会员号
     */
    public void setMemberNoFrom(String memberNoFrom) {
        this.memberNoFrom = memberNoFrom;
    }

    /**
     * 获取受让发起人会员号
     *
     * @return member_no_to - 受让发起人会员号
     */
    public String getMemberNoTo() {
        return memberNoTo;
    }

    /**
     * 设置受让发起人会员号
     *
     * @param memberNoTo 受让发起人会员号
     */
    public void setMemberNoTo(String memberNoTo) {
        this.memberNoTo = memberNoTo;
    }

    /**
     * 获取平台转让发生时间
     *
     * @return transfer_time - 平台转让发生时间
     */
    public Date getTransferTime() {
        return transferTime;
    }

    /**
     * 设置平台转让发生时间
     *
     * @param transferTime 平台转让发生时间
     */
    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    /**
     * 获取确认转让执行时间
     *
     * @return exchange_execution_time - 确认转让执行时间
     */
    public Date getExchangeExecutionTime() {
        return exchangeExecutionTime;
    }

    /**
     * 设置确认转让执行时间
     *
     * @param exchangeExecutionTime 确认转让执行时间
     */
    public void setExchangeExecutionTime(Date exchangeExecutionTime) {
        this.exchangeExecutionTime = exchangeExecutionTime;
    }

    /**
     * 获取交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;
     *
     * @return status - 交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;
     *
     * @param status 交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;
     */
    public void setStatus(Byte status) {
        this.status = status;
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