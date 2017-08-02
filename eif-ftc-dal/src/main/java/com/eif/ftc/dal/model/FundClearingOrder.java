package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_clearing")
public class FundClearingOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金清盘业务单号
     */
    @Column(name = "fund_clearing_order_no")
    private String fundClearingOrderNo;

    /**
     * 业务订单项流水号
     */
    @Column(name = "business_order_item_no")
    private String businessOrderItemNo;

    /**
     * 市场类别：一级市场 = 1; 二级市场 = 2;
     */
    @Column(name = "market_level")
    private Integer marketLevel;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 会员ID
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 投资人基金交易账号
     */
    @Column(name = "transaction_account_id")
    private String transactionAccountId;

    /**
     * ta交易确认日期,格式为：YYYYMMDD
            
     */
    @Column(name = "transaction_cfm_date")
    private String transactionCfmDate;

    /**
     * 结算币种,具体编码依GB/T 12406-2008
            
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 基金冻结总份数
     */
    @Column(name = "total_frozen_vol")
    private BigDecimal totalFrozenVol;

    /**
     * 基金份数余额
     */
    @Column(name = "fund_vol_balance")
    private BigDecimal fundVolBalance;

    /**
     * 业绩报酬
     */
    @Column(name = "achievement_pay")
    private BigDecimal achievementPay;

    /**
     * 业绩补偿
     */
    @Column(name = "achievement_compen")
    private BigDecimal achievementCompen;

    /**
     * 冻结金额
     */
    @Column(name = "frozen_balance")
    private BigDecimal frozenBalance;

    /**
     * 基金账户交易确认份数
     */
    @Column(name = "confirmed_vol")
    private BigDecimal confirmedVol;

    /**
     * 每笔交易确认金额,含所有费用的总金额
     */
    @Column(name = "confirmed_amount")
    private BigDecimal confirmedAmount;

    /**
     * 基金单位净值
     */
    private BigDecimal nav;

    /**
     * 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    @Column(name = "share_class")
    private String shareClass;

    /**
     * 交易核心交易单流水号
     */
    @Column(name = "transcore_trans_no")
    private String transcoreTransNo;

    /**
     * 产品预期收益
     */
    @Column(name = "dividend_amount")
    private BigDecimal dividendAmount;

    /**
     * 活期产品收益
     */
    @Column(name = "current_dividend_amount")
    private BigDecimal currentDividendAmount;

    /**
     * 额外加息金额
     */
    @Column(name = "coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 团购奖励金额
     */
    @Column(name = "groupon_amount")
    private BigDecimal grouponAmount;

    /**
     * 营销贴息金额
     */
    @Column(name = "marketing_discount")
    private BigDecimal marketingDiscount;

    /**
     * 平台利润金额（用户投资带来的利润超过预期收益时）
     */
    @Column(name = "platform_profit")
    private BigDecimal platformProfit;

    @Column(name = "external_serial_no")
    private String externalSerialNo;

    /**
     * 记录单处理状态
     */
    private Integer status;

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
     * 获取基金清盘业务单号
     *
     * @return fund_clearing_order_no - 基金清盘业务单号
     */
    public String getFundClearingOrderNo() {
        return fundClearingOrderNo;
    }

    /**
     * 设置基金清盘业务单号
     *
     * @param fundClearingOrderNo 基金清盘业务单号
     */
    public void setFundClearingOrderNo(String fundClearingOrderNo) {
        this.fundClearingOrderNo = fundClearingOrderNo;
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
     * 获取市场类别：一级市场 = 1; 二级市场 = 2;
     *
     * @return market_level - 市场类别：一级市场 = 1; 二级市场 = 2;
     */
    public Integer getMarketLevel() {
        return marketLevel;
    }

    /**
     * 设置市场类别：一级市场 = 1; 二级市场 = 2;
     *
     * @param marketLevel 市场类别：一级市场 = 1; 二级市场 = 2;
     */
    public void setMarketLevel(Integer marketLevel) {
        this.marketLevel = marketLevel;
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取会员ID
     *
     * @return member_no - 会员ID
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置会员ID
     *
     * @param memberNo 会员ID
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取投资人基金交易账号
     *
     * @return transaction_account_id - 投资人基金交易账号
     */
    public String getTransactionAccountId() {
        return transactionAccountId;
    }

    /**
     * 设置投资人基金交易账号
     *
     * @param transactionAccountId 投资人基金交易账号
     */
    public void setTransactionAccountId(String transactionAccountId) {
        this.transactionAccountId = transactionAccountId;
    }

    /**
     * 获取ta交易确认日期,格式为：YYYYMMDD
            
     *
     * @return transaction_cfm_date - ta交易确认日期,格式为：YYYYMMDD
            
     */
    public String getTransactionCfmDate() {
        return transactionCfmDate;
    }

    /**
     * 设置ta交易确认日期,格式为：YYYYMMDD
            
     *
     * @param transactionCfmDate ta交易确认日期,格式为：YYYYMMDD
            
     */
    public void setTransactionCfmDate(String transactionCfmDate) {
        this.transactionCfmDate = transactionCfmDate;
    }

    /**
     * 获取结算币种,具体编码依GB/T 12406-2008
            
     *
     * @return currency_type - 结算币种,具体编码依GB/T 12406-2008
            
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置结算币种,具体编码依GB/T 12406-2008
            
     *
     * @param currencyType 结算币种,具体编码依GB/T 12406-2008
            
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * 获取基金冻结总份数
     *
     * @return total_frozen_vol - 基金冻结总份数
     */
    public BigDecimal getTotalFrozenVol() {
        return totalFrozenVol;
    }

    /**
     * 设置基金冻结总份数
     *
     * @param totalFrozenVol 基金冻结总份数
     */
    public void setTotalFrozenVol(BigDecimal totalFrozenVol) {
        this.totalFrozenVol = totalFrozenVol;
    }

    /**
     * 获取基金份数余额
     *
     * @return fund_vol_balance - 基金份数余额
     */
    public BigDecimal getFundVolBalance() {
        return fundVolBalance;
    }

    /**
     * 设置基金份数余额
     *
     * @param fundVolBalance 基金份数余额
     */
    public void setFundVolBalance(BigDecimal fundVolBalance) {
        this.fundVolBalance = fundVolBalance;
    }

    /**
     * 获取业绩报酬
     *
     * @return achievement_pay - 业绩报酬
     */
    public BigDecimal getAchievementPay() {
        return achievementPay;
    }

    /**
     * 设置业绩报酬
     *
     * @param achievementPay 业绩报酬
     */
    public void setAchievementPay(BigDecimal achievementPay) {
        this.achievementPay = achievementPay;
    }

    /**
     * 获取业绩补偿
     *
     * @return achievement_compen - 业绩补偿
     */
    public BigDecimal getAchievementCompen() {
        return achievementCompen;
    }

    /**
     * 设置业绩补偿
     *
     * @param achievementCompen 业绩补偿
     */
    public void setAchievementCompen(BigDecimal achievementCompen) {
        this.achievementCompen = achievementCompen;
    }

    /**
     * 获取冻结金额
     *
     * @return frozen_balance - 冻结金额
     */
    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    /**
     * 设置冻结金额
     *
     * @param frozenBalance 冻结金额
     */
    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    /**
     * 获取基金账户交易确认份数
     *
     * @return confirmed_vol - 基金账户交易确认份数
     */
    public BigDecimal getConfirmedVol() {
        return confirmedVol;
    }

    /**
     * 设置基金账户交易确认份数
     *
     * @param confirmedVol 基金账户交易确认份数
     */
    public void setConfirmedVol(BigDecimal confirmedVol) {
        this.confirmedVol = confirmedVol;
    }

    /**
     * 获取每笔交易确认金额,含所有费用的总金额
     *
     * @return confirmed_amount - 每笔交易确认金额,含所有费用的总金额
     */
    public BigDecimal getConfirmedAmount() {
        return confirmedAmount;
    }

    /**
     * 设置每笔交易确认金额,含所有费用的总金额
     *
     * @param confirmedAmount 每笔交易确认金额,含所有费用的总金额
     */
    public void setConfirmedAmount(BigDecimal confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }

    /**
     * 获取基金单位净值
     *
     * @return nav - 基金单位净值
     */
    public BigDecimal getNav() {
        return nav;
    }

    /**
     * 设置基金单位净值
     *
     * @param nav 基金单位净值
     */
    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    /**
     * 获取收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     *
     * @return share_class - 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    public String getShareClass() {
        return shareClass;
    }

    /**
     * 设置收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     *
     * @param shareClass 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    public void setShareClass(String shareClass) {
        this.shareClass = shareClass;
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
     * 获取产品预期收益
     *
     * @return dividend_amount - 产品预期收益
     */
    public BigDecimal getDividendAmount() {
        return dividendAmount;
    }

    /**
     * 设置产品预期收益
     *
     * @param dividendAmount 产品预期收益
     */
    public void setDividendAmount(BigDecimal dividendAmount) {
        this.dividendAmount = dividendAmount;
    }

    /**
     * 获取活期产品收益
     *
     * @return current_dividend_amount - 活期产品收益
     */
    public BigDecimal getCurrentDividendAmount() {
        return currentDividendAmount;
    }

    /**
     * 设置活期产品收益
     *
     * @param currentDividendAmount 活期产品收益
     */
    public void setCurrentDividendAmount(BigDecimal currentDividendAmount) {
        this.currentDividendAmount = currentDividendAmount;
    }

    /**
     * 获取额外加息金额
     *
     * @return coupon_amount - 额外加息金额
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * 设置额外加息金额
     *
     * @param couponAmount 额外加息金额
     */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * 获取团购奖励金额
     *
     * @return groupon_amount - 团购奖励金额
     */
    public BigDecimal getGrouponAmount() {
        return grouponAmount;
    }

    /**
     * 设置团购奖励金额
     *
     * @param grouponAmount 团购奖励金额
     */
    public void setGrouponAmount(BigDecimal grouponAmount) {
        this.grouponAmount = grouponAmount;
    }

    /**
     * 获取营销贴息金额
     *
     * @return marketing_discount - 营销贴息金额
     */
    public BigDecimal getMarketingDiscount() {
        return marketingDiscount;
    }

    /**
     * 设置营销贴息金额
     *
     * @param marketingDiscount 营销贴息金额
     */
    public void setMarketingDiscount(BigDecimal marketingDiscount) {
        this.marketingDiscount = marketingDiscount;
    }

    /**
     * 获取平台利润金额（用户投资带来的利润超过预期收益时）
     *
     * @return platform_profit - 平台利润金额（用户投资带来的利润超过预期收益时）
     */
    public BigDecimal getPlatformProfit() {
        return platformProfit;
    }

    /**
     * 设置平台利润金额（用户投资带来的利润超过预期收益时）
     *
     * @param platformProfit 平台利润金额（用户投资带来的利润超过预期收益时）
     */
    public void setPlatformProfit(BigDecimal platformProfit) {
        this.platformProfit = platformProfit;
    }

    /**
     * @return external_serial_no
     */
    public String getExternalSerialNo() {
        return externalSerialNo;
    }

    /**
     * @param externalSerialNo
     */
    public void setExternalSerialNo(String externalSerialNo) {
        this.externalSerialNo = externalSerialNo;
    }

    /**
     * 获取记录单处理状态
     *
     * @return status - 记录单处理状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置记录单处理状态
     *
     * @param status 记录单处理状态
     */
    public void setStatus(Integer status) {
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