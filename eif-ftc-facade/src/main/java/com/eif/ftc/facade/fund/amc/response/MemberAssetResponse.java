package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.response.MemberTotalAssetBean;

/**
 * Created by Matt on 15/12/17.
 */
public class MemberAssetResponse extends BaseResponse {

    private static final long serialVersionUID = -3063577732947431249L;

    private MemberTotalAssetBean memberTotalAssetBean;

    /**
     * @return 用户资产信息
     * @occurs required
     */
    public MemberTotalAssetBean getMemberTotalAssetBean() {
        return memberTotalAssetBean;
    }

    public void setMemberTotalAssetBean(MemberTotalAssetBean memberTotalAssetBean) {
        this.memberTotalAssetBean = memberTotalAssetBean;
    }
}
