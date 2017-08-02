package com.eif.ftc.facade.fund.amc.dto.request;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/29.
 */
public class MemberAssetQueryBean extends BaseDTO {

    private static final long serialVersionUID = 6772035870591103664L;

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
