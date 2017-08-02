package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.FundAssetListQueryBean;

/**
 * Created by Matt on 16/1/6.
 */
public class FundAssetListRequest extends BaseRequest {

    private static final long serialVersionUID = 2720425978537927022L;


    private FundAssetListQueryBean fundAssetListQueryBean;


    public FundAssetListQueryBean getFundAssetListQueryBean() {
        return fundAssetListQueryBean;
    }

    /**
     * @param fundAssetListQueryBean 查询基金信息Bean
     * @occurs required
     */
    public void setFundAssetListQueryBean(FundAssetListQueryBean fundAssetListQueryBean) {
        this.fundAssetListQueryBean = fundAssetListQueryBean;
    }
}
