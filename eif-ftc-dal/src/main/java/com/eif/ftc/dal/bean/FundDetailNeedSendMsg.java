package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/8/15.
 */
public class FundDetailNeedSendMsg {
    private String fundDetailUUID;
    private BigDecimal confirmedAddAmount;
    private Long productId;
    private String memberNo;

    public String getFundDetailUUID() {
        return fundDetailUUID;
    }

    public void setFundDetailUUID(String fundDetailUUID) {
        this.fundDetailUUID = fundDetailUUID;
    }

    public BigDecimal getConfirmedAddAmount() {
        return confirmedAddAmount;
    }

    public void setConfirmedAddAmount(BigDecimal confirmedAddAmount) {
        this.confirmedAddAmount = confirmedAddAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
