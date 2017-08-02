package com.eif.ftc.facade.fund.job.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FundTransOrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohan on 2/15/16.
 */
public class GetFundTransOrderListForCheckingResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private List<FundTransOrderBean> fundTransOrderList = new ArrayList<>();


    /**
     * @return 业务单信息列表
     * @occurs required
     */
    public List<FundTransOrderBean> getFundTransOrderList() {
        return fundTransOrderList;
    }

    public void setFundTransOrderList(List<FundTransOrderBean> fundTransOrderList) {
        this.fundTransOrderList = fundTransOrderList;
    }

}
