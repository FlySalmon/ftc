package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/15.
 */
public class FundAccountBean{


    private Long id;

    /**
     *  用户交易账户UUID,所属表字段为t_amc_mem_fund_acc.fund_acc_uuid
     */
    private String fundAccountNo;

    /**
     *  ftc的开户业务单,所属表字段为t_amc_mem_fund_acc.ftc_create_acc_no
     */
    private String ftcCreateAccountNo;


    /**
     *  会员号,所属表字段为t_amc_mem_fund_acc.member_no
     */
    private String MemberNo;

    /**
     *  基金交易账号,所属表字段为t_amc_mem_fund_acc.transaction_account
     */
    private String transactionAccount;

    /**
     *  账户状态，1-创建中，2-开户成功，3-开户失败,所属表字段为t_amc_mem_fund_acc.account_status
     */
    private Integer accountStatus;

    /**
     *  TA账户号,所属表字段为t_amc_mem_fund_acc.ta_account
     */
    private String taAccountId;


    /**
     *  账户状态，风控控制,所属表字段为t_amc_mem_fund_acc.account_risk_status
     */
    private Integer accountRiskStatus;


    /**
     *  @return 用户资金账户主键
     *  @occurs required
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  @return 用户资金账户号
     *  @occurs required
     */
    public String getFundAccountNo() {
        return fundAccountNo;
    }

    public void setFundAccountNo(String fundAccountNo) {
        this.fundAccountNo = fundAccountNo;
    }

    /**
     *  @return ftc创建用户业务单号
     *  @occurs required
     */
    public String getFtcCreateAccountNo() {
        return ftcCreateAccountNo;
    }

    public void setFtcCreateAccountNo(String ftcCreateAccountNo) {
        this.ftcCreateAccountNo = ftcCreateAccountNo;
    }

    /**
     *  @return 用户会员号
     *  @occurs required
     */
    public String getMemberNo() {
        return MemberNo;
    }

    public void setMemberNo(String memberNo) {
        MemberNo = memberNo;
    }

    /**
     *  @return 基金资产平台交易账号(TA用)
     *  @occurs required
     */
    public String getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    /**
     *  @return 账户状态 1 -  基金公司开户中, 2 - 基金公司开户成功, 3 - 基金公司开户失败, 4 - 清盘 5 - 冻结, 6 - 正常, 7 - 止付
     *  @occurs required
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     *  @return TA的用户号
     *  @occurs optional
     */
    public String getTaAccountId() {
        return taAccountId;
    }

    public void setTaAccountId(String taAccountId) {
        this.taAccountId = taAccountId;
    }

    public Integer getAccountRiskStatus() {
        return accountRiskStatus;
    }

    public void setAccountRiskStatus(Integer accountRiskStatus) {
        this.accountRiskStatus = accountRiskStatus;
    }
}
