package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/8/11.
 */
public class FundTotalUpdateBean {


    private String fundTotalUUID;

    private BigDecimal amount;

    private BigDecimal grouponAmount;

    public String getFundTotalUUID() {
        return fundTotalUUID;
    }

    public void setFundTotalUUID(String fundTotalUUID) {
        this.fundTotalUUID = fundTotalUUID;
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
