package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;

import java.util.ArrayList;

/**
 * Created by Matt on 16/1/6.
 */
public class FundBonusListResponse extends BaseResponse {

    private static final long serialVersionUID = -8777253276243958219L;

    private ArrayList<BonusBean> bonusBeen = new ArrayList<BonusBean>();

    private Integer totalRecords;

    /**
     * @return 某只基金持有的红利信息表
     * @occurs required
     */
    public ArrayList<BonusBean> getBonusBeen() {
        return bonusBeen;
    }

    public void setBonusBeen(ArrayList<BonusBean> bonusBeen) {
        this.bonusBeen = bonusBeen;
    }

    /**
     * @return 红利分红总条数
     * @occurs required
     */
    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }
}
