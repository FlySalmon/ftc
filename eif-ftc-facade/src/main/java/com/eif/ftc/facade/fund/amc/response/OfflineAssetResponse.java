package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.OfflineAssetInfo;

import java.util.List;

/**
 * Created by Matt on 16/5/3.
 */
public class OfflineAssetResponse extends BaseResponse {

    private static final long serialVersionUID = -1171029950315836215L;

    private List<OfflineAssetInfo> offlineAssetInfos;


    /**
     * @return 线下资产信息
     * @occurs required
     */
    public List<OfflineAssetInfo> getOfflineAssetInfos() {
        return offlineAssetInfos;
    }


    public void setOfflineAssetInfos(List<OfflineAssetInfo> offlineAssetInfos) {
        this.offlineAssetInfos = offlineAssetInfos;
    }
}
