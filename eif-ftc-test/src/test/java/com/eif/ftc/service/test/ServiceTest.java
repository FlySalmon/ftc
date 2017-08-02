//package com.eif.ftc.service.test;
//
//import com.alibaba.fastjson.JSON;
//import com.eif.framework.common.utils.pkg.BaseResponse;
//import com.eif.ftc.dal.bean.FundDetailUpdateBean;
//import com.eif.ftc.dal.bean.FundTotalUpdateBean;
//import com.eif.ftc.dal.bean.MemberAssetUpdateBean;
//import com.eif.ftc.dal.dao.FundDetailAlterationMapper;
//import com.eif.ftc.dal.dao.FundDetailMapper;
//import com.eif.ftc.dal.dao.FundTransOrderMapper;
//import com.eif.ftc.dal.model.FundTransOrder;
//import com.eif.ftc.facade.FundCheckingJobFacade;
//import com.eif.ftc.facade.FundOperFacade;
//import com.eif.ftc.facade.amc.AMCFacade;
//import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
//import com.eif.ftc.facade.fund.amc.request.AddOfflineAssetRequest;
//import com.eif.ftc.facade.fund.amc.request.AssetAndFundInfoRequest;
//import com.eif.ftc.facade.fund.amc.request.QueryOfflineAssetRequest;
//import com.eif.ftc.facade.fund.amc.response.AssetAndFundInfoResponse;
//import com.eif.ftc.facade.fund.amc.response.OfflineAssetResponse;
//import com.eif.ftc.facade.fund.job.request.GetFundTransOrderListForCheckingRequest;
//import com.eif.ftc.facade.fund.job.response.GetFundTransOrderListForCheckingResponse;
//import com.eif.ftc.facade.fund.oper.request.LargeInvestmentClientsRequest;
//import com.eif.ftc.facade.fund.oper.response.LargeInvestmentClientsResponse;
//
//import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath*:/spring/applicationContext-disconf.xml",
//        "classpath*:/spring/applicationContext-dal.xml",
//        "classpath*:/spring/applicationContext-dubbo-consumer.xml",
//        "classpath*:/spring/applicationContext-rocketmq-producer.xml",
//        "classpath*:/spring/applicationContext-integration.xml",
//        "classpath*:/spring/applicationContext-service.xml",
//        "classpath*:/spring/applicationContext-biz.xml",
//        "classpath*:/spring/applicationContext-util.xml",
//        "classpath*:/spring/applicationContext-dubbo-provider.xml"})
//public class ServiceTest {
//    @Autowired
//    FundCheckingJobFacade fundCheckingJobFacade;
//
//
//    @Autowired
//    FundDetailMapper fundDetailMapper;
//
//
//    @Autowired
//    FundDetailAlterationMapper fundDetailAlterationMapper;
//
//    @Autowired
//    AMCFacade amcFacade;
//
//    @Autowired
//    FundOperFacade fundOperFacade;
//
//    @Autowired
//    FundTransOrderMapper fundTransOrderMapper;
//
////    @Autowired
////    FundTransOrderMapper fundTransOrderMapper;
////
////    @Autowired
////    FundAcctOrderMapper fundAcctOrderMapper;
////
////
////    @Autowired
////    FundAcctOrderService fundAccountService;
////
////
////    @Autowired
////    FundAmcDataService fundAmcDataService;
////
////    @Autowired
////    FundQueryService fundQueryService;
////
////    @Autowired
////    MapperFacade mapperFacade;
////
////    @Autowired
////    FundTransFacade fundTransFacade;
//
//    @Test
//    public void test() {
//        FundTransOrder fundTransOrder = new FundTransOrder();
//        fundTransOrder.setId(6L);
//        fundTransOrder.setRefFundAccountNo("test");
////        int effectedRow = fundTransOrderMapper.updateByPrimaryKeySelective(fundTransOrder);
//
//    }
//
//    @Test
//    public void applyFundAccountTest() {
////        // 创建用户
////        OpenFundAccountRequest openFundAccountRequest = new OpenFundAccountRequest();
////        openFundAccountRequest.setProductId(RandomUtils.nextLong());
////        openFundAccountRequest.setBizChannel(BizChannel.ANDROID);
////        openFundAccountRequest.setMemberNo("1");
////        OpenFundAccountResponse openFundAccountResponse = new OpenFundAccountResponse();
//////        fundAccountService.openFundAccount(openFundAccountRequest, openFundAccountResponse);
////
////        // 生成TA单据
////        TAJobPageableRequest request = new TAJobPageableRequest();
////        request.setStartDt(DateUtils.addDays(new Date(), -5));
////        request.setEndDt(DateUtils.addDays(new Date(), 5));
////        request.setPageNum(1);
////        request.setPageSize(1);
////        taFundService.applyFundAccount(request);
////
////        FundAcctOrderExample example = new FundAcctOrderExample();
////        FundAcctOrderExample.Criteria criteria = example.createCriteria();
////        List<Integer> statList = new ArrayList<Integer>();
////        statList.add(FundAcctOrderStatus.TA_PROCESSING);
////        criteria.andStatusIn(statList);
////        List<FundAcctOrder> r = fundAcctOrderMapper.selectByExample(example);
////
////        // 模拟TA确认
////        TAFundCreateAcctCfmOrderRequest taFundCreateAcctCfmOrderRequest = new TAFundCreateAcctCfmOrderRequest();
////        List<TAFundCreateAcctCfmOrderBean> taFundCreateAcctCfmOrderList = new ArrayList<TAFundCreateAcctCfmOrderBean>();
////        TAFundCreateAcctCfmOrderBean bean = new TAFundCreateAcctCfmOrderBean();
////        bean.setTaAccountId(String.valueOf(RandomUtils.nextInt()));
////        bean.setAppSheetSerialNo(r.get(0).getRefAppSheetSerialNo());
////        bean.setBranchCode("000009");
////        bean.setDistributorCode("000009");
////        bean.setBusinessCode("001");
////        bean.setReturnCode("000");
////        bean.setTransactionAccountId("");
////        bean.setTransactionCfmDate("");
////        bean.setTaSerialNo("");
////        bean.setTransactionTime("");
////        bean.setTransactionDate("");
////
////        taFundCreateAcctCfmOrderList.add(bean);
////        taFundCreateAcctCfmOrderRequest.setTaFundCreateAcctCfmOrderList(taFundCreateAcctCfmOrderList);
////
////        taFundService.applyFundAccountCfm(taFundCreateAcctCfmOrderRequest);
//    }
//
//    @Test
//    public void fundNormalSubscribeTest() {
////        // 认购
////        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
////        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setAmount(new BigDecimal(500));
////        bean.setCardNo("123");
////        bean.setDebitMemberNo(UUIDGenerator.gen());
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
////        paymentOptionBean.add(bean);
////        ForegroundBuyFundRequest req1 = new ForegroundBuyFundRequest();
////        req1.setBizChannel(BizChannel.ANDROID);
////        req1.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req1.setFundTransAmount(new BigDecimal(500));
////        req1.setIsAdjusted(false);
////        req1.setMemberNo("4");
////        req1.setOperatorNo(UUIDGenerator.gen());
////        req1.setOrderNo(UUIDGenerator.gen());
////        req1.setTrackingNo(UUIDGenerator.gen());
////        req1.setPayMethod(JSON.toJSONString(paymentOptionBean));
////        req1.setMerMemberNo(UUIDGenerator.gen());
////        req1.setPayMethodDesc("test");
////        req1.setProductId(2L);
////        req1.setRemark("test");
////        req1.setTriggerReason(TriggerReason.NORMAL);
////
////        String bizOrderNo = fundTransFacade.foregroundBuyFund(req1).getFundTransOrderNo();
////
////        // 支付成功
////        FundPaymentCallbackMessage message = new
////                FundPaymentCallbackMessage();
////        message.setTransStatus(TransactionStatus.SETTLED);
////        FundPaymentCallbackBizInfo bizInfo = new
////                FundPaymentCallbackBizInfo();
////        bizInfo.setBizOrderNo(bizOrderNo);
////        bizInfo.setBizTransType(FundTransType.PURCHASING);
////        List<FundPaymentCallbackBizInfo> list = new
////                ArrayList<FundPaymentCallbackBizInfo>();
////        list.add(bizInfo);
////        message.setFundPaymentCallbackBizInfoList(list);
////        fundTransService.consumeTransCoreStatus(message);
////
////        // TA 认购申请
////        TAJobPageableRequest request = new TAJobPageableRequest();
////        request.setStartDt(DateUtils.addDays(new Date(), -5));
////        request.setEndDt(DateUtils.addDays(new Date(), 5));
////        request.setPageNum(1);
////        request.setPageSize(1);
////        Page<TAFundSubscribingOrderBean> p = taFundService.applyFundSubscribing(request);
////
////        // 模拟TA确认
////        TAFundSubscribingCfmOrderRequest taFundPurchasingCfmOrderRequest = new TAFundSubscribingCfmOrderRequest();
////        List<TAFundSubscribingCfmOrderBean> taFundPurchasingCfmOrderList = new ArrayList<TAFundSubscribingCfmOrderBean>();
////        TAFundSubscribingCfmOrderBean taBean = new TAFundSubscribingCfmOrderBean();
////        taFundPurchasingCfmOrderList.add(taBean);
////        taFundPurchasingCfmOrderRequest.setTaFundSubscribingCfmOrderList(taFundPurchasingCfmOrderList);
////        taBean.setTransactionDate("");
////        taBean.setTransactionTime("");
////        taBean.setApplicationAmount(new BigDecimal(500));
////        taBean.setTransactionCfmDate("");
////        taBean.setTransactionAccountId("4");
////        taBean.setTaSerialNo("");
////        taBean.setShareClass("");
////        taBean.setReturnCode("0000");
////        taBean.setNav(BigDecimal.ZERO);
////        taBean.setDownloadDate("");
////        taBean.setAppSheetSerialNo(p.get(0).getAppSheetSerialNo());
////        taBean.setBranchCode("");
////        taBean.setBusinessCode("");
////        taBean.setConfirmedAmount(new BigDecimal(500));
////        taBean.setDistributorCode("");
////        taBean.setFundCode("222");
////        taBean.setTaAccountId("4");
////
////        taFundService.applyFundSubscribingCfm(taFundPurchasingCfmOrderRequest);
////
////        // TA 认购结果通知
////        TAFundSubscribingResultOrderRequest taFundSubscribingResultOrderRequest = new TAFundSubscribingResultOrderRequest();
////        List<TAFundSubscribingResultOrderBean> beanList = new ArrayList<>();
////        TAFundSubscribingResultOrderBean taFundSubscribingCfmOrderBean = new TAFundSubscribingResultOrderBean();
////        taFundSubscribingCfmOrderBean.setTransactionCfmDate("");
////        taFundSubscribingCfmOrderBean.setShareClass("");
////        taFundSubscribingCfmOrderBean.setDistributorCode("0009");
////        taFundSubscribingCfmOrderBean.setApplicationAmount(new BigDecimal(500));
////        taFundSubscribingCfmOrderBean.setAppSheetSerialNo(p.get(0).getAppSheetSerialNo());
////        taFundSubscribingCfmOrderBean.setBranchCode("0009");
////        taFundSubscribingCfmOrderBean.setConfirmedAmount(new BigDecimal(500));
////        taFundSubscribingCfmOrderBean.setDownloadDate("");
////        taFundSubscribingCfmOrderBean.setFundCode("222");
////        taFundSubscribingCfmOrderBean.setNav(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setReturnCode("0000");
////        taFundSubscribingCfmOrderBean.setTransactionDate("");
////        taFundSubscribingCfmOrderBean.setTransactionTime("");
////        taFundSubscribingCfmOrderBean.setTaAccountId(p.get(0).getTaAccountId());
////        taFundSubscribingCfmOrderBean.setTaSerialNo("");
////        taFundSubscribingCfmOrderBean.setTransactionAccountId(p.get(0).getTransactionAccountId());
////        taFundSubscribingCfmOrderBean.setAgencyFee(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setNav(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setCharge(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setTransferFee(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setInterest(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setConfirmedVol(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setCurrencyType("ISO");
////        taFundSubscribingCfmOrderBean.setRaiseInterest(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setBusinessCode("004");
////        taFundSubscribingCfmOrderBean.setInterestTax(BigDecimal.ZERO);
////        taFundSubscribingCfmOrderBean.setVolumeByInterest(BigDecimal.ZERO);
////
////        beanList.add(taFundSubscribingCfmOrderBean);
////        taFundSubscribingResultOrderRequest.setTaFundSubscribingResultOrderList(beanList);
////        taFundService.applyFundSubscribingResultCfm(taFundSubscribingResultOrderRequest);
//    }
//
//    /**
//     * 活期申购->支付成功->TA申购申请->TA申购确认->活期赎回->TA赎回申请->TA赎回确认
//     */
//    @Test
//    public void fundNormalPurchaseRedeemTest() {
////        // 申购
////        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
////        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setAmount(new BigDecimal(500));
////        bean.setCardNo("123");
////        bean.setDebitMemberNo(UUIDGenerator.gen());
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
////        paymentOptionBean.add(bean);
////        ForegroundBuyFundRequest req1 = new ForegroundBuyFundRequest();
////        req1.setBizChannel(BizChannel.ANDROID);
////        req1.setFundTransAmount(new BigDecimal(500));
////        req1.setIsAdjusted(false);
////        req1.setMemberNo("4");
////        req1.setOperatorNo(UUIDGenerator.gen());
////        req1.setTrackingNo(UUIDGenerator.gen());
////        req1.setPayMethod(JSON.toJSONString(paymentOptionBean));
////        req1.setMerMemberNo(UUIDGenerator.gen());
////        req1.setPayMethodDesc("test");
////        req1.setProductId(1);
////        req1.setRemark("test");
////        req1.setTriggerReason(TriggerReason.NORMAL);
////
////        String bizOrderNo = fundTransFacade.foregroundBuyFund(req1).getFundTransOrderNo();
////
////        // 支付成功
////        FundPaymentCallbackMessage message = new
////                FundPaymentCallbackMessage();
////        message.setTransStatus(TransactionStatus.SETTLED);
////        FundPaymentCallbackBizInfo bizInfo = new
////                FundPaymentCallbackBizInfo();
////        bizInfo.setBizOrderNo(bizOrderNo);
////        bizInfo.setBizTransType(FundTransType.PURCHASING);
////        List<FundPaymentCallbackBizInfo> list = new
////                ArrayList<FundPaymentCallbackBizInfo>();
////        list.add(bizInfo);
////        message.setFundPaymentCallbackBizInfoList(list);
////        fundTransService.consumeTransCoreStatus(message);
////
////        // TA 申购申请
////        TAJobPageableRequest request = new TAJobPageableRequest();
////        request.setStartDt(DateUtils.addDays(new Date(), -5));
////        request.setEndDt(DateUtils.addDays(new Date(), 5));
////        request.setPageNum(1);
////        request.setPageSize(1);
////        Page<TAFundPurchasingOrderBean> p = taFundService.applyFundPurchasing(request);
////
////        // 模拟TA确认
////        TAFundPurchasingCfmOrderRequest fundPurchaseConfirmRequest = new TAFundPurchasingCfmOrderRequest();
////        List<TAFundPurchasingCfmOrderBean> taFundPurchasingCfmOrderList = new ArrayList<TAFundPurchasingCfmOrderBean>();
////        TAFundPurchasingCfmOrderBean taBean = new TAFundPurchasingCfmOrderBean();
////        taFundPurchasingCfmOrderList.add(taBean);
////        fundPurchaseConfirmRequest.setTaFundPurchasingCfmOrderList(taFundPurchasingCfmOrderList);
////        taBean.setTransactionDate("");
////        taBean.setTransactionTime("");
////        taBean.setAgencyFee(BigDecimal.ONE);
////        taBean.setApplicationAmount(new BigDecimal(500));
////        taBean.setTransferFee(BigDecimal.ZERO);
////        taBean.setTransactionCfmDate("");
////        taBean.setTransactionAccountId("4");
////        taBean.setTaSerialNo("");
////        taBean.setShareClass("");
////        taBean.setReturnCode("0000");
////        taBean.setNav(BigDecimal.ZERO);
////        taBean.setDownloadDate("");
////        taBean.setAppSheetSerialNo(p.get(0).getAppSheetSerialNo());
////        taBean.setBranchCode("");
////        taBean.setBusinessCode("");
////        taBean.setConfirmedAmount(new BigDecimal(500));
////        taBean.setConfirmedVol(new BigDecimal(500));
////        taBean.setDistributorCode("");
////        taBean.setCharge(BigDecimal.ZERO);
////        taBean.setFundCode("111");
////        taBean.setCurrencyType("ISO");
////        taBean.setTaAccountId("4");
////
////        taFundService.applyFundPurchasingCfm(fundPurchaseConfirmRequest);
////
////        try {
////            Thread.sleep(3000L);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////
////        // 发起赎回
////        RedeemFundRequest req = new RedeemFundRequest();
////        req.setBizChannel(BizChannel.ANDROID);
////        req.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req.setFundTransAmount(new BigDecimal(500));
////        req.setIsAdjusted(false);
////        req.setMemberNo("4");
////        req.setOperatorNo(UUIDGenerator.gen());
////        req.setOrderNo(UUIDGenerator.gen());
////        req.setTrackingNo(UUIDGenerator.gen());
////        req.setMerMemberNo(UUIDGenerator.gen());
////        req.setProductId((long) 1);
////        req.setRemark("test");
////        req.setTriggerReason(TriggerReason.NORMAL);
////        fundTransFacade.redeemFund(req);
////
////        // TA赎回申请
////        TAJobPageableRequest redeemPageReguest = new TAJobPageableRequest();
////        redeemPageReguest.setStartDt(DateUtils.addDays(new Date(), -5));
////        redeemPageReguest.setEndDt(DateUtils.addDays(new Date(), 5));
////        redeemPageReguest.setPageNum(1);
////        redeemPageReguest.setPageSize(1);
////
////        Page<TAFundRedeemingOrderBean> page = taFundService.applyFundRedeeming(redeemPageReguest);
////
////        // TA赎回确认
////        TAFundRedeemingCfmOrderRequest taFundRedeemingCfmOrderRequest = new TAFundRedeemingCfmOrderRequest();
////        List<TAFundRedeemingCfmOrderBean> beanList = new ArrayList<>();
////        taFundRedeemingCfmOrderRequest.setTaFundRedeemingCfmOrderList(beanList);
////        TAFundRedeemingCfmOrderBean redeemingCfmOrderBean = new TAFundRedeemingCfmOrderBean();
////        redeemingCfmOrderBean.setTaAccountId(page.get(0).getTaAccountId());
////        redeemingCfmOrderBean.setCurrencyType("ISO");
////        redeemingCfmOrderBean.setAchievementCompen(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setAchievementPay(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setAgencyFee(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setApplicationVol(new BigDecimal(500));
////        redeemingCfmOrderBean.setAppSheetSerialNo(page.get(0).getAppSheetSerialNo());
////        redeemingCfmOrderBean.setBranchCode("000009");
////        redeemingCfmOrderBean.setBreachFee(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setBreachFeeBackToFund(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setBusinessCode("002");
////        redeemingCfmOrderBean.setLargeRedemptionFlag("0");
////        redeemingCfmOrderBean.setDownloadDate("");
////        redeemingCfmOrderBean.setTransactionDate("");
////        redeemingCfmOrderBean.setTransactionTime("");
////        redeemingCfmOrderBean.setCharge(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setBusinessFinishFlag("0");
////        redeemingCfmOrderBean.setConfirmedAmount(new BigDecimal(500));
////        redeemingCfmOrderBean.setConfirmedVol(new BigDecimal(500));
////        redeemingCfmOrderBean.setReturnCode("0000");
////        redeemingCfmOrderBean.setTaSerialNo("123");
////        redeemingCfmOrderBean.setTransactionAccountId(page.get(0).getTransactionAccountId());
////        redeemingCfmOrderBean.setNav(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setFundCode("111");
////        redeemingCfmOrderBean.setPunishFee(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setOtherFee1(BigDecimal.ZERO);
////        redeemingCfmOrderBean.setTransactionCfmDate("");
////        redeemingCfmOrderBean.setDistributorCode("");
////        redeemingCfmOrderBean.setTransferFee(BigDecimal.ONE);
////        redeemingCfmOrderBean.setShareClass("0");
////
////        beanList.add(redeemingCfmOrderBean);
////        taFundService.applyFundRedeemingCfm(taFundRedeemingCfmOrderRequest);
//    }
//
//    @Test
//    public void buyTest() throws Exception {
////        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
////
////        ForegroundBuyFundRequest req1 = new ForegroundBuyFundRequest();
////        req1.setBizChannel(BizChannel.ANDROID);
////        req1.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req1.setFundTransAmount(new BigDecimal(500));
////        req1.setIsAdjusted(false);
////        req1.setMemberNo("1");
////        req1.setOperatorNo(UUIDGenerator.gen());
////        req1.setOrderNo(UUIDGenerator.gen());
////        req1.setTrackingNo(UUIDGenerator.gen());
////        req1.setPayMethod(JSON.toJSONString(paymentOptionBean));
////        req1.setMerMemberNo(UUIDGenerator.gen());
////        req1.setPayMethodDesc("test");
////        req1.setProductId(1);
////        req1.setRemark("test");
////        req1.setTriggerReason(TriggerReason.NORMAL);
////
////        fundTransFacade.foregroundBuyFund(req1);
//    }
//
//    @Test
//    public void immRedeemTest() throws Exception {
//        // 申购
////        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
////        TransactionPaymentOptionBean bean = new TransactionPaymentOptionBean();
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setAmount(new BigDecimal(500));
////        bean.setCardNo("123");
////        bean.setDebitMemberNo(UUIDGenerator.gen());
////        bean.setCreditMemberNo(UUIDGenerator.gen());
////        bean.setPaymentInstrumentType(PaymentInstrumentType.DCP);
////        paymentOptionBean.add(bean);
////        ForegroundBuyFundRequest req1 = new ForegroundBuyFundRequest();
////        req1.setBizChannel(BizChannel.ANDROID);
////        req1.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req1.setFundTransAmount(new BigDecimal(500));
////        req1.setIsAdjusted(false);
////        req1.setMemberNo("1");
////        req1.setOperatorNo(UUIDGenerator.gen());
////        req1.setOrderNo(UUIDGenerator.gen());
////        req1.setTrackingNo(UUIDGenerator.gen());
////        req1.setPayMethod(JSON.toJSONString(paymentOptionBean));
////        req1.setMerMemberNo(UUIDGenerator.gen());
////        req1.setPayMethodDesc("test");
////        req1.setProductId(1);
////        req1.setRemark("test");
////        req1.setTriggerReason(TriggerReason.NORMAL);
////
////        String bizOrderNo = fundTransFacade.foregroundBuyFund(req1).getFundTransOrderNo();
////
////        // 支付成功
////        FundPaymentCallbackMessage message = new
////                FundPaymentCallbackMessage();
////        message.setTransStatus(TransactionStatus.SETTLED);
////        FundPaymentCallbackBizInfo bizInfo = new
////                FundPaymentCallbackBizInfo();
////        bizInfo.setBizOrderNo(bizOrderNo);
////        bizInfo.setBizTransType(FundTransType.PURCHASING);
////        List<FundPaymentCallbackBizInfo> list = new
////                ArrayList<FundPaymentCallbackBizInfo>();
////        list.add(bizInfo);
////        message.setFundPaymentCallbackBizInfoList(list);
////        fundTransService.consumeTransCoreStatus(message);
//
//        // 模拟TA确认
////        FundTransOrderExample example = new FundTransOrderExample();
////        FundTransOrderExample.Criteria c = example.createCriteria();
////        c.andFundTransOrderNoEqualTo(bizOrderNo);
//
////        FundTransOrder uOrder = new FundTransOrder();
////        uOrder.setStatus(FundTransOrderStatus.TA_TRANS_PROCESSING);
////        fundTransOrderMapper.updateByExampleSelective(uOrder, example);
////
////        Thread.sleep(3000);
////        // 赎回
////        RedeemFundRequest req = new RedeemFundRequest();
////        req.setBizChannel(BizChannel.ANDROID);
////        req.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req.setFundTransAmount(new BigDecimal(500));
////        req.setIsAdjusted(false);
////        req.setMemberNo("1");
////        req.setOperatorNo(UUIDGenerator.gen());
////        req.setOrderNo(UUIDGenerator.gen());
////        req.setTrackingNo(UUIDGenerator.gen());
////        req.setMerMemberNo(UUIDGenerator.gen());
////        req.setProductId((long) 1);
////        req.setRemark("test");
////        req.setTriggerReason(TriggerReason.NORMAL);
////        fundTransFacade.redeemFund(req);
//    }
//
//    @Test
//    public void NormalRedeemTest() throws Exception {
////        // 赎回
////        RedeemFundRequest req = new RedeemFundRequest();
////        req.setBizChannel(BizChannel.ANDROID);
////        req.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req.setFundTransAmount(new BigDecimal(500));
////        req.setIsAdjusted(false);
////        req.setMemberNo("3");
////        req.setOperatorNo(UUIDGenerator.gen());
////        req.setOrderNo(UUIDGenerator.gen());
////        req.setTrackingNo(UUIDGenerator.gen());
////        req.setMerMemberNo(UUIDGenerator.gen());
////        req.setProductId((long) 1);
////        req.setRemark("test");
////        req.setTriggerReason(TriggerReason.NORMAL);
////        fundTransFacade.redeemFund(req);
//    }
//
//    @Test
//    public void ComboRedeemTest() throws Exception {
////        List<TransactionPaymentOptionBean> paymentOptionBean = new ArrayList<TransactionPaymentOptionBean>();
////
////        ForegroundBuyFundRequest req1 = new ForegroundBuyFundRequest();
////        req1.setBizChannel(BizChannel.ANDROID);
////        req1.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req1.setFundTransAmount(new BigDecimal(500));
////        req1.setIsAdjusted(false);
////        req1.setMemberNo("2");
////        req1.setOperatorNo(UUIDGenerator.gen());
////        req1.setOrderNo(UUIDGenerator.gen());
////        req1.setTrackingNo(UUIDGenerator.gen());
////        req1.setPayMethod(JSON.toJSONString(paymentOptionBean));
////        req1.setMerMemberNo(UUIDGenerator.gen());
////        req1.setPayMethodDesc("test");
////        req1.setProductId(1);
////        req1.setRemark("test");
////        req1.setTriggerReason(TriggerReason.NORMAL);
////
////        String bizOrderNo = fundTransFacade.foregroundBuyFund(req1).getFundTransOrderNo();
////
////        // 支付成功
////        FundPaymentCallbackMessage message = new
////                FundPaymentCallbackMessage();
////        message.setTransStatus(TransactionStatus.SETTLED);
////        FundPaymentCallbackBizInfo bizInfo = new
////                FundPaymentCallbackBizInfo();
////        bizInfo.setBizOrderNo(bizOrderNo);
////        bizInfo.setBizTransType(FundTransType.PURCHASING);
////        List<FundPaymentCallbackBizInfo> list = new
////                ArrayList<FundPaymentCallbackBizInfo>();
////        list.add(bizInfo);
////        message.setFundPaymentCallbackBizInfoList(list);
////        fundTransService.consumeTransCoreStatus(message);
////
////        Thread.sleep(3000);
////        // 赎回
////        RedeemFundRequest req = new RedeemFundRequest();
////        req.setBizChannel(BizChannel.ANDROID);
////        req.setBusinessItemOrderNo(UUIDGenerator.gen());
////        req.setFundTransAmount(new BigDecimal(600));
////        req.setIsAdjusted(false);
////        req.setMemberNo("2");
////        req.setOperatorNo(UUIDGenerator.gen());
////        req.setOrderNo(UUIDGenerator.gen());
////        req.setTrackingNo(UUIDGenerator.gen());
////        req.setMerMemberNo(UUIDGenerator.gen());
////        req.setProductId((long) 1);
////        req.setRemark("test");
////        req.setTriggerReason(TriggerReason.NORMAL);
////        fundTransFacade.redeemFund(req);
//    }
//
//    @Test
//    public void fundClearingTest() throws Exception {
////        TAFundClearingOrderRequest request = new TAFundClearingOrderRequest();
////        List<TAFundClearingOrderBean> taOrderList = new ArrayList<TAFundClearingOrderBean>();
////        TAFundClearingOrderBean order = new TAFundClearingOrderBean();
////        order.setAchievementCompen(new BigDecimal(0));
////        order.setAchievementPay(new BigDecimal(0));
////        order.setBusinessCode("150");
////        order.setDistributorCode("286");
////        order.setDownloadDate("20160102");
////        order.setFrozenBalance(new BigDecimal(0));
////        order.setFundVolBalance(new BigDecimal(0));
////        order.setConfirmedAmount(new BigDecimal(500));
////        order.setConfirmedVol(new BigDecimal(500));
////        order.setCurrencyType("156");
////        order.setFundCode("002");
////        order.setNav(new BigDecimal(1));
////        order.setShareClass("1");
////        order.setTaAccountId("TA001");
////        order.setTransactionAccountId("000001");
////        order.setTransactionCfmDate("20160102");
////        order.setTotalFrozenVol(new BigDecimal(0));
////        order.setTaSerialNo("201601030140220001");
////        taOrderList.add(order);
////
////        request.setTaFundClearingOrderList(taOrderList);
////        taFundService.applyFundClearing(request);
////
////        Long productId = 1L;
////        fundClearingService.doPayment(productId);
//    }
//
//
//    @Test
//    @Ignore
//    public void testEnum()
//    {
//
//        try {
//
//            GetFundTransOrderListForCheckingRequest request = new GetFundTransOrderListForCheckingRequest();
//
//            ArrayList<String> listString = new ArrayList<String>();
//            listString.add("16030420293315801532");
//
////            request.setBusinessOrderItemNO(listString);
//
//            GetFundTransOrderListForCheckingResponse result = fundCheckingJobFacade.getFundTransOrderListForChecking(request);
//
//
////            DateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date startDate = startDateFormat.parse("2012-09-13 22:36:01");
////
////            DateFormat endDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date endDate = endDateFormat.parse("2016-03-23 22:36:01");
////            GetFundTransOrdersForCheckingPageableRequest req = new GetFundTransOrdersForCheckingPageableRequest();
////            req.setEndDate(endDate);
////            req.setStartDate(startDate);
////            req.setPageNum(1);
////            req.setPageSize(10);
////            req.setCalCount(false);
////
////
////            PageableResponse<FundTransOrderBean> result = fundCheckingJobFacade.getSucceedFundTransOrdersForCheckingByPage(req);
//            System.out.println(result);
//        }catch(Exception ex)
//        {
//            System.out.println(ex);
//        }
//
//    }
//
//
//
//    @Test
//    public void testUnic()
//    {
//        try{
//            AssetAndFundInfoRequest req = new AssetAndFundInfoRequest();
//            req.setProductId(10L);
//            req.setMemberNo("qqqqqq");
//            AssetAndFundInfoResponse resp = amcFacade.getAssetInfoAndFundInfo(req);
//            System.out.println(resp);
//        }catch (Exception ex)
//        {
//            System.out.println(ex.toString());
//        }
//
//
//
////        try {
////            FundDetail detail =  new FundDetail();
////
////            detail.setFundDetailUuid("");
////
////            detail.setFundTotalId(1L);
////
////            detail.setFundTotalUuid("1234567");
////
////            detail.setProductId(10000L);
////
////            detail.setFundTotalShare(BigDecimal.ONE);
////
////            detail.setFundTotalAmount(BigDecimal.ZERO);
////
////            detail.setYesterdayProfit(BigDecimal.ZERO);
////
////            detail.setTotalProfit(BigDecimal.ZERO);
////
////            detail.setCreateTime(new Date());
////
////            detail.setUpdateTime(new Date());
////
////            detail.setFrozenAmount(BigDecimal.ZERO);
////
////
////            detail.setFrozenShare(BigDecimal.ZERO);
////
////
////            detail.setConfirmedAddAmount(BigDecimal.ZERO);
////
////            detail.setConfirmedAddShare(BigDecimal.ZERO);
////
////            detail.setConfirmedAddShare(BigDecimal.ZERO);
////
////            detail.setUnconfirmedAddAmount(BigDecimal.ZERO);
////
////            detail.setUnconfirmedAddShare(BigDecimal.ZERO);
////
////            detail.setConfirmedSubAmount(BigDecimal.ZERO);
////
////            detail.setConfirmedSubShare(BigDecimal.ZERO);
////
////            detail.setUnconfirmedSubAmount(BigDecimal.ZERO);
////
////            detail.setUnconfirmedSubShare(BigDecimal.ZERO);
////
////            detail.setBonusTotalAmount(BigDecimal.ZERO);
////
////            detail.setProfitTotalAmount(BigDecimal.ZERO);
////
////            detail.setExpectBonusAmount(BigDecimal.ZERO);
////
////            detail.setSettlementTime(new Date());
////
////            detail.setExpectProfitAmount(BigDecimal.ZERO);
////
////            detail.setHasSettlement(1);
////
////            detail.setSettlementCapital(BigDecimal.ZERO);
////
////
////
////
////
////
////
////            int result = fundDetailMapper.insertIgnore(detail);
////            if(result == 0)
////            {
////                // do nothing
////                System.out.println("Ingnore success!");
////            }
////
////            System.out.println("Not Ingnore success!");
////
////
////        }catch (Exception ex)
////        {
////            System.out.println(ex);
////        }
//
////        FundSubscriptionBean fundSubscriptionBean = new FundSubscriptionBean();
////        fundSubscriptionBean.setProductId(15714L);
////        fundSubscriptionBean.setExpectProfitAmount(new BigDecimal(100));
////        fundSubscriptionBean.setFtcOrderCreateTime(new Date());
////        fundSubscriptionBean.setFtcOrderNo("123456");
////        fundSubscriptionBean.setFundAmount(new BigDecimal(1000));
////        fundSubscriptionBean.setMemberNo("402887255288d852015296f8702509ae");
////
////
////
////
////        fundDetailAlterationService.addFundDetailAlterationBySubscriptionRecord(fundSubscriptionBean);
////        fundDetailAlterationService.addFundDetailAlterationBySubscriptionRecord(fundSubscriptionBean);
////        fundDetailAlterationService.addFundDetailAlterationBySubscriptionRecord(fundSubscriptionBean);
////        fundDetailAlterationService.addFundDetailAlterationBySubscriptionRecord(fundSubscriptionBean);
//    }
//
//
//    @Test
//    @Ignore
//    public void testOfflineAdd()
//    {
//        AddOfflineAssetRequest addOfflineAssetRequest = new AddOfflineAssetRequest();
//        addOfflineAssetRequest.setBonusAmount(new BigDecimal(0.1));
//        addOfflineAssetRequest.setCustomerCardNo("sdfgh");
//        addOfflineAssetRequest.setCustomerName("shabo");
//        addOfflineAssetRequest.setCustomerPhone("18511111111");
//        addOfflineAssetRequest.setOfflineCode("asdfg");
//        addOfflineAssetRequest.setProductId(10L);
//        addOfflineAssetRequest.setMemberNo("asdfghjkl");
//        addOfflineAssetRequest.setProductName("nimabidfgadg");
//        addOfflineAssetRequest.setTotalProfit(new BigDecimal(10));
//        addOfflineAssetRequest.setSettlementCapital(new BigDecimal(1000));
//        addOfflineAssetRequest.setProfitAmount(new BigDecimal(10));
//        addOfflineAssetRequest.setInceptionDate(new Date());
//        addOfflineAssetRequest.setDueDate(new Date());
//        try {
//            BaseResponse resp = amcFacade.addOfflineAsset(addOfflineAssetRequest);
//            System.out.println(resp);
//        }catch(Throwable ex)
//        {
//            System.out.println(ex.toString());
//        }
//
//    }
//
//
//    @Test
//    @Ignore
//    public void queryFundInfoList()
//    {
//        QueryOfflineAssetRequest queryOfflineAssetRequest = new QueryOfflineAssetRequest();
//        queryOfflineAssetRequest.setMemberNo("asdfghjkl");
//
//        try {
//            OfflineAssetResponse resp = amcFacade.queryOfflineAsset(queryOfflineAssetRequest);
//            System.out.println(resp);
//        }catch(Throwable ex)
//        {
//            System.out.println(ex.toString());
//        }
//    }
//
//    @Test
//    public void checkLargeInvestmentClientsTest() {
//    	LargeInvestmentClientsRequest request = new LargeInvestmentClientsRequest();
//    	request.setThreshold(new BigDecimal("500"));
//    	Set<String> memberNos = new HashSet<>();
//    	memberNos.add("2c90b9b856922bcc01569262c2200012");
//    	request.setMemberNoSet(memberNos);
//    	LargeInvestmentClientsResponse resp = fundOperFacade.checkLargeInvestmentClients(request);
//    	if (!resp.isSuccess()) {
//    		System.out.println(resp);
//    	}
//    }
//
//
//    @Test
//    public void testDal() {
//        List<String> ftcOrderNo = new ArrayList<String>();
//        ftcOrderNo.add("16071817113904600347000200100127");
//        ftcOrderNo.add("16071916524476400380000200100127");
//        ftcOrderNo.add("16071917241965200383000200200127");
//        ftcOrderNo.add("16072010442125000000000200100127");
//        ftcOrderNo.add("16072011400599000020000200100127");
//        ftcOrderNo.add("16072015030345000060000200100127");
//        ftcOrderNo.add("16072015243484700077000200100127");
//        ftcOrderNo.add("16072015373136900084000200100127");
//        ftcOrderNo.add("16072017143763100098000200100127");
//        ftcOrderNo.add("16072017200613000101000200100127");
//        ftcOrderNo.add("16072017303719000113000200100127");
//        ftcOrderNo.add("16072017405649300117000200100127");
//        ftcOrderNo.add("16072019221164500133000200100127");
//        ftcOrderNo.add("16072019290619500141000200100127");
//        ftcOrderNo.add("16072019294680300144000200100127");
//        ftcOrderNo.add("16072020395316400146000200100127");
//        ftcOrderNo.add("16072020524109800149000200100127");
//        ftcOrderNo.add("16072109154848700151000200100127");
//        ftcOrderNo.add("16072109321824100153000200100127");
//        ftcOrderNo.add("16072109450161100155000200100127");
//
//        try {
//            List<FundDetailUpdateBean> fundDetailUpdateBeen = fundDetailAlterationMapper.selectFundDetailByFTCOrderNoList(ftcOrderNo, AlterationStatus.CUT_CANCLE);
//            List<FundTotalUpdateBean> fundTotalUpdateBeen = fundDetailAlterationMapper.selectFundTotalByFTCOrderNoList(ftcOrderNo, AlterationStatus.CUT_CANCLE);
//            List<MemberAssetUpdateBean> memberAssetUpdateBeen = fundDetailAlterationMapper.selectMemberAssetByFTCOrderNoList(ftcOrderNo, AlterationStatus.CUT_CANCLE);
//
//
//            System.out.println("nimabi");
//        } catch (Exception ex) {
//            System.out.println("nimabi" + ex.toString());
//        }
//
////        List<FundDetailUpdateBean> detailresult = fundDetailAlterationMapper.
//
//    }
//
//
//
//    @Test
//    public void testJson()
//    {
//
//        String orderNo = "16081911370183500002000200100127";
//
////        List<String> orderNos = new ArrayList<String>();
////        orderNos.add(orderNo);
////        List<FundTransOrder> result = fundTransOrderMapper.selectByFundTransOrderNo(orderNos);
////        FundTransOrder targetOrder = result.get(0);
////        String test= "[{\"amount\":200.000000,\"creditMemberNo\":\"2c90b9b856922bcc01569262c2200012\",\"identifierType\":1,\"paymentInstrumentNo\":\"QAI00000000000007735\",\"paymentInstrumentType\":6}]";
//
//
//        FundTransOrder queryOrder = new FundTransOrder();
//        queryOrder.setFundTransOrderNo(orderNo);
//        List<FundTransOrder> targetOrderList = fundTransOrderMapper.select(queryOrder);
//        FundTransOrder targetOrder = targetOrderList.get(0);
//        try {
////            List<TransactionPaymentOptionBean> paymentOptions = JSON.parseArray(
////                    test, TransactionPaymentOptionBean.class);
//            List<TransactionPaymentOptionBean> paymentOptions = JSON.parseArray(
//                    targetOrder.getPayMethod(), TransactionPaymentOptionBean.class);
//            System.out.println(paymentOptions);
//        }catch(Exception ex)
//        {
//            System.out.println(ex);
//        }
//    }
//}
