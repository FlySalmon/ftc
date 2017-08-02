package com.eif.ftc.facade;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundDividendOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.fund.oper.request.*;
import com.eif.ftc.facade.fund.oper.response.*;

/**
 * @tag FundOperFacade
 */
public interface FundOperFacade {
	/**
	 * @brief 分页获取业务交易单
	 * @param request 查询条件
	 * @return 分页业务交易单结果
     */
	PageableResponse<FundTransOrderBean> getFundTransOrdersListByPage(QueryFundTransOrderRequest request);


	/**
	 * @brief 根据业务单号获取业务单信息
	 * @param request 基金业务单号
	 * @return 基金业务结果
	 */
	FundTransOrderResponse getFundTransOrder(GetFundTransOrderDetailsRequest request);

	/**
	 * @brief 分页获取业务交易单
	 * @param request 查询条件
	 * @return 分页业务交易单结果
	 */
	PageableResponse<FundTransOrderBean> getMemberFundTransOrdersListByPage(QueryMemberFundTransOrderRequest request);

	/**
	 * @brief 获取兑付单
	 * @param request 查询条件
	 * @return 兑付单信息
	 */
	QueryFundClearingOrderResponse getFundClearingOrder(QueryFundClearingOrderRequest request);

	/**
	 * @brief 分页获取兑付单
	 * @param request 查询条件
	 * @return 兑付单信息
	 */
	PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPage(FundClearingOrderRequest request);

	/**
	 * @brief 分页获取红利单
	 * @param request 查询条件
	 * @return 红利单信息
	 */
	PageableResponse<FundDividendOrderBean> getFundDividendOrdersByPage(FundDividendOrderRequest request);

	/**
	 * @brief 根据外部订单号获取基金订单信息
	 * @param request 外部单号请求
	 * @return
     */
	FundTransOrderResponse getFundTransOrderByOuterOrderNo(QueryFundOrderByOuterOrderNoRequest request);
	
	/**
	 * @brief 判断用户是否为大额投资客户（存在单笔投资额大于20W）
	 * @param request 大额投资用户条件
	 * @return
	 */
	LargeInvestmentClientsResponse checkLargeInvestmentClients(LargeInvestmentClientsRequest request);
	
	/**
	 * @brief 统计好友的投资金额
	 * @param request
	 * @return
	 */
	FriendsInvestmentResponse getFriendsInvestment(FriendsInvestmentRequest request);

	/**
	 * @brief 查询解禁单明细
	 * @param request
	 * @return
	 */
	FundTransUnbannedOrderResponse getFundTransOrder(FundTransUnbannedOrderRequest request);

	/**
	 * @brief 根据外部订单号列表获取基金订单信息
	 * @param request 外部单号列表请求
	 * @return
	 */
	FundTransOrderListResponse getFundTransOrderListByOuterOrderNoList(QueryFundOrderByOuterOrderNoListRequest request);


	
}
