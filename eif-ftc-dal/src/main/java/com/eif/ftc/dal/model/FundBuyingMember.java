package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_buying_member")
public class FundBuyingMember {
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
     * 购买会员号
     */
    @Column(name = "member_id")
    private String memberId;

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
     * 获取购买会员号
     *
     * @return member_id - 购买会员号
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置购买会员号
     *
     * @param memberId 购买会员号
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
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