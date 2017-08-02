package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;

/**
 * Created by Matt on 15/12/18.
 */
public class FundTotalAssetRequest extends BaseRequest {

    private static final long serialVersionUID = -4883417236604104374L;

    private FundTotalAssetRequestBean fundTotalAssetRequestBean;


    public FundTotalAssetRequestBean getFundTotalAssetRequestBean() {
        return fundTotalAssetRequestBean;
    }

    /**
     * @param fundTotalAssetRequestBean 用户与产品信息
     * @occurs required
     */
    public void setFundTotalAssetRequestBean(FundTotalAssetRequestBean fundTotalAssetRequestBean) {
        this.fundTotalAssetRequestBean = fundTotalAssetRequestBean;
    }
}
