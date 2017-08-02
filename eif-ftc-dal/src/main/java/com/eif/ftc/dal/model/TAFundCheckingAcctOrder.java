package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_ftc_ta_fund_checking_acct_order")
public class TAFundCheckingAcctOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 持有人可用基金份数
     */
    @Column(name = "available_vol")
    private BigDecimal availableVol;

    /**
     * 基金总份数（含冻结）
     */
    @Column(name = "total_vol_of_dist_in_ta")
    private BigDecimal totalVolOfDistInTa;

    /**
     * ta投资人基金交易账号
     */
    @Column(name = "transaction_account_id")
    private String transactionAccountId;

    /**
     * ta销售人代码
     */
    @Column(name = "distributor_code")
    private String distributorCode;

    /**
     * 网点号码, 同销售人代码
     */
    @Column(name = "branch_code")
    private String branchCode;

    /**
     * 基金代码
     */
    @Column(name = "fund_code")
    private String fundCode;

    /**
     * 投资人基金账号
     */
    @Column(name = "ta_account_id")
    private String taAccountId;

    /**
     * ta交易确认日期,格式为：YYYYMMDD
            
     */
    @Column(name = "transaction_cfm_date")
    private String transactionCfmDate;

    /**
     * 收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金
     */
    @Column(name = "share_class")
    private String shareClass;

    /**
     * 明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            
     */
    @Column(name = "detail_flag")
    private String detailFlag;

    /**
     * 货币基金未付收益金额, 对货币基金，明细标志为0时必填
     */
    @Column(name = "undistribute_monetary_income")
    private BigDecimal undistributeMonetaryIncome;

    /**
     * 货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填
     */
    @Column(name = "undistribute_monetary_income_flag")
    private String undistributeMonetaryIncomeFlag;

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
     * 获取持有人可用基金份数
     *
     * @return available_vol - 持有人可用基金份数
     */
    public BigDecimal getAvailableVol() {
        return availableVol;
    }

    /**
     * 设置持有人可用基金份数
     *
     * @param availableVol 持有人可用基金份数
     */
    public void setAvailableVol(BigDecimal availableVol) {
        this.availableVol = availableVol;
    }

    /**
     * 获取基金总份数（含冻结）
     *
     * @return total_vol_of_dist_in_ta - 基金总份数（含冻结）
     */
    public BigDecimal getTotalVolOfDistInTa() {
        return totalVolOfDistInTa;
    }

    /**
     * 设置基金总份数（含冻结）
     *
     * @param totalVolOfDistInTa 基金总份数（含冻结）
     */
    public void setTotalVolOfDistInTa(BigDecimal totalVolOfDistInTa) {
        this.totalVolOfDistInTa = totalVolOfDistInTa;
    }

    /**
     * 获取ta投资人基金交易账号
     *
     * @return transaction_account_id - ta投资人基金交易账号
     */
    public String getTransactionAccountId() {
        return transactionAccountId;
    }

    /**
     * 设置ta投资人基金交易账号
     *
     * @param transactionAccountId ta投资人基金交易账号
     */
    public void setTransactionAccountId(String transactionAccountId) {
        this.transactionAccountId = transactionAccountId;
    }

    /**
     * 获取ta销售人代码
     *
     * @return distributor_code - ta销售人代码
     */
    public String getDistributorCode() {
        return distributorCode;
    }

    /**
     * 设置ta销售人代码
     *
     * @param distributorCode ta销售人代码
     */
    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    /**
     * 获取网点号码, 同销售人代码
     *
     * @return branch_code - 网点号码, 同销售人代码
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * 设置网点号码, 同销售人代码
     *
     * @param branchCode 网点号码, 同销售人代码
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * 获取基金代码
     *
     * @return fund_code - 基金代码
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 设置基金代码
     *
     * @param fundCode 基金代码
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
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
     * 获取明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            
     *
     * @return detail_flag - 明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            
     */
    public String getDetailFlag() {
        return detailFlag;
    }

    /**
     * 设置明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            
     *
     * @param detailFlag 明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            
     */
    public void setDetailFlag(String detailFlag) {
        this.detailFlag = detailFlag;
    }

    /**
     * 获取货币基金未付收益金额, 对货币基金，明细标志为0时必填
     *
     * @return undistribute_monetary_income - 货币基金未付收益金额, 对货币基金，明细标志为0时必填
     */
    public BigDecimal getUndistributeMonetaryIncome() {
        return undistributeMonetaryIncome;
    }

    /**
     * 设置货币基金未付收益金额, 对货币基金，明细标志为0时必填
     *
     * @param undistributeMonetaryIncome 货币基金未付收益金额, 对货币基金，明细标志为0时必填
     */
    public void setUndistributeMonetaryIncome(BigDecimal undistributeMonetaryIncome) {
        this.undistributeMonetaryIncome = undistributeMonetaryIncome;
    }

    /**
     * 获取货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填
     *
     * @return undistribute_monetary_income_flag - 货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填
     */
    public String getUndistributeMonetaryIncomeFlag() {
        return undistributeMonetaryIncomeFlag;
    }

    /**
     * 设置货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填
     *
     * @param undistributeMonetaryIncomeFlag 货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填
     */
    public void setUndistributeMonetaryIncomeFlag(String undistributeMonetaryIncomeFlag) {
        this.undistributeMonetaryIncomeFlag = undistributeMonetaryIncomeFlag;
    }
}