package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_trans_order_status_info")
public class FundTransOrderStatusInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 基金交易业务单主表id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 基金业务单状态
     */
    private Integer status;

    /**
     * 状态迁移时间
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
     * 获取基金交易业务单主表id
     *
     * @return order_id - 基金交易业务单主表id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置基金交易业务单主表id
     *
     * @param orderId 基金交易业务单主表id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
     * 获取状态迁移时间
     *
     * @return update_time - 状态迁移时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置状态迁移时间
     *
     * @param updateTime 状态迁移时间
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