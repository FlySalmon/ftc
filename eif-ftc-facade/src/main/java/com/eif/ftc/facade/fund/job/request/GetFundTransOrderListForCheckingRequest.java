package com.eif.ftc.facade.fund.job.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohan on 2/15/16.
 */
public class GetFundTransOrderListForCheckingRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private List<String> fundTransOrderNo = new ArrayList<>();

    public List<String> getFundTransOrderNo() {
        return fundTransOrderNo;
    }


    /**
     * @param fundTransOrderNo 业务单号列表
     * @occurs required
     */
    public void setFundTransOrderNo(List<String> fundTransOrderNo) {
        this.fundTransOrderNo = fundTransOrderNo;
    }
}
