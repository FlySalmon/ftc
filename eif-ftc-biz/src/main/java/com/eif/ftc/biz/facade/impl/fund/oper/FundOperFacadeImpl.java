package com.eif.ftc.biz.facade.impl.fund.oper;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.facade.fund.oper.request.*;
import com.eif.ftc.facade.fund.oper.response.*;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.service.trans.FundQueryService;
import ma.glasnost.orika.MapperFacade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.dal.bean.MemberInvestmentBean;
import com.eif.ftc.facade.FundOperFacade;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.dto.FriendInvestmentBean;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundDividendOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.dto.LargeInvestmentClientResponseBean;

public class FundOperFacadeImpl implements FundOperFacade {

    @Autowired
	FundQueryService fundQueryService;

    @Autowired
    MapperFacade mapperFacade;

    @Override
    public PageableResponse<FundTransOrderBean> getFundTransOrdersListByPage(QueryFundTransOrderRequest request) {
        PageableResponse<FundTransOrderBean> resp = fundQueryService.getFundTransOrdersListByPage(request);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

    @Override
    public FundTransOrderResponse getFundTransOrder(GetFundTransOrderDetailsRequest request) {
        FundTransOrderResponse fundTransOrderResponse = new FundTransOrderResponse();
        fundQueryService.getFundTransOrder(request.getFundTransOrderNo(), fundTransOrderResponse);
        fundTransOrderResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
        fundTransOrderResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        return fundTransOrderResponse;
    }


    @Override
    public PageableResponse<FundTransOrderBean> getMemberFundTransOrdersListByPage(QueryMemberFundTransOrderRequest request) {
        PageableResponse<FundTransOrderBean> resp = fundQueryService.getMemberFundTransOrdersListByPage(request);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

	@Override
	public QueryFundClearingOrderResponse getFundClearingOrder(QueryFundClearingOrderRequest request) {
		QueryFundClearingOrderResponse resp = new QueryFundClearingOrderResponse();
		if ((request.getProductId() == null || request.getMemberNo().isEmpty()) &&
				request.getBusinessOrderItemNo().isEmpty() &&
				request.getFundClearingOrderNo().isEmpty()) {
			resp.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			resp.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return resp;
		}
		
		resp.setFundClearingOrderBean(fundQueryService.getFundClearingOrder(request));

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
	}

    @Override
    public PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPage(FundClearingOrderRequest request) {
    	PageableResponse<FundClearingOrderBean> resp = fundQueryService.getFundClearingOrdersByPage(request, false);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
    }

    @Override
    public FundTransOrderResponse getFundTransOrderByOuterOrderNo(QueryFundOrderByOuterOrderNoRequest request) {
        FundTransOrderResponse fundTransOrderResponse = new FundTransOrderResponse();
        fundQueryService.getFundTransOrderByOuterOrderNo(request.getOuterSysNo(), request.getOuterOrderNo(), fundTransOrderResponse);
        fundTransOrderResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
        fundTransOrderResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        return fundTransOrderResponse;
    }

	@Override
	public LargeInvestmentClientsResponse checkLargeInvestmentClients(LargeInvestmentClientsRequest request) {
		LargeInvestmentClientsResponse resp = new LargeInvestmentClientsResponse();
		if ((request.getThreshold() == null) || (request.getThreshold().compareTo(BigDecimal.ZERO) == 0)) {
			resp.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			resp.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return resp;
		}
		
		// set交易类型
		List<Integer> transTypeList = new ArrayList<>();
		if (request.getFundTransType() != null) {
			transTypeList.add(request.getFundTransType());
		} else {
			transTypeList.add(FundTransType.SUBSCRIBING);
			transTypeList.add(FundTransType.PURCHASING);
		}
		
		//获取大额投资用户列表
		List<String> memberNoList = fundQueryService.getLargeInvestmentClinets(
				new ArrayList<>(request.getMemberNoSet()), request.getThreshold(), transTypeList);
		Set<String> memberNos = new HashSet<String>(memberNoList);
		
		List<LargeInvestmentClientResponseBean> clientResponseBeanList = new ArrayList<>();
		for (String memberNo : request.getMemberNoSet()) {
			LargeInvestmentClientResponseBean clientResponseBean = new LargeInvestmentClientResponseBean();
			clientResponseBean.setMemberNo(memberNo);
			if (memberNos.contains(memberNo)) {
				clientResponseBean.setLargeInvestmentClient(true);
			}else {
				clientResponseBean.setLargeInvestmentClient(false);
			}
			
			clientResponseBeanList.add(clientResponseBean);
		}
		resp.setLargeInvestmentClientsResponse(clientResponseBeanList);
		resp.setRespCode(CommonRspCode.SUCCESS.getCode());
		resp.setMsg(CommonRspCode.SUCCESS.getMessage());
		return resp;
	}

	@Override
	public FriendsInvestmentResponse getFriendsInvestment(FriendsInvestmentRequest request) {
		FriendsInvestmentResponse resp = new FriendsInvestmentResponse();
		if (request.getFriendMemberNos() == null || 
				request.getFriendMemberNos().isEmpty() ||
				request.getStartDateTime() == null ||
				request.getEndDateTime() == null) {
			resp.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			resp.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
		} else {
			List<MemberInvestmentBean> investments = new ArrayList<>();
			
			//获取好友定期投资额
			List<MemberInvestmentBean> li = fundQueryService.getFriendsInvestment(
					FundTransType.SUBSCRIBING, request.getStartDateTime(), request.getEndDateTime(), 
					request.getFriendMemberNos());
			if (li != null) {
				investments.addAll(li);
			}
			//获取活期、活包定投资额
			li = fundQueryService.getFriendsInvestment(
					FundTransType.PURCHASING, request.getStartDateTime(), request.getEndDateTime(), 
					request.getFriendMemberNos());
			if (li != null) {
				investments.addAll(li);
			}
			
			//汇总好友投资额
			BigDecimal totalAmount = BigDecimal.ZERO;
			Map<String, FriendInvestmentBean> map = new HashMap<>();
			for (MemberInvestmentBean investment : investments) {
				FriendInvestmentBean fInvestment = map.get(investment.getMemberNo());
				if (fInvestment == null) {
					fInvestment = new FriendInvestmentBean();
					fInvestment.setMemberNo(investment.getMemberNo());
					fInvestment.setCurrentAmount(BigDecimal.ZERO);
					fInvestment.setRegularAmount(BigDecimal.ZERO);
				}
				
				if (investment.getFundTransType().equals(FundTransType.PURCHASING)) {//活期
					fInvestment.setCurrentAmount(
							fInvestment.getCurrentAmount().add(investment.getAmount()));
				} else {//定期 + 活包定
					fInvestment.setRegularAmount(
							fInvestment.getRegularAmount().add(investment.getAmount()));
					totalAmount = totalAmount.add(investment.getAmount());
				}

				map.put(investment.getMemberNo(), fInvestment);
			}

			resp.setInvestAmount(totalAmount);
			resp.setFriendInvestments(new ArrayList<>(map.values()));
			resp.setRespCode(CommonRspCode.SUCCESS.getCode());
	        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
		}

        return resp;
	}

	@Override
	public FundTransUnbannedOrderResponse getFundTransOrder(FundTransUnbannedOrderRequest request) {
		return null;
	}

	@Override
	public FundTransOrderListResponse getFundTransOrderListByOuterOrderNoList(QueryFundOrderByOuterOrderNoListRequest request) {
		FundTransOrderListResponse fundTransOrderListResponse = new FundTransOrderListResponse();
		fundQueryService.getFundTransOrderListByOuterOrderNoList(request.getOuterSysNo(),request.getOuterOrderNoList(),fundTransOrderListResponse);
		fundTransOrderListResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
		fundTransOrderListResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
    	return fundTransOrderListResponse;
	}

	@Override
	public PageableResponse<FundDividendOrderBean> getFundDividendOrdersByPage(FundDividendOrderRequest request) {
		PageableResponse<FundDividendOrderBean> resp = fundQueryService.getFundDividendOrdersByPage(request);

        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());

        return resp;
	}

}
