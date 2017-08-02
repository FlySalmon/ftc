package com.eif.ftc.service.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.eif.fis.facade.dto.ftc.InvestProperty;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.ExchangeType;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.TransferMode;
import com.eif.ftc.facade.fund.transfer.enumeration.TransferTransactionMode;
import com.eif.ftc.facade.fund.transfer.request.CalculateTransferPriceAndFeeRequest;
import com.eif.ftc.facade.fund.transfer.request.CancelTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.CheckAssetTransactionRuleRequest;
import com.eif.ftc.facade.fund.transfer.request.CreateTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CalculateTransferPriceAndFeeResponse;
import com.eif.ftc.facade.fund.transfer.response.CreateTransferorOrderResponse;
import com.eif.ftc.integration.contract.ContractIntService;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.test.prepare.CommonDataPrepare;
import com.eif.ftc.service.test.prepare.FisDataPrepare;
import com.eif.ftc.service.test.prepare.MemberDataPrepare;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.member.facade.pkg.constant.RiskLevel;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.risk.facade.bean.UserBlockingBean;
import com.eif.risk.facade.constant.UserBlockingType;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundTransferorServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Spy
    @Resource
    OfcIntService ofcIntService;

    @Spy
    @Resource
    FisIntService fisIntService;

    @Spy
    @Resource
    RiskIntService riskIntService;

    @Spy
    @Resource
    MemberIntService memberIntService;

    @Spy
    @Resource
    ContractIntService contractIntService;
    
    @Spy
    @Resource
    FundAccountService fundAccountService;

    @Spy
    @Resource
    MemberAssetService memberAssetService;

    @Spy
    @Resource
    RedisConcurrentLock redisConcurrentLock;

    @Spy
    @Resource
    SequenceGenerator sequenceGenerator;

    @InjectMocks
    @Autowired
	FundTransferorService fundTransferorService;

    @Autowired
    FundTransferorOrderMapper fundTransferorOrderMapper;
    
    @Before
    public void before() {
        Mockito.doReturn(UUIDGenerator.gen().substring(0, 23)).when(sequenceGenerator).taSeqNoGen(Mockito.anyString());
        CommonDataPrepare.buildConcurrentSuccess(redisConcurrentLock);
    }

	@After
	public void after() {
		Mockito.reset(fisIntService);
		Mockito.reset(riskIntService);
		Mockito.reset(ofcIntService);
		Mockito.reset(memberIntService);
		Mockito.reset(contractIntService);

		Mockito.reset(fundAccountService);
		Mockito.reset(memberAssetService);
	}

    @Test
    public void checkAssetTransactionRuleSuccTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11070.88"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(new BigDecimal("50"));
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(new BigDecimal("20.88"));
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));

    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	CheckAssetTransactionRuleRequest request = new CheckAssetTransactionRuleRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	fundTransferorService.checkAssetTransactionRule(request);
    }

    @Test
    public void checkAssetTransactionRuleHoldDaysNotEnoughTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11000"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(BigDecimal.ZERO);
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(BigDecimal.ZERO);
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	product.setInceptionDate(DateUtils.addDays(product.getInceptionDate(), 10));
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	CheckAssetTransactionRuleRequest request = new CheckAssetTransactionRuleRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	try {
    		fundTransferorService.checkAssetTransactionRule(request);
    		Assert.fail();
    	} catch (BusinessException be) {
    		// 最短持有天数不足
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH.getCode(), be.getCode());
        }
    }

    @Test
    public void checkAssetTransactionRuleMaturityDaysNotEnoughTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11000"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(BigDecimal.ZERO);
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(BigDecimal.ZERO);
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));

    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	product.setDueDate(DateUtils.addDays(product.getInceptionDate(), 20));
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	CheckAssetTransactionRuleRequest request = new CheckAssetTransactionRuleRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	try {
    		fundTransferorService.checkAssetTransactionRule(request);
    		Assert.fail();
    	} catch (BusinessException be) {
    		// 份额离到期天数不足
            Assert.assertEquals(FTCRespCode.ERR_FUND_TRANSFER_EXPIRED_NOT_ENOUGH.getCode(), be.getCode());
        }
    }
    
    @Test
    public void calculateTransferPriceAndFeeSuccTest() {
    	FundTotalAssetBean memberProductShare = new FundTotalAssetBean();
    	memberProductShare.setTotalAmount(new BigDecimal("11000"));
    	memberProductShare.setExchangeStatus(ExchangeType.NORMAL.getValue());
    	memberProductShare.setConfirmedAmount(new BigDecimal("10000"));
    	memberProductShare.setExpectBonusAmount(BigDecimal.ZERO);
    	memberProductShare.setExpectProfitAmount(new BigDecimal("1000"));
    	memberProductShare.setGrouponAmount(BigDecimal.ZERO);
    	memberProductShare.setUnconfirmedAmount(BigDecimal.ZERO);
    	Mockito.doReturn(memberProductShare).when(memberAssetService).getFundTotalAsset(Mockito.any(FundTotalAssetRequestBean.class));

    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	CalculateTransferPriceAndFeeRequest request = new CalculateTransferPriceAndFeeRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	request.setRate(FisDataPrepare.NORMAL_EXPECTED_RATE);
    	CalculateTransferPriceAndFeeResponse response = new CalculateTransferPriceAndFeeResponse();
    	fundTransferorService.calculateTransferPriceAndFee(request, response);
    	
    	Assert.assertNotNull(response.getExpectedAmount());
    	Assert.assertNotNull(response.getExpectedFee());
    	Assert.assertNotNull(response.getLimitation());
    }
    
    @Test
    public void createTransferorOrderSuccTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000130").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
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
		
		 // 验证单据存在
        FundTransferorOrder queryOrder = new FundTransferorOrder();
        queryOrder.setTrackingNo(request.getTrackingNo());
        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
        Assert.assertNotNull(transOrder);
    }

    @Test
    public void createTransferorOrderFailTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000131").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	request.setBizChannel(BizChannel.H5);
    	request.setProductId(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	request.setRate(FisDataPrepare.NORMAL_EXPECTED_RATE);
    	request.setTrackingNo(UUIDGenerator.gen());
    	request.setTransferMode(TransferMode.YIELDS_MODE);
    	request.setTransferTransactionMode(TransferTransactionMode.BUYOUT_MODE);
    	request.setExpectedAmount(new BigDecimal("10050.82"));
    	request.setExpectedFee(new BigDecimal("20"));
		CreateTransferorOrderResponse response = new CreateTransferorOrderResponse();
		try {
			fundTransferorService.createTransferorOrder(request, response);
    		Assert.fail();
		} catch (BusinessException be) {
			 // 验证单据不存在
	        FundTransferorOrder queryOrder = new FundTransferorOrder();
	        queryOrder.setTrackingNo(request.getTrackingNo());
	        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
	        Assert.assertNull(transOrder);
		}
    }

    @Test
    public void cancelTransferorOrderSuccTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000134").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
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
		
