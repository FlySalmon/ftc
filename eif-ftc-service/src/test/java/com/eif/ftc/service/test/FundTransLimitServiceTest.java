package com.eif.ftc.service.test;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.constant.WhiteListType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdInvenInfoResp;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoResp;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.cache.HadesClient;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.code.FTCRespCode;
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
import com.eif.ftc.service.test.prepare.*;
import com.eif.ftc.service.trans.FundBuyingService;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.trans.FundRedeemService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.risk.facade.response.QueryDayStatByProdResp;
import com.eif.risk.facade.response.QueryDayStatResp;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import org.apache.commons.lang.time.DateUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 10/12/16.
 */
@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,  MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransLimitServiceTest  extends WithAutoTransactionalJUnit4SpringContextRunner {

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

    @After
    public void after() {
        Mockito.reset(fisIntService);
        Mockito.reset(riskIntService);
        Mockito.reset(ofcIntService);
        Mockito.reset(memberIntService);
        Mockito.reset(transcoreIntService);
    }


    @Before
    public void before() {
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).acctOrderGen(Mockito.anyInt());
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).transOrderGen(Mockito.anyInt());
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).amcNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 23)).when(sequenceGenerator).taSeqNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 16)).when(sequenceGenerator).transAccGen();

        Mockito.reset(fisIntService);
        Mockito.reset(riskIntService);
        Mockito.reset(ofcIntService);
        Mockito.reset(memberIntService);
        Mockito.reset(transcoreIntService);

        CommonDataPrepare.buildConcurrentSuccess(redisConcurrentLock);
        FisDataPrepare.buildFreezeProdInventoryForSuccess(fisIntService);
        MemberDataPrepare.buildBatchUpdateRookieForSuccess(memberIntService);
        MarketDataPrepare.buildFreezeGrouponInventoryForSuccess(marketIntService);
        MarketDataPrepare.buildUnfreezeAndReturnGrouponInventoryForSuccess(marketIntService);
        RiskDataPrepare.buildDeductDayStat(riskIntService);
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);
        RiskDataPrepare.buildQueryDayAmountForSuccess(riskIntService);
    }

    /**
     * 测试前提: 非新手用户, 新手产品
     * 测试场景: 测试下单时新手产品的限制
     * 测试步骤: 1. 模拟一个非新手用户信息
     *          2. 模拟一个新手产品
     *          3. 下购买单
     * 检查点: 期待返回错误码00200010008(新手专享无法购买)
     * 测试清理: 无
     */
    @Test
    public void testNovicePacksBuyLimitForCreateFundTransOrder() {
        // 测试新手产品限制判断
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setIsNovicePacks(true);
        }
        else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setIsNovicePacks(true);
        }

//        queryProdTransInfoResp.setIsNovicePacks(true);
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryMemberInfoResponse queryMemberInfoResponse = MemberDataPrepare.buildBaseQueryMembersInfoByMemberNo();
        queryMemberInfoResponse.getUserInfoBean().setIsRookie(0);
        Mockito.doReturn(queryMemberInfoResponse).when(memberIntService).queryMembersInfoByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);

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

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_ROOKIE_LIMIT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testWhiteListBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        //新白名单产品判断
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setWhiteListType(WhiteListType.BUY_RESTRICTION);
            queryProdTransInfoResp.getNormalProdTransInfo().setWhiteListGroupId(Integer.valueOf(MemberDataPrepare.WHITE_LIST_GROUP_ID));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setWhiteListType(WhiteListType.BUY_RESTRICTION);
            queryProdTransInfoResp.getCurrentProdTransInfo().setWhiteListGroupId(Integer.valueOf(MemberDataPrepare.WHITE_LIST_GROUP_ID));
        }


        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        List<String> whiteList = new ArrayList<>();
        Mockito.doReturn(whiteList).when(memberIntService).queryWhiteList(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);

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

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_WHITE_GROUP_LIMIT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testIsEmployeeExclusiveBuyLimitForCreateFundTransOrder() {
        // 员工产品判断
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setIsEmployeeExclusive(true);
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setIsEmployeeExclusive(true);
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryMemberInfoResponse queryMemberInfoResponse = MemberDataPrepare.buildBaseQueryMembersInfoByMemberNo();
        queryMemberInfoResponse.getUserInfoBean().setIsStaff(0);
        Mockito.doReturn(queryMemberInfoResponse).when(memberIntService).queryMembersInfoByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);

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

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_STAFF_LIMIT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testProdTransTimeBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 定期产品募集时间判断
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setTransEndTime(DateUtils.addDays(new Date(), -1));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setTransEndTime(DateUtils.addDays(new Date(), -1));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

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

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_TIME_EXCEEDED.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testMinBuyAmtBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 小于单笔最小购买限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setMinBuyAmt(new BigDecimal(100));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setMinBuyAmt(new BigDecimal(100));
        }


        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(10));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_MIN_BUY_AMT_EXCEEDED.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testMaxBuyAmtBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 超过单笔限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setMaxBuyAmt(new BigDecimal(100));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setMaxBuyAmt(new BigDecimal(100));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(1000));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_MAX_BUY_AMT_EXCEEDED.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testPerIncAmtBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 不符合阶梯规则
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();


        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setPerIncAmt(new BigDecimal(3));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setPerIncAmt(new BigDecimal(3));
        }

