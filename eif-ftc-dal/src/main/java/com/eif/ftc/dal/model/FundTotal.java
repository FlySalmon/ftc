package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_total")
public class FundTotal {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金资产汇总UUID
     */
    @Column(name = "fund_total_uuid")
    private String fundTotalUuid;

    @Column(name = "member_asset_id")
    private Long memberAssetId;

    @Column(name = "member_asset_uuid")
    private String memberAssetUuid;

    /**
     * 用户资产账户id
     */
    @Column(name = "fund_acc_id")
    private Long fundAccId;

    /**
     * 用户资产账户uuid
     */
    @Column(name = "fund_acc_uuid")
    private String fundAccUuid;

    /**
     * 用户总资产
     */
    @Column(name = "total_asset")
    private BigDecimal totalAsset;

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
     * 获取基金资产汇总UUID
     *
     * @return fund_total_uuid - 基金资产汇总UUID
     */
    public String getFundTotalUuid() {
        return fundTotalUuid;
    }

    /**
     * 设置基金资产汇总UUID
     *
     * @param fundTotalUuid 基金资产汇总UUID
     */
    public void setFundTotalUuid(String fundTotalUuid) {
        this.fundTotalUuid = fundTotalUuid;
    }

    /**
     * @return member_asset_id
     */
    public Long getMemberAssetId() {
        return memberAssetId;
    }

    /**
     * @param memberAssetId
     */
    public void setMemberAssetId(Long memberAssetId) {
        this.memberAssetId = memberAssetId;
    }

    /**
     * @return member_asset_uuid
     */
    public String getMemberAssetUuid() {
        return memberAssetUuid;
    }

    /**
     * @param memberAssetUuid
     */
    public void setMemberAssetUuid(String memberAssetUuid) {
        this.memberAssetUuid = memberAssetUuid;
    }

    /**
     * 获取用户资产账户id
     *
     * @return fund_acc_id - 用户资产账户id
     */
    public Long getFundAccId() {
        return fundAccId;
    }

    /**
     * 设置用户资产账户id
     *
     * @param fundAccId 用户资产账户id
     */
    public void setFundAccId(Long fundAccId) {
        this.fundAccId = fundAccId;
    }

    /**
     * 获取用户资产账户uuid
     *
     * @return fund_acc_uuid - 用户资产账户uuid
     */
    public String getFundAccUuid() {
        return fundAccUuid;
    }

    /**
     * 设置用户资产账户uuid
     *
     * @param fundAccUuid 用户资产账户uuid
     */
    public void setFundAccUuid(String fundAccUuid) {
        this.fundAccUuid = fundAccUuid;
    }

    /**
     * 获取用户总资产
     *
     * @return total_asset - 用户总资产
     */
    public BigDecimal getTotalAsset() {
        return totalAsset;
    }

    /**
     * 设置用户总资产
     *
     * @param totalAsset 用户总资产
     */
    public void setTotalAsset(BigDecimal totalAsset) {
        this.totalAsset = totalAsset;
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
}