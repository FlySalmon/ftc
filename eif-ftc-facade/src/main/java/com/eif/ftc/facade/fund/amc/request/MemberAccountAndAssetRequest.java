package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.MemberAssetQueryBean;

/**
 * Created by Matt on 16/2/5.
 */
public class MemberAccountAndAssetRequest extends BaseRequest {

    private static final long serialVersionUID = 9017489124469575551L;

    private MemberAssetQueryBean memberAssetQueryBean;

    public MemberAssetQueryBean getMemberAssetQueryBean() {
        return memberAssetQueryBean;
    }

    /**
     * @param memberAssetQueryBean 会员账户与资产查询
     * @occurs required
     */
    public void setMemberAssetQueryBean(MemberAssetQueryBean memberAssetQueryBean) {
        this.memberAssetQueryBean = memberAssetQueryBean;
    }
}
