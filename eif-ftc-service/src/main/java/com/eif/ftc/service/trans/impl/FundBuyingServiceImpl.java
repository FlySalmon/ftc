package com.eif.ftc.service.trans.impl;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.utils.constant.CurrencyISOCode;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundInteractType;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.request.CreateFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.PayFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.ResumePayRequest;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.constant.PayPartnerNoType;
import com.eif.ftc.service.data.fund.FundAmcDataService;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.action.FundBuyingCreateActionService;
import com.eif.ftc.service.trans.action.FundBuyingLimitedActionService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import com.lts.core.commons.utils.DateUtils;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundBuyingService")
public class FundBuyingServiceImpl implements FundBuyingService {

    static Logger logger = LoggerFactory.getLogger(FundBuyingServiceImpl.class);

    @Resource
    SequenceGenerator sequenceGenerator;

    @Autowired
    MapperFacade mapperFacade;

    @Resource
    FisIntService fisIntService;

    @Resource
    OfcIntService ofcIntService;

    @Resource
    RiskIntService riskIntService;

    @Resource
    MemberIntService memberIntService;

    @Autowired
    FundBuyingLimitedActionService fundBuyingLimitedActionService;

    @Autowired
    FundBuyingCreateActionService fundBuyingCreateActionService;

    @Autowired
    FundBuyingPayActionService fundBuyingPayActionService;

    @Autowired
    FundAmcDataService fundAmcDataService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    TranscoreIntService transcoreIntService;

    @Autowired
    FundTransDataService fundTransDataService;

    @Autowired
    FundCommTransActionService fundCommTransActionService;

    @Resource
    HadesClient hadesClient;

    @Resource
    RedisConcurrentLock redisConcurrentLock;

    static int KEY_EXPIRED_TIME_IN_SECOND = 30 * 60; // 30分钟

    // TODO: 接返回回调
    public void createFundBuyingOrder(CreateFundBuyingOrderRequest createFundBuyingOrderRequest, CreateFundBuyingOrderResponse createFundBuyingOrderResponse) {

        // 获取需要的用户, 产品信息
        Date curDate = DateUtils.now();
        BigDecimal fundTransAmount = createFundBuyingOrderRequest.getFundTransAmount();

//        QueryProdTransInfoResp queryProdTransInfoResp = fisIntService
//                .queryProdTransInfo(createFundBuyingOrderRequest.getProductId());
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(createFundBuyingOrderRequest.getProductId());
        String productName;
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
        } else {
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
        }
        QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByMemberNo(createFundBuyingOrderRequest.getMemberNo());


        // 创建订单限制判断
        fundBuyingLimitedActionService.doCreateOrderLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);

        // 初步构建业务单
        FundTransOrder transOrder = new FundTransOrder();
        mapperFacade.map(createFundBuyingOrderRequest, transOrder);

        //初步初始化订单
        baseInitTransOrder(transOrder, curDate, queryProdTransInfoResp);

        // 并发控制
        redisConcurrentLock.acquire(createFundBuyingOrderRequest.getTrackingNo(), KEY_EXPIRED_TIME_IN_SECOND);

        try {
            // 判断amc账户状态, 是否交易禁止, 并获取用户信息
            FundAccountBean resultBean = fundCommTransActionService.checkAndBuildAmcAccount(transOrder, memberResp);
            // 是否用户需要开户
            boolean isMemberExist = (resultBean == null) ? false : true;

            // 创建ofc用户订单, 同时构建业务单相关信息, 失败则异步发送ofc用户订单失败消息
            fundCommTransActionService.buildForBusinessOrderItem(transOrder, productName, createFundBuyingOrderRequest.getMarketField(), queryProdTransInfoResp.getCloseType());

            // 事前风控校验, 失败则更新业务单和用户订单风控拒绝
            fundBuyingCreateActionService.riskPreCheck(transOrder, createFundBuyingOrderRequest.getDeviceInfo(), createFundBuyingOrderRequest.getDevId(), createFundBuyingOrderRequest.getIp(), productName);

            try {
                fundCommTransActionService.doCreateFundTransTransaction(transOrder, curDate, isMemberExist, createFundBuyingOrderRequest.getOuterSysNo(), createFundBuyingOrderRequest.getOuterOrderNo());
            } catch (DuplicateKeyException ex) {
                // 保证幂等性
                FundTransOrder queryFunTransOrder = new FundTransOrder();
                queryFunTransOrder.setTrackingNo(createFundBuyingOrderRequest.getTrackingNo());
                transOrder = fundTransOrderMapper.selectOne(queryFunTransOrder);
            }

        } finally {
            redisConcurrentLock.release(createFundBuyingOrderRequest.getTrackingNo());
        }

        // 成功更新业务单号
        createFundBuyingOrderResponse.setFundTransOrderNo(transOrder.getFundTransOrderNo());
        createFundBuyingOrderResponse.setBusinessOrderItemNo(transOrder.getBusinessOrderItemNo());

    }

    public void payFundBuyingOrder(PayFundBuyingOrderRequest payFundBuyingOrderRequest, PayFundBuyingOrderResponse resp) {

        // 安全防护
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andFundTransOrderNoEqualTo(payFundBuyingOrderRequest.getFundTransOrderNo());

        List<FundTransOrder> transOrderList = fundTransOrderMapper.selectByExample(example);
        // 找不到单据
        if (transOrderList.size() == 0 || transOrderList.get(0).getStatus() != FundTransOrderStatus.NEED_PAY) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getCode());
        }

        FundTransOrder transOrder = transOrderList.get(0);

        //获取产品信息
