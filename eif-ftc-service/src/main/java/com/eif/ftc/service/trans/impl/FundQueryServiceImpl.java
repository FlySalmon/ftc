package com.eif.ftc.service.trans.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdSummaryInfo;
import com.eif.fis.facade.dto.mtp.SecMarketProd;
import com.eif.fis.facade.dto.omc.CurrentProdInfo;
import com.eif.fis.facade.response.ftc.QueryProdInfoByProdIdsResp;
import com.eif.fis.facade.response.ftc.QueryProdInfoByProdNameResp;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.bean.MemberInvestmentBean;
import com.eif.ftc.dal.dao.FundClearingOrderQueryMapper;
import com.eif.ftc.dal.dao.FundDividendOrderQueryMapper;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.dao.OuterOrderRelMapper;
import com.eif.ftc.dal.model.*;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundDividendOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrderListForCheckingRequest;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrdersForCheckingPageableRequest;
import com.eif.ftc.facade.fund.oper.request.*;
import com.eif.ftc.facade.fund.oper.response.FundTransOrderListResponse;
import com.eif.ftc.facade.fund.oper.response.FundTransOrderResponse;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.service.trans.FundQueryService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundQueryService")
public class FundQueryServiceImpl implements FundQueryService {
    private final static Logger logger = LoggerFactory.getLogger(FundQueryServiceImpl.class);

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    private FundClearingOrderQueryMapper fundClearingOrderQueryMapper;

    @Autowired
    private FundDividendOrderQueryMapper fundDividendOrderQueryMapper;

    @Resource
    MemberIntService memberIntService;

    @Resource
    FisIntService fisIntService;

    @Autowired
    OuterOrderRelMapper outerOrderRelMapper;

    public void getFundTransOrderByOuterOrderNo(String outerSysNo, String outerOrderNo, FundTransOrderResponse fundTransOrderResponse) {
        OuterOrderRelExample outerOrderRelExample = new OuterOrderRelExample();
        OuterOrderRelExample.Criteria criteria = outerOrderRelExample.createCriteria();
        criteria.andOuterSysNoEqualTo(outerSysNo);
        criteria.andOuterOrderNoEqualTo(outerOrderNo);
        List<OuterOrderRel> outerOrderRelList = outerOrderRelMapper.selectByExample(outerOrderRelExample);

        if (!CollectionUtils.isEmpty(outerOrderRelList) && outerOrderRelList.get(outerOrderRelList.size() - 1).getBizSysNo().equals(BizSysCode.FTC_SYSTEM)) {
            int orderListSize = outerOrderRelList.size();
            FundTransOrder order = new FundTransOrder();
            order.setFundTransOrderNo(outerOrderRelList.get(orderListSize - 1).getBizOrderNo());
            FundTransOrder targetOrder = fundTransOrderMapper.selectOne(order);
            if (fundTransOrderResponse != null) {
                mapperFacade.map(targetOrder, fundTransOrderResponse);
                fundTransOrderResponse.setMemberName(memberIntService.queryMembersInfoByMemberNo(targetOrder.getMemberNo()).getUserInfoBean().getRealName());
                QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(targetOrder.getProductId());
                String productName;
                if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {//定期
                    productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
                } else {
                    productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
                }
                fundTransOrderResponse.setProductName(productName);
            } else {
                throw new BusinessException(FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getCode());
            }
        } else {
            throw new BusinessException(FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getCode());
        }

    }

    public void getFundTransOrder(String fundTransOrderNo, FundTransOrderResponse fundTransOrderResponse) {
        FundTransOrder order = new FundTransOrder();
        order.setFundTransOrderNo(fundTransOrderNo);
        FundTransOrder targetOrder = fundTransOrderMapper.selectOne(order);
        if (fundTransOrderResponse != null) {
            mapperFacade.map(targetOrder, fundTransOrderResponse);
            fundTransOrderResponse.setMemberName(memberIntService.queryMembersInfoByMemberNo(targetOrder.getMemberNo()).getUserInfoBean().getRealName());
            QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(targetOrder.getProductId());
            String productName;
            if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {//定期
                productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
            } else {
                productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
            }
            fundTransOrderResponse.setProductName(productName);
        }
    }