//        queryProdTransInfoResp.setPerIncAmt(new BigDecimal(3));
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

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

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_INC_AMT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testPerUdbLimitAmtBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 单户单日购买最高金额/份额：用户购买产品的单日最高限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setPerUdbLimitAmt(new BigDecimal(1000));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setPerUdbLimitAmt(new BigDecimal(1000));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(1001));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testLimitBuyAmtBuyLimitForCreateFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 单人可购买的产品总共最高限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setLimitBuyAmt(new BigDecimal(1000));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setLimitBuyAmt(new BigDecimal(1000));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(1001));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();

        try {
            fundBuyingService.createFundBuyingOrder(request, response);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据不存在
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(request.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertNull(transOrder);
        }

    }

    @Test
    public void testTagTotalAmountBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);


        // 根据tag判断总限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();
        TagRules tagRules = new TagRules();
        tagRules.setPerUserBuyLimit(new BigDecimal(200));
        tagRules.setTagId(1L);

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setTagRules(tagRules);
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setTagRules(tagRules);
        }


//        queryProdTransInfoResp.setTagRules(tagRules);
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryDayStatResp queryDayStatResp = RiskDataPrepare.buildBaseQueryDayAmount();
        queryDayStatResp.setTagTotalCount(1);
        queryDayStatResp.setTagTotalAmount(new BigDecimal(100));
        Mockito.doReturn(queryDayStatResp).when(riskIntService).queryDayAmount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(java.util.Date.class), Mockito.any(TagRules.class));

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(101));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);

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
        bean.setAmount(new BigDecimal(101));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_RULE_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testDayPerUdbLimitAmtBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);


        // 单户单日购买最高金额/份额：用户购买产品的单日最高限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();


        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setPerUdbLimitAmt(new BigDecimal(200));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setPerUdbLimitAmt(new BigDecimal(200));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryDayStatResp queryDayStatResp = RiskDataPrepare.buildBaseQueryDayAmount();
        queryDayStatResp.setDayCount(1);
        queryDayStatResp.setDayAmount(new BigDecimal(100));
        Mockito.doReturn(queryDayStatResp).when(riskIntService).queryDayAmount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(java.util.Date.class), Mockito.any(TagRules.class));

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(101));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);

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
        bean.setAmount(new BigDecimal(101));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testTotalLimitBuyAmtBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 单人可购买的产品总共最高限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setLimitBuyAmt(new BigDecimal(200));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setLimitBuyAmt(new BigDecimal(200));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryDayStatResp queryDayStatResp = RiskDataPrepare.buildBaseQueryDayAmount();
        queryDayStatResp.setTotalCount(1);
        queryDayStatResp.setTotalAmount(new BigDecimal(100));
        Mockito.doReturn(queryDayStatResp).when(riskIntService).queryDayAmount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(java.util.Date.class), Mockito.any(TagRules.class));

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(101));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);

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
        bean.setAmount(new BigDecimal(101));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testDailyMaxBuyAmtBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 单人可购买的产品总共最高限额
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setDailyMaxBuyAmt(new BigDecimal(200));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setDailyMaxBuyAmt(new BigDecimal(200));
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

        QueryDayStatByProdResp queryDayStatByProdResp = RiskDataPrepare.buildBaseQueryDayStatByProd();
        queryDayStatByProdResp.setDayCount(1);
        queryDayStatByProdResp.setDayAmount(new BigDecimal(100));
        Mockito.doReturn(queryDayStatByProdResp).when(riskIntService).queryTodayStatByProd(Mockito.anyInt(), Mockito.anyLong());

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(101));
        request.setBizChannel(BizChannel.H5);
        request.setIsAdjusted(false);
        request.setTriggerReason(TriggerReason.NORMAL);
        request.setTrackingNo(UUIDGenerator.gen());
        request.setMerMemberNo(UUIDGenerator.gen());
        request.setOperatorNo(UUIDGenerator.gen());
        request.setRemark("test");

        CreateFundBuyingOrderResponse response = new CreateFundBuyingOrderResponse();
        fundBuyingService.createFundBuyingOrder(request, response);

        FisDataPrepare.buildQueryProdInvenInfoForSuccess(fisIntService);
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, false);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, true);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);

        PayFundBuyingOrderRequest payFundBuyingOrderRequest = new PayFundBuyingOrderRequest();
        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setAmount(new BigDecimal(101));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_BUY_TOTAL_DAY_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testSellableAmountBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 可售金额判断
        QueryProdInvenInfoResp queryProdInvenInfoResp = FisDataPrepare.buildBaseQueryProdInvenInfo();
        queryProdInvenInfoResp.setSellableAmount(new BigDecimal(100));
        Mockito.doReturn(queryProdInvenInfoResp).when(fisIntService).queryProdInvenInfo(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);

        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();

        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);


        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(101));
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
        TranscoreDataPrepare.buildCreateTransCoreOrderForSuccess(transcoreIntService, true);
        TranscoreDataPrepare.buildPaymentRoutingForSuccess(transcoreIntService, false);
        MemberDataPrepare.buildUpdateRookieStatusAndRetIsRookieForSuccess(memberIntService, false);
        RiskDataPrepare.buildAddDayStatForSuccess(riskIntService);

        PayFundBuyingOrderRequest payFundBuyingOrderRequest = new PayFundBuyingOrderRequest();
        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setAmount(new BigDecimal(101));
        bean.setCardNo("123");
        bean.setDebitMemberNo(UUIDGenerator.gen());
        bean.setCreditMemberNo(UUIDGenerator.gen());
        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
        paymentOptionBean.add(bean);
        payFundBuyingOrderRequest.setFundTransOrderNo(response.getFundTransOrderNo());
        payFundBuyingOrderRequest.setPayMethod(JSON.toJSONString(paymentOptionBean));
        payFundBuyingOrderRequest.setSmsControl(SmsStrategy.ALWAYS_SEND_SMS.getValue());

        PayFundBuyingOrderResponse payFundBuyingOrderResponse = new PayFundBuyingOrderResponse();

        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testSellableAccountBuyLimitForPayFundTransOrder() {
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 定期产品200人限制, 判断是否已经买过判断
        QueryProdInvenInfoResp queryProdInvenInfoResp = FisDataPrepare.buildBaseQueryProdInvenInfo();
        queryProdInvenInfoResp.setAvaliableHeadCount(0);
        Mockito.doReturn(queryProdInvenInfoResp).when(fisIntService).queryProdInvenInfo(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);

        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryProdTransInfo();


        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
        }

        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);


        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_NEW_PRODUCT_ID);
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

        try {
            fundBuyingService.payFundBuyingOrder(payFundBuyingOrderRequest, payFundBuyingOrderResponse);
            Assert.fail();
        }
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setFundTransOrderNo(response.getFundTransOrderNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.PAY_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testCloseTypeRedeemLimit() {
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
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);

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

        Mockito.reset(fisIntService);
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryCurrentProdTransInfo();
        queryProdTransInfoResp.setCloseType(ProductCloseType.CLOSE);
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);

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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_REDEEM_CLOSED.getCode(), be.getCode());
        }

    }

    @Test
    public void testMinSellAmtRedeemLimit() {
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
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);

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

        // 小于单笔赎回下限
        Mockito.reset(fisIntService);
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryCurrentProdTransInfo();
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setMinSellAmt(new BigDecimal(100));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setMinSellAmt(new BigDecimal(100));
        }