//        QueryProdTransInfoResp queryProdTransInfoResp = fisIntService
//                .queryProdTransInfo(transOrder.getProductId());
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(transOrder.getProductId());

        Long orderExpireTime;
        String productName;
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            orderExpireTime = queryProdTransInfoResp.getNormalProdTransInfo().getOrderExpireTime();
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
        } else {
            orderExpireTime = queryProdTransInfoResp.getCurrentProdTransInfo().getOrderExpireTime();
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
        }

        Date curDate = DateUtils.now();
        QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByMemberNo(transOrder.getMemberNo());

        // 并发控制
        redisConcurrentLock.acquire(payFundBuyingOrderRequest.getFundTransOrderNo() + FundTransOrderStatus.NEED_PAY, KEY_EXPIRED_TIME_IN_SECOND);

        try {
            // 预支付订单判断
            fundBuyingLimitedActionService.doPrePayOrderLimitedCheck(curDate, transOrder.getFundTransAmount(), memberResp, queryProdTransInfoResp);


            // 初始构建
            // 直接覆盖支付方式
            if (!StringUtils.isEmpty(payFundBuyingOrderRequest.getPayMethod())) {
                transOrder.setPayMethod(payFundBuyingOrderRequest.getPayMethod());
            }
            //折扣信息
            transOrder.setDiscountAmount(payFundBuyingOrderRequest.getDiscountAmount());
            List<TransactionPaymentOptionBean> paymentOptions = JSON.parseArray(
                    transOrder.getPayMethod(), TransactionPaymentOptionBean.class);
            // 透传交易核心信息, 后续在mq回调中传回
            Map<String, Object> extField = new HashMap<>();
            extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, payFundBuyingOrderRequest.getExpectedProfitAmount());
            extField.put(TransCoreConstant.TRANS_DEV_ID, payFundBuyingOrderRequest.getDevId());
            extField.put(TransCoreConstant.TRANS_IP, payFundBuyingOrderRequest.getIp());
            extField.put(TransCoreConstant.TRANS_DEV_INFO, payFundBuyingOrderRequest.getDeviceInfo());
            // 支付核心需要, 活期商户号
            if (transOrder.getFundTransType().equals(FundTransType.PURCHASING)) {
                for (TransactionPaymentOptionBean bean : paymentOptions) {
                    bean.setPayPartnerNo(PayPartnerNoType.EIF_CURRENT);
                }
                transOrder.setPayMethod(JSON.toJSONString(paymentOptions));
            }

            // 如果包含代扣支付，则需要进行预路由
            RoutePaymentProviderInfoResponse paymentRoutingResp = null;
            if (fundBuyingPayActionService.checkDCPPaymentOption(paymentOptions)) {
                //进行支付预路由处理
                paymentRoutingResp = fundBuyingPayActionService.doPaymentRouting(paymentOptions, payFundBuyingOrderRequest.getSmsControl(), transOrder.getMemberNo());
            }

            //不需要OTP验证
            if (paymentRoutingResp == null || paymentRoutingResp.getSmsStrategy().equals(SmsStrategy.NEVER_SEND_SMS)) {

                // 支付控制, 包括库存, 新手, 流量
                fundBuyingPayActionService.payingControl(transOrder, queryProdTransInfoResp, memberResp, curDate, FundTransOrderStatus.NEED_PAY,
                        payFundBuyingOrderRequest.getDeviceInfo(), payFundBuyingOrderRequest.getDevId(), payFundBuyingOrderRequest.getIp());


                fundBuyingPayActionService.payWithoutOTP(transOrder, paymentRoutingResp, paymentOptions,
                        curDate, orderExpireTime, productName, JSON.toJSONString(extField));


            } else { // 需要OTP验证
                // 调用交易核心扣款
                CreateTransResponse transResponse = transcoreIntService.createTransCoreOrder(
                        transOrder, TransCode.CHARGE, paymentOptions, TransAttribute.PAYMENT,
                        JSON.toJSONString(extField), null, paymentRoutingResp, productName);
                if (transResponse.getTransactionStatus() == TransactionStatus.AUTH_PENDING) {
                    // 更新业务单信息, 由于auth_pending的逻辑不处理, 则不考虑同步异步前后问题
                    transOrder.setStatus(FundTransOrderStatus.AUTH_PENDING);
                    transOrder.setTranscoreTransNo(transResponse.getTransNo());
                    List<Integer> prevStatusList = new ArrayList<>();
                    prevStatusList.add(FundTransOrderStatus.NEED_PAY);
                    fundBuyingPayActionService.updatePayFundTransOrderTransaction(transOrder, prevStatusList, curDate);
                    ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName);

                    // 返回值赋值
                    resp.setPaymentNo(transResponse.getAuthWaitingPaymentNo());

                } else {
                    // 状态错误
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getMessage(),
                            FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getCode());
                }
            }
        } catch (Exception e) {
            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.NEED_PAY);
            fundBuyingPayActionService.updateFundTransOrderForException(transOrder, curDate, e, prevStatusList, productName);
            throw e;
        } finally {
            redisConcurrentLock.release(payFundBuyingOrderRequest.getFundTransOrderNo() + FundTransOrderStatus.NEED_PAY);
        }

        // 返回值设置
        resp.setTransCoreNo(transOrder.getTranscoreTransNo());
        resp.setFundTransStatus(transOrder.getStatus()); // status: Paying
        resp.setFundTransOrderNo(transOrder.getFundTransOrderNo());
        resp.setBusinessOrderItemNo(transOrder.getBusinessOrderItemNo());

    }

    public void resumePay(ResumePayRequest request, ResumePayResponse response) {
        // 安全防护
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andFundTransOrderNoEqualTo(request.getFundTransOrderNo());

        List<FundTransOrder> transOrderList = fundTransOrderMapper.selectByExample(example);
        // 找不到单据
        if (transOrderList.size() == 0 || transOrderList.get(0).getStatus() != FundTransOrderStatus.AUTH_PENDING) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ORDER_QUERY_NOT_FOUND.getCode());
        }

        FundTransOrder transOrder = transOrderList.get(0);

        //获取基础信息
        Date curDate = DateUtils.now();
