package com.eif.ftc.integration.member;


import com.eif.member.facade.pkg.dto.MemberUpdateBean;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.member.facade.pkg.response.QueryMemberFundsToInfoResponse;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.member.facade.pkg.response.QueryOrgMembersInfoResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bohan on 1/11/16.
 */
public interface MemberIntService {

    void batchUpdateRookie(List<MemberUpdateBean> updateBeans);

    boolean updateRookieStatusAndRetIsRookie(String memberNo, int isRookie);

    QueryMemberInfoResponse queryMembersInfoByMemberNo(String memberNo);

    QueryMemberInfoResponse queryMembersInfoByPhone(String phone);

    QueryOrgMembersInfoResponse queryOrgMembersInfo(Integer businessType);

    QueryMemberFundsToInfoResponse queryMemberFundsToInfo(List<String> memberNos);

    List<String> queryWhiteList(String memberNO);

    /**
     * 根据会员号获取会员信息
     *
     * @param memberNos
     * @return
     */
    public Map<String, MemberIdentityBean> getMemberByMemberNos(Set<String> memberNos);

    /**
     * 根据会员号获取会员信息
     *
     * @param memberNo
     * @return
     */
    public MemberIdentityBean getMemberByMemberNo(String memberNo);

}
