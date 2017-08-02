package com.eif.ftc.facade.fund.amc.dto.request;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/22.
 */
public class FundAccountMemberNoBean extends BaseDTO {

    private static final long serialVersionUID = -567088350436590073L;

    private String memberNO;

    public String getMemberNO() {
        return memberNO;
    }

    /**
     * @param memberNO 用户会员号
     * @occurs required
     */
    public void setMemberNO(String memberNO) {
        this.memberNO = memberNO;
    }
}
