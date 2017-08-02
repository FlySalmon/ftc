package com.eif.ftc.facade.fund.amc.dto;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 2017/4/24.
 */
public class MemberAssetBean extends BaseDTO{

    private static final long serialVersionUID = -967787157728481170L;

    private Long productId;

    private BigDecimal totalAmount;

    private BigDecimal confirmedAddAmount;

    private BigDecimal unConfirmedAddAmount;

    private BigDecimal unConfirmedSubAmount;

    private BigDecimal totalBonusAmount;

    private BigDecimal totalProfitAmount;

    private BigDecimal yesterdayProfit;

    private BigDecimal expectProfitAmount;

    private BigDecimal settlementCapital;

    private BigDecimal expectBonusAmount;

    private BigDecimal grouponAmount;

    private Integer closeType;

    private Integer hasSettlement;

    private Date settlementDate;

    /**
     * @return 产品id
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
    public BigDecimal getConfirmedAddAmount() {
        return confirmedAddAmount;
    }

    public void setConfirmedAddAmount(BigDecimal confirmedAddAmount) {
        this.confirmedAddAmount = confirmedAddAmount;
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


    /**
     * @return 总红利
     * @occurs required
     */
    public BigDecimal getTotalProfitAmount() {
        return totalProfitAmount;
    }

    public void setTotalProfitAmount(BigDecimal totalProfitAmount) {
        this.totalProfitAmount = totalProfitAmount;
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
     * @return 预期收益
     * @occurs required
     */
    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
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
     * @return 红利收益
     * @occurs required
     */
    public BigDecimal getExpectBonusAmount() {
        return expectBonusAmount;
    }

    public void setExpectBonusAmount(BigDecimal expectBonusAmount) {
        this.expectBonusAmount = expectBonusAmount;
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
     * @return 封闭类型
     * @occurs required
     */
    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    /**
     * @return 资产是否清盘
     * @occurs required
     */
    public Integer getHasSettlement() {
        return hasSettlement;
    }

    public void setHasSettlement(Integer hasSettlement) {
        this.hasSettlement = hasSettlement;
    }

    /**
     * @return 清盘日期
     * @occurs required
     */
    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}