    public PageableResponse<FundTransOrderBean> getFundTransOrdersListByPage(QueryFundTransOrderRequest request) {
        //check page info
        if (request.getPageNum() < 1 || request.getPageSize() < 1) {
            logger.error("invalid request page info.");
            throw new BusinessException(FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getMessage(),
                    FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getCode());
        }

        QueryMemberFundTransOrderRequest memberFundTransOrderRequest = new QueryMemberFundTransOrderRequest();
        mapperFacade.map(request, memberFundTransOrderRequest);
        if (StringUtils.isEmpty(request.getMemberNo()) &&
                !StringUtils.isEmpty(request.getMemberPhoneNumber())) {
            QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByPhone(request.getMemberPhoneNumber());
            memberFundTransOrderRequest.setMemberNo(memberResp.getUserInfoBean().getMemberNo());
        }

        return getMemberFundTransOrdersListByPage(memberFundTransOrderRequest);
    }


    @Override
    public PageableResponse<FundTransOrderBean> getFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request) {
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andFinishTimeGreaterThanOrEqualTo(request.getStartDate());
        criteria.andFinishTimeLessThan(request.getEndDate());
        criteria.andBusinessOrderItemNoNotEqualTo("");

        PageHelper.startPage(request.getPageNum(), request.getPageSize(), request.isCalCount());

        PageableResponse<FundTransOrderBean> resp = new PageableResponse<>();
        Page<FundTransOrder> orderList = (Page<FundTransOrder>) fundTransOrderMapper.selectByExample(example);
        resp.initFromPage(orderList);
        List<FundTransOrderBean> orderDTOList = mapperFacade.mapAsList(orderList, FundTransOrderBean.class);
        resp.setList(orderDTOList);

        return resp;
    }

    @Override
    public List<FundTransOrderBean> getFundTransOrderListForChecking(GetFundTransOrderListForCheckingRequest request) {
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andFundTransOrderNoIn(request.getFundTransOrderNo());
        List<FundTransOrder> fundTransOrders = fundTransOrderMapper.selectByExample(example);

        List<FundTransOrderBean> retList = mapperFacade.mapAsList(fundTransOrders, FundTransOrderBean.class);

        return retList;
    }

    @Override
    public PageableResponse<FundTransOrderBean> getMemberFundTransOrdersListByPage(QueryMemberFundTransOrderRequest request) {
        PageableResponse<FundTransOrderBean> resp = new PageableResponse<>();
        if (!StringUtils.isEmpty(request.getOrderBy())) {
            PageHelper.orderBy(request.getOrderBy());
        }
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);

        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        if (request.getStartDate() != null && request.getEndDate() != null) {
            criteria.andCreateTimeBetween(request.getStartDate(), request.getEndDate());
        } else if (request.getStartDate() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartDate());
        } else if (request.getEndDate() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndDate());
        }

        if (request.getOrderStatus() != -1) {
            criteria.andStatusEqualTo(request.getOrderStatus());
        }
        if (request.getTransType() != -1) {
            criteria.andFundTransTypeEqualTo(request.getTransType());
        }

        if (request.getProductID() != null) {
            criteria.andProductIdEqualTo(request.getProductID());
        }

        if (!StringUtils.isEmpty(request.getMemberNo())) {
            criteria.andMemberNoEqualTo(request.getMemberNo());
        }

        Map<Long, String> productMap = new HashMap<>();
        if (!StringUtils.isEmpty(request.getProductName())) {
            QueryProdInfoByProdNameResp productResp = fisIntService.queryProdInfoByProdName(request.getProductName());
            if (!CollectionUtils.isEmpty(productResp.getInfos())) {//定期产品
                for (ProdSummaryInfo product : productResp.getInfos()) {
                    productMap.put(product.getProductId(), product.getProductName());
                }
            }
            if (!CollectionUtils.isEmpty(productResp.getCurrentProdInfos())) {//活期产品
                for (ProdSummaryInfo product : productResp.getCurrentProdInfos()) {
                    productMap.put(product.getProductId(), product.getProductName());
                }
            }
            if (productMap.size() > 0) {
                criteria.andProductIdIn(new ArrayList<>(productMap.keySet()));
            } else {
                return resp;
            }
            criteria.andProductIdIn(new ArrayList<>(productMap.keySet()));
        }

        if (!StringUtils.isEmpty(request.getFundTransOrderNo())) {
            criteria.andFundTransOrderNoEqualTo(request.getFundTransOrderNo());
        }

        Page<FundTransOrder> orderList = (Page<FundTransOrder>) fundTransOrderMapper.selectByExample(example);
        resp.initFromPage(orderList);
        if (CollectionUtils.isEmpty(orderList)) return resp;

        //获取产品和会员信息
        Set<Long> productIds = new HashSet<Long>();
        Set<String> memberNos = new HashSet<String>();
        for (FundTransOrder fundTransOrder : orderList) {
            productIds.add(fundTransOrder.getProductId());
            memberNos.add(fundTransOrder.getMemberNo());
        }
