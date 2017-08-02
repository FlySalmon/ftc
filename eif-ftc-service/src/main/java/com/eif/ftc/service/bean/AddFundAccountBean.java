package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/14.
 */
public class AddFundAccountBean {

    private String memberNo;

    /**
     *  ftc的开户业务单,所属表字段为t_amc_mem_fund_acc.ftc_create_acc_no
     */
    private String ftcCreateAccNo;


    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo 会员号(会员系统主键)
     * @occurs required
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getFtcCreateAccNo() {
        return ftcCreateAccNo;
    }

    /**
     * @param ftcCreateAccNo ftc业务单号(开户单)
     * @occurs required
     */
    public void setFtcCreateAccNo(String ftcCreateAccNo) {
        this.ftcCreateAccNo = ftcCreateAccNo;
    }
}
