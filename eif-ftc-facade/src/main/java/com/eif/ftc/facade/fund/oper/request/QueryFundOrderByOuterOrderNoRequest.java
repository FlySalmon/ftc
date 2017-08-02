package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by bohan on 1/15/16.
 */
public class QueryFundOrderByOuterOrderNoRequest extends BaseRequest {

    private String outerOrderNo;

    private String outerSysNo;

    public String getOuterOrderNo() {
        return outerOrderNo;
    }


    /**
     * @param outerOrderNo 外部关联单号
     * @occurs required
     */
    public void setOuterOrderNo(String outerOrderNo) {
        this.outerOrderNo = outerOrderNo;
    }

    public String getOuterSysNo() {
        return outerSysNo;
    }


    /**
     * @param outerSysNo 外部关联系统号
     * @occurs required
     */
    public void setOuterSysNo(String outerSysNo) {
        this.outerSysNo = outerSysNo;
    }

}

