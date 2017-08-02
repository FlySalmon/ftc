package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_bonus_detail_alteration")
public class FundBonusDetailAlteration {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "bonus_alteration_uuid")
    private String bonusAlterationUuid;

    /**
     * 基金资产明细id
     */
    @Column(name = "fund_detail_id")
    private Long fundDetailId;

    /**
     * 基金资产明细表的UUID
     */
    @Column(name = "fund_detail_uuid")
    private String fundDetailUuid;

    /**
     * 基金资产汇总表的ID
     */
    @Column(name = "fund_total_id")
    private Long fundTotalId;

    /**
     * 基金资产汇总表的uuid
     */
    @Column(name = "fund_total_uuid")
    private String fundTotalUuid;

    /**
     * FTC红利单号
     */
    @Column(name = "ftc_order_no")
    private String ftcOrderNo;

    /**
     * 该只基金的红利金额
     */
    @Column(name = "fund_bonus_amount")
    private BigDecimal fundBonusAmount;

    /**
     * 红利发放日期
     */
    @Column(name = "bonus_time")
    private Date bonusTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0-活期红利；1-活包定红利；2-定期红利
     */
    @Column(name = "bonus_type")
    private Integer bonusType;

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
     * @return bonus_alteration_uuid
     */
    public String getBonusAlterationUuid() {
        return bonusAlterationUuid;
    }

    /**
     * @param bonusAlterationUuid
     */
    public void setBonusAlterationUuid(String bonusAlterationUuid) {
        this.bonusAlterationUuid = bonusAlterationUuid;
    }

    /**
     * 获取基金资产明细id
     *
     * @return fund_detail_id - 基金资产明细id
     */
    public Long getFundDetailId() {
        return fundDetailId;
    }

    /**
     * 设置基金资产明细id
     *
     * @param fundDetailId 基金资产明细id
     */
    public void setFundDetailId(Long fundDetailId) {
        this.fundDetailId = fundDetailId;
    }

    /**
     * 获取基金资产明细表的UUID
     *
     * @return fund_detail_uuid - 基金资产明细表的UUID
     */
    public String getFundDetailUuid() {
        return fundDetailUuid;
    }

    /**
     * 设置基金资产明细表的UUID
     *
     * @param fundDetailUuid 基金资产明细表的UUID
     */
    public void setFundDetailUuid(String fundDetailUuid) {
        this.fundDetailUuid = fundDetailUuid;
    }

    /**
     * 获取基金资产汇总表的ID
     *
     * @return fund_total_id - 基金资产汇总表的ID
     */
    public Long getFundTotalId() {
        return fundTotalId;
    }

    /**
     * 设置基金资产汇总表的ID
     *
     * @param fundTotalId 基金资产汇总表的ID
     */
    public void setFundTotalId(Long fundTotalId) {
        this.fundTotalId = fundTotalId;
    }

    /**
     * 获取基金资产汇总表的uuid
     *
     * @return fund_total_uuid - 基金资产汇总表的uuid
     */
    public String getFundTotalUuid() {
        return fundTotalUuid;
    }

    /**
     * 设置基金资产汇总表的uuid
     *
     * @param fundTotalUuid 基金资产汇总表的uuid
     */
    public void setFundTotalUuid(String fundTotalUuid) {
        this.fundTotalUuid = fundTotalUuid;
    }

    /**
     * 获取FTC红利单号
     *
     * @return ftc_order_no - FTC红利单号
     */
    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * 设置FTC红利单号
     *
     * @param ftcOrderNo FTC红利单号
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    /**
     * 获取该只基金的红利金额
     *
     * @return fund_bonus_amount - 该只基金的红利金额
     */
    public BigDecimal getFundBonusAmount() {
        return fundBonusAmount;
    }

    /**
     * 设置该只基金的红利金额
     *
     * @param fundBonusAmount 该只基金的红利金额
     */
    public void setFundBonusAmount(BigDecimal fundBonusAmount) {
        this.fundBonusAmount = fundBonusAmount;
    }

    /**
     * 获取红利发放日期
     *
     * @return bonus_time - 红利发放日期
     */
    public Date getBonusTime() {
        return bonusTime;
    }

    /**
     * 设置红利发放日期
     *
     * @param bonusTime 红利发放日期
     */
    public void setBonusTime(Date bonusTime) {
        this.bonusTime = bonusTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取0-活期红利；1-活包定红利；2-定期红利
     *
     * @return bonus_type - 0-活期红利；1-活包定红利；2-定期红利
     */
    public Integer getBonusType() {
        return bonusType;
    }

    /**
     * 设置0-活期红利；1-活包定红利；2-定期红利
     *
     * @param bonusType 0-活期红利；1-活包定红利；2-定期红利
     */
    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }
}