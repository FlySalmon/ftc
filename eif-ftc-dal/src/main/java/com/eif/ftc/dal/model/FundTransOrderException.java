package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_trans_order_exception")
public class FundTransOrderException {
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
     * 错误码
     */
    @Column(name = "err_code")
    private String errCode;

    /**
     * 错误时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 错误信息
     */
    @Column(name = "err_msg")
    private String errMsg;

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
     * 获取错误码
     *
     * @return err_code - 错误码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置错误码
     *
     * @param errCode 错误码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取错误时间
     *
     * @return create_time - 错误时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置错误时间
     *
     * @param createTime 错误时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取错误信息
     *
     * @return err_msg - 错误信息
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置错误信息
     *
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}