//        queryProdTransInfoResp.setMinSellAmt(new BigDecimal(100));
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);


        RedeemFundRequest redeemFundRequest = new RedeemFundRequest();
        redeemFundRequest.setBizChannel(BizChannel.ANDROID);
        redeemFundRequest.setFundTransAmount(new BigDecimal(99));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_MIN_REDEEM_AMT_EXCEEDED.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(redeemFundRequest.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.REDEEM_FAILED, transOrder.getStatus().intValue());
        }

    }

    @Test
    public void testMaxSellAmtRedeemLimit() {
        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(1000));
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
        bean.setAmount(new BigDecimal(1000));
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

        // 超过单笔赎回上限
        Mockito.reset(fisIntService);
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryCurrentProdTransInfo();
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setMaxSellAmt(new BigDecimal(100));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setMaxSellAmt(new BigDecimal(100));
        }
//        queryProdTransInfoResp.setMaxSellAmt(new BigDecimal(100));
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);


        RedeemFundRequest redeemFundRequest = new RedeemFundRequest();
        redeemFundRequest.setBizChannel(BizChannel.ANDROID);
        redeemFundRequest.setFundTransAmount(new BigDecimal(101));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_MAX_REDEEM_AMT_EXCEEDED.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(redeemFundRequest.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.REDEEM_FAILED, transOrder.getStatus().intValue());
        }
    }

    @Test
    public void testPerUdsLimitAmtRedeemLimit() {
        FisDataPrepare.buildQueryCurrentProdTransInfoForSuccess(fisIntService);
        MemberDataPrepare.buildQueryMembersInfoByMemberNoForSuccess(memberIntService);
        OfcDataPrepare.buildCreateBusinessOrderItemForSuccess(ofcIntService);
        RiskDataPrepare.buildRiskCheckTransIngForSuccess(riskIntService);
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);


        // 创建订单
        CreateFundBuyingOrderRequest request = new CreateFundBuyingOrderRequest();
        request.setProductId(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);
        request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
        request.setFundTransAmount(new BigDecimal(1000));
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
        bean.setAmount(new BigDecimal(1000));
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

        // 单户单日赎回最高金额/份额：用户赎回产品的单日最高限额
        Mockito.reset(fisIntService);
        QueryProdTransInfoV2Resp queryProdTransInfoResp = FisDataPrepare.buildBaseQueryCurrentProdTransInfo();
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            queryProdTransInfoResp.getNormalProdTransInfo().setPerUdsLimitAmt(new BigDecimal(50));
        }else
        {
            queryProdTransInfoResp.getCurrentProdTransInfo().setPerUdsLimitAmt(new BigDecimal(50));
        }
