package com.eif.ftc.service.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.service.test.prepare.*;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.request.ftc.DeductionMonetaryInstrumentBalanceReq;
import com.eif.fis.facade.request.ftc.FreezeProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeProdInventoryReq;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner.AutoMockLevel;
import com.eif.framework.test.runner.DefaultRunner.MockLevelEnum;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
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
import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.service.test.amc.FundDetailAlterationTestService;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.market.facade.request.groupon.FreezeGrouponInventoryReq;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bohan on 10/12/16.
 */
@AutoMockLevel(MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,  MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransTimeoutServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Spy
    HadesClient hadesClient;
    @Spy
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

    /**
     * 测试前提:
     * 测试场景: 测试如果库存没有冻结上,则支付库存不需要补偿的场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testFreezeProdInventoryPayNotCompensable() {

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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);
        FisDataPrepare.buildUnfreezeProdInventoryForSuccess(fisIntService);

        Mockito.doThrow(new BusinessException(FTCRespCode.ERR_FROZEN_TOKEN_EXIST.getMessage(), FTCRespCode.ERR_FROZEN_TOKEN_EXIST.getCode())).when(fisIntService).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));

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
        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(memberIntService, Mockito.times(2)).updateRookieStatusAndRetIsRookie(Mockito.anyString(), Mockito.anyInt());
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            Mockito.verify(fisIntService, Mockito.never()).unfreezeProdInventory(Mockito.any(UnfreezeProdInventoryReq.class));

            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }
    }

    /**
     * 测试前提:
     * 测试场景: 测试支付库存补偿场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testFreezeProdInventoryPayCompensable() {

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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);
        FisDataPrepare.buildUnfreezeProdInventoryForSuccess(fisIntService);

        Mockito.doThrow(new BusinessException(CommonRspCode.TIMEOUT.getMessage(), CommonRspCode.TIMEOUT.getCode())).when(fisIntService).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));

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
        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(memberIntService, Mockito.times(2)).updateRookieStatusAndRetIsRookie(Mockito.anyString(), Mockito.anyInt());
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            Mockito.verify(fisIntService, Mockito.times(1)).unfreezeProdInventory(Mockito.any(UnfreezeProdInventoryReq.class));

            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }
    }

    /**
     * 测试前提:
     * 测试场景: 测试支付风控补偿场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testAddDayQuotaPayCompensable() {

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

        Mockito.doThrow(new BusinessException(CommonRspCode.TIMEOUT.getMessage(), CommonRspCode.TIMEOUT.getCode())).when(riskIntService).addDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));

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
        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(memberIntService, Mockito.times(2)).updateRookieStatusAndRetIsRookie(Mockito.anyString(), Mockito.anyInt());
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }
    }

    /**
     * 测试前提:
     * 测试场景: 测试支付团购库存补偿场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testFreezeGrouponInventoryPayCompensable() {

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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);

        Mockito.doThrow(new BusinessException(CommonRspCode.TIMEOUT.getMessage(), CommonRspCode.TIMEOUT.getCode())).when(marketIntService).freezeGrouponInventory(Mockito.any(FreezeGrouponInventoryReq.class));

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
        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(memberIntService, Mockito.times(2)).updateRookieStatusAndRetIsRookie(Mockito.anyString(), Mockito.anyInt());
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            Mockito.verify(fisIntService, Mockito.times(1)).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));
            Mockito.verify(marketIntService, Mockito.times(1)).unfreezeAndReturnGrouponInventory(Mockito.anyString());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }
    }

    /**
     * 测试前提:
     * 测试场景: 测试赎回风控补偿场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testAddDayQuotaRedeemCompensable() {

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

        Mockito.doThrow(new BusinessException(CommonRspCode.TIMEOUT.getMessage(), CommonRspCode.TIMEOUT.getCode())).when(riskIntService).addDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));

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

        try {
            fundRedeemService.redeemFund(redeemFundRequest, redeemFundResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(redeemFundRequest.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.REDEEM_FAILED, transOrder.getStatus().intValue());
        }
    }


    /**
     * 测试前提:
     * 测试场景: 测试赎回流动性补偿场景
     * 测试步骤:
     * 检查点:
     * 测试清理:
     */
    @Test
    public void testDeductionMonetaryInstrumentBalanceRedeemCompensable() {

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

        Mockito.doThrow(new BusinessException(CommonRspCode.TIMEOUT.getMessage(), CommonRspCode.TIMEOUT.getCode())).when(fisIntService).deductionMonetaryInstrumentBalance(Mockito.any(DeductionMonetaryInstrumentBalanceReq.class));

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

        try {
            fundRedeemService.redeemFund(redeemFundRequest, redeemFundResponse);
            Assert.fail();
        }
        catch (Exception e) {
            Mockito.verify(riskIntService, Mockito.times(1)).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
            Mockito.verify(fisIntService, Mockito.times(2)).deductionMonetaryInstrumentBalance(Mockito.any(DeductionMonetaryInstrumentBalanceReq.class));
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(redeemFundRequest.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.REDEEM_FAILED, transOrder.getStatus().intValue());
        }
    }
}
