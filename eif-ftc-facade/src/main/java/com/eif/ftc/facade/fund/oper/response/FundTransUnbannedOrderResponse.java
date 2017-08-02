package com.eif.ftc.facade.fund.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FundTransUnbannedOrderBean;

import java.util.List;

/**
 * Created by Matt on 2017/4/24.
 */
public class FundTransUnbannedOrderResponse extends BaseResponse{

    private static final long serialVersionUID = 6988513343481411754L;

    private List<FundTransUnbannedOrderBean> fundTransUnbannedOrderBeanList;

    /**
     * @return 解禁单明细信息
     * @occurs required
     */
    public List<FundTransUnbannedOrderBean> getFundTransUnbannedOrderBeanList() {
        return fundTransUnbannedOrderBeanList;
    }

    public void setFundTransUnbannedOrderBeanList(List<FundTransUnbannedOrderBean> fundTransUnbannedOrderBeanList) {
        this.fundTransUnbannedOrderBeanList = fundTransUnbannedOrderBeanList;
    }
}
