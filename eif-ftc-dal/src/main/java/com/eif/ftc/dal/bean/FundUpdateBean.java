package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 2016/11/23.
 */
public class FundUpdateBean {

    private String uuid;

    private BigDecimal capital;

    private BigDecimal expectBonusAmount;

    private BigDecimal currentBonusAmount;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getExpectBonusAmount() {
        return expectBonusAmount;
    }

    public void setExpectBonusAmount(BigDecimal expectBonusAmount) {
        this.expectBonusAmount = expectBonusAmount;
    }

    public BigDecimal getCurrentBonusAmount() {
        return currentBonusAmount;
    }

    public void setCurrentBonusAmount(BigDecimal currentBonusAmount) {
        this.currentBonusAmount = currentBonusAmount;
    }
}