//        QueryProdTransInfoResp queryProdTransInfoResp = fisIntService
//                .queryProdTransInfo(transOrder.getProductId());
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(transOrder.getProductId());

        Long orderExpireTime;
        String productName;
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            orderExpireTime = queryProdTransInfoResp.getNormalProdTransInfo().getOrderExpireTime();
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
        } else {
            orderExpireTime = queryProdTransInfoResp.getCurrentProdTransInfo().getOrderExpireTime();
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
        }


        QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByMemberNo(transOrder.getMemberNo());

        // 并发控制
        redisConcurrentLock.acquire(request.getFundTransOrderNo() + FundTransOrderStatus.AUTH_PENDING, KEY_EXPIRED_TIME_IN_SECOND);

        try {
            // 支付控制, 包括库存, 新手, 流量
            fundBuyingPayActionService.payingControl(transOrder, queryProdTransInfoResp, memberResp, curDate, FundTransOrderStatus.AUTH_PENDING, request.getDeviceInfo(), request.getDevId(), request.getIp());

            // 调用交易核心的继续支付接口, 此方法不会抛出异常
            fundBuyingPayActionService.resumePayTrans(transOrder.getTranscoreTransNo(), request.getPin(), transOrder, curDate, orderExpireTime, productName);


        } catch (Exception e) {
            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.AUTH_PENDING);
            fundBuyingPayActionService.updateFundTransOrderForException(transOrder, curDate, e, prevStatusList, productName);
            throw e;
        } finally {
            // 结束并发控制
            redisConcurrentLock.release(transOrder.getFundTransOrderNo() + FundTransOrderStatus.AUTH_PENDING);
        }

        // 设置返回值
        response.setTransNo(transOrder.getTranscoreTransNo());
        response.setTransStatus(TransactionStatus.SETTLING);
    }


    private void baseInitTransOrder(FundTransOrder fundTransOrder, Date curDate, QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            fundTransOrder.setFundTransType(FundTransType.SUBSCRIBING);
        } else if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.OPEN) || queryProdTransInfoResp.getCloseType().equals(ProductCloseType.HALF_OPEN) || queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE_OPEN)) {
            fundTransOrder.setFundTransType(FundTransType.PURCHASING);
        } else {
            //未知产品封闭类型
            throw new BusinessException(FTCRespCode.ERR_FIS_UNKNOWN_PRODUCT_CLOSE_TYPE.getMessage(), FTCRespCode.ERR_FIS_UNKNOWN_PRODUCT_CLOSE_TYPE.getCode());
        }

        fundTransOrder.setFundTransOrderNo(sequenceGenerator.transOrderGen(fundTransOrder.getFundTransType()));
        fundTransOrder.setRefundAmount(BigDecimal.ZERO);
        fundTransOrder.setStatus(FundTransOrderStatus.NEED_PAY);
        fundTransOrder.setUpdateTime(curDate);
        fundTransOrder.setCreateTime(curDate);
        fundTransOrder.setCurrencyType(CurrencyISOCode.CHINA);
        fundTransOrder.setFundInteractType(FundInteractType.FILE);
    }

}
