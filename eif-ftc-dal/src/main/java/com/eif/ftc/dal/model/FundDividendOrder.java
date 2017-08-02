package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_dividend")
public class FundDividendOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金分红业务单号
     */
    @Column(name = "fund_dividend_order_no")
    private String fundDividendOrderNo;

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
     * 会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 投资人基金交易账号
     */
    @Column(name = "transaction_account_id")
    private String transactionAccountId;

    /**
     * 投资人基金账号
     */
    @Column(name = "ta_account_id")
    private String taAccountId;

    /**
     * 交易确认日期,格式为：YYYYMMDD
            
     */
    @Column(name = "transaction_cfm_date")
    private String transactionCfmDate;

    /**
     * 每笔交易确认金额,含所有费用的总金额
     */
    @Column(name = "confirmed_amount")
    private BigDecimal confirmedAmount;

    /**
     * 手续费,投资人应付总手续费
     */
    private BigDecimal charge;

    /**
     * 代理费, 手续费中划归销售人的部分
            
     */
    @Column(name = "agency_fee")
    private BigDecimal agencyFee;

    /**
     * 过户费,
     */
    @Column(name = "transfer_fee")
    private BigDecimal transferFee;

    /**
     * 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    @Column(name = "share_class")
    private String shareClass;

    /**
     * 分红单位, 举例：每千份分多少，则分红单位就为一千
     */
    @Column(name = "draw_bonus_unit")
    private BigDecimal drawBonusUnit;

    /**
     * 分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理
     */
    @Column(name = "dividend_type")
    private String dividendType;

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
     * 结算币种,具体编码依GB/T 12406-2008
            
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 红利/红利再投资基数,登记日基金持有人的基金份数
     */
    @Column(name = "basisfor_calculating_dividend")
    private BigDecimal basisforCalculatingDividend;

    /**
     * 分红日/发放日
     */
    @Column(name = "divident_date")
    private String dividentDate;

    /**
     * 基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
     */
    @Column(name = "vol_of_dividend_for_reinvestment")
    private BigDecimal volOfDividendForReinvestment;

    /**
     * 基金账户红利资金,红利总金额,含冻结红利及再投资的红利
     */
    @Column(name = "dividend_amount")
    private BigDecimal dividendAmount;

    /**
     * 贴息金额
     */
    @Column(name = "discount_interest")
    private BigDecimal discountInterest;

    /**
     * 除权日
     */
    @Column(name = "xr_rate")
    private String xrRate;

    /**
     * 权益登记日期, 格式为：YYYYMMDD
     */
    @Column(name = "registration_date")
    private String registrationDate;

    /**
     * 单位基金分红金额（含税）,举例：每千份分两元，则此处填2。
     */
    @Column(name = "dividend_per_unit")
    private BigDecimal dividendPerUnit;

    /**
     * 默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式
     */
    @Column(name = "def_dividend_method")
    private String defDividendMethod;

    /**
     * 交易核心交易单流水号
     */
    @Column(name = "transcore_trans_no")
    private String transcoreTransNo;

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
     * 获取基金分红业务单号
     *
     * @return fund_dividend_order_no - 基金分红业务单号
     */
    public String getFundDividendOrderNo() {
        return fundDividendOrderNo;
    }

    /**
     * 设置基金分红业务单号
     *
     * @param fundDividendOrderNo 基金分红业务单号
     */
    public void setFundDividendOrderNo(String fundDividendOrderNo) {
        this.fundDividendOrderNo = fundDividendOrderNo;
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
     * 获取投资人基金账号
     *
     * @return ta_account_id - 投资人基金账号
     */
    public String getTaAccountId() {
        return taAccountId;
    }

    /**
     * 设置投资人基金账号
     *
     * @param taAccountId 投资人基金账号
     */
    public void setTaAccountId(String taAccountId) {
        this.taAccountId = taAccountId;
    }

    /**
     * 获取交易确认日期,格式为：YYYYMMDD
            
     *
     * @return transaction_cfm_date - 交易确认日期,格式为：YYYYMMDD
            
     */
    public String getTransactionCfmDate() {
        return transactionCfmDate;
    }

    /**
     * 设置交易确认日期,格式为：YYYYMMDD
            
     *
     * @param transactionCfmDate 交易确认日期,格式为：YYYYMMDD
            
     */
    public void setTransactionCfmDate(String transactionCfmDate) {
        this.transactionCfmDate = transactionCfmDate;
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
     * 获取手续费,投资人应付总手续费
     *
     * @return charge - 手续费,投资人应付总手续费
     */
    public BigDecimal getCharge() {
        return charge;
    }

    /**
     * 设置手续费,投资人应付总手续费
     *
     * @param charge 手续费,投资人应付总手续费
     */
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    /**
     * 获取代理费, 手续费中划归销售人的部分
            
     *
     * @return agency_fee - 代理费, 手续费中划归销售人的部分
            
     */
    public BigDecimal getAgencyFee() {
        return agencyFee;
    }

    /**
     * 设置代理费, 手续费中划归销售人的部分
            
     *
     * @param agencyFee 代理费, 手续费中划归销售人的部分
            
     */
    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    /**
     * 获取过户费,
     *
     * @return transfer_fee - 过户费,
     */
    public BigDecimal getTransferFee() {
        return transferFee;
    }

    /**
     * 设置过户费,
     *
     * @param transferFee 过户费,
     */
    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
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
     * 获取分红单位, 举例：每千份分多少，则分红单位就为一千
     *
     * @return draw_bonus_unit - 分红单位, 举例：每千份分多少，则分红单位就为一千
     */
    public BigDecimal getDrawBonusUnit() {
        return drawBonusUnit;
    }

    /**
     * 设置分红单位, 举例：每千份分多少，则分红单位就为一千
     *
     * @param drawBonusUnit 分红单位, 举例：每千份分多少，则分红单位就为一千
     */
    public void setDrawBonusUnit(BigDecimal drawBonusUnit) {
        this.drawBonusUnit = drawBonusUnit;
    }

    /**
     * 获取分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理
     *
     * @return dividend_type - 分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理
     */
    public String getDividendType() {
        return dividendType;
    }

    /**
     * 设置分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理
     *
     * @param dividendType 分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理
     */
    public void setDividendType(String dividendType) {
        this.dividendType = dividendType;
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
     * 获取红利/红利再投资基数,登记日基金持有人的基金份数
     *
     * @return basisfor_calculating_dividend - 红利/红利再投资基数,登记日基金持有人的基金份数
     */
    public BigDecimal getBasisforCalculatingDividend() {
        return basisforCalculatingDividend;
    }

    /**
     * 设置红利/红利再投资基数,登记日基金持有人的基金份数
     *
     * @param basisforCalculatingDividend 红利/红利再投资基数,登记日基金持有人的基金份数
     */
    public void setBasisforCalculatingDividend(BigDecimal basisforCalculatingDividend) {
        this.basisforCalculatingDividend = basisforCalculatingDividend;
    }

    /**
     * 获取分红日/发放日
     *
     * @return divident_date - 分红日/发放日
     */
    public String getDividentDate() {
        return dividentDate;
    }

    /**
     * 设置分红日/发放日
     *
     * @param dividentDate 分红日/发放日
     */
    public void setDividentDate(String dividentDate) {
        this.dividentDate = dividentDate;
    }

    /**
     * 获取基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
     *
     * @return vol_of_dividend_for_reinvestment - 基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
     */
    public BigDecimal getVolOfDividendForReinvestment() {
        return volOfDividendForReinvestment;
    }

    /**
     * 设置基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
     *
     * @param volOfDividendForReinvestment 基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股
     */
    public void setVolOfDividendForReinvestment(BigDecimal volOfDividendForReinvestment) {
        this.volOfDividendForReinvestment = volOfDividendForReinvestment;
    }

    /**
     * 获取基金账户红利资金,红利总金额,含冻结红利及再投资的红利
     *
     * @return dividend_amount - 基金账户红利资金,红利总金额,含冻结红利及再投资的红利
     */
    public BigDecimal getDividendAmount() {
        return dividendAmount;
    }

    /**
     * 设置基金账户红利资金,红利总金额,含冻结红利及再投资的红利
     *
     * @param dividendAmount 基金账户红利资金,红利总金额,含冻结红利及再投资的红利
     */
    public void setDividendAmount(BigDecimal dividendAmount) {
        this.dividendAmount = dividendAmount;
    }

    /**
     * 获取贴息金额
     *
     * @return discount_interest - 贴息金额
     */
	public BigDecimal getDiscountInterest() {
		return discountInterest;
	}

    /**
     * 设置贴息金额
     *
     * @param discountInterest 贴息金额
     */
	public void setDiscountInterest(BigDecimal discountInterest) {
		this.discountInterest = discountInterest;
	}

    /**
     * 获取除权日
     *
     * @return xr_rate - 除权日
     */
    public String getXrRate() {
        return xrRate;
    }

    /**
     * 设置除权日
     *
     * @param xrRate 除权日
     */
    public void setXrRate(String xrRate) {
        this.xrRate = xrRate;
    }

    /**
     * 获取权益登记日期, 格式为：YYYYMMDD
     *
     * @return registration_date - 权益登记日期, 格式为：YYYYMMDD
     */
    public String getRegistrationDate() {
        return registrationDate;
    }

    /**
     * 设置权益登记日期, 格式为：YYYYMMDD
     *
     * @param registrationDate 权益登记日期, 格式为：YYYYMMDD
     */
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * 获取单位基金分红金额（含税）,举例：每千份分两元，则此处填2。
     *
     * @return dividend_per_unit - 单位基金分红金额（含税）,举例：每千份分两元，则此处填2。
     */
    public BigDecimal getDividendPerUnit() {
        return dividendPerUnit;
    }

    /**
     * 设置单位基金分红金额（含税）,举例：每千份分两元，则此处填2。
     *
     * @param dividendPerUnit 单位基金分红金额（含税）,举例：每千份分两元，则此处填2。
     */
    public void setDividendPerUnit(BigDecimal dividendPerUnit) {
        this.dividendPerUnit = dividendPerUnit;
    }

    /**
     * 获取默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式
     *
     * @return def_dividend_method - 默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式
     */
    public String getDefDividendMethod() {
        return defDividendMethod;
    }

    /**
     * 设置默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式
     *
     * @param defDividendMethod 默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式
     */
    public void setDefDividendMethod(String defDividendMethod) {
        this.defDividendMethod = defDividendMethod;
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