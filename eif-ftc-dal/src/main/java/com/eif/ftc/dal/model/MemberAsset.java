package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_mem_asset")
public class MemberAsset {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户资产uuid
     */
    @Column(name = "member_asset_uuid")
    private String memberAssetUuid;

    /**
     * 会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 交易渠道号
     */
    @Column(name = "channel_no")
    private Integer channelNo;

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
     * 确认扣减金额
     */
    @Column(name = "confirmed_sub_amount")
    private BigDecimal confirmedSubAmount;

    /**
     * 确认扣减份额
     */
    @Column(name = "confirmed_sub_share")
    private BigDecimal confirmedSubShare;

    /**
     * 未确认扣减金额
     */
    @Column(name = "unconfirmed_sub_amount")
    private BigDecimal unconfirmedSubAmount;

    /**
     * 未确认扣减份额
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
     * 获取用户资产uuid
     *
     * @return member_asset_uuid - 用户资产uuid
     */
    public String getMemberAssetUuid() {
        return memberAssetUuid;
    }

    /**
     * 设置用户资产uuid
     *
     * @param memberAssetUuid 用户资产uuid
     */
    public void setMemberAssetUuid(String memberAssetUuid) {
        this.memberAssetUuid = memberAssetUuid;
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
     * 获取交易渠道号
     *
     * @return channel_no - 交易渠道号
     */
    public Integer getChannelNo() {
        return channelNo;
    }

    /**
     * 设置交易渠道号
     *
     * @param channelNo 交易渠道号
     */
    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
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
     * 获取确认扣减金额
     *
     * @return confirmed_sub_amount - 确认扣减金额
     */
    public BigDecimal getConfirmedSubAmount() {
        return confirmedSubAmount;
    }

    /**
     * 设置确认扣减金额
     *
     * @param confirmedSubAmount 确认扣减金额
     */
    public void setConfirmedSubAmount(BigDecimal confirmedSubAmount) {
        this.confirmedSubAmount = confirmedSubAmount;
    }

    /**
     * 获取确认扣减份额
     *
     * @return confirmed_sub_share - 确认扣减份额
     */
    public BigDecimal getConfirmedSubShare() {
        return confirmedSubShare;
    }

    /**
     * 设置确认扣减份额
     *
     * @param confirmedSubShare 确认扣减份额
     */
    public void setConfirmedSubShare(BigDecimal confirmedSubShare) {
        this.confirmedSubShare = confirmedSubShare;
    }

    /**
     * 获取未确认扣减金额
     *
     * @return unconfirmed_sub_amount - 未确认扣减金额
     */
    public BigDecimal getUnconfirmedSubAmount() {
        return unconfirmedSubAmount;
    }

    /**
     * 设置未确认扣减金额
     *
     * @param unconfirmedSubAmount 未确认扣减金额
     */
    public void setUnconfirmedSubAmount(BigDecimal unconfirmedSubAmount) {
        this.unconfirmedSubAmount = unconfirmedSubAmount;
    }

    /**
     * 获取未确认扣减份额
     *
     * @return unconfirmed_sub_share - 未确认扣减份额
     */
    public BigDecimal getUnconfirmedSubShare() {
        return unconfirmedSubShare;
    }

    /**
     * 设置未确认扣减份额
     *
     * @param unconfirmedSubShare 未确认扣减份额
     */
    public void setUnconfirmedSubShare(BigDecimal unconfirmedSubShare) {
        this.unconfirmedSubShare = unconfirmedSubShare;
    }
}