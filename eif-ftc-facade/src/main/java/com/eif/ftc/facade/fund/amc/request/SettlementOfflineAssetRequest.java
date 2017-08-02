package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by Matt on 16/5/3.
 */
public class SettlementOfflineAssetRequest extends BaseRequest {

    private static final long serialVersionUID = 3595861642648242548L;


    private String offlineAssetUUID;

    public String getOfflineAssetUUID() {
        return offlineAssetUUID;
    }

    public void setOfflineAssetUUID(String offlineAssetUUID) {
        this.offlineAssetUUID = offlineAssetUUID;
    }
}
