package com.eif.ftc.facade.dto;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 2017/4/24.
 */
public class FundTransUnbannedOrderBean extends BaseDTO{

    private static final long serialVersionUID = -8852126381888020789L;

    private String fundTransOrderNo;

    private String orderDetailNo;

    private String outerOrderNo;

    private BigDecimal amount;

    private Byte type;

    private Byte status;

    private Date finishTime;

    private Date createTime;

    /**
     * @return 解禁单号
     * @occurs required
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    /**
     * @return 解禁单明细号
     * @occurs required
     */
    public String getOrderDetailNo() {
        return orderDetailNo;
    }

    public void setOrderDetailNo(String orderDetailNo) {
        this.orderDetailNo = orderDetailNo;
    }


    /**
     * @return 解禁单外部关联号
     * @occurs required
     */
    public String getOuterOrderNo() {
        return outerOrderNo;
    }

    public void setOuterOrderNo(String outerOrderNo) {
        this.outerOrderNo = outerOrderNo;
    }


    /**
     * @return 解禁单明细金额
     * @occurs required
     */
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return 解禁单类型（0-活期转让单，1-转余额明细单）
     * @occurs required
     */
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return 解禁单明细状态（0-未开始，1-转让中，2-已结束）
     * @occurs required
     */
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    /**
     * @return 最终状态结束时间
     * @occurs required
     */
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return 创建时间
     * @occurs required
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
