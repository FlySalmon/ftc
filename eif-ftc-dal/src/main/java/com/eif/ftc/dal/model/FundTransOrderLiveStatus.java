package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_trans_order_live_status")
public class FundTransOrderLiveStatus {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金交易业务单号
     */
    @Column(name = "fund_trans_order_no")
    private String fundTransOrderNo;

    /**
     * 基金业务单状态
     */
    private Integer status;

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
     * 操作人
     */
    @Column(name = "operator_no")
    private String operatorNo;

    /**
     * 备注
     */
    private String remark;

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
     * 获取基金交易业务单号
     *
     * @return fund_trans_order_no - 基金交易业务单号
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    /**
     * 设置基金交易业务单号
     *
     * @param fundTransOrderNo 基金交易业务单号
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    /**
     * 获取基金业务单状态
     *
     * @return status - 基金业务单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置基金业务单状态
     *
     * @param status 基金业务单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取操作人
     *
     * @return operator_no - 操作人
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * 设置操作人
     *
     * @param operatorNo 操作人
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}