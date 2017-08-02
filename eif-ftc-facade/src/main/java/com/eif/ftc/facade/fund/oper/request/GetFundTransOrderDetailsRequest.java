package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by bohan on 1/13/16.
 */
public class GetFundTransOrderDetailsRequest extends BaseRequest {

    private String fundTransOrderNo;

    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }


    /**
     * @param fundTransOrderNo 业务单号
     * @occurs required
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }
}