//        List<ProdSummaryInfo> productList = fisIntService.queryProdInfoByProdIds(
//        		new ArrayList<>(productIds)).getInfos();
//        Map<Long, ProdSummaryInfo> productMap = new HashMap<>();
//        for (ProdSummaryInfo product : productList) {
//        	productMap.put(product.getProductId(), product);
//        }

        if (!(productMap.size() > 0)) {
            productMap = getProductName(new ArrayList<>(productIds));
        }
        Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);

        List<FundTransOrderBean> orderDTOList = new ArrayList<>();
        for (FundTransOrder fundTransOrder : orderList) {
            FundTransOrderBean bean = new FundTransOrderBean();
            mapperFacade.map(fundTransOrder, bean);

            //产品名称
            String str = "";
            if (productMap.containsKey(fundTransOrder.getProductId())) {
                str = productMap.get(fundTransOrder.getProductId());//.getProductName();
            }
            bean.setProductName(str);

            //会员信息
            str = "";
            if (memberMap.containsKey(fundTransOrder.getMemberNo())) {
                str = memberMap.get(fundTransOrder.getMemberNo()).getVerifiedMobile();
            }
            bean.setMemberPhoneNumber(str);
            orderDTOList.add(bean);
        }
        resp.setList(orderDTOList);

        return resp;
    }

    private Map<Long, String> getProductName(List<Long> productIds) {
        Map<Long, String> productNameMap = new HashMap<>();
        QueryProdInfoByProdIdsResp resp = fisIntService.queryProdInfoByProdIds(productIds);
        if (resp.getInfos() != null) {
            for (ProdSummaryInfo product : resp.getInfos()) {
                productNameMap.put(product.getProductId(), product.getProductName());
            }
        }
        if (resp.getCurrentProdInfos() != null) {
            for (CurrentProdInfo product : resp.getCurrentProdInfos()) {
                productNameMap.put(product.getProductId(), product.getProductName());
            }
        }
        if (resp.getSecMarketProds() != null) {
            for (SecMarketProd product : resp.getSecMarketProds()) {
                productNameMap.put(product.getId(), product.getProductName());
            }
        }
        return productNameMap;
    }

    @Override
    public PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPage(
            FundClearingOrderRequest request, boolean forChecking) {
//        logger.info(">>>>> getFundClearingOrdersByPage Request Parameters: " + JSON.toJSONString(request));
//		//check request parameters
//		if (request.getBusinessOrderItemNo().isEmpty() && request.getMemberNo().isEmpty() &&
//			request.getProductId() == null && request.getStartDateTime() == null &&
//			request.getEndDateTime() == null && request.getStatus() == null) {
//			logger.error("request is null parameter.");
//			throw new BusinessException(FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getMessage(),
//					FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getCode());
//		}
        //check page info
        if (request.getPageNum() < 1 || request.getPageSize() < 1) {
            logger.error("invalid request page info.");
            throw new BusinessException(FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getMessage(),
                    FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getCode());
        }

        FundClearingOrderQueryModel queryModel = new FundClearingOrderQueryModel();
        mapperFacade.map(request, queryModel);

        //get fund clearing order
        PageableResponse<FundClearingOrderBean> resp = new PageableResponse<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);
        Page<FundClearingOrder> clearOrderList = (Page<FundClearingOrder>) fundClearingOrderQueryMapper.queryOrder(queryModel);
        resp.initFromPage(clearOrderList);
        if (CollectionUtils.isEmpty(clearOrderList))
            return resp;

        resp.setList(getFundClearingOrderBeanList(clearOrderList, forChecking));
        return resp;
    }

    @Override
    public PageableResponse<FundTransOrderBean> getSucceedFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request) {
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartDate());
        criteria.andCreateTimeLessThan(request.getEndDate());

//        criteria.andFinishTimeGreaterThanOrEqualTo(request.getStartDate());
//        criteria.andFinishTimeLessThan(request.getEndDate());
        criteria.andBusinessOrderItemNoNotEqualTo("");
        ArrayList<Integer> status = new ArrayList<Integer>();
        status.add(FundTransOrderStatus.PAY_SUC);
        status.add(FundTransOrderStatus.TA_TRANS_SUC);
        status.add(FundTransOrderStatus.TA_TRANS_PROCESSING);
        status.add(FundTransOrderStatus.TA_TRANS_CFM);
        criteria.andStatusIn(status);

        PageHelper.startPage(request.getPageNum(), request.getPageSize(), request.isCalCount());

        PageableResponse<FundTransOrderBean> resp = new PageableResponse<>();
        Page<FundTransOrder> orderList = (Page<FundTransOrder>) fundTransOrderMapper.selectByExample(example);
        resp.initFromPage(orderList);
        List<FundTransOrderBean> orderDTOList = mapperFacade.mapAsList(orderList, FundTransOrderBean.class);
        resp.setList(orderDTOList);
        return resp;
    }

    @Override
    public FundClearingOrderBean getFundClearingOrder(QueryFundClearingOrderRequest request) {
        FundClearingOrderQueryModel queryModel = new FundClearingOrderQueryModel();
        queryModel.setFundClearingOrderNo(request.getFundClearingOrderNo());
        queryModel.setBusinessOrderItemNo(request.getBusinessOrderItemNo());
        queryModel.setMemberNo(request.getMemberNo());
        queryModel.setProductId(request.getProductId());

        List<FundClearingOrder> clearOrderList = fundClearingOrderQueryMapper.queryOrder(queryModel);
        List<FundClearingOrderBean> orderBeanList = getFundClearingOrderBeanList(clearOrderList, false);
        return (orderBeanList.size() > 0) ? orderBeanList.get(0) : null;
    }

    @Override
    public List<FundClearingOrderBean> getByFundClearingOrderNos(
            List<String> fundClearingOrderNos, boolean forChecking) {
        List<FundClearingOrderBean> orderBeanList = null;
        if (fundClearingOrderNos != null && fundClearingOrderNos.size() > 0) {
            List<FundClearingOrder> clearOrderList = fundClearingOrderQueryMapper.queryByFundClearingOrderNos(fundClearingOrderNos);

            orderBeanList = getFundClearingOrderBeanList(clearOrderList, forChecking);
        }

        return orderBeanList;
    }

    /**
     * 根据兑付单获取结果单（包括产品信息）
     *
     * @param clearOrderList
     * @param forChecking
     * @return
     */
    private List<FundClearingOrderBean> getFundClearingOrderBeanList(
            List<FundClearingOrder> clearOrderList, boolean forChecking) {
        Map<Long, String> productMap = new HashMap<>();
        if (!forChecking) {
            //get product name
            Set<Long> productIds = new HashSet<Long>();
            for (FundClearingOrder clearOrder : clearOrderList) {
                productIds.add(clearOrder.getProductId());
            }

            productMap = getProductName(new ArrayList<>(productIds));
//            List<ProdSummaryInfo> productList = fisIntService.queryProdInfoByProdIds(new ArrayList<>(productIds)).getInfos();
//            for (ProdSummaryInfo product : productList) {
//                productMap.put(product.getProductId(), product);
//            }
        }

        //set result
        List<FundClearingOrderBean> orderBeanList = new ArrayList<>();
        for (FundClearingOrder clearOrder : clearOrderList) {
            FundClearingOrderBean orderBean = new FundClearingOrderBean();
            mapperFacade.map(clearOrder, orderBean);
            orderBean.setAmount(clearOrder.getConfirmedAmount());
            if (!forChecking) {
                String productName = "";
                if (productMap.containsKey(clearOrder.getProductId())) {
                    productName = productMap.get(clearOrder.getProductId());//.getProductName();
                }
                orderBean.setProductName(productName);
            }
            orderBeanList.add(orderBean);
        }

        return orderBeanList;
    }

    @Override
    public PageableResponse<FundDividendOrderBean> getFundDividendOrdersByPage(FundDividendOrderRequest request) {
        //check page info
        if (request.getPageNum() < 1 || request.getPageSize() < 1) {
            logger.error("invalid request page info.");
            throw new BusinessException(FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getMessage(),
                    FTCRespCode.ERR_PROVIDER_WRONG_PARAMETER.getCode());
        }

        FundDividendOrderQueryModel queryModel = new FundDividendOrderQueryModel();
        mapperFacade.map(request, queryModel);

        //get fund dividend order
        PageableResponse<FundDividendOrderBean> resp = new PageableResponse<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);
        Page<FundDividendOrder> divOrderList = (Page<FundDividendOrder>) fundDividendOrderQueryMapper.queryOrder(queryModel);
        resp.initFromPage(divOrderList);
        if (CollectionUtils.isEmpty(divOrderList))
            return resp;

        resp.setList(getFundDividendOrderBeanList(divOrderList));
        return resp;
    }

    private List<FundDividendOrderBean> getFundDividendOrderBeanList(
            List<FundDividendOrder> divOrderList) {
        //get product name
        Set<Long> productIds = new HashSet<Long>();
        for (FundDividendOrder divOrder : divOrderList) {
            productIds.add(divOrder.getProductId());
        }
        Map<Long, String> productMap = getProductName(new ArrayList<>(productIds));

        //set result
        List<FundDividendOrderBean> orderBeanList = new ArrayList<>();
        for (FundDividendOrder divOrder : divOrderList) {
            FundDividendOrderBean orderBean = new FundDividendOrderBean();
            mapperFacade.map(divOrder, orderBean);

            String productName = "";
            if (productMap.containsKey(divOrder.getProductId())) {
                productName = productMap.get(divOrder.getProductId());
            }
            orderBean.setProductName(productName);

            orderBeanList.add(orderBean);
        }

        return orderBeanList;
    }

    @Override
    public List<String> getLargeInvestmentClinets(List<String> memberNos,
                                                  BigDecimal threshold, List<Integer> transTypeList) {
        return fundTransOrderMapper.queryLargeInvestmentClinets(memberNos, threshold, transTypeList);
    }

    @Override
    public List<MemberInvestmentBean> getFriendsInvestment(
            Integer transType, Date startDt, Date endDt, List<String> memberNos) {
        if (transType.equals(FundTransType.SUBSCRIBING)) {
            //获取定期投资总额
            return fundTransOrderMapper.queryFriendsInvestment(
                    transType, startDt, endDt, memberNos, null);
        }

        List<MemberInvestmentBean> memberInvestments = new ArrayList<>();
        //获取活期、活包定产品
        List<Long> productIds = fundTransOrderMapper.queryMemberProductIds(
                FundTransType.PURCHASING, startDt, endDt, memberNos);
        if (!CollectionUtils.isEmpty(productIds)) {
            if (productIds.size() <= 100) {
                memberInvestments = getCurrentRegularFriendsInvestment(startDt, endDt, memberNos, productIds);
            } else {//大于100个产品
                List<Long> ids = new ArrayList<>();
//    			Map<String, BigDecimal> memberAmountMap = new HashMap<>();
                for (int i = 0; i < productIds.size(); i++) {
                    ids.add(productIds.get(i));

                    if (ids.size() >= 100 || (i >= productIds.size() - 1)) {
                        List<MemberInvestmentBean> li = getCurrentRegularFriendsInvestment(startDt, endDt, memberNos, ids);
                        if (!CollectionUtils.isEmpty(li)) {
//				    		//按会员汇总金额
//				    		for (MemberInvestmentBean investment : li) {
//				    			BigDecimal amount = (investment.getAmount() != null) ? investment.getAmount() : BigDecimal.ZERO;
//				    			if (memberAmountMap.containsKey(investment.getMemberNo())) {
//				    				amount = amount.add(memberAmountMap.get(investment.getMemberNo()));
//				    			}
//				    			memberAmountMap.put(investment.getMemberNo(), investment.getAmount());
//				    		}

                            memberInvestments.addAll(li);
                        }
                        ids.clear();
                    }
                }

//	    		Iterator<String> iter = memberAmountMap.keySet().iterator();
//	    		while (iter.hasNext()) {
//	    			String memberNo = iter.next();
//	    			MemberInvestmentBean investment = new MemberInvestmentBean();
//	    			investment.setMemberNo(memberNo);
//	    			investment.setFundTransType(FundTransType.PURCHASING);
//	    			investment.setAmount(memberAmountMap.get(memberNo));
//	    			
//	    			memberInvestments.add(investment);
//	    		}
            }
        }

        return memberInvestments;
    }

    /**
     * 获取会员活包定产品投资额
     *
     * @param startDt
     * @param endDt
     * @param memberNos
     * @param productIds
     * @return
     */
    private List<MemberInvestmentBean> getCurrentRegularFriendsInvestment(
            Date startDt, Date endDt, List<String> memberNos, List<Long> productIds) {
        QueryProdInfoByProdIdsResp prodResp = fisIntService.queryProdInfoByProdIds(productIds);
        Set<Long> currentIds = new HashSet<>();
        List<Long> currentRegularIds = new ArrayList<>();
        for (CurrentProdInfo product : prodResp.getCurrentProdInfos()) {
            if (product.getCloseType().equals(ProductCloseType.HALF_OPEN)) {
                currentRegularIds.add(product.getProductId());
            } else {
                currentIds.add(product.getProductId());
            }
        }

        List<MemberInvestmentBean> li = new ArrayList<>();
        if (!CollectionUtils.isEmpty(currentRegularIds)) {
            //获取活包定投资总额
            List<MemberInvestmentBean> ll = fundTransOrderMapper.queryFriendsInvestment(
                    FundTransType.PURCHASING, startDt, endDt, memberNos, currentRegularIds);
            if (!CollectionUtils.isEmpty(ll)) {
                for (MemberInvestmentBean asset : ll) {
                    asset.setFundTransType(FundTransType.SUBSCRIBING);
                    li.add(asset);
                }
            }
        }
        if (!CollectionUtils.isEmpty(currentIds)) {
            //获取活期投资总额
            List<MemberInvestmentBean> ll = fundTransOrderMapper.queryFriendsInvestment(
                    FundTransType.PURCHASING, startDt, endDt, memberNos, new ArrayList<>(currentIds));
            if (!CollectionUtils.isEmpty(ll)) {
                li.addAll(ll);
            }
        }
        return li;
    }

    @Override
    public void getFundTransOrderListByOuterOrderNoList(String outerSysNo, List<String> outerOrderNoList, FundTransOrderListResponse fundTransOrderListResponse) {
        OuterOrderRelExample outerOrderRelExample = new OuterOrderRelExample();
        OuterOrderRelExample.Criteria criteria = outerOrderRelExample.createCriteria();
        criteria.andOuterSysNoEqualTo(outerSysNo);
        criteria.andOuterOrderNoIn(outerOrderNoList);
        List<OuterOrderRel> outerOrderRelList = outerOrderRelMapper.selectByExample(outerOrderRelExample);
        List<FundTransOrderBean> fundTransOrderBeans = new ArrayList<FundTransOrderBean>();

        Set<Long> productIds = new HashSet<Long>();
        Set<String> memberNos = new HashSet<String>();
        List<String> fundTransOrderNoList = new ArrayList<String>();
        for (OuterOrderRel outerOrderRel : outerOrderRelList) {
            fundTransOrderNoList.add(outerOrderRel.getBizOrderNo());
        }

        List<FundTransOrder> fundTransOrderList = new ArrayList<FundTransOrder>();
        if (fundTransOrderNoList.size() > 0) {
            FundTransOrderExample example = new FundTransOrderExample();
            FundTransOrderExample.Criteria queryCriteria = example.createCriteria();
            queryCriteria.andFundTransOrderNoIn(fundTransOrderNoList);
            fundTransOrderList = fundTransOrderMapper.selectByExample(example);
        }


        for (FundTransOrder fundTransOrder : fundTransOrderList) {
            productIds.add(fundTransOrder.getProductId());
            memberNos.add(fundTransOrder.getMemberNo());
            FundTransOrderBean fundTransOrderBean = new FundTransOrderBean();
            mapperFacade.map(fundTransOrder, fundTransOrderBean);
            fundTransOrderBeans.add(fundTransOrderBean);
        }

        fundTransOrderListResponse.setFundTransOrderBeans(fundTransOrderBeans);

        Map<Long, ProdInfo> prodInfoMap = new HashMap<>();
        Map<String, MemberIdentityBean> memberIdentityBeanMap = new HashMap<>();


        if (productIds.size() > 0) {
            prodInfoMap = fisIntService.queryProductCommonInfos(productIds);
        }

        if (memberNos.size() > 0) {
            memberIdentityBeanMap = memberIntService.getMemberByMemberNos(memberNos);
        }

        for (FundTransOrderBean bean : fundTransOrderListResponse.getFundTransOrderBeans()) {
            bean.setMemberName(memberIdentityBeanMap.get(bean.getMemberNo()).getRealName());
            bean.setProductName(prodInfoMap.get(bean.getProductId()).getProductName());
        }

    }

}
