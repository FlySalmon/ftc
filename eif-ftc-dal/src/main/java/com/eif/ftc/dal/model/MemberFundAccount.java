package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_mem_fund_acc")
public class MemberFundAccount {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户交易账户UUID
     */
    @Column(name = "fund_acc_uuid")
    private String fundAccUuid;

    /**
     * ftc的开户业务单
     */
    @Column(name = "ftc_create_acc_no")
    private String ftcCreateAccNo;

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
     * 基金交易账号
     */
    @Column(name = "transaction_account")
    private String transactionAccount;

    /**
     * 基金公司号，根据购买的产品确定（我们平台对TA的编号）
     */
    @Column(name = "ta_company_no")
    private Long taCompanyNo;

    /**
     * 账户状态，1-创建中，2-开户成功，3-开户失败
     */
    @Column(name = "account_status")
    private Integer accountStatus;

    /**
     * TA账户号
     */
    @Column(name = "ta_account")
    private String taAccount;

    /**
     * FTC创建时间，跑批处理用
     */
    @Column(name = "ftc_create_time")
    private Date ftcCreateTime;

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
     * 风控状态，1-通过，2-禁止交易
     */
    @Column(name = "account_risk_status")
    private Integer accountRiskStatus;

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
     * 获取用户交易账户UUID
     *
     * @return fund_acc_uuid - 用户交易账户UUID
     */
    public String getFundAccUuid() {
        return fundAccUuid;
    }

    /**
     * 设置用户交易账户UUID
     *
     * @param fundAccUuid 用户交易账户UUID
     */
    public void setFundAccUuid(String fundAccUuid) {
        this.fundAccUuid = fundAccUuid;
    }

    /**
     * 获取ftc的开户业务单
     *
     * @return ftc_create_acc_no - ftc的开户业务单
     */
    public String getFtcCreateAccNo() {
        return ftcCreateAccNo;
    }

    /**
     * 设置ftc的开户业务单
     *
     * @param ftcCreateAccNo ftc的开户业务单
     */
    public void setFtcCreateAccNo(String ftcCreateAccNo) {
        this.ftcCreateAccNo = ftcCreateAccNo;
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
     * 获取基金交易账号
     *
     * @return transaction_account - 基金交易账号
     */
    public String getTransactionAccount() {
        return transactionAccount;
    }

    /**
     * 设置基金交易账号
     *
     * @param transactionAccount 基金交易账号
     */
    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    /**
     * 获取基金公司号，根据购买的产品确定（我们平台对TA的编号）
     *
     * @return ta_company_no - 基金公司号，根据购买的产品确定（我们平台对TA的编号）
     */
    public Long getTaCompanyNo() {
        return taCompanyNo;
    }

    /**
     * 设置基金公司号，根据购买的产品确定（我们平台对TA的编号）
     *
     * @param taCompanyNo 基金公司号，根据购买的产品确定（我们平台对TA的编号）
     */
    public void setTaCompanyNo(Long taCompanyNo) {
        this.taCompanyNo = taCompanyNo;
    }

    /**
     * 获取账户状态，1-创建中，2-开户成功，3-开户失败
     *
     * @return account_status - 账户状态，1-创建中，2-开户成功，3-开户失败
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    /**
     * 设置账户状态，1-创建中，2-开户成功，3-开户失败
     *
     * @param accountStatus 账户状态，1-创建中，2-开户成功，3-开户失败
     */
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * 获取TA账户号
     *
     * @return ta_account - TA账户号
     */
    public String getTaAccount() {
        return taAccount;
    }

    /**
     * 设置TA账户号
     *
     * @param taAccount TA账户号
     */
    public void setTaAccount(String taAccount) {
        this.taAccount = taAccount;
    }

    /**
     * 获取FTC创建时间，跑批处理用
     *
     * @return ftc_create_time - FTC创建时间，跑批处理用
     */
    public Date getFtcCreateTime() {
        return ftcCreateTime;
    }

    /**
     * 设置FTC创建时间，跑批处理用
     *
     * @param ftcCreateTime FTC创建时间，跑批处理用
     */
    public void setFtcCreateTime(Date ftcCreateTime) {
        this.ftcCreateTime = ftcCreateTime;
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
     * @return account_risk_status
     */
    public Integer getAccountRiskStatus() {
        return accountRiskStatus;
    }

    /**
     * @param accountRiskStatus
     */
    public void setAccountRiskStatus(Integer accountRiskStatus) {
        this.accountRiskStatus = accountRiskStatus;
    }
}