package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_profit_alteration")
public class FundProfitAlteration {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金资产明细变动UUID
     */
    @Column(name = "profit_alteration_uuid")
    private String profitAlterationUuid;

    /**
     * 基金资产明细ID
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
     * 该只基金加息的金额
     */
    @Column(name = "profit_amount")
    private BigDecimal profitAmount;

    /**
     * 金融交易系统单号
     */
    @Column(name = "ftc_order_no")
    private String ftcOrderNo;

    /**
     * 加息时间
     */
    @Column(name = "profit_add_time")
    private Date profitAddTime;

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
     * 0-加息券，1-团购贴息，2-活包定贴息
     */
    @Column(name = "profit_type")
    private Integer profitType;

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
     * 获取基金资产明细变动UUID
     *
     * @return profit_alteration_uuid - 基金资产明细变动UUID
     */
    public String getProfitAlterationUuid() {
        return profitAlterationUuid;
    }

    /**
     * 设置基金资产明细变动UUID
     *
     * @param profitAlterationUuid 基金资产明细变动UUID
     */
    public void setProfitAlterationUuid(String profitAlterationUuid) {
        this.profitAlterationUuid = profitAlterationUuid;
    }

    /**
     * 获取基金资产明细ID
     *
     * @return fund_detail_id - 基金资产明细ID
     */
    public Long getFundDetailId() {
        return fundDetailId;
    }

    /**
     * 设置基金资产明细ID
     *
     * @param fundDetailId 基金资产明细ID
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
     * 获取该只基金加息的金额
     *
     * @return profit_amount - 该只基金加息的金额
     */
    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    /**
     * 设置该只基金加息的金额
     *
     * @param profitAmount 该只基金加息的金额
     */
    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    /**
     * 获取金融交易系统单号
     *
     * @return ftc_order_no - 金融交易系统单号
     */
    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * 设置金融交易系统单号
     *
     * @param ftcOrderNo 金融交易系统单号
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    /**
     * 获取加息时间
     *
     * @return profit_add_time - 加息时间
     */
    public Date getProfitAddTime() {
        return profitAddTime;
    }

    /**
     * 设置加息时间
     *
     * @param profitAddTime 加息时间
     */
    public void setProfitAddTime(Date profitAddTime) {
        this.profitAddTime = profitAddTime;
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
     * 获取0-加息券，1-团购贴息，2-活包定贴息
     *
     * @return profit_type - 0-加息券，1-团购贴息，2-活包定贴息
     */
    public Integer getProfitType() {
        return profitType;
    }

    /**
     * 设置0-加息券，1-团购贴息，2-活包定贴息
     *
     * @param profitType 0-加息券，1-团购贴息，2-活包定贴息
     */
    public void setProfitType(Integer profitType) {
        this.profitType = profitType;
    }
}