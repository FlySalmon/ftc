package com.eif.ftc.service.bean;

import com.eif.ftc.dal.model.FundTransOrder;

/**
 * Created by Matt on 16/8/22.
 */
public class FundTransOrderForRechargeBean {

    private FundTransOrder fundTransOrder;

    private int result;

    public FundTransOrder getFundTransOrder() {
        return fundTransOrder;
    }

    public void setFundTransOrder(FundTransOrder fundTransOrder) {
        this.fundTransOrder = fundTransOrder;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
