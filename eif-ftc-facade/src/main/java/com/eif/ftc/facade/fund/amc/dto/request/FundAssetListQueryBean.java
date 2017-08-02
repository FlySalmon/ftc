package com.eif.ftc.facade.fund.amc.dto.request;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 16/1/6.
 */
public class FundAssetListQueryBean extends BaseDTO {

    private static final long serialVersionUID = 478884057063601309L;

    private String memberNO;


    public String getMemberNO() {
        return memberNO;
    }

    /**
     * @param memberNO 会员号
     * @occurs required
     */
    public void setMemberNO(String memberNO) {
        this.memberNO = memberNO;
    }
}
