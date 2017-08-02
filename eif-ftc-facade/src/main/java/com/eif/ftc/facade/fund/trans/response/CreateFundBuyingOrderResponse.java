package com.eif.ftc.facade.fund.trans.response;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.framework.common.utils.pkg.BaseResponse;

import java.math.BigDecimal;

/**
 * Created by bohan on 1/7/16.
 */
public class CreateFundBuyingOrderResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String fundTransOrderNo;

    private String businessOrderItemNo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    /**
     * @return 基金交易业务单号
     * @occurs required
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }

    /**
     * @return 订单业务项号
     * @occurs required
     */
    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }
}
