package com.eif.ftc.service.fund.amc;

import com.eif.ftc.facade.fund.amc.dto.request.FundOfflineAssetBean;
import com.eif.ftc.facade.fund.amc.dto.response.OfflineAssetInfo;

import java.util.List;

/**
 * Created by Matt on 16/5/3.
 */
public interface FundOfflineDetailService {
    void addOfflineAsset(FundOfflineAssetBean fundOfflineAssetBean);
    List<OfflineAssetInfo> queryOfflineAssetByMemberNo(String memberNO);
    void settlementOfflineAsset(String offlineAssetUuid);
}
