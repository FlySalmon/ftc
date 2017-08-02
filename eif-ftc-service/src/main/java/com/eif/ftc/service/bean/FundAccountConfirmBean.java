package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/21.
 */
public class FundAccountConfirmBean {



    /**
     *  ftc的开户业务单,所属表字段为t_amc_mem_fund_acc.ftc_create_acc_no
     */
    private String ftcCreateAccountNo;


    /**
     *  账户状态，1-创建中，2-开户成功，3-开户失败,所属表字段为t_amc_mem_fund_acc.account_status
     */
    private Integer accountStatus;

    /**
     *  TA账户号,所属表字段为t_amc_mem_fund_acc.ta_account
     */
    private String taAccountId;


    public String getFtcCreateAccountNo() {
        return ftcCreateAccountNo;
    }

    /**
     * @param ftcCreateAccountNo 创建用户单号(原始单号)
     * @occurs required
     */
    public void setFtcCreateAccountNo(String ftcCreateAccountNo) {
        this.ftcCreateAccountNo = ftcCreateAccountNo;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus 账户状态 1 - 基金公司开户中, 2 - 基金公司开户成功, 3 - 基金公司开户失败, 4 - 清盘 5 - 冻结, 6 - 正常, 7 - 止付
     * @occurs required
     */
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getTaAccountId() {
        return taAccountId;
    }

    /**
     * @param taAccountId TA的用户号
     * @occurs required
     */
    public void setTaAccountId(String taAccountId) {
        this.taAccountId = taAccountId;
    }
}
