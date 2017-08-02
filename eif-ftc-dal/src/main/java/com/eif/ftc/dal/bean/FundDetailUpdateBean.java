package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/8/11.
 */
public class FundDetailUpdateBean {
    private String fundDetailUUID;
    private BigDecimal amount;
    private BigDecimal grouponAmount;

    public String getFundDetailUUID() {
        return fundDetailUUID;
    }

    public void setFundDetailUUID(String fundDetailUUID) {
        this.fundDetailUUID = fundDetailUUID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getGrouponAmount() {
        return grouponAmount;
    }

    public void setGrouponAmount(BigDecimal grouponAmount) {
        this.grouponAmount = grouponAmount;
    }

}
