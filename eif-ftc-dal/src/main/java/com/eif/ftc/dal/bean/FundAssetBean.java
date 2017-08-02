package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/3/7.
 */
public class FundAssetBean {
    private Long productId;

    private BigDecimal totalAmount;

    private BigDecimal totalBonusAmount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalBonusAmount() {
        return totalBonusAmount;
    }

    public void setTotalBonusAmount(BigDecimal totalBonusAmount) {
        this.totalBonusAmount = totalBonusAmount;
    }
}
