package com.eif.ftc.service.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.eif.fis.facade.dto.ftc.InvestProperty;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.ExchangeType;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.TransferMode;
import com.eif.ftc.facade.fund.transfer.enumeration.TransferTransactionMode;
import com.eif.ftc.facade.fund.transfer.request.CreateTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.CreateTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.PayTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CreateTransfereeOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.CreateTransferorOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.PayTransfereeOrderResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.test.prepare.CommonDataPrepare;
import com.eif.ftc.service.test.prepare.FisDataPrepare;
import com.eif.ftc.service.test.prepare.MemberDataPrepare;
import com.eif.ftc.service.transfer.FundTransfereeService;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.member.facade.pkg.constant.RiskLevel;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.request.RoutePaymentProviderInfoRequest;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransfereeServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Mock
    DefaultRMQProducer producer;

    @Spy
    @Resource
    private FisIntService fisIntService;

    @Spy
    @Resource
    FundAccountService fundAccountService;

    @Spy
    @Resource
    MemberAssetService memberAssetService;

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
    RedisConcurrentLock redisConcurrentLock;

    @Spy
    @Resource
    SequenceGenerator sequenceGenerator;

    @InjectMocks
    @Autowired
	FundTransferorService fundTransferorService;

    @InjectMocks
    @Autowired
	FundTransfereeService fundTransfereeService;

    @Autowired
    FundTransferorOrderMapper fundTransferorOrderMapper;

    @Autowired
    FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Before
    public void before() {
        Mockito.doReturn(UUIDGenerator.gen()).when(sequenceGenerator).amcNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 23)).when(sequenceGenerator).taSeqNoGen(Mockito.anyString());
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 16)).when(sequenceGenerator).transAccGen();
        
        CommonDataPrepare.buildConcurrentSuccess(redisConcurrentLock);
    }

	@After
	public void after() {
		Mockito.reset(fisIntService);
		Mockito.reset(fundTransfereeService);
		Mockito.reset(riskIntService);
		Mockito.reset(ofcIntService);
		Mockito.reset(memberIntService);
		Mockito.reset(transcoreIntService);
		Mockito.reset(fundAccountService);
		Mockito.reset(memberAssetService);
	}

    @Test
    public void createTransfereeOrderSuccTest() {
    	FundAccountBean fundAccountBean = new FundAccountBean();
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	
    	Mockito.doNothing().when(riskIntService).transfereeRiskCheck(Mockito.any(FundTransfereeOrder.class), Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());

    	ProdInfo product = FisDataPrepare.buildSecMktProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	
    	Mockito.doReturn("16120714170007000170").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransfereeOrder.class), Mockito.anyString());
    	
    	CreateTransfereeOrderRequest request = new CreateTransfereeOrderRequest();
    	request.setBizChannel(BizChannel.IOS);
    	request.setFundTransferAmount(new BigDecimal("100500"));
    	request.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	request.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	request.setTrackingNo(UUIDGenerator.gen());
    	CreateTransfereeOrderResponse response = new CreateTransfereeOrderResponse();
    	fundTransfereeService.createTransfereeOrder(request, response);
    	
    	Assert.assertNotNull(response.getBusinessOrderItemNo());
    	Assert.assertNotNull(response.getFundTransfereeOrderNo());
    }

    @Test
    public void createTransfereeOrderFailTest() {
    	FundAccountBean fundAccountBean = new FundAccountBean();
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.FROZEN);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	
    	Mockito.doNothing().when(riskIntService).transfereeRiskCheck(Mockito.any(FundTransfereeOrder.class), Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());

    	ProdInfo product = FisDataPrepare.buildSecMktProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	
    	Mockito.doReturn("16120714170007000171").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransfereeOrder.class), Mockito.anyString());
    	
    	CreateTransfereeOrderRequest request = new CreateTransfereeOrderRequest();
    	request.setBizChannel(BizChannel.IOS);
    	request.setFundTransferAmount(new BigDecimal("100500"));
    	request.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	request.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	request.setTrackingNo(UUIDGenerator.gen());
    	CreateTransfereeOrderResponse response = new CreateTransfereeOrderResponse();
    	try {
        	fundTransfereeService.createTransfereeOrder(request, response);
    		Assert.fail();
		} catch (BusinessException be) {
			// 账户状态不正确
			Assert.assertEquals(FTCRespCode.ERR_FUND_ACCT_STATUS.getCode(), be.getCode());
		}
    }

    @Test
    public void payTransfereeOrderWithoutOTPSuccTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11070.88"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(new BigDecimal("50"));
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(new BigDecimal("20.88"));
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));

    	FundAccountBean fundAccountBean = new FundAccountBean();
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000172").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	request.setBizChannel(BizChannel.H5);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	request.setRate(FisDataPrepare.NORMAL_EXPECTED_RATE);
    	request.setTrackingNo(UUIDGenerator.gen());
    	request.setTransferMode(TransferMode.YIELDS_MODE);
    	request.setTransferTransactionMode(TransferTransactionMode.BUYOUT_MODE);
    	request.setExpectedAmount(new BigDecimal("10064.82"));
    	request.setExpectedFee(new BigDecimal("50"));
		CreateTransferorOrderResponse response = new CreateTransferorOrderResponse();
		fundTransferorService.createTransferorOrder(request, response);
		
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		updateOrder.setFundTransferorOrderNo(response.getFundTransferorOrderNo());
//		updateOrder.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
		updateOrder.setTransferAgreementNo("10000");
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(response.getFundTransferorOrderNo());
		fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
		
		FundAccountBean fundAccountBean2 = new FundAccountBean();
		fundAccountBean2.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
		fundAccountBean2.setTransactionAccount(sequenceGenerator.transAccGen());
		fundAccountBean2.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
		fundAccountBean2.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean2).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	
    	Mockito.doNothing().when(riskIntService).transfereeRiskCheck(Mockito.any(FundTransfereeOrder.class), Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());

    	ProdInfo mktProduct = FisDataPrepare.buildSecMktProductCommonInfo();
    	Mockito.doReturn(mktProduct).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	
    	Mockito.doReturn("16120714170007000173").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransfereeOrder.class), Mockito.anyString());
    	
    	CreateTransfereeOrderRequest request2 = new CreateTransfereeOrderRequest();
    	request2.setBizChannel(BizChannel.IOS);
    	request2.setFundTransferAmount(new BigDecimal("10114.82"));
    	request2.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	request2.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	request2.setTrackingNo(UUIDGenerator.gen());
    	CreateTransfereeOrderResponse response2 = new CreateTransfereeOrderResponse();
    	fundTransfereeService.createTransfereeOrder(request2, response2);
    	
    	PayTransfereeOrderRequest request3 = new PayTransfereeOrderRequest();
    	request3.setFundTransfereeOrderNo(response2.getFundTransfereeOrderNo());
    	List<TransactionPaymentOptionBean> paymentOptions = new ArrayList<>();
    	TransactionPaymentOptionBean payment = new TransactionPaymentOptionBean();
    	payment.setAmount(new BigDecimal("10114.82"));
    	payment.setCreditMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	payment.setDebitMemberNo("00000000000000000000000000000000");
    	payment.setIdentifierType(1);
    	payment.setPayPartnerNo("EIF");
    	payment.setPaymentInstrumentType(PaymentInstrumentType.MEMBER_BALANCE);
    	payment.setPaymentInstrumentNo("QAI00000000000001005");
    	paymentOptions.add(payment);
    	request3.setPayMethod(JSON.toJSONString(paymentOptions));
    	request3.setDiscountAmount(BigDecimal.ZERO);
    	request3.setSmsControl(0);
    	
    	Mockito.doNothing().when(fisIntService).freezeMktProductInventory(
    			Mockito.anyLong(), Mockito.any(BigDecimal.class), Mockito.anyString(),Mockito.any(Date.class));

    	Mockito.doReturn(true).when(memberIntService).updateRookieStatusAndRetIsRookie(
    			Mockito.anyString(),Mockito.anyInt());

    	PayTransfereeOrderResponse response3 = new PayTransfereeOrderResponse();
    	fundTransfereeService.payTransfereeOrder(request3, response3);
    	
    	Assert.assertNotNull(response3.getBusinessOrderItemNo());
    	Assert.assertNotNull(response3.getFundTransfereeOrderNo());
    	
    	// 验证转让单
        FundTransferorOrder queryOrder1 = new FundTransferorOrder();
        queryOrder1.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
        FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder1);
        Assert.assertEquals(transferorOrder.getStatus().longValue(), FundTransferorOrderStatus.TRANSFERING);

    	// 验证受让单
        FundTransfereeOrder queryOrder2 = new FundTransfereeOrder();
        queryOrder2.setBusinessOrderItemNo(response3.getBusinessOrderItemNo());
        FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder2);
        Assert.assertEquals(transfereeOrder.getStatus().longValue(), FundTransfereeOrderStatus.PAYING);
    }

    @Test
    public void payTransfereeOrderWithOTPSuccTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11070.88"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(new BigDecimal("50"));
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(new BigDecimal("20.88"));
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));

    	FundAccountBean fundAccountBean = new FundAccountBean();
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000270").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID + 1).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEROR_MEMBER_NO);
    	request.setBizChannel(BizChannel.H5);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	request.setRate(FisDataPrepare.NORMAL_EXPECTED_RATE);
    	request.setTrackingNo(UUIDGenerator.gen());
    	request.setTransferMode(TransferMode.YIELDS_MODE);
    	request.setTransferTransactionMode(TransferTransactionMode.BUYOUT_MODE);
    	request.setExpectedAmount(new BigDecimal("10064.82"));
    	request.setExpectedFee(new BigDecimal("50"));
		CreateTransferorOrderResponse response = new CreateTransferorOrderResponse();
		fundTransferorService.createTransferorOrder(request, response);
		
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		updateOrder.setFundTransferorOrderNo(response.getFundTransferorOrderNo());
//		updateOrder.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID + 1);
		updateOrder.setTransferAgreementNo("10001");
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(response.getFundTransferorOrderNo());
		fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
		
		FundAccountBean fundAccountBean2 = new FundAccountBean();
		fundAccountBean2.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
		fundAccountBean2.setTransactionAccount(sequenceGenerator.transAccGen());
		fundAccountBean2.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
		fundAccountBean2.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean2).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	
    	Mockito.doNothing().when(riskIntService).transfereeRiskCheck(Mockito.any(FundTransfereeOrder.class), Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());

    	ProdInfo mktProduct = FisDataPrepare.buildSecMktProductCommonInfo();
    	mktProduct.setProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID + 1);
    	Mockito.doReturn(mktProduct).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID + 1);
    	
    	Mockito.doReturn("16120714170007000270").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransfereeOrder.class), Mockito.anyString());
    	
    	CreateTransfereeOrderRequest request2 = new CreateTransfereeOrderRequest();
    	request2.setBizChannel(BizChannel.IOS);
    	request2.setFundTransferAmount(new BigDecimal("10114.82"));
    	request2.setMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	request2.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID + 1);
    	request2.setTrackingNo(UUIDGenerator.gen());
    	CreateTransfereeOrderResponse response2 = new CreateTransfereeOrderResponse();
    	fundTransfereeService.createTransfereeOrder(request2, response2);
    	
    	PayTransfereeOrderRequest request3 = new PayTransfereeOrderRequest();
    	request3.setFundTransfereeOrderNo(response2.getFundTransfereeOrderNo());
    	List<TransactionPaymentOptionBean> paymentOptions = new ArrayList<>();
    	TransactionPaymentOptionBean payment = new TransactionPaymentOptionBean();
    	payment.setAmount(new BigDecimal("10114.82"));
    	payment.setCreditMemberNo(MemberDataPrepare.NORMAL_TRNASFEREE_MEMBER_NO);
    	payment.setDebitMemberNo("00000000000000000000000000000000");
    	payment.setIdentifierType(1);
    	payment.setPayPartnerNo("EIF");
    	payment.setPaymentInstrumentType(PaymentInstrumentType.DCP);
    	payment.setPaymentInstrumentNo("QAI00000000000001005");
    	paymentOptions.add(payment);
    	request3.setPayMethod(JSON.toJSONString(paymentOptions));
    	request3.setDiscountAmount(BigDecimal.ZERO);
    	request3.setSmsControl(0);
    	
    	Mockito.doNothing().when(fisIntService).freezeMktProductInventory(
    			Mockito.anyLong(), Mockito.any(BigDecimal.class), Mockito.anyString(),Mockito.any(Date.class));

    	Mockito.doReturn(true).when(memberIntService).updateRookieStatusAndRetIsRookie(
    			Mockito.anyString(),Mockito.anyInt());
    	
    	RoutePaymentProviderInfoResponse paymentRoutingResp = new RoutePaymentProviderInfoResponse();
    	paymentRoutingResp.setSmsStrategy(SmsStrategy.ALWAYS_SEND_SMS);
    	paymentRoutingResp.setRespCode("1000");
    	Mockito.doReturn(paymentRoutingResp).when(transcoreIntService).paymentRouting(Mockito.any(RoutePaymentProviderInfoRequest.class));
    	
    	CreateTransResponse transResponse = new CreateTransResponse();
    	transResponse.setRespCode("1000");
    	transResponse.setTransactionStatus(TransactionStatus.AUTH_PENDING);
    	transResponse.setTransNo("1000000001");
    	transResponse.setAuthWaitingPaymentNo("QAI00000000000001005");
    	Mockito.doReturn(transResponse).when(transcoreIntService).createTransCoreOrder(
    			Mockito.any(FundTransfereeOrder.class), Mockito.anyString(), 
    			Mockito.anyString(), Mockito.anyListOf(TransactionPaymentOptionBean.class), Mockito.anyString(),
    			Mockito.anyString(), Mockito.any(RoutePaymentProviderInfoResponse.class));
    	
    	PayTransfereeOrderResponse response3 = new PayTransfereeOrderResponse();
    	fundTransfereeService.payTransfereeOrder(request3, response3);

    	Assert.assertNotNull(response3.getBusinessOrderItemNo());
    	Assert.assertNotNull(response3.getFundTransfereeOrderNo());
    	
    	// 验证转让单
        FundTransferorOrder queryOrder1 = new FundTransferorOrder();
        queryOrder1.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
        FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder1);
        Assert.assertEquals(transferorOrder.getStatus().longValue(), FundTransferorOrderStatus.NEED_TRANSFER);

    	// 验证受让单
        FundTransfereeOrder queryOrder2 = new FundTransfereeOrder();
        queryOrder2.setBusinessOrderItemNo(response3.getBusinessOrderItemNo());
        FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder2);
        Assert.assertEquals(transfereeOrder.getStatus().longValue(), FundTransfereeOrderStatus.AUTH_PENDING);
    }

    @Test
    @Ignore
    public void payTransfereeOrderFailTest() {
    	
    }

    @Test
    @Ignore
    public void resumePayTransfereeOrderSuccTest() {
    	
    }

    @Test
    @Ignore
    public void resumePayTransfereeOrderFailTest() {
    	
    }

    @Test
    @Ignore
    public void handleCreateTranscoreMessageSuccTest() {
    	
    }

    @Test
    @Ignore
    public void handleTranscoreStatusMessageSuccTest() {
    	
    }

    @Test
    @Ignore
    public void handleTranscoreStatusMessageExchangeSuccTest() {
    	
    }

    @Test
    @Ignore
    public void handleTranscoreStatusMessageExchangeFailTest() {
    	
    }

    @Test
    @Ignore
    public void handleSignContractResultSuccTest() {
    	
    }

    @Test
    @Ignore
    public void handleTranscoreReportEventResultSuccTest() {
    	
    }
    
}
