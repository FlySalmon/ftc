package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;

/**
 * Created by Matt on 15/12/18.
 */
public class FundTotalAssetResponse extends BaseResponse {

    private static final long serialVersionUID = -2690533465277636915L;

    private FundTotalAssetBean fundTotalAssetBean;

    /**
     * @result 用户基金总资产Bean
     * @occurs required
     */
    public FundTotalAssetBean getFundTotalAssetBean() {
        return fundTotalAssetBean;
    }


    public void setFundTotalAssetBean(FundTotalAssetBean fundTotalAssetBean) {
        this.fundTotalAssetBean = fundTotalAssetBean;
    }
}
