package com.eif.ftc.facade.fund.trans.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * Created by bohan on 1/15/16.
 */
public class ResumePayResponse extends BaseResponse {

    private String transNo;
    private Integer transStatus;


    /**
     * @return 交易单编号
     * @occurs required
     */
    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }


    /**
     * @return 交易单状态
     * @occurs required
     */
    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }
}
