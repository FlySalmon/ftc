package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_offline_detail")
public class FundOfflineDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金线下资产明细表的UUID
     */
    @Column(name = "fund_offline_detail_uuid")
    private String fundOfflineDetailUuid;

    /**
     * 会员NO
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 用户产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 该只基金的总金额
     */
    @Column(name = "fund_total_amount")
    private BigDecimal fundTotalAmount;

    /**
     * 用户累计收益
     */
    @Column(name = "total_profit")
    private BigDecimal totalProfit;

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
     * 红利总额
     */
    @Column(name = "bonus_total_amount")
    private BigDecimal bonusTotalAmount;

    /**
     * 加息券总额
     */
    @Column(name = "profit_total_amount")
    private BigDecimal profitTotalAmount;

    /**
     * 清盘时间
     */
    @Column(name = "settlement_time")
    private Date settlementTime;

    /**
     * 是否清盘，1-否，2-是
     */
    @Column(name = "has_settlement")
    private Integer hasSettlement;

    /**
     * 兑付本金
     */
    @Column(name = "settlement_capital")
    private BigDecimal settlementCapital;

    /**
     * 幂等键
     */
    @Column(name = "offline_code")
    private String offlineCode;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_cardno")
    private String customerCardno;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "inception_date")
    private Date inceptionDate;

    @Column(name = "due_date")
    private Date dueDate;

    /**
     * 线下资产标记
     */
    @Column(name = "offline_mark")
    private Integer offlineMark;

    @Column(name = "soft_deleted")
    private Integer softDeleted;

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
     * 获取基金线下资产明细表的UUID
     *
     * @return fund_offline_detail_uuid - 基金线下资产明细表的UUID
     */
    public String getFundOfflineDetailUuid() {
        return fundOfflineDetailUuid;
    }

    /**
     * 设置基金线下资产明细表的UUID
     *
     * @param fundOfflineDetailUuid 基金线下资产明细表的UUID
     */
    public void setFundOfflineDetailUuid(String fundOfflineDetailUuid) {
        this.fundOfflineDetailUuid = fundOfflineDetailUuid;
    }

    /**
     * 获取会员NO
     *
     * @return member_no - 会员NO
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置会员NO
     *
     * @param memberNo 会员NO
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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
     * 获取该只基金的总金额
     *
     * @return fund_total_amount - 该只基金的总金额
     */
    public BigDecimal getFundTotalAmount() {
        return fundTotalAmount;
    }

    /**
     * 设置该只基金的总金额
     *
     * @param fundTotalAmount 该只基金的总金额
     */
    public void setFundTotalAmount(BigDecimal fundTotalAmount) {
        this.fundTotalAmount = fundTotalAmount;
    }

    /**
     * 获取用户累计收益
     *
     * @return total_profit - 用户累计收益
     */
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    /**
     * 设置用户累计收益
     *
     * @param totalProfit 用户累计收益
     */
    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
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
     * 获取红利总额
     *
     * @return bonus_total_amount - 红利总额
     */
    public BigDecimal getBonusTotalAmount() {
        return bonusTotalAmount;
    }

    /**
     * 设置红利总额
     *
     * @param bonusTotalAmount 红利总额
     */
    public void setBonusTotalAmount(BigDecimal bonusTotalAmount) {
        this.bonusTotalAmount = bonusTotalAmount;
    }

    /**
     * 获取加息券总额
     *
     * @return profit_total_amount - 加息券总额
     */
    public BigDecimal getProfitTotalAmount() {
        return profitTotalAmount;
    }

    /**
     * 设置加息券总额
     *
     * @param profitTotalAmount 加息券总额
     */
    public void setProfitTotalAmount(BigDecimal profitTotalAmount) {
        this.profitTotalAmount = profitTotalAmount;
    }

    /**
     * 获取清盘时间
     *
     * @return settlement_time - 清盘时间
     */
    public Date getSettlementTime() {
        return settlementTime;
    }

    /**
     * 设置清盘时间
     *
     * @param settlementTime 清盘时间
     */
    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }

    /**
     * 获取是否清盘，1-否，2-是
     *
     * @return has_settlement - 是否清盘，1-否，2-是
     */
    public Integer getHasSettlement() {
        return hasSettlement;
    }

    /**
     * 设置是否清盘，1-否，2-是
     *
     * @param hasSettlement 是否清盘，1-否，2-是
     */
    public void setHasSettlement(Integer hasSettlement) {
        this.hasSettlement = hasSettlement;
    }

    /**
     * 获取兑付本金
     *
     * @return settlement_capital - 兑付本金
     */
    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }

    /**
     * 设置兑付本金
     *
     * @param settlementCapital 兑付本金
     */
    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
    }

    /**
     * 获取幂等键
     *
     * @return offline_code - 幂等键
     */
    public String getOfflineCode() {
        return offlineCode;
    }

    /**
     * 设置幂等键
     *
     * @param offlineCode 幂等键
     */
    public void setOfflineCode(String offlineCode) {
        this.offlineCode = offlineCode;
    }

    /**
     * @return customer_phone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return customer_name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return customer_cardno
     */
    public String getCustomerCardno() {
        return customerCardno;
    }

    /**
     * @param customerCardno
     */
    public void setCustomerCardno(String customerCardno) {
        this.customerCardno = customerCardno;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return inception_date
     */
    public Date getInceptionDate() {
        return inceptionDate;
    }

    /**
     * @param inceptionDate
     */
    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    /**
     * @return due_date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 获取线下资产标记
     *
     * @return offline_mark - 线下资产标记
     */
    public Integer getOfflineMark() {
        return offlineMark;
    }

    /**
     * 设置线下资产标记
     *
     * @param offlineMark 线下资产标记
     */
    public void setOfflineMark(Integer offlineMark) {
        this.offlineMark = offlineMark;
    }

    /**
     * @return soft_deleted
     */
    public Integer getSoftDeleted() {
        return softDeleted;
    }

    /**
     * @param softDeleted
     */
    public void setSoftDeleted(Integer softDeleted) {
        this.softDeleted = softDeleted;
    }
}