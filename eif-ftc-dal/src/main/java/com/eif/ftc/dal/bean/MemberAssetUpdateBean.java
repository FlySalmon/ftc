package com.eif.ftc.dal.bean;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/8/11.
 */
public class MemberAssetUpdateBean {

    private String memberAssetUUID;

    private BigDecimal amount;

    private BigDecimal groupOnAmount;

    public String getMemberAssetUUID() {
        return memberAssetUUID;
    }

    public void setMemberAssetUUID(String memberAssetUUID) {
        this.memberAssetUUID = memberAssetUUID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getGroupOnAmount() {
        return groupOnAmount;
    }

    public void setGroupOnAmount(BigDecimal groupOnAmount) {
        this.groupOnAmount = groupOnAmount;
    }

}
