package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_amc_fund_detail_alteration")
public class FundDetailAlteration {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金资产明细变动UUID
     */
    @Column(name = "detail_alteration_uuid")
    private String detailAlterationUuid;

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
     * 该只基金的总份额
     */
    @Column(name = "fund_share")
    private BigDecimal fundShare;

    /**
     * 该只基金的总金额
     */
    @Column(name = "fund_amount")
    private BigDecimal fundAmount;

    /**
     * 基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消
     */
    @Column(name = "alteration_status")
    private Integer alterationStatus;

    /**
     * 基金变动类型，0-增加，1-减少
     */
    @Column(name = "alteration_type")
    private Integer alterationType;

    /**
     * 金融交易系统单号
     */
    @Column(name = "ftc_order_no")
    private String ftcOrderNo;

    /**
     * FTC单号创建时间
     */
    @Column(name = "ftc_order_create_time")
    private Date ftcOrderCreateTime;

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
     * @return detail_alteration_uuid - 基金资产明细变动UUID
     */
    public String getDetailAlterationUuid() {
        return detailAlterationUuid;
    }

    /**
     * 设置基金资产明细变动UUID
     *
     * @param detailAlterationUuid 基金资产明细变动UUID
     */
    public void setDetailAlterationUuid(String detailAlterationUuid) {
        this.detailAlterationUuid = detailAlterationUuid;
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
     * 获取该只基金的总份额
     *
     * @return fund_share - 该只基金的总份额
     */
    public BigDecimal getFundShare() {
        return fundShare;
    }

    /**
     * 设置该只基金的总份额
     *
     * @param fundShare 该只基金的总份额
     */
    public void setFundShare(BigDecimal fundShare) {
        this.fundShare = fundShare;
    }

    /**
     * 获取该只基金的总金额
     *
     * @return fund_amount - 该只基金的总金额
     */
    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * 设置该只基金的总金额
     *
     * @param fundAmount 该只基金的总金额
     */
    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    /**
     * 获取基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消
     *
     * @return alteration_status - 基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消
     */
    public Integer getAlterationStatus() {
        return alterationStatus;
    }

    /**
     * 设置基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消
     *
     * @param alterationStatus 基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消
     */
    public void setAlterationStatus(Integer alterationStatus) {
        this.alterationStatus = alterationStatus;
    }

    /**
     * 获取基金变动类型，0-增加，1-减少
     *
     * @return alteration_type - 基金变动类型，0-增加，1-减少
     */
    public Integer getAlterationType() {
        return alterationType;
    }

    /**
     * 设置基金变动类型，0-增加，1-减少
     *
     * @param alterationType 基金变动类型，0-增加，1-减少
     */
    public void setAlterationType(Integer alterationType) {
        this.alterationType = alterationType;
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
     * 获取FTC单号创建时间
     *
     * @return ftc_order_create_time - FTC单号创建时间
     */
    public Date getFtcOrderCreateTime() {
        return ftcOrderCreateTime;
    }

    /**
     * 设置FTC单号创建时间
     *
     * @param ftcOrderCreateTime FTC单号创建时间
     */
    public void setFtcOrderCreateTime(Date ftcOrderCreateTime) {
        this.ftcOrderCreateTime = ftcOrderCreateTime;
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
}