package com.eif.ftc.service.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/24.
 */
public class FundDetailAlterationDealBean {
    private String ftcOrderNo;
    private Date ftcOrderCreateTime;
    private Integer orderType;
    private BigDecimal fundAmount;
    private BigDecimal fundShare;
    private Integer dealStatus;
    private BigDecimal expectProfitAmount;
//    private BigDecimal expectBonusAmount;

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    public Date getFtcOrderCreateTime() {
        return ftcOrderCreateTime;
    }

    public void setFtcOrderCreateTime(Date ftcOrderCreateTime) {
        this.ftcOrderCreateTime = ftcOrderCreateTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public BigDecimal getFundShare() {
        return fundShare;
    }

    public void setFundShare(BigDecimal fundShare) {
        this.fundShare = fundShare;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
    }

//    public BigDecimal getExpectBonusAmount() {
//        return expectBonusAmount;
//    }
//
//    public void setExpectBonusAmount(BigDecimal expectBonusAmount) {
//        this.expectBonusAmount = expectBonusAmount;
//    }
}
