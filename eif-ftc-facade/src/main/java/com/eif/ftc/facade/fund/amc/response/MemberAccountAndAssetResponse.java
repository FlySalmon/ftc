package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.MemberAccountAndAssetBean;

/**
 * Created by Matt on 16/2/15.
 */
public class MemberAccountAndAssetResponse extends BaseResponse {

    private static final long serialVersionUID = -5098425320722628873L;

    private MemberAccountAndAssetBean memberAccountAndAssetBean;

    /**
     * @return 用户账户信息与基金信息列表
     * @occurs required
     */
    public MemberAccountAndAssetBean getMemberAccountAndAssetBean() {
        return memberAccountAndAssetBean;
    }

    public void setMemberAccountAndAssetBean(MemberAccountAndAssetBean memberAccountAndAssetBean) {
        this.memberAccountAndAssetBean = memberAccountAndAssetBean;
    }
}
