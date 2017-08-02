package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.MemberAssetQueryBean;

/**
 * Created by Matt on 15/12/17.
 */
public class MemberAssetRequest extends BaseRequest {

    private static final long serialVersionUID = -3063577732947431249L;

    private MemberAssetQueryBean memberAssetQueryBean;

    public MemberAssetQueryBean getMemberAssetQueryBean() {
        return memberAssetQueryBean;
    }
    /**
     * @param memberAssetQueryBean 会员资产查询
     * @occurs required
     */
    public void setMemberAssetQueryBean(MemberAssetQueryBean memberAssetQueryBean) {
        this.memberAssetQueryBean = memberAssetQueryBean;
    }
}
