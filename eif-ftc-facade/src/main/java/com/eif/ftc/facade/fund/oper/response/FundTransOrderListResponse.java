package com.eif.ftc.facade.fund.oper.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.dto.FundTransOrderBean;

import java.util.List;

/**
 * Created by Matt on 2017/4/26.
 */
public class FundTransOrderListResponse extends BaseResponse{

    private static final long serialVersionUID = 2626695504616562183L;

    private List<FundTransOrderBean> fundTransOrderBeans;

    /**
     * @return 订单列表
     * @occurs required
     */
    public List<FundTransOrderBean> getFundTransOrderBeans() {
        return fundTransOrderBeans;
    }

    public void setFundTransOrderBeans(List<FundTransOrderBean> fundTransOrderBeans) {
        this.fundTransOrderBeans = fundTransOrderBeans;
    }
}
