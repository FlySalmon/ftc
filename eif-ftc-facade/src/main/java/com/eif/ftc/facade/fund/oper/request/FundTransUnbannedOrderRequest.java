package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by Matt on 2017/4/24.
 */
public class FundTransUnbannedOrderRequest extends BaseRequest{

    private static final long serialVersionUID = -6005810237271244480L;

    private String fundTransOrderNo;

    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    /**
     * @param fundTransOrderNo 业务订单号（FTC单号）
     * @occurs required
     */
    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }
}
