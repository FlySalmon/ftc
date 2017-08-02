package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

/**
 * Created by Matt on 16/5/3.
 */
public class QueryOfflineAssetRequest extends BaseRequest {

    private static final long serialVersionUID = 5632525377559796668L;

    private String memberNo;

    public String getMemberNo() {
        return memberNo;
    }


    /**
     * @param memberNo 会员号
     * @occurs required
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
