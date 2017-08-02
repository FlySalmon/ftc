package com.eif.ftc.service.test.prepare;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.member.facade.pkg.dto.responseDTO.UserInfoBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.member.facade.pkg.response.QueryMembersIdentityResponse;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bohan on 10/6/16.
 */
public class MemberDataPrepare {

    public final static String NORMAL_SUC_MEMBER_NO = "1";
    public final static String WHITE_LIST_GROUP_ID = "1";
    public final static String NORMAL_TRNASFEROR_MEMBER_NO = "2";
    public final static String NORMAL_TRNASFEREE_MEMBER_NO = "3";

    public static QueryMemberInfoResponse buildBaseQueryMembersInfoByMemberNo() {
        QueryMemberInfoResponse queryMemberInfoResponse = new QueryMemberInfoResponse();
        queryMemberInfoResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
        queryMemberInfoResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setIsRookie(0);
        userInfoBean.setAllowTxn(1);
        userInfoBean.setIsStaff(0);
        userInfoBean.setMemberNo(NORMAL_SUC_MEMBER_NO);
        queryMemberInfoResponse.setUserInfoBean(userInfoBean);
        return queryMemberInfoResponse;
    }

    public static void buildQueryMembersInfoByMemberNoForSuccess(MemberIntService memberIntService) {
        QueryMemberInfoResponse queryMemberInfoResponse = new QueryMemberInfoResponse();
        queryMemberInfoResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
        queryMemberInfoResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setIsRookie(0);
        userInfoBean.setAllowTxn(1);
        userInfoBean.setIsStaff(0);
        userInfoBean.setMemberNo(NORMAL_SUC_MEMBER_NO);
        queryMemberInfoResponse.setUserInfoBean(userInfoBean);

        Mockito.doReturn(queryMemberInfoResponse).when(memberIntService).queryMembersInfoByMemberNo(NORMAL_SUC_MEMBER_NO);
    }

    public static void buildGetMembersByMemberNoForSuccess(MemberIntService memberIntService) {
        MemberIdentityBean member = new MemberIdentityBean();
        member.setMemberNo(NORMAL_SUC_MEMBER_NO);
        member.setVerifiedMobile("13511111111");

//        Set<String> memberNos = new HashSet<>();
//        memberNos.add(NORMAL_SUC_MEMBER_NO);
        Mockito.doReturn(member).when(memberIntService).getMemberByMemberNo(NORMAL_SUC_MEMBER_NO);
    }

    public static void buildQueryWhiteListForSuccess(MemberIntService memberIntService) {
        List<String> whiteList = new ArrayList<>();
        whiteList.add(WHITE_LIST_GROUP_ID);
        Mockito.doReturn(whiteList).when(memberIntService).queryWhiteList(NORMAL_SUC_MEMBER_NO);
    }

    public static void buildUpdateRookieStatusAndRetIsRookieForSuccess(MemberIntService memberIntService, boolean isRookie) {
        Mockito.doReturn(isRookie).when(memberIntService).updateRookieStatusAndRetIsRookie(Mockito.eq(NORMAL_SUC_MEMBER_NO), Mockito.anyInt());
    }

    public static void buildBatchUpdateRookieForSuccess(MemberIntService memberIntService) {
        Mockito.doNothing().when(memberIntService).batchUpdateRookie(Mockito.anyList());
    }
}
