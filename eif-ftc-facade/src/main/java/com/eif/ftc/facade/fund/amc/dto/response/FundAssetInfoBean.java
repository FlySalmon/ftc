package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/29.
 */
public class FundAssetInfoBean extends BaseDTO {

    private static final long serialVersionUID = 4482569038709671849L;

    private Long productId;

    private BigDecimal totalAmount;

    private BigDecimal totalProfit;

    private BigDecimal yesterdayProfit;

    private Date settlementDate;

    private BigDecimal expectProfitAmount;

    private BigDecimal settlementCapital;

    private BigDecimal expectBonusAmount;

    private BigDecimal grouponAmount;

    private Integer hasSettlement;

    private Byte exchangeStatus;

    private String fundDetailUuid;

    /**
     * @return 产品号
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return 总金额
     * @occurs required
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
     * @return 清盘时间
     * @occurs required
     */
    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * @return 预期加息收益
     * @occurs required
     */
    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
    }

    /**
     * @return 兑付本金
     * @occurs required
     */
    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }

    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
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
     * @return 预期团购收益
     * @occurs required
     */
    public BigDecimal getGrouponAmount() {
        return grouponAmount;
    }

    public void setGrouponAmount(BigDecimal grouponAmount) {
        this.grouponAmount = grouponAmount;
    }


    /**
     * @return 是否清盘
     * @occurs required
     */
    public Integer getHasSettlement() {
        return hasSettlement;
    }

    public void setHasSettlement(Integer hasSettlement) {
        this.hasSettlement = hasSettlement;
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
     * @return 资产明细uuid
     * @occurs required
     */
    public String getFundDetailUuid() {
        return fundDetailUuid;
    }

    public void setFundDetailUuid(String fundDetailUuid) {
        this.fundDetailUuid = fundDetailUuid;
    }
}
