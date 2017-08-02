package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/4/20.
 */
public class AssetAndFundInfoResponse extends BaseResponse {

    private static final long serialVersionUID = -5065567906918408586L;

    private String accountNo;

    private String transactionAccount;

    private String tAAccount;

    private BigDecimal totalAmount;

    private BigDecimal confirmedAddAmout;

    private BigDecimal unConfirmedAddAmount;

    private BigDecimal unConfirmedSubAmount;

    private BigDecimal totalBonusAmount;

    /**
     * @return 资产账户号
     * @occurs required
     */

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }



    /**
     * @return 基金交易账号
     * @occurs required
     */
    public String getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }


    /**
     * @return TA账号
     * @occurs required
     */
    public String gettAAccount() {
        return tAAccount;
    }

    public void settAAccount(String tAAccount) {
        this.tAAccount = tAAccount;
    }


    /**
     * @return 总资产金额
     * @occurs required
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }



    /**
     * @return 确认增加资产
     * @occurs required
     */
    public BigDecimal getConfirmedAddAmout() {
        return confirmedAddAmout;
    }

    public void setConfirmedAddAmout(BigDecimal confirmedAddAmout) {
        this.confirmedAddAmout = confirmedAddAmout;
    }


    /**
     * @return 未确认增加资产
     * @occurs required
     */
    public BigDecimal getUnConfirmedAddAmount() {
        return unConfirmedAddAmount;
    }

    public void setUnConfirmedAddAmount(BigDecimal unConfirmedAddAmount) {
        this.unConfirmedAddAmount = unConfirmedAddAmount;
    }


    /**
     * @return 未确认扣减资产
     * @occurs required
     */
    public BigDecimal getUnConfirmedSubAmount() {
        return unConfirmedSubAmount;
    }

    public void setUnConfirmedSubAmount(BigDecimal unConfirmedSubAmount) {
        this.unConfirmedSubAmount = unConfirmedSubAmount;
    }


    /**
     * @return 红利总收益
     * @occurs required
     */
    public BigDecimal getTotalBonusAmount() {
        return totalBonusAmount;
    }

    public void setTotalBonusAmount(BigDecimal totalBonusAmount) {
        this.totalBonusAmount = totalBonusAmount;
    }

}
