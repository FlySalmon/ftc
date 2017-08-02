package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_detail")
public class FundDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金资产明细表的UUID
     */
    @Column(name = "fund_detail_uuid")
    private String fundDetailUuid;

    /**
     * 基金资产汇总表的ID
     */
    @Column(name = "fund_total_id")
    private Long fundTotalId;

    /**
     * 基金资产汇总表的uuid
     */
    @Column(name = "fund_total_uuid")
    private String fundTotalUuid;

    /**
     * 用户产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 该只基金的总份额
     */
    @Column(name = "fund_total_share")
    private BigDecimal fundTotalShare;

    /**
     * 该只基金的总金额
     */
    @Column(name = "fund_total_amount")
    private BigDecimal fundTotalAmount;

    /**
     * 用户昨日收益
     */
    @Column(name = "yesterday_profit")
    private BigDecimal yesterdayProfit;

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
     * 冻结金额
     */
    @Column(name = "frozen_amount")
    private BigDecimal frozenAmount;

    /**
     * 冻结份额
     */
    @Column(name = "frozen_share")
    private BigDecimal frozenShare;

    /**
     * 确认增加金额
     */
    @Column(name = "confirmed_add_amount")
    private BigDecimal confirmedAddAmount;

    /**
     * 确认增加份额
     */
    @Column(name = "confirmed_add_share")
    private BigDecimal confirmedAddShare;

    /**
     * 未确认增加金额
     */
    @Column(name = "unconfirmed_add_amount")
    private BigDecimal unconfirmedAddAmount;

    /**
     * 未确认增加份额
     */
    @Column(name = "unconfirmed_add_share")
    private BigDecimal unconfirmedAddShare;

    /**
     * 确认减少金额
     */
    @Column(name = "confirmed_sub_amount")
    private BigDecimal confirmedSubAmount;

    /**
     * 确认减少份额
     */
    @Column(name = "confirmed_sub_share")
    private BigDecimal confirmedSubShare;

    /**
     * 未确认减少金额
     */
    @Column(name = "unconfirmed_sub_amount")
    private BigDecimal unconfirmedSubAmount;

    /**
     * 未确认减少份额
     */
    @Column(name = "unconfirmed_sub_share")
    private BigDecimal unconfirmedSubShare;

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
     * 预期红利
     */
    @Column(name = "expect_bonus_amount")
    private BigDecimal expectBonusAmount;

    /**
     * 清盘时间
     */
    @Column(name = "settlement_time")
    private Date settlementTime;

    /**
     * 预期加息券
     */
    @Column(name = "expect_profit_amount")
    private BigDecimal expectProfitAmount;

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
     * 团购收益金额
     */
    @Column(name = "groupon_bonus")
    private BigDecimal grouponBonus;

    /**
     * 是否团购产品；0-非团购，1-团购
     */
    @Column(name = "groupon_type")
    private Byte grouponType;

    /**
     * 产品类型（封闭-1，半封闭半开放-2，开放-0）
     */
    @Column(name = "product_close_type")
    private Integer productCloseType;

    /**
     * 活包定TA返回的红利
     */
    @Column(name = "half_open_bonus_amount")
    private BigDecimal halfOpenBonusAmount;

    /**
     * 转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。
     */
    @Column(name = "exchange_status")
    private Byte exchangeStatus;

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
     * 获取基金资产明细表的UUID
     *
     * @return fund_detail_uuid - 基金资产明细表的UUID
     */
    public String getFundDetailUuid() {
        return fundDetailUuid;
    }

    /**
     * 设置基金资产明细表的UUID
     *
     * @param fundDetailUuid 基金资产明细表的UUID
     */
    public void setFundDetailUuid(String fundDetailUuid) {
        this.fundDetailUuid = fundDetailUuid;
    }

    /**
     * 获取基金资产汇总表的ID
     *
     * @return fund_total_id - 基金资产汇总表的ID
     */
    public Long getFundTotalId() {
        return fundTotalId;
    }

    /**
     * 设置基金资产汇总表的ID
     *
     * @param fundTotalId 基金资产汇总表的ID
     */
    public void setFundTotalId(Long fundTotalId) {
        this.fundTotalId = fundTotalId;
    }

    /**
     * 获取基金资产汇总表的uuid
     *
     * @return fund_total_uuid - 基金资产汇总表的uuid
     */
    public String getFundTotalUuid() {
        return fundTotalUuid;
    }

    /**
     * 设置基金资产汇总表的uuid
     *
     * @param fundTotalUuid 基金资产汇总表的uuid
     */
    public void setFundTotalUuid(String fundTotalUuid) {
        this.fundTotalUuid = fundTotalUuid;
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
     * 获取该只基金的总份额
     *
     * @return fund_total_share - 该只基金的总份额
     */
    public BigDecimal getFundTotalShare() {
        return fundTotalShare;
    }

    /**
     * 设置该只基金的总份额
     *
     * @param fundTotalShare 该只基金的总份额
     */
    public void setFundTotalShare(BigDecimal fundTotalShare) {
        this.fundTotalShare = fundTotalShare;
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
     * 获取用户昨日收益
     *
     * @return yesterday_profit - 用户昨日收益
     */
    public BigDecimal getYesterdayProfit() {
        return yesterdayProfit;
    }

    /**
     * 设置用户昨日收益
     *
     * @param yesterdayProfit 用户昨日收益
     */
    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
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
     * 获取冻结金额
     *
     * @return frozen_amount - 冻结金额
     */
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    /**
     * 设置冻结金额
     *
     * @param frozenAmount 冻结金额
     */
    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    /**
     * 获取冻结份额
     *
     * @return frozen_share - 冻结份额
     */
    public BigDecimal getFrozenShare() {
        return frozenShare;
    }

    /**
     * 设置冻结份额
     *
     * @param frozenShare 冻结份额
     */
    public void setFrozenShare(BigDecimal frozenShare) {
        this.frozenShare = frozenShare;
    }

    /**
     * 获取确认增加金额
     *
     * @return confirmed_add_amount - 确认增加金额
     */
    public BigDecimal getConfirmedAddAmount() {
        return confirmedAddAmount;
    }

    /**
     * 设置确认增加金额
     *
     * @param confirmedAddAmount 确认增加金额
     */
    public void setConfirmedAddAmount(BigDecimal confirmedAddAmount) {
        this.confirmedAddAmount = confirmedAddAmount;
    }

    /**
     * 获取确认增加份额
     *
     * @return confirmed_add_share - 确认增加份额
     */
    public BigDecimal getConfirmedAddShare() {
        return confirmedAddShare;
    }

    /**
     * 设置确认增加份额
     *
     * @param confirmedAddShare 确认增加份额
     */
    public void setConfirmedAddShare(BigDecimal confirmedAddShare) {
        this.confirmedAddShare = confirmedAddShare;
    }

    /**
     * 获取未确认增加金额
     *
     * @return unconfirmed_add_amount - 未确认增加金额
     */
    public BigDecimal getUnconfirmedAddAmount() {
        return unconfirmedAddAmount;
    }

    /**
     * 设置未确认增加金额
     *
     * @param unconfirmedAddAmount 未确认增加金额
     */
    public void setUnconfirmedAddAmount(BigDecimal unconfirmedAddAmount) {
        this.unconfirmedAddAmount = unconfirmedAddAmount;
    }

    /**
     * 获取未确认增加份额
     *
     * @return unconfirmed_add_share - 未确认增加份额
     */
    public BigDecimal getUnconfirmedAddShare() {
        return unconfirmedAddShare;
    }

    /**
     * 设置未确认增加份额
     *
     * @param unconfirmedAddShare 未确认增加份额
     */
    public void setUnconfirmedAddShare(BigDecimal unconfirmedAddShare) {
        this.unconfirmedAddShare = unconfirmedAddShare;
    }

    /**
     * 获取确认减少金额
     *
     * @return confirmed_sub_amount - 确认减少金额
     */
    public BigDecimal getConfirmedSubAmount() {
        return confirmedSubAmount;
    }

    /**
     * 设置确认减少金额
     *
     * @param confirmedSubAmount 确认减少金额
     */
    public void setConfirmedSubAmount(BigDecimal confirmedSubAmount) {
        this.confirmedSubAmount = confirmedSubAmount;
    }

    /**
     * 获取确认减少份额
     *
     * @return confirmed_sub_share - 确认减少份额
     */
    public BigDecimal getConfirmedSubShare() {
        return confirmedSubShare;
    }

    /**
     * 设置确认减少份额
     *
     * @param confirmedSubShare 确认减少份额
     */
    public void setConfirmedSubShare(BigDecimal confirmedSubShare) {
        this.confirmedSubShare = confirmedSubShare;
    }

    /**
     * 获取未确认减少金额
     *
     * @return unconfirmed_sub_amount - 未确认减少金额
     */
    public BigDecimal getUnconfirmedSubAmount() {
        return unconfirmedSubAmount;
    }

    /**
     * 设置未确认减少金额
     *
     * @param unconfirmedSubAmount 未确认减少金额
     */
    public void setUnconfirmedSubAmount(BigDecimal unconfirmedSubAmount) {
        this.unconfirmedSubAmount = unconfirmedSubAmount;
    }

    /**
     * 获取未确认减少份额
     *
     * @return unconfirmed_sub_share - 未确认减少份额
     */
    public BigDecimal getUnconfirmedSubShare() {
        return unconfirmedSubShare;
    }

    /**
     * 设置未确认减少份额
     *
     * @param unconfirmedSubShare 未确认减少份额
     */
    public void setUnconfirmedSubShare(BigDecimal unconfirmedSubShare) {
        this.unconfirmedSubShare = unconfirmedSubShare;
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
     * 获取预期红利
     *
     * @return expect_bonus_amount - 预期红利
     */
    public BigDecimal getExpectBonusAmount() {
        return expectBonusAmount;
    }

    /**
     * 设置预期红利
     *
     * @param expectBonusAmount 预期红利
     */
    public void setExpectBonusAmount(BigDecimal expectBonusAmount) {
        this.expectBonusAmount = expectBonusAmount;
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
     * 获取预期加息券
     *
     * @return expect_profit_amount - 预期加息券
     */
    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    /**
     * 设置预期加息券
     *
     * @param expectProfitAmount 预期加息券
     */
    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
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
     * 获取团购收益金额
     *
     * @return groupon_bonus - 团购收益金额
     */
    public BigDecimal getGrouponBonus() {
        return grouponBonus;
    }

    /**
     * 设置团购收益金额
     *
     * @param grouponBonus 团购收益金额
     */
    public void setGrouponBonus(BigDecimal grouponBonus) {
        this.grouponBonus = grouponBonus;
    }

    /**
     * 获取是否团购产品；0-非团购，1-团购
     *
     * @return groupon_type - 是否团购产品；0-非团购，1-团购
     */
    public Byte getGrouponType() {
        return grouponType;
    }

    /**
     * 设置是否团购产品；0-非团购，1-团购
     *
     * @param grouponType 是否团购产品；0-非团购，1-团购
     */
    public void setGrouponType(Byte grouponType) {
        this.grouponType = grouponType;
    }

    /**
     * 获取产品类型（封闭-1，半封闭半开放-2，开放-0）
     *
     * @return product_close_type - 产品类型（封闭-1，半封闭半开放-2，开放-0）
     */
    public Integer getProductCloseType() {
        return productCloseType;
    }

    /**
     * 设置产品类型（封闭-1，半封闭半开放-2，开放-0）
     *
     * @param productCloseType 产品类型（封闭-1，半封闭半开放-2，开放-0）
     */
    public void setProductCloseType(Integer productCloseType) {
        this.productCloseType = productCloseType;
    }

    /**
     * 获取活包定TA返回的红利
     *
     * @return half_open_bonus_amount - 活包定TA返回的红利
     */
    public BigDecimal getHalfOpenBonusAmount() {
        return halfOpenBonusAmount;
    }

    /**
     * 设置活包定TA返回的红利
     *
     * @param halfOpenBonusAmount 活包定TA返回的红利
     */
    public void setHalfOpenBonusAmount(BigDecimal halfOpenBonusAmount) {
        this.halfOpenBonusAmount = halfOpenBonusAmount;
    }

    /**
     * 获取转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。
     *
     * @return exchange_status - 转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。
     */
    public Byte getExchangeStatus() {
        return exchangeStatus;
    }

    /**
     * 设置转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。
     *
     * @param exchangeStatus 转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。
     */
    public void setExchangeStatus(Byte exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}