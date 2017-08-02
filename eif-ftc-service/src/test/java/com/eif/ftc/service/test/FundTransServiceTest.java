package com.eif.ftc.service.test;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.request.ftc.FreezeProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.id.ZkIdGenerator;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.constant.BonusAlternationType;
import com.eif.ftc.dal.constant.ProfitAlternationType;
import com.eif.ftc.dal.dao.*;
import com.eif.ftc.dal.model.*;
import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;
import com.eif.ftc.facade.fund.amc.request.FundBonusPageableRequest;
import com.eif.ftc.facade.fund.amc.response.MemberAssetByCloseTypeResponse;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatusDetail;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.enumeration.TriggerReason;
import com.eif.ftc.facade.fund.trans.request.CreateFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.PayFundBuyingOrderRequest;
import com.eif.ftc.facade.fund.trans.request.RedeemFundRequest;
import com.eif.ftc.facade.fund.trans.request.ResumePayRequest;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.RedeemFundResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundPurchaseConfirmBean;
import com.eif.ftc.service.bean.FundRedemptionConfirmBean;
import com.eif.ftc.service.constant.AMCOrderType;
import com.eif.ftc.service.data.fund.FundAmcDataService;
import com.eif.ftc.service.fund.amc.FundBonusService;
import com.eif.ftc.service.fund.amc.FundDetailAlterationService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.service.test.amc.FundDetailAlterationTestService;
import com.eif.ftc.service.test.prepare.*;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.service.trans.action.FundBuyingCreateActionService;
import com.eif.ftc.service.trans.action.FundBuyingLimitedActionService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.service.trans.impl.FundBuyingServiceImpl;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import ma.glasnost.orika.MapperFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 10/6/16.
 */
