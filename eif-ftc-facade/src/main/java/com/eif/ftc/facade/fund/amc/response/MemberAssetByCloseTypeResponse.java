package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.amc.dto.MemberAssetBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Matt on 2017/4/24.
 */
public class MemberAssetByCloseTypeResponse extends BaseResponse{

    private static final long serialVersionUID = 3585519586240164477L;

    List<MemberAssetBean> memberAssetBeanList;


    /**
     * @return 购买的产品列表及信息
     * @occurs required
     */
    public List<MemberAssetBean> getMemberAssetBeanList() {
        return memberAssetBeanList;
    }

    public void setMemberAssetBeanList(List<MemberAssetBean> memberAssetBeanList) {
        this.memberAssetBeanList = memberAssetBeanList;
    }
}
