package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
public class FundTotalAssetBean extends BaseDTO {

    private static final long serialVersionUID = -5763618758801732575L;

    private BigDecimal totalAmount;

    private BigDecimal frozenAmount;

    private BigDecimal activeAmount;

    private BigDecimal confirmedAmount;

    private BigDecimal unconfirmedAmount;

    private BigDecimal expectProfitAmount;

    private BigDecimal expectBonusAmount;

    private BigDecimal unconfirmedSubAmount;

    private BigDecimal yesterdayProfit;

    private BigDecimal totalProfit;

    private BigDecimal grouponAmount;

    private Byte exchangeStatus;

    private BigDecimal settlementCapital;

    private Date settlementDate;


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return 用户基金总资产
     * @occurs required
     */
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }


    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    /**
     * @return 用户活动中资产
     * @occurs required
     */
    public BigDecimal getActiveAmount() {
        return activeAmount;
    }

    public void setActiveAmount(BigDecimal activeAmount) {
        this.activeAmount = activeAmount;
    }

    /**
     * @return 用户已确认资产
     * @occurs required
     */
    public BigDecimal getConfirmedAmount() {
        return confirmedAmount;
    }

    public void setConfirmedAmount(BigDecimal confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }

    /**
     * @return 用户未确认资产
     * @occurs required
     */
    public BigDecimal getUnconfirmedAmount() {
        return unconfirmedAmount;
    }

    public void setUnconfirmedAmount(BigDecimal unconfirmedAmount) {
        this.unconfirmedAmount = unconfirmedAmount;
    }

    /**
     * @return 用户预期加息收益
     * @occurs required
     */
    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
    }


    /**
     * @return 预期红利收益
     * @occurs required
     */
    public BigDecimal getExpectBonusAmount() {
        return expectBonusAmount;
    }

    public void setExpectBonusAmount(BigDecimal expectBonusAmount) {
        this.expectBonusAmount = expectBonusAmount;
    }


    /**
     * @return 未确认减少收益
     * @occurs required
     */
    public BigDecimal getUnconfirmedSubAmount() {
        return unconfirmedSubAmount;
    }

    public void setUnconfirmedSubAmount(BigDecimal unconfirmedSubAmount) {
        this.unconfirmedSubAmount = unconfirmedSubAmount;
    }


    /**
     * @return 昨日收益
     * @occurs required
     */
    public BigDecimal getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }


    /**
     * @return 总收益
     * @occurs required
     */
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }


    /**
     * @return 团购收益
     * @occurs required
     */
    public BigDecimal getGrouponAmount() {
        return grouponAmount;
    }

    public void setGrouponAmount(BigDecimal grouponAmount) {
        this.grouponAmount = grouponAmount;
    }

    /**
     * @return 二级市场交易状态
     * @occurs required
     */
    public Byte getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Byte exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }


    /**
     * @return 投资本金
     * @occurs required
     */
    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }

    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
    }


    /**
     * @return 清盘时间
     * @occurs required
     */
    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}