@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Mock
    HadesClient hadesClient;
    @Mock
    DefaultRMQProducer producer;

    @Spy
    @Resource
    SequenceGenerator sequenceGenerator;

    @Spy
    @Resource
    RedisConcurrentLock redisConcurrentLock;

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
    TranscoreIntService transcoreIntService;

    @Spy
    @Resource
    MarketIntService marketIntService;

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

    @InjectMocks
    @Autowired
    FundBonusService fundBonusService;

    @InjectMocks
    @Autowired
    MemberAssetService memberAssetService;



    private final static String ip = "1.1.1.1"; //NOSONAR


    @Autowired
    MemberFundAccountMapper memberFundAccountMapper;


    @Autowired
    FundTotalMapper fundTotalMapper;


    @Autowired
    FundDetailMapper fundDetailMapper;

    @Autowired
    FundBonusDetailAlterationMapper fundBonusDetailAlterationMapper;

    @Autowired
    FundProfitAlterationMapper fundProfitAlterationMapper;

    @Before
    public void before() {
        Random random = new Random();

//        Mockito.doReturn(UUIDGenerator.gen() + String.format("%01d", random.nextInt(9))).when(zkId17Generator).genId();
//        Mockito.doReturn(UUIDGenerator.gen() + String.format("%03d", random.nextInt(999))).when(zkId20Generator).genId();

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
        RiskDataPrepare.buildDeductDayStat(riskIntService);
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);
        RiskDataPrepare.buildQueryDayAmountForSuccess(riskIntService);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);
    }

    @After
    public void after() {
        Mockito.reset(fisIntService);
        Mockito.reset(riskIntService);
        Mockito.reset(ofcIntService);
        Mockito.reset(memberIntService);
        Mockito.reset(transcoreIntService);
    }

    @Test
    public void testCreateFundBuyingOrderSuc() {
        FisDataPrepare.buildQueryProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(100));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();

        fundBuyingService.createFundBuyingOrder(request, response);
    }

    @Test
    public void testPayFundBuyingOrderNoOTPSuc() {

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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
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

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
    }

    @Test
    public void testPayFundBuyingOrderWithOTPSuc() {

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
        payFundBuyingOrderRequest.setSmsControl(SmsStrategy.ALWAYS_SEND_SMS.getValue());

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
    }

    @Test
    public void testResumePaySuc() {

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

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);

    }

    @Test
    public void testChargeSuc() {

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

    }

    @Test
    public void testChargeFailed() {

        FisDataPrepare.buildQueryProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);
        FisDataPrepare.buildUnfreezeProdInventoryForSuccess(fisIntService);


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
        message.setTransStatus(TransactionStatus.SETTLE_FAILED);
        message.setTransCode(TransCode.CHARGE);
        message.setBizTransType(FundTransType.SUBSCRIBING);

        Map<String, Object> extField = new HashMap<>();
        extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, payFundBuyingOrderRequest.getExpectedProfitAmount());
        extField.put(TransCoreConstant.TRANS_DEV_ID, payFundBuyingOrderRequest.getDevId());
        extField.put(TransCoreConstant.TRANS_IP, payFundBuyingOrderRequest.getIp());
        extField.put(TransCoreConstant.TRANS_DEV_INFO, payFundBuyingOrderRequest.getDeviceInfo());
        message.setExtField(JSON.toJSONString(extField));

        fundMQConsumerService.consumeTransCoreStatusMessage(message);

    }

    @Test
    public void testRefundSuc() {

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

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);


        FisDataPrepare.buildQueryProdTaTransInfoForSuccess(fisIntService);
        FisDataPrepare.buildUnfreezeAndDeductionProdInventoryForRefund(fisIntService);

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

        CusTransResultRequest settingsMessage = new CusTransResultRequest();
        settingsMessage.setBizOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        settingsMessage.setTransStatus(TransactionStatus.SETTLED);
        settingsMessage.setTransCode(TransCode.REFUND);
        settingsMessage.setBizTransType(FundTransType.SUBSCRIBING);
        fundMQConsumerService.consumeSettingsMessage(settingsMessage);

    }

    @Test
    public void testRefundFailed() {

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

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        TranscoreDataPrepare.buildResumePayTransForSuccess(transcoreIntService);

        ResumePayRequest resumePayRequest = new ResumePayRequest();
        resumePayRequest.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        resumePayRequest.setPin("123");

        ResumePayResponse resumePayResponse = new ResumePayResponse();

        fundBuyingService.resumePay(resumePayRequest, resumePayResponse);


        FisDataPrepare.buildQueryProdTaTransInfoForSuccess(fisIntService);
        FisDataPrepare.buildUnfreezeAndDeductionProdInventoryForRefund(fisIntService);

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

        CusTransResultRequest settingsMessage = new CusTransResultRequest();
        settingsMessage.setBizOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        settingsMessage.setTransStatus(TransactionStatus.SETTLE_FAILED);
        settingsMessage.setTransCode(TransCode.REFUND);
        settingsMessage.setBizTransType(FundTransType.SUBSCRIBING);
        fundMQConsumerService.consumeSettingsMessage(settingsMessage);
    }

    @Test
    public void testExpiredJob() {
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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
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

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();
        fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);

        FisDataPrepare.buildUnfreezeProdInventoryBatchForSuccess(fisIntService);
        fundOrderExpiredService.orderExpiredScan();
    }

    @Test
    public void testRedeemSuc() {

        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

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

        Mockito.verify(fisIntService, Mockito.never()).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));

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

        Mockito.verify(fisIntService, Mockito.never()).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));

        // 模拟申购确认
        List<FundTransOrder> transOrderList = new ArrayList<>();
        FundTransOrder targetOrder = new FundTransOrder();
        targetOrder.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        targetOrder.setCreateTime(new Date());
        targetOrder.setFundTransAmount(request.getFundTransAmount());
        transOrderList.add(targetOrder);
        List<FundPurchaseConfirmBean> transConfirmList = new ArrayList<>();
        for (FundTransOrder transOrder : transOrderList) {
            FundPurchaseConfirmBean transCfm = new FundPurchaseConfirmBean();
            transCfm.setAlterationStatus(AlterationStatus.TA_CONFIRM);
            transCfm.setFtcOrderCreateTime(transOrder.getCreateTime());
            transCfm.setFtcOrderNo(transOrder.getFundTransOrderNo());
            transCfm.setFundShare(transOrder.getFundTransAmount());
            transConfirmList.add(transCfm);
        }

        fundDetailAlterationTestService.updateFundByPurchaseConfirmList(transConfirmList);

        FisDataPrepare.buildDeductionMonetaryInstrumentBalanceForSuccess(fisIntService);

        RedeemFundRequest redeemFundRequest = new RedeemFundRequest();
        redeemFundRequest.setBizChannel(BizChannel.ANDROID);
        redeemFundRequest.setFundTransAmount(new BigDecimal(100));
        redeemFundRequest.setIsAdjusted(false);
        redeemFundRequest.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        redeemFundRequest.setOperatorNo(UUIDGenerator.gen());
        redeemFundRequest.setTrackingNo(UUIDGenerator.gen());
        redeemFundRequest.setMerMemberNo(UUIDGenerator.gen());
        redeemFundRequest.setProductId((FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID));
        redeemFundRequest.setRemark("test");
        redeemFundRequest.setTriggerReason(TriggerReason.NORMAL);

        RedeemFundResponse redeemFundResponse = new RedeemFundResponse();

        fundRedeemService.redeemFund(redeemFundRequest, redeemFundResponse);
    }

    @Test
    public void testAMC(){

        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

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

        Mockito.verify(fisIntService, Mockito.never()).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));

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

        Mockito.verify(fisIntService, Mockito.never()).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));

        // 模拟申购确认
        List<FundTransOrder> transOrderList = new ArrayList<>();
        FundTransOrder targetOrder = new FundTransOrder();
        targetOrder.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        targetOrder.setCreateTime(new Date());
        targetOrder.setFundTransAmount(request.getFundTransAmount());
        transOrderList.add(targetOrder);
        List<FundPurchaseConfirmBean> transConfirmList = new ArrayList<>();
        for (FundTransOrder transOrder : transOrderList) {
            FundPurchaseConfirmBean transCfm = new FundPurchaseConfirmBean();
            transCfm.setAlterationStatus(AlterationStatus.TA_CONFIRM);
            transCfm.setFtcOrderCreateTime(transOrder.getCreateTime());
            transCfm.setFtcOrderNo(transOrder.getFundTransOrderNo());
            transCfm.setFundShare(transOrder.getFundTransAmount());
            transConfirmList.add(transCfm);
        }

        fundDetailAlterationTestService.updateFundByPurchaseConfirmList(transConfirmList);




        //模拟分红
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);

        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());

        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(),FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);

        Date nowDate = new Date();
        for(int i = 0 ; i < 10 ; i++){
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(nowDate);

            calendar.add(Calendar.DAY_OF_MONTH, 0-i);//加一天

            Date bonusTime = calendar.getTime();


            FundBonusDetailAlteration fundBonusDetailAlteration = new FundBonusDetailAlteration();
            fundBonusDetailAlteration.setFundTotalUuid(fundTotal.getFundTotalUuid());
            fundBonusDetailAlteration.setBonusAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.BONUS_DETAIL_ALTERATION));
            fundBonusDetailAlteration.setBonusTime(bonusTime);
            fundBonusDetailAlteration.setFtcOrderNo("XXXX");
            fundBonusDetailAlteration.setFundBonusAmount(new BigDecimal("0.01"));
            fundBonusDetailAlteration.setFundDetailId(fundDetail.getId());
            fundBonusDetailAlteration.setFundDetailUuid(fundDetail.getFundDetailUuid());
            fundBonusDetailAlteration.setFundTotalId(fundTotal.getId());
            fundBonusDetailAlteration.setCreateTime(new Date());
            fundBonusDetailAlteration.setUpdateTime(new Date());
            fundBonusDetailAlteration.setBonusType(BonusAlternationType.OPEN.getValue());
            fundBonusDetailAlterationMapper.insert(fundBonusDetailAlteration);


            FundProfitAlteration fundProfitAlteration = new FundProfitAlteration();
            fundProfitAlteration.setCreateTime(new Date());
            fundProfitAlteration.setFtcOrderNo("");
            fundProfitAlteration.setFundDetailId(fundDetail.getId());
            fundProfitAlteration.setFundDetailUuid(fundDetail.getFundDetailUuid());
            fundProfitAlteration.setFundTotalId(fundTotal.getId());
            fundProfitAlteration.setFundTotalUuid(fundTotal.getFundTotalUuid());
            fundProfitAlteration.setProfitAddTime(bonusTime);
            fundProfitAlteration.setProfitAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.PROFIT_DETAIL_ALTERATION));
            fundProfitAlteration.setProfitAmount(new BigDecimal("0.11"));
            fundProfitAlteration.setProfitType(ProfitAlternationType.COTOP.getValue());
            fundProfitAlteration.setUpdateTime(new Date());
            fundProfitAlterationMapper.insert(fundProfitAlteration);


        }


        FundBonusPageableRequest fundBonusPageableRequest = new FundBonusPageableRequest();
        fundBonusPageableRequest.setProductId(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);
        fundBonusPageableRequest.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        fundBonusPageableRequest.setPageNum(1);
        fundBonusPageableRequest.setPageSize(5);

        PageableResponse<BonusBean> pageableResponse = fundBonusService.getFundBonusList(fundBonusPageableRequest);

        System.out.println(pageableResponse);





    }



    @Test
    public void testAMCQueryMemberAssetByCloseType()
    {
        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

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

        Mockito.verify(fisIntService, Mockito.never()).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));

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

        Mockito.verify(fisIntService, Mockito.never()).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));

        // 模拟申购确认
        List<FundTransOrder> transOrderList = new ArrayList<>();
        FundTransOrder targetOrder = new FundTransOrder();
        targetOrder.setFundTransOrderNo(payFundBuyingOrderResponse.getFundTransOrderNo());
        targetOrder.setCreateTime(new Date());
        targetOrder.setFundTransAmount(request.getFundTransAmount());
        transOrderList.add(targetOrder);
        List<FundPurchaseConfirmBean> transConfirmList = new ArrayList<>();
        for (FundTransOrder transOrder : transOrderList) {
            FundPurchaseConfirmBean transCfm = new FundPurchaseConfirmBean();
            transCfm.setAlterationStatus(AlterationStatus.TA_CONFIRM);
            transCfm.setFtcOrderCreateTime(transOrder.getCreateTime());
            transCfm.setFtcOrderNo(transOrder.getFundTransOrderNo());
            transCfm.setFundShare(transOrder.getFundTransAmount());
            transConfirmList.add(transCfm);
        }

        fundDetailAlterationTestService.updateFundByPurchaseConfirmList(transConfirmList);




        //模拟分红
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);

        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());

        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(),FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);

        Date nowDate = new Date();
        for(int i = 0 ; i < 10 ; i++){
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(nowDate);

            calendar.add(Calendar.DAY_OF_MONTH, 0-i);//加一天

            Date bonusTime = calendar.getTime();


            FundBonusDetailAlteration fundBonusDetailAlteration = new FundBonusDetailAlteration();
            fundBonusDetailAlteration.setFundTotalUuid(fundTotal.getFundTotalUuid());
            fundBonusDetailAlteration.setBonusAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.BONUS_DETAIL_ALTERATION));
            fundBonusDetailAlteration.setBonusTime(bonusTime);
            fundBonusDetailAlteration.setFtcOrderNo("XXXX");
            fundBonusDetailAlteration.setFundBonusAmount(new BigDecimal("0.01"));
            fundBonusDetailAlteration.setFundDetailId(fundDetail.getId());
            fundBonusDetailAlteration.setFundDetailUuid(fundDetail.getFundDetailUuid());
            fundBonusDetailAlteration.setFundTotalId(fundTotal.getId());
            fundBonusDetailAlteration.setCreateTime(new Date());
            fundBonusDetailAlteration.setUpdateTime(new Date());
            fundBonusDetailAlteration.setBonusType(BonusAlternationType.OPEN.getValue());
            fundBonusDetailAlterationMapper.insert(fundBonusDetailAlteration);
        }

        MemberAssetByCloseTypeResponse resp  = new MemberAssetByCloseTypeResponse();

        List<Integer> closeTypeList = new ArrayList<>();
        closeTypeList.add(ProductCloseType.OPEN);
        closeTypeList.add(ProductCloseType.CLOSE_OPEN);
        closeTypeList.add(ProductCloseType.HALF_OPEN);

        memberAssetService.queryMemberAssetByCloseType(resp,MemberDataPrepare.NORMAL_SUC_MEMBER_NO,closeTypeList);
        System.out.println(resp);


    }
}
