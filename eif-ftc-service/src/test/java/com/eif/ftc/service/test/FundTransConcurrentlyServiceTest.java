package com.eif.ftc.service.test;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.InvestmentUnit;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.constant.WhiteListType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.dto.ftc.CurrentProdTransInfo;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.pagination.pagehelper.PageInfo;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.enumeration.TriggerReason;
import com.eif.ftc.facade.fund.trans.request.CreateFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.PayFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.ResumePayRequest;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.service.test.amc.FundDetailAlterationTestService;
import com.eif.ftc.service.test.prepare.*;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 10/13/16.
 */
@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,  MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransConcurrentlyServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Mock
    HadesClient hadesClient;
    @Mock
    DefaultRMQProducer producer;

    @Spy
    @Resource
    SequenceGenerator sequenceGenerator;

    @Spy
    @Resource
    FisIntService fisIntService;

    @Spy
    @Resource
    OfcIntService ofcIntService;

    @Spy
    @Resource
    RiskIntService riskIntService;

    @Spy
    @Resource
    MemberIntService memberIntService;

    @Spy
    @Resource
    MarketIntService marketIntService;

    @Spy
    @Resource
    TranscoreIntService transcoreIntService;

    @Spy
    @Resource
    RedisConcurrentLock redisConcurrentLock;

    @Autowired
    FundDetailAlterationTestService fundDetailAlterationTestService;

    @InjectMocks
    @Autowired
    FundBuyingService fundBuyingService;

    @InjectMocks
    @Autowired
    FundMQConsumerService fundMQConsumerService;

    @InjectMocks
    @Autowired
    FundOrderExpiredService fundOrderExpiredService;

    @InjectMocks
    @Autowired
    FundRedeemService fundRedeemService;

    private final static String ip = "1.1.1.1"; //NOSONAR

    @Before
    public void before() {
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).acctOrderGen(Mockito.anyInt());
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).transOrderGen(Mockito.anyInt());
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).amcNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 23)).when(sequenceGenerator).taSeqNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 16)).when(sequenceGenerator).transAccGen();

        CommonDataPrepare.buildConcurrentSuccess(redisConcurrentLock);
        FisDataPrepare.buildFreezeProdInventoryForSuccess(fisIntService);
        MemberDataPrepare.buildBatchUpdateRookieForSuccess(memberIntService);
        MarketDataPrepare.buildFreezeGrouponInventoryForSuccess(marketIntService);
        MarketDataPrepare.buildUnfreezeAndReturnGrouponInventoryForSuccess(marketIntService);
        MarketDataPrepare.buildSendMemberInvestmentForSuccess(marketIntService);
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);
    }

    @After
    public void after() {
        Mockito.reset(fisIntService);
        Mockito.reset(riskIntService);
        Mockito.reset(ofcIntService);
        Mockito.reset(memberIntService);
        Mockito.reset(transcoreIntService);
    }

    @Rollback
    @Test
    @NotTransactional
    public void testExpiredJobSucOrderly() {

        FisDataPrepare.buildQueryProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);
        MarketDataPrepare.buildUnfreezeAndDeductGrouponInventoryForSuccess(marketIntService);

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(100));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);


        RiskDataPrepare.buildQueryDayAmountForSuccess(riskIntService);
        RiskDataPrepare.buildQueryTodayStatByProdForSuccess(riskIntService);
        FisDataPrepare.buildQueryProdInvenInfoForSuccess(fisIntService);
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, true);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, false);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        PayFundBuyingOrderRequest payFundBuyingOrderRequest = new PayFundBuyingOrderRequest();
        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setAmount(new BigDecimal(100));
        bean.setCardNo("123");
        bean.setDebitMemberNo(UUIDGenerator.gen());
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
        paymentOptionBean.add(bean);
        payFundBuyingOrderRequest.setFundTransOrderNo(response.getFundTransOrderNo());
        payFundBuyingOrderRequest.setPayMethod(JSON.toJSONString(paymentOptionBean));
        payFundBuyingOrderRequest.setSmsControl(SmsStrategy.AVOID_SEND_SMS.getValue());

        payFundBuyingOrderRequest.setExpectedProfitAmount(BigDecimal.ZERO);
        payFundBuyingOrderRequest.setDeviceInfo("device123");
        payFundBuyingOrderRequest.setIp(ip);
        payFundBuyingOrderRequest.setDevId(UUIDGenerator.gen());

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);


        FisDataPrepare.buildQueryProdTaTransInfoForSuccess(fisIntService);
        FisDataPrepare.buildUnfreezeAndDeductionProdInventoryForSuccess(fisIntService);

        MQTransInfoBean message = new MQTransInfoBean();
        message.setBizOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        message.setTransStatus(TransactionStatus.SETTLED);
        message.setTransCode(TransCode.CHARGE);
        message.setBizTransType(FundTransType.SUBSCRIBING);

        Map<String, Object> extField = new HashMap<>();
        extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, payFundBuyingOrderRequest.getExpectedProfitAmount());
        extField.put(TransCoreConstant.TRANS_DEV_ID, payFundBuyingOrderRequest.getDevId());
        extField.put(TransCoreConstant.TRANS_IP, payFundBuyingOrderRequest.getIp());
        extField.put(TransCoreConstant.TRANS_DEV_INFO, payFundBuyingOrderRequest.getDeviceInfo());
        message.setExtField(JSON.toJSONString(extField));

        fundMQConsumerService.consumeTransCoreStatusMessage(message);

        FisDataPrepare.buildUnfreezeProdInventoryBatchForSuccess(fisIntService);
        PageInfo<FundTransOrder> page = fundOrderExpiredService.processByPage(1, 50, new Date(), false);
        Assert.assertEquals(0, page.getSize());
    }

    @Rollback
    @Test
    @NotTransactional
    public void testRefundSucOrderly() {

        FisDataPrepare.buildQueryProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(100));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);


        RiskDataPrepare.buildQueryDayAmountForSuccess(riskIntService);
        RiskDataPrepare.buildQueryTodayStatByProdForSuccess(riskIntService);
        FisDataPrepare.buildQueryProdInvenInfoForSuccess(fisIntService);
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, true);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, false);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);

        PayFundBuyingOrderRequest payFundBuyingOrderRequest = new PayFundBuyingOrderRequest();
        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setAmount(new BigDecimal(100));
        bean.setCardNo("123");
        bean.setDebitMemberNo(UUIDGenerator.gen());
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
        paymentOptionBean.add(bean);
        payFundBuyingOrderRequest.setFundTransOrderNo(response.getFundTransOrderNo());
        payFundBuyingOrderRequest.setPayMethod(JSON.toJSONString(paymentOptionBean));
        payFundBuyingOrderRequest.setSmsControl(SmsStrategy.AVOID_SEND_SMS.getValue());

        payFundBuyingOrderRequest.setExpectedProfitAmount(BigDecimal.ZERO);
        payFundBuyingOrderRequest.setDeviceInfo("device123");
        payFundBuyingOrderRequest.setIp(ip);
        payFundBuyingOrderRequest.setDevId(UUIDGenerator.gen());

        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);
        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);

        FisDataPrepare.buildUnfreezeProdInventoryBatchForSuccess(fisIntService);
        fundOrderExpiredService.orderExpiredScan();

        // 验证单据过期关闭
        FundTransOrder queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
        FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
        Assert.assertEquals(FundTransOrderStatus.CLOSE_BY_EXPIRY, transOrder.getStatus().intValue());

        FisDataPrepare.buildQueryProdTaTransInfoForSuccess(fisIntService);
        FisDataPrepare.buildUnfreezeAndDeductionProdInventoryForSuccess(fisIntService);

        MQTransInfoBean message = new MQTransInfoBean();
        message.setBizOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        message.setTransStatus(TransactionStatus.SETTLED);
        message.setTransCode(TransCode.CHARGE);
        message.setBizTransType(FundTransType.SUBSCRIBING);

        Map<String, Object> extField = new HashMap<>();
        extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, payFundBuyingOrderRequest.getExpectedProfitAmount());
        extField.put(TransCoreConstant.TRANS_DEV_ID, payFundBuyingOrderRequest.getDevId());
        extField.put(TransCoreConstant.TRANS_IP, payFundBuyingOrderRequest.getIp());
        extField.put(TransCoreConstant.TRANS_DEV_INFO, payFundBuyingOrderRequest.getDeviceInfo());
        message.setExtField(JSON.toJSONString(extField));

        fundMQConsumerService.consumeTransCoreStatusMessage(message);

        // 验证单据退款
        queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
        transOrder = fundTransOrderMapper.selectOne(queryOrder);
        Assert.assertEquals(FundTransOrderStatus.REFUNDING, transOrder.getStatus().intValue());


    }


    @Test
    @Rollback
    @NotTransactional
    public void testExpiredAndSucConcurrently() {
        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);
        FisDataPrepare.buildQueryProdTransInfoForSuccess(fisIntService);
        RiskDataPrepare.buildDeductDayStat(riskIntService);

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(100));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);


        RiskDataPrepare.buildQueryDayAmountForSuccess(riskIntService);
        RiskDataPrepare.buildQueryTodayStatByProdForSuccess(riskIntService);
        FisDataPrepare.buildQueryProdInvenInfoForSuccess(fisIntService);
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, true);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, false);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);

        PayFundBuyingOrderRequest payFundBuyingOrderRequest = new PayFundBuyingOrderRequest();
        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setAmount(new BigDecimal(100));
        bean.setCardNo("123");
        bean.setDebitMemberNo(UUIDGenerator.gen());
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
        paymentOptionBean.add(bean);
        payFundBuyingOrderRequest.setFundTransOrderNo(response.getFundTransOrderNo());
        payFundBuyingOrderRequest.setPayMethod(JSON.toJSONString(paymentOptionBean));
        payFundBuyingOrderRequest.setSmsControl(SmsStrategy.AVOID_SEND_SMS.getValue());

        payFundBuyingOrderRequest.setExpectedProfitAmount(BigDecimal.ZERO);
        payFundBuyingOrderRequest.setDeviceInfo("device123");
        payFundBuyingOrderRequest.setIp(ip);
        payFundBuyingOrderRequest.setDevId(UUIDGenerator.gen());

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);


        FisDataPrepare.buildQueryProdTaTransInfoForSuccess(fisIntService);
        FisDataPrepare.buildUnfreezeAndDeductionProdInventoryForSuccess(fisIntService);

        MQTransInfoBean message = new MQTransInfoBean();
        message.setBizOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        message.setTransStatus(TransactionStatus.SETTLED);
        message.setTransCode(TransCode.CHARGE);
        message.setBizTransType(FundTransType.SUBSCRIBING);

        Map<String, Object> extField = new HashMap<>();
        extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, payFundBuyingOrderRequest.getExpectedProfitAmount());
        extField.put(TransCoreConstant.TRANS_DEV_ID, payFundBuyingOrderRequest.getDevId());
        extField.put(TransCoreConstant.TRANS_IP, payFundBuyingOrderRequest.getIp());
        extField.put(TransCoreConstant.TRANS_DEV_INFO, payFundBuyingOrderRequest.getDeviceInfo());
        message.setExtField(JSON.toJSONString(extField));

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Thread.sleep(3000);
                long normalBaseProductID = 1000L;
                QueryProdTransInfoV2Resp queryProdTransInfoResp = new QueryProdTransInfoV2Resp();
                CurrentProdTransInfo currentProdTransInfo = new CurrentProdTransInfo();

                currentProdTransInfo.setId(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);
                currentProdTransInfo.setProductName("测试活期产品");
                currentProdTransInfo.setCloseType(ProductCloseType.OPEN);
                currentProdTransInfo.setOrderExpireTime(-1L);
                currentProdTransInfo.setBizChannel(new Integer(BizChannel.H5).toString());
                currentProdTransInfo.setIsNovicePacks(false);
                currentProdTransInfo.setIsEmployeeExclusive(false);
                currentProdTransInfo.setWhiteListType(WhiteListType.NO_WHITE_LIST);
                currentProdTransInfo.setInvestmentUnit(InvestmentUnit.SUM);
                currentProdTransInfo.setPerAmount(new BigDecimal(1));
                currentProdTransInfo.setMinBuyAmt(new BigDecimal(-1));
                currentProdTransInfo.setMaxBuyAmt(new BigDecimal(-1));
                currentProdTransInfo.setMinSellAmt(new BigDecimal(-1));
                currentProdTransInfo.setMaxSellAmt(new BigDecimal(-1));
                currentProdTransInfo.setPerIncAmt(new BigDecimal(1));
                // 单户单日购买最高金额/份额：用户购买产品的单日最高限额
                currentProdTransInfo.setPerUdbLimitAmt(new BigDecimal(-1));
                // 单户单日赎回最高金额/份额：用户赎回产品的单日最高限额
                currentProdTransInfo.setPerUdsLimitAmt(new BigDecimal(-1));
                // 单人可购买的产品总共最高限额
                currentProdTransInfo.setLimitBuyAmt(new BigDecimal(-1));
                // 判断是否超过今日产品的总限额
                currentProdTransInfo.setDailyMaxBuyAmt(new BigDecimal(-1));
                // 设置rule
                TagRules tagRules = new TagRules();
                tagRules.setTagId(1L);
                tagRules.setPerUserBuyLimit(new BigDecimal(-1));
                currentProdTransInfo.setTagRules(tagRules);

                queryProdTransInfoResp.setCurrentProdTransInfo(currentProdTransInfo);
                queryProdTransInfoResp.setCloseType(ProductCloseType.OPEN);
                queryProdTransInfoResp.setRespCode(CommonRspCode.SUCCESS.getCode());
                queryProdTransInfoResp.setMsg(CommonRspCode.SUCCESS.getMessage());
                return queryProdTransInfoResp;
            }
        }).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                FisDataPrepare.buildUnfreezeProdInventoryBatchForSuccess(fisIntService);
                PageInfo<FundTransOrder> pageInfo = fundOrderExpiredService.processByPage(1, 10, new Date(), false);
                Mockito.verify(riskIntService, Mockito.times(pageInfo.getSize())).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            }
        });

        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fundMQConsumerService.consumeTransCoreStatusMessage(message);

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FundTransOrder queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
        FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);

        if (transOrder.getStatus() == FundTransOrderStatus.REFUNDING) {
            System.out.println("REFUNDING");
            Mockito.verify(riskIntService, Mockito.never()).riskCheckTransPost(Mockito.any(FundTransOrder.class), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        }
        else {
            System.out.println("status:" + transOrder.getStatus());
            Assert.fail();
        }

    }

}
