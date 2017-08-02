package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_purchase_daily_vol")
public class FundPurchaseDailyVol {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 购买金额
     */
    private BigDecimal amount;

    /**
     * 购买份额
     */
    private BigDecimal shares;

    /**
     * 记录日期
     */
    @Column(name = "record_time")
    private Date recordTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取购买金额
     *
     * @return amount - 购买金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置购买金额
     *
     * @param amount 购买金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取购买份额
     *
     * @return shares - 购买份额
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置购买份额
     *
     * @param shares 购买份额
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    /**
     * 获取记录日期
     *
     * @return record_time - 记录日期
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * 设置记录日期
     *
     * @param recordTime 记录日期
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}