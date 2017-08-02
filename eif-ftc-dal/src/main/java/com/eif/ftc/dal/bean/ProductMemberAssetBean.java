package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/3/22.
 */
public class ProductMemberAssetBean {
    /**
     * 产品Id
     */
    private Long productId;

    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 交易账号
     */
    private String transactionAccountId;


    /**
     * 本金
     */
    private BigDecimal capital;



    /**
     * 预期收益
     */
    private BigDecimal expectBonusAmount;


    /**
     * TA收益
     */

    private BigDecimal currentBonusAmount;



    /**
     * 加息金额
     */
    private BigDecimal couponAmount;

    /**
     * 团购活动奖励金额
     */
    private BigDecimal grouponBonus;


    /**
     * 产品类型
     */
    private Integer closeType;


    /**
     * TA账号
     */
    private String taAccountId;



    /**
     * 资产账户号
     */
    private String assetAccountNo;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getTransactionAccountId() {
        return transactionAccountId;
    }

    public void setTransactionAccountId(String transactionAccountId) {
        this.transactionAccountId = transactionAccountId;
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

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getGrouponBonus() {
        return grouponBonus;
    }

    public void setGrouponBonus(BigDecimal grouponBonus) {
        this.grouponBonus = grouponBonus;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public String getTaAccountId() {
        return taAccountId;
    }

    public void setTaAccountId(String taAccountId) {
        this.taAccountId = taAccountId;
    }

    public String getAssetAccountNo() {
        return assetAccountNo;
    }

    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }
}