//		FundTransferorOrder updateOrder = new FundTransferorOrder();
//		updateOrder.setFundTransferorOrderNo(response.getFundTransferorOrderNo());
//		updateOrder.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
//		FundTransferorOrderExample example = new FundTransferorOrderExample();
//		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
//		criteria.andFundTransferorOrderNoEqualTo(response.getFundTransferorOrderNo());
//		fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		
		Mockito.doNothing().when(fisIntService).cancelMktProduct(Mockito.anyLong());

		Mockito.doNothing().when(memberAssetService).unfreezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());;

    	ProdInfo product1 = FisDataPrepare.buildSecMktProductCommonInfo();
    	Mockito.doReturn(product1).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	
		CancelTransferorOrderRequest request1 = new CancelTransferorOrderRequest();
		request1.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
		
		fundTransferorService.cancelTransferorOrder(request1);
		
		// 验证单据状态
        FundTransferorOrder queryOrder = new FundTransferorOrder();
        queryOrder.setBusinessOrderItemNo(request1.getBusinessOrderItemNo());
        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
        Assert.assertEquals(transOrder.getStatus().intValue(), FundTransferorOrderStatus.CANCEL);
    }

    @Test
    public void cancelTransferorOrderFailTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000135").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
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
		updateOrder.setStatus(FundTransferorOrderStatus.TRANSFERING);
//		updateOrder.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessOrderItemNoEqualTo(response.getBusinessOrderItemNo());
		fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		

		Mockito.doNothing().when(memberAssetService).unfreezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

