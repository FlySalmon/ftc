package com.eif.ftc.service.trans;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.dal.bean.MemberInvestmentBean;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundDividendOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrderListForCheckingRequest;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrdersForCheckingPageableRequest;
import com.eif.ftc.facade.fund.oper.request.FundClearingOrderRequest;
import com.eif.ftc.facade.fund.oper.request.FundDividendOrderRequest;
import com.eif.ftc.facade.fund.oper.request.QueryFundClearingOrderRequest;
import com.eif.ftc.facade.fund.oper.request.QueryFundTransOrderRequest;
import com.eif.ftc.facade.fund.oper.request.QueryMemberFundTransOrderRequest;
import com.eif.ftc.facade.fund.oper.response.FundTransOrderListResponse;
import com.eif.ftc.facade.fund.oper.response.FundTransOrderResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundQueryService {
    /**
     * 获取单个业务单信息
     * @param fundTransOrderNo
     * @param fundTransOrderResponse
     */
    void getFundTransOrder(String fundTransOrderNo, FundTransOrderResponse fundTransOrderResponse);

    /**
     * 分页获取业务单列表
     * @param request
     * @return
     */
    PageableResponse<FundTransOrderBean> getFundTransOrdersListByPage(QueryFundTransOrderRequest request);

    PageableResponse<FundTransOrderBean> getMemberFundTransOrdersListByPage(QueryMemberFundTransOrderRequest request);

    PageableResponse<FundTransOrderBean> getFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request);

    List<FundTransOrderBean> getFundTransOrderListForChecking(GetFundTransOrderListForCheckingRequest request);

    /**
     * 获取兑付单
     * @param request
     * @return
     */
    FundClearingOrderBean getFundClearingOrder(QueryFundClearingOrderRequest request);

    /**
     * 分页获取兑付单
     * @param request
     * @param forChecking
     * @return
     */
    PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPage(FundClearingOrderRequest request, boolean forChecking);

    /**
     * 分页获取红利单
     * @param request
     * @return
     */
    PageableResponse<FundDividendOrderBean> getFundDividendOrdersByPage(FundDividendOrderRequest request);

    /**
     * 根据业务单号获取清盘兑付单列表
     * @param fundClearingOrderNos
     * @param forChecking
     * @return
     */
    List<FundClearingOrderBean> getByFundClearingOrderNos(List<String> fundClearingOrderNos, boolean forChecking);

    /**
     * 获取有效交易单
     * @param request
     * @return
     */
    PageableResponse<FundTransOrderBean> getSucceedFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request);

    /**
     * 根据外部订单号获取基金订单信息
     * @param outerSysNo 外部系统号
     * @param outerOrderNo 外部系统订单号
     * @param fundTransOrderResponse
     */
    void getFundTransOrderByOuterOrderNo(String outerSysNo, String outerOrderNo, FundTransOrderResponse fundTransOrderResponse);

    /**
     * 获取大额投资用户
     * @param memberNos
     * @param threshold
     * @param transTypeList
     * @return
     */
    List<String> getLargeInvestmentClinets(List<String> memberNos, BigDecimal threshold, List<Integer> transTypeList);

    /**
     * 统计好友投资总额
     * @param transType
     * @param startDt
     * @param endDt
     * @param memberNos
     * @return
     */
    List<MemberInvestmentBean> getFriendsInvestment(Integer transType, Date startDt, Date endDt, List<String> memberNos);


    void getFundTransOrderListByOuterOrderNoList(String outerSysNo, List<String> outerOrderNoList, FundTransOrderListResponse fundTransOrderListResponse);
}
