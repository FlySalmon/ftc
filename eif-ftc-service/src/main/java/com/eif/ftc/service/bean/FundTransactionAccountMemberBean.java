package com.eif.ftc.service.bean;


import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/28.
 */
public class FundTransactionAccountMemberBean {

    private String memberNo;

    private String transActionAccount;

    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo 会员编号
     * @occurs required
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getTransActionAccount() {
        return transActionAccount;
    }

    /**
     * @param transActionAccount 交易账号
     * @occurs required
     */
    public void setTransActionAccount(String transActionAccount) {
        this.transActionAccount = transActionAccount;
    }
}