//    	Mockito.doNothing().when(fisIntService).cancelMktProduct(Mockito.anyLong());
    	
		CancelTransferorOrderRequest request1 = new CancelTransferorOrderRequest();
		request1.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
		
		try {
			fundTransferorService.cancelTransferorOrder(request1);
			Assert.fail();
		} catch (Exception be) {
			// 验证单据状态
	        FundTransferorOrder queryOrder = new FundTransferorOrder();
	        queryOrder.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
	        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
	        Assert.assertNotEquals(transOrder.getStatus().intValue(), FundTransferorOrderStatus.NEED_TRANSFER);
		}
    }

    @Test
    public void handleRiskLockUserMessageSuccTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000136").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
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
		
		Map<Long, ProdInfo> productMap = new HashMap<>();
		productMap.put(product.getProductId(), product);
    	Mockito.doReturn(productMap).when(fisIntService).queryProductCommonInfos(Mockito.anySet());

    	List<Long> li = null;
		Mockito.doReturn(li).when(fisIntService).cancelMktProducts(Mockito.anySet());

		Mockito.doNothing().when(memberAssetService).unfreezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());
		
    	UserBlockingBean blockingBean = new UserBlockingBean();
    	Set<String> memberNos = new HashSet<>();
    	memberNos.add(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	blockingBean.setBlockType(UserBlockingType.BLACKLIST);
    	blockingBean.setMemberNos(memberNos);
    	
    	fundTransferorService.handleRiskLockUserMessage(blockingBean);
    	
    	// 验证单据状态
        FundTransferorOrder queryOrder = new FundTransferorOrder();
        queryOrder.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
        Assert.assertEquals(transOrder.getStatus().intValue(), FundTransferorOrderStatus.CANCEL_BY_RISK);
    }

    @Test
    public void handleRiskLockUserMessageFailTest() {
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
    	fundAccountBean.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	fundAccountBean.setTransactionAccount(sequenceGenerator.transAccGen());
    	fundAccountBean.setFundAccountNo(sequenceGenerator.amcNoGen(String.valueOf(FundTransType.TRANSFEREE)));
    	fundAccountBean.setAccountStatus(FundAcctStatus.TA_SUCCESS);
    	Mockito.doReturn(fundAccountBean).when(fundAccountService).getFundAccountByMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	
    	SecMarketRule marketRule = FisDataPrepare.buildSecMktProductRule();
    	Mockito.doReturn(marketRule).when(fisIntService).queryMktProductTransactionRule(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);
    	
    	ProdInfo product = FisDataPrepare.buildFirstProductCommonInfo();
    	Mockito.doReturn(product).when(fisIntService).queryProductCommonInfo(FisDataPrepare.NORMAL_SUC_PRODUCT_ID);

    	Mockito.doReturn("16111114170007000137").when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransferorOrder.class), Mockito.anyString());
    	
    	MemberIdentityBean member = new MemberIdentityBean();
    	member.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	member.setRealName("你好");
    	member.setCertificationType(2);
    	member.setCertificationNo("11111111111111111");
    	member.setLoginAlias("你好");
    	member.setRiskLevel(RiskLevel.LEVEL_2);
    	member.setVerifiedMobile("13500001111");
    	Map<String, MemberIdentityBean> memberMap = new HashMap<>();
    	memberMap.put(MemberDataPrepare.NORMAL_SUC_MEMBER_NO, member);
    	Mockito.doReturn(memberMap).when(memberIntService).getMemberByMemberNos(Mockito.anySet());

    	Mockito.doNothing().when(memberAssetService).freezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());

    	CreateTransferorOrderRequest request = new CreateTransferorOrderRequest();
    	request.setMemberNo(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
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

    	Mockito.doReturn(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID).when(fisIntService).createMktProduct(
    			Mockito.anyString(), Mockito.anyLong(), Mockito.anyListOf(InvestProperty.class), 
    			Mockito.any(Date.class), Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class));
    	
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		updateOrder.setFundTransferorOrderNo(response.getFundTransferorOrderNo());
		updateOrder.setStatus(FundTransferorOrderStatus.TRANSFERING);
//		updateOrder.setMktProductId(FisDataPrepare.NORMAL_SEC_MKT_SUC_PRODUCT_ID);
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(response.getFundTransferorOrderNo());
		fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		
		Map<Long, ProdInfo> productMap = new HashMap<>();
		productMap.put(product.getProductId(), product);
    	Mockito.doReturn(productMap).when(fisIntService).queryProductCommonInfos(Mockito.anySet());

    	List<Long> li = null;
		Mockito.doReturn(li).when(fisIntService).cancelMktProducts(Mockito.anySet());

		Mockito.doNothing().when(memberAssetService).unfreezeMemberProductAsset(Mockito.anyString(), Mockito.anyLong());
		
    	UserBlockingBean blockingBean = new UserBlockingBean();
    	Set<String> memberNos = new HashSet<>();
    	memberNos.add(MemberDataPrepare.NORMAL_SUC_MEMBER_NO);
    	blockingBean.setBlockType(UserBlockingType.BLACKLIST);
    	blockingBean.setMemberNos(memberNos);
    	
    	fundTransferorService.handleRiskLockUserMessage(blockingBean);
    	
    	// 验证单据状态
        FundTransferorOrder queryOrder = new FundTransferorOrder();
        queryOrder.setBusinessOrderItemNo(response.getBusinessOrderItemNo());
        FundTransferorOrder transOrder = fundTransferorOrderMapper.selectOne(queryOrder);
        Assert.assertNotEquals(transOrder.getStatus().intValue(), FundTransferorOrderStatus.CANCEL_BY_RISK);
    }
    
}
