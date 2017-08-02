package com.eif.ftc.integration.impl.member;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.MemberFacade;
import com.eif.member.facade.MemberInnerFacade;
import com.eif.member.facade.pkg.dto.MemberUpdateBean;
import com.eif.member.facade.pkg.dto.requestDTO.InnerQueryMemberBean;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.member.facade.pkg.request.*;
import com.eif.member.facade.pkg.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bohan on 1/11/16.
 */
@Service("memberIntService")
public class MemberIntServiceImpl implements MemberIntService {

    @Resource(name = "memberInnerFacade")
    MemberInnerFacade memberInnerFacade;

    @Resource(name = "memberFacade")
    MemberFacade memberFacade;

    private static Logger logger = LoggerFactory.getLogger(MemberIntServiceImpl.class);

    @Override
    public void batchUpdateRookie(List<MemberUpdateBean> updateBeans) {
    	if (CollectionUtils.isEmpty(updateBeans)) return;
    	
        BatchUpdateRookieRequest batchUpdateRookieRequest = new BatchUpdateRookieRequest();
        batchUpdateRookieRequest.setUpdateBeans(updateBeans);

        BatchUpdateRookieResponse resp = memberInnerFacade.batchUpdateRookieStatus(batchUpdateRookieRequest);

        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FUND_UPDATE_MEMBER_ROOKIE.getMessage(),
                    FTCRespCode.ERR_FUND_UPDATE_MEMBER_ROOKIE.getCode(), resp.getRespCode());
        }
    }

    @Override
    public boolean updateRookieStatusAndRetIsRookie(String memberNo, int isRookie) {
        // 更新新手状态
        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest();
        MemberUpdateBean memberUpdateBean = new MemberUpdateBean();
//        memberUpdateBean.setIsRookie(0);
        memberUpdateBean.setLockStatus(isRookie);
        memberUpdateBean.setMemberNo(memberNo);
        memberUpdateRequest.setMemberUpdateBean(memberUpdateBean);
        MemberUpdateResponse memberUpdateResponse = memberInnerFacade.updateRookieStatus(memberUpdateRequest);
        if (!memberUpdateResponse.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            throw new BusinessException(FTCRespCode.ERR_FUND_UPDATE_MEMBER_ROOKIE.getMessage(),
                    FTCRespCode.ERR_FUND_UPDATE_MEMBER_ROOKIE.getCode(), memberUpdateResponse.getRespCode());
        }


        return !memberUpdateResponse.getNotRookie();
    }

    /**
     *
     * @param memberNo
     * @return
     */
    @Override
    public QueryMemberInfoResponse queryMembersInfoByMemberNo(String memberNo) {
        QueryMemberInfoRequest memberReq = new QueryMemberInfoRequest();
        InnerQueryMemberBean iqmb = new InnerQueryMemberBean();
        iqmb.setMemberNo(memberNo);
//        iqmb.setQueryType(QueryTypes.QUERY_TYPE_MEMBER_NO);

        memberReq.setInnerQueryMemberBean(iqmb);

        QueryMemberInfoResponse memberResp = memberInnerFacade.queryMemberInfo(memberReq);
        if (!memberResp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage() + String.format(" memberNO: %s", memberNo),
                    FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode(), memberResp.getRespCode());
        }

        return memberResp;
    }

    /**
     *
     * @param phone
     * @return
     */
    @Override
    public QueryMemberInfoResponse queryMembersInfoByPhone(String phone) {
        QueryMemberInfoRequest memberReq = new QueryMemberInfoRequest();
        InnerQueryMemberBean iqmb = new InnerQueryMemberBean();
        iqmb.setPhoneNo(phone);
//        iqmb.setQueryType(QueryTypes.QUERY_TYPE_PHONE_NO);
        memberReq.setInnerQueryMemberBean(iqmb);

        QueryMemberInfoResponse memberResp = memberInnerFacade.queryMemberInfo(memberReq);
        if (!memberResp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
                    FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode(), memberResp.getRespCode());
        }

        return memberResp;
    }

    @Override
    public QueryOrgMembersInfoResponse queryOrgMembersInfo(Integer businessType) {
        QueryOrgMembersInfoReuqest request = new QueryOrgMembersInfoReuqest();
        request.setBusinessType(businessType);
        QueryOrgMembersInfoResponse resp = memberInnerFacade.queryOrgMembersInfo(request);
        if (!resp.isSuccess()) {
            logger.error("query company member info failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

    @Override
    public QueryMemberFundsToInfoResponse queryMemberFundsToInfo(List<String> memberNos) {
        QueryMemberFundsToInfoRequest memberRequest = new QueryMemberFundsToInfoRequest();
        memberRequest.setMemberNos(memberNos);
        QueryMemberFundsToInfoResponse memberResp = memberInnerFacade.queryMemberFundsToInfo(memberRequest);
        if (!memberResp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            logger.error("query member money trends failed, errmsg: " + memberResp.getMsg());
            throw new BusinessException(memberResp.getMsg(), memberResp.getRespCode());
        }

        return memberResp;
    }

    @Override
    public List<String> queryWhiteList(String memberNO)
    {
        WhiteListGroupRequest request = new WhiteListGroupRequest();
        request.setMemberNo(memberNO);

        WhiteListGroupResponse respose = memberFacade.queryWhiteListGroup(request);
        if (!respose.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            logger.error("query member whiteList  failed, errmsg: " + respose.getMsg());
            throw new BusinessException(respose.getMsg(), respose.getRespCode());
        }
        return respose.getWhiteListGroups();
    }

	@Override
	public Map<String, MemberIdentityBean> getMemberByMemberNos(Set<String> memberNos) {
		Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	
		if (!CollectionUtils.isEmpty(memberNos)) {
	    	//获取会员基本信息
	        QueryMembersIdentityRequest request = new QueryMembersIdentityRequest();
	        request.setMemberNos(new ArrayList<>(memberNos));
	        QueryMembersIdentityResponse resp = memberInnerFacade.queryMembersInfoByMemberNos(request);
	        if (!resp.isSuccess()) {
	            logger.error("query member info from member failed, errmsg: " + resp.toString());
	            throw new BusinessException(resp.getMsg(), resp.getRespCode());
	        }
	
	        for (MemberIdentityBean memberInfo : resp.getMemberIdBeanList()) {
	        	memberMap.put(memberInfo.getMemberNo(), memberInfo);
	        }
		}
		
        return memberMap;
	}

	@Override
	public MemberIdentityBean getMemberByMemberNo(String memberNo) {
		List<String> li = new ArrayList<>();
		li.add(memberNo);
		//获取会员基本信息
        QueryMembersIdentityRequest request = new QueryMembersIdentityRequest();
        request.setMemberNos(li);
        QueryMembersIdentityResponse resp = memberInnerFacade.queryMembersInfoByMemberNos(request);
        if (!resp.isSuccess() || resp.getMemberIdBeanList().size() < 1) {
            logger.error("query member info from member failed, errmsg: " + resp.toString());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }
        
        return resp.getMemberIdBeanList().get(0);
	}
	
}
