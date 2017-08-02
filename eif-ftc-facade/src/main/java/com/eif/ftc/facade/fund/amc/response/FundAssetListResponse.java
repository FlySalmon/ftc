package com.eif.ftc.facade.fund.amc.response;
import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.FundAssetListBean;

/**
 * Created by Matt on 16/1/6.
 */
public class FundAssetListResponse extends BaseResponse {

    private static final long serialVersionUID = -508543359494199742L;


    private FundAssetListBean fundAssetListBean;

    /**
     * @return 持有的基金信息Bean
     * @occurs required
     */
    public FundAssetListBean getFundAssetListBean() {
        return fundAssetListBean;
    }

    public void setFundAssetListBean(FundAssetListBean fundAssetListBean) {
        this.fundAssetListBean = fundAssetListBean;
    }
}
