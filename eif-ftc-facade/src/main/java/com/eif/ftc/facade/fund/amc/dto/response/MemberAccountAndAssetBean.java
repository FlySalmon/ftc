package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Matt on 16/2/15.
 */
public class MemberAccountAndAssetBean extends BaseDTO {

    private static final long serialVersionUID = -5616522192533891346L;

    private AssetAccountAndTotalAssetBean assetAccountAndTotalAssetBean;

    private ArrayList<AssetInfoPerFundBean> assetInfoPerFundBeen = new ArrayList<AssetInfoPerFundBean>();

    /**
     * @return 用户账户与总资产信息
     * @occurs required
     */
    public AssetAccountAndTotalAssetBean getAssetAccountAndTotalAssetBean() {
        return assetAccountAndTotalAssetBean;
    }

    public void setAssetAccountAndTotalAssetBean(AssetAccountAndTotalAssetBean assetAccountAndTotalAssetBean) {
        this.assetAccountAndTotalAssetBean = assetAccountAndTotalAssetBean;
    }


    /**
     * @return 用户基金资产列表
     * @occurs required
     */
    public ArrayList<AssetInfoPerFundBean> getAssetInfoPerFundBeen() {
        return assetInfoPerFundBeen;
    }

    public void setAssetInfoPerFundBeen(ArrayList<AssetInfoPerFundBean> assetInfoPerFundBeen) {
        this.assetInfoPerFundBeen = assetInfoPerFundBeen;
    }
}