//        queryProdTransInfoResp.setPerUdsLimitAmt(new BigDecimal(50));
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(FisDataPrepare.NORMAL_CURRENT_SUC_PRODUCT_ID);

        Mockito.reset(riskIntService);
        RiskDataPrepare.buildGetRiskUserLockForSuccess(riskIntService);
        QueryDayStatResp queryDayStatResp = RiskDataPrepare.buildBaseQueryDayAmount();
        queryDayStatResp.setDayAmount(new BigDecimal(100));
        queryDayStatResp.setDayCount(1);
        Mockito.doReturn(queryDayStatResp).when(riskIntService).queryDayAmount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(java.util.Date.class), Mockito.any(TagRules.class));
        RiskDataPrepare.buildGetRiskUserLock(riskIntService);

        RedeemFundRequest redeemFundRequest = new RedeemFundRequest();
        redeemFundRequest.setBizChannel(BizChannel.ANDROID);
        redeemFundRequest.setFundTransAmount(new BigDecimal(51));
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
        catch (BusinessException be) {
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT.getCode(), be.getCode());
            // 验证单据失败
            FundTransOrder queryOrder = new FundTransOrder();
            queryOrder.setTrackingNo(redeemFundRequest.getTrackingNo());
            FundTransOrder transOrder = fundTransOrderMapper.selectOne(queryOrder);
            Assert.assertEquals(FundTransOrderStatus.REDEEM_FAILED, transOrder.getStatus().intValue());
        }

    }
}

