package com.eif.ftc.facade.fund.amc.dto.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 16/5/4.
 */
public class FundOfflineAssetBean {

    private String memberNo;

    private Long productId;

    private BigDecimal settlementCapital;

    private BigDecimal totalProfit;

    private BigDecimal bonusAmount;

    private BigDecimal profitAmount;

    private String customerPhone;

    private String customerName;

    private String customerCardNo;

    private String productName;

    private String offlineCode;


    private Date inceptionDate;

    private Date dueDate;


    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }

    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOfflineCode() {
        return offlineCode;
    }

    public void setOfflineCode(String offlineCode) {
        this.offlineCode = offlineCode;
    }
}
