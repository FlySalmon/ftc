package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.MemberAssetQueryBean;

/**
 * Created by Matt on 16/2/23.
 */
public class QueryMemberAccStatusRequest extends BaseRequest {

    private static final long serialVersionUID = -1836735567549671946L;

    private MemberAssetQueryBean memberAssetQueryBean;

    public MemberAssetQueryBean getMemberAssetQueryBean() {
        return memberAssetQueryBean;
    }

    /**
     * @param memberAssetQueryBean 会员账户状态查询
     * @occurs required
     */
    public void setMemberAssetQueryBean(MemberAssetQueryBean memberAssetQueryBean) {
        this.memberAssetQueryBean = memberAssetQueryBean;
    }
}