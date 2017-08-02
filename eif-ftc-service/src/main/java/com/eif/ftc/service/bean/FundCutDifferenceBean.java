package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Matt on 15/12/19.
 */
public class FundCutDifferenceBean {

    private String ftcOrderNo;

    private String ftcOrderOriginalNo;

    private BigDecimal fundAmount;

    private Integer orderType;

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * @param ftcOrderNo 当前业务单号
     * @occurs required
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    public String getFtcOrderOriginalNo() {
        return ftcOrderOriginalNo;
    }

    /**
     * @param ftcOrderOriginalNo 原单号
     * @occurs required
     */
    public void setFtcOrderOriginalNo(String ftcOrderOriginalNo) {
        this.ftcOrderOriginalNo = ftcOrderOriginalNo;
    }

    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * @param fundAmount 金额
     * @occurs required
     */
    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public Integer getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 业务类型 1-申购,2-赎回,3-不操作
     * @occurs required
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
