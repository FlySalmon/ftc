package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Matt on 16/1/8.
 */
public class FundAssetListBean extends BaseDTO {

    private static final long serialVersionUID = -3925114117330076814L;

    ArrayList<FundAssetInfoBean> handleFunds = new ArrayList<FundAssetInfoBean>();


    ArrayList<FundAssetInfoBean> finishFunds = new ArrayList<FundAssetInfoBean>();

    /**
     * @return 持有的基金信息表
     * @occurs required
     */
    public ArrayList<FundAssetInfoBean> getHandleFunds() {
        return handleFunds;
    }

    public void setHandleFunds(ArrayList<FundAssetInfoBean> handleFunds) {
        this.handleFunds = handleFunds;
    }

    /**
     * @return 兑付的基金信息表
     * @occurs required
     */
    public ArrayList<FundAssetInfoBean> getFinishFunds() {
        return finishFunds;
    }

    public void setFinishFunds(ArrayList<FundAssetInfoBean> finishFunds) {
        this.finishFunds = finishFunds;
    }
}
