package com.eif.ftc.facade.fund.trans.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * Created by bohan on 1/7/16.
 */
public class PayFundBuyingOrderResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String fundTransOrderNo;

    private String transCoreNo;

    private int fundTransStatus;

    private String businessOrderItemNo;

    private String paymentNo;

    /**
     * @return 支付方式
     * @occurs required
     */
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    /**
     * @return 交易业务单号
     * @occurs required
     */
    public String getFundTransOrderNo() {
        return fundTransOrderNo;
    }

    public void setFundTransOrderNo(String fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }


    /**
     * @return 支付单号
     * @occurs required
     */
    public String getTransCoreNo() {
        return transCoreNo;
    }

    public void setTransCoreNo(String transCoreNo) {
        this.transCoreNo = transCoreNo;
    }


    /**
     * @return 交易业务单状态
     * @occurs required
     */
    public int getFundTransStatus() {
        return fundTransStatus;
    }

    public void setFundTransStatus(int fundTransStatus) {
        this.fundTransStatus = fundTransStatus;
    }

    /**
     * @return 业务单项号
     * @occurs required
     */
    public String getBusinessOrderItemNo() {
        return businessOrderItemNo;
    }

    public void setBusinessOrderItemNo(String businessOrderItemNo) {
        this.businessOrderItemNo = businessOrderItemNo;
    }
}
