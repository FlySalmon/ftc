package com.eif.ftc.dal.model;

import javax.persistence.*;

@Table(name = "t_ftc_outer_order_rel")
public class OuterOrderRel {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 外部业务系统号
     */
    @Column(name = "outer_sys_no")
    private String outerSysNo;

    /**
     * 外部订单号
     */
    @Column(name = "outer_order_no")
    private String outerOrderNo;

    /**
     * 内部业务系统号
     */
    @Column(name = "biz_sys_no")
    private String bizSysNo;

    /**
     * 业务单号
     */
    @Column(name = "biz_order_no")
    private String bizOrderNo;

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
     * 获取外部业务系统号
     *
     * @return outer_sys_no - 外部业务系统号
     */
    public String getOuterSysNo() {
        return outerSysNo;
    }

    /**
     * 设置外部业务系统号
     *
     * @param outerSysNo 外部业务系统号
     */
    public void setOuterSysNo(String outerSysNo) {
        this.outerSysNo = outerSysNo;
    }

    /**
     * 获取外部订单号
     *
     * @return outer_order_no - 外部订单号
     */
    public String getOuterOrderNo() {
        return outerOrderNo;
    }

    /**
     * 设置外部订单号
     *
     * @param outerOrderNo 外部订单号
     */
    public void setOuterOrderNo(String outerOrderNo) {
        this.outerOrderNo = outerOrderNo;
    }

    /**
     * 获取内部业务系统号
     *
     * @return biz_sys_no - 内部业务系统号
     */
    public String getBizSysNo() {
        return bizSysNo;
    }

    /**
     * 设置内部业务系统号
     *
     * @param bizSysNo 内部业务系统号
     */
    public void setBizSysNo(String bizSysNo) {
        this.bizSysNo = bizSysNo;
    }

    /**
     * 获取业务单号
     *
     * @return biz_order_no - 业务单号
     */
    public String getBizOrderNo() {
        return bizOrderNo;
    }

    /**
     * 设置业务单号
     *
     * @param bizOrderNo 业务单号
     */
    public void setBizOrderNo(String bizOrderNo) {
        this.bizOrderNo = bizOrderNo;
    }
}