package com.eif.ftc.service.test.prepare;

import com.eif.fis.facade.constant.ChargeBase;
import com.eif.fis.facade.constant.InvestmentUnit;
import com.eif.fis.facade.constant.MarketLevel;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.constant.WhiteListType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.dto.ftc.CurrentProdTransInfo;
import com.eif.fis.facade.dto.ftc.NormalProdTransInfo;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.fis.facade.dto.ftc.SecMarketPricingRule;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.fis.facade.dto.ftc.SecMarketTransRule;
import com.eif.fis.facade.request.ftc.DeductionMonetaryInstrumentBalanceReq;
import com.eif.fis.facade.request.ftc.FreezeProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeProdInventoryReq;
import com.eif.fis.facade.response.ftc.*;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;
import org.apache.commons.lang.time.DateUtils;
import org.mockito.Mockito;
import org.omg.CORBA.COMM_FAILURE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 10/6/16.
 */
public class FisDataPrepare {

    public final static long NORMAL_SUC_PRODUCT_ID = 1L;
    public final static long NORMAL_NEW_PRODUCT_ID = 2L;
    public final static long NORMAL_CURRENT_SUC_PRODUCT_ID = 3L;
    public final static long NORMAL_SEC_MKT_SUC_PRODUCT_ID = 4L;
    public final static BigDecimal NORMAL_EXPECTED_RATE = new BigDecimal("0.1");

    //定期产品构造
    public static QueryProdTransInfoV2Resp buildBaseQueryProdTransInfo() {
        long normalBaseProductID = 100L;
        QueryProdTransInfoV2Resp queryProdTransInfoResp = new QueryProdTransInfoV2Resp();

        NormalProdTransInfo normalProdTransInfo = new NormalProdTransInfo();
//        private CurrentProdTransInfo currentProdTransInfo;

        normalProdTransInfo.setId(NORMAL_SUC_PRODUCT_ID);
        normalProdTransInfo.setProductName("测试定期产品");
        normalProdTransInfo.setCloseType(ProductCloseType.CLOSE);
        normalProdTransInfo.setOrderExpireTime(-1L);
        normalProdTransInfo.setBaseProductId(normalBaseProductID);
        normalProdTransInfo.setBizChannel(new Integer(BizChannel.H5).toString());
        normalProdTransInfo.setIsNovicePacks(false);
        normalProdTransInfo.setIsEmployeeExclusive(false);
        normalProdTransInfo.setTransBeginTime(DateUtils.addDays(new Date(), -1));
        normalProdTransInfo.setTransEndTime(DateUtils.addDays(new Date(), 1));
        normalProdTransInfo.setWhiteListType(WhiteListType.NO_WHITE_LIST);
        normalProdTransInfo.setGroupBuyId(1L);
        normalProdTransInfo.setInvestmentUnit(InvestmentUnit.SUM);
        normalProdTransInfo.setPerAmount(new BigDecimal(1));
        normalProdTransInfo.setMinBuyAmt(new BigDecimal(-1));
        normalProdTransInfo.setMaxBuyAmt(new BigDecimal(-1));
        normalProdTransInfo.setPerIncAmt(new BigDecimal(-1));
        // 单户单日购买最高金额/份额：用户购买产品的单日最高限额
        normalProdTransInfo.setPerUdbLimitAmt(new BigDecimal(-1));
        // 单人可购买的产品总共最高限额
        normalProdTransInfo.setLimitBuyAmt(new BigDecimal(-1));
        // 判断是否超过今日产品的总限额
        normalProdTransInfo.setDailyMaxBuyAmt(new BigDecimal(-1));
        // 设置rule
        TagRules tagRules = new TagRules();
        tagRules.setTagId(1L);
        tagRules.setPerUserBuyLimit(new BigDecimal(-1));
        normalProdTransInfo.setTagRules(tagRules);

        queryProdTransInfoResp.setNormalProdTransInfo(normalProdTransInfo);
        queryProdTransInfoResp.setRespCode(CommonRspCode.SUCCESS.getCode());
        queryProdTransInfoResp.setMsg(CommonRspCode.SUCCESS.getMessage());
        queryProdTransInfoResp.setCloseType(ProductCloseType.CLOSE);
        return queryProdTransInfoResp;
    }

    public static QueryProdInvenInfoResp buildBaseQueryProdInvenInfo() {
        QueryProdInvenInfoResp queryProdInvenInfoResp = new QueryProdInvenInfoResp();
        queryProdInvenInfoResp.setRespCode(CommonRspCode.SUCCESS.getCode());
        queryProdInvenInfoResp.setMsg(CommonRspCode.SUCCESS.getMessage());
        queryProdInvenInfoResp.setProductId(NORMAL_SUC_PRODUCT_ID);
        queryProdInvenInfoResp.setAvaliableHeadCount(200);
        queryProdInvenInfoResp.setBizChannel(BizChannel.H5);
        queryProdInvenInfoResp.setFrozenAmount(BigDecimal.ZERO);
        queryProdInvenInfoResp.setFrozenHeadCount(0);
        queryProdInvenInfoResp.setSellableAmount(new BigDecimal(10000));

        return queryProdInvenInfoResp;
    }


    //活期产品构造
    public static QueryProdTransInfoV2Resp buildBaseQueryCurrentProdTransInfo() {
        long normalBaseProductID = 1000L;
        QueryProdTransInfoV2Resp queryProdTransInfoResp = new QueryProdTransInfoV2Resp();
        CurrentProdTransInfo currentProdTransInfo = new CurrentProdTransInfo();


        currentProdTransInfo.setId(NORMAL_CURRENT_SUC_PRODUCT_ID);
        currentProdTransInfo.setProductName("测试活期产品");
        currentProdTransInfo.setCloseType(ProductCloseType.OPEN);
        currentProdTransInfo.setOrderExpireTime(-1L);
//        currentProdTransInfo.setBaseProductId(normalBaseProductID);
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
        currentProdTransInfo.setCloseDays(1);
        currentProdTransInfo.setPerYearDate(365);

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

    public static void buildQueryProdTransInfoForSuccess(FisIntService fisIntService) {
        QueryProdTransInfoV2Resp queryProdTransInfoResp = buildBaseQueryProdTransInfo();
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(NORMAL_SUC_PRODUCT_ID);
    }

    public static void buildQueryCurrentProdTransInfoForSuccess(FisIntService fisIntService) {
        QueryProdTransInfoV2Resp queryProdTransInfoResp = buildBaseQueryCurrentProdTransInfo();
        Mockito.doReturn(queryProdTransInfoResp).when(fisIntService).queryProdTransInfoV2(NORMAL_CURRENT_SUC_PRODUCT_ID);
    }


    public static void buildQueryProdInvenInfoForSuccess(FisIntService fisIntService) {
        QueryProdInvenInfoResp queryProdInvenInfoResp = new QueryProdInvenInfoResp();
        queryProdInvenInfoResp.setRespCode(CommonRspCode.SUCCESS.getCode());
        queryProdInvenInfoResp.setMsg(CommonRspCode.SUCCESS.getMessage());
        queryProdInvenInfoResp.setProductId(NORMAL_SUC_PRODUCT_ID);
        queryProdInvenInfoResp.setAvaliableHeadCount(200);
        queryProdInvenInfoResp.setBizChannel(BizChannel.H5);
        queryProdInvenInfoResp.setFrozenAmount(BigDecimal.ZERO);
        queryProdInvenInfoResp.setFrozenHeadCount(0);
        queryProdInvenInfoResp.setSellableAmount(new BigDecimal(500));
        Mockito.doReturn(queryProdInvenInfoResp).when(fisIntService).queryProdInvenInfo(NORMAL_SUC_PRODUCT_ID);
    }

    public static void buildFreezeProdInventoryForSuccess(FisIntService fisIntService) {
        FreezeProdInventoryResp freezeProdInventoryResp = new FreezeProdInventoryResp();
        freezeProdInventoryResp.setRespCode(CommonRspCode.SUCCESS.getCode());
        freezeProdInventoryResp.setMsg(CommonRspCode.SUCCESS.getMessage());
        Mockito.doReturn(freezeProdInventoryResp).when(fisIntService).freezeProdInventory(Mockito.any(FreezeProdInventoryReq.class));
    }

    public static void buildQueryProdTaTransInfoForSuccess(FisIntService fisIntService) {

        ProdTaTransInfo prodTaTransInfo = new ProdTaTransInfo();
        prodTaTransInfo.setProductLimit(1);
        // 年收益天数
        prodTaTransInfo.setPerYearDate(365);
        // 年收益率
        prodTaTransInfo.setAnnualYieldRate(BigDecimal.valueOf(6.0));

        Mockito.doReturn(prodTaTransInfo).when(fisIntService).queryProdTaTransInfo(Mockito.anyLong());
    }

    public static void buildUnfreezeAndDeductionProdInventoryForSuccess(FisIntService fisIntService) {
        Mockito.doNothing().when(fisIntService).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));
    }

    public static void buildUnfreezeAndDeductionProdInventoryForRefund(FisIntService fisIntService) {
        Mockito.doThrow(new BusinessException(FTCRespCode.ERR_UNFREEZE_AS_FAIL_ALREADY.getMessage(), FTCRespCode.ERR_UNFREEZE_AS_FAIL_ALREADY.getCode())).when(fisIntService).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));
    }

    public static void buildUnfreezeProdInventoryForSuccess(FisIntService fisIntService) {
        Mockito.doNothing().when(fisIntService).unfreezeProdInventory(Mockito.any(UnfreezeProdInventoryReq.class));
    }

    public static void buildUnfreezeProdInventoryBatchForSuccess(FisIntService fisIntService) {
        Mockito.doReturn(null).when(fisIntService).unfreezeProdInventoryBatch(Mockito.anyList());
    }

    public static void buildDeductionMonetaryInstrumentBalanceForSuccess(FisIntService fisIntService) {
        Mockito.doNothing().when(fisIntService).deductionMonetaryInstrumentBalance(Mockito.any(DeductionMonetaryInstrumentBalanceReq.class));
    }

    public static SecMarketRule buildSecMktProductRule() {
    	SecMarketRule secMarketRule = new SecMarketRule();
    	SecMarketTransRule secMarketTransRule = new SecMarketTransRule();
    	secMarketTransRule.setDaysBeforeDueDate(5);
    	secMarketTransRule.setFirstTransHoldDay(10);
    	secMarketTransRule.setIsSupportMktActivity(true);
    	secMarketTransRule.setNextTransHoldDay(5);
    	secMarketTransRule.setTransValidTime(72);
    	
    	SecMarketPricingRule secMarketPricingRule = new SecMarketPricingRule();
    	secMarketPricingRule.setMaxTransRate(new BigDecimal("0.12"));
    	secMarketPricingRule.setMinTransRate(new BigDecimal("0.08"));
    	
    	SecMarketChargeRule secMarketChargeRule = new SecMarketChargeRule();
    	secMarketChargeRule.setChargeBase(ChargeBase.investment_amount);
    	secMarketChargeRule.setDiscountRate(new BigDecimal("1"));
    	secMarketChargeRule.setChargeRate(new BigDecimal("0.01"));
    	secMarketChargeRule.setIsMinCharge(true);
    	secMarketChargeRule.setMinChargeAmt(new BigDecimal("2"));
    	secMarketChargeRule.setIsMaxCharge(true);
    	secMarketChargeRule.setMaxChargeAmt(new BigDecimal("50"));
//    	secMarketChargeRule.setServiceFeeMode();
//    	secMarketChargeRule.setServiceFeePayer();
    	
    	secMarketRule.setSecMarketTransRule(secMarketTransRule);
    	secMarketRule.setSecMarketPricingRule(secMarketPricingRule);
    	secMarketRule.setSecMarketChargeRule(secMarketChargeRule);
    	return secMarketRule;
    }
    
    public static QuerySecMarketRuleByProdIdsResp buildSecMarketRuleByProdIds() {
    	QuerySecMarketRuleByProdIdsResp resp = new QuerySecMarketRuleByProdIdsResp();
    	resp.setRespCode(CommonRspCode.SUCCESS.getCode());
    	resp.setMsg(CommonRspCode.SUCCESS.getMessage());
    	
    	List<SecMarketRule> rules = new ArrayList<>();
    	rules.add(buildSecMktProductRule());
    	resp.setSecMarketTransRules(rules);
    	return resp;
    }
    
    public static ProdInfo buildFirstProductCommonInfo() {
    	Date curDate = new Date();
    	ProdInfo product = new ProdInfo();
    	product.setProductId(NORMAL_SUC_PRODUCT_ID);
    	product.setAnnualYieldRate(new BigDecimal("0.1"));
    	product.setClearAccount("123456788");
    	product.setCloseType(ProductCloseType.CLOSE);
    	product.setDueDate(DateUtils.addDays(curDate, 345));
    	product.setInceptionDate(DateUtils.addDays(curDate, -15));
    	product.setMarketLevel(MarketLevel.FIRST);
    	product.setOrderExpireTime(600);
    	product.setPerYearDate(365);
    	product.setProductName("测试产品-一级市场");
    	product.setSupportTransfer(true);
    	product.setProductLimit(360);
    	product.setRevenuesOnRedemptionDay(false);
    	
    	return product;
    }

    public static ProdInfo buildSecMktProductCommonInfo() {
    	Date curDate = new Date();
    	ProdInfo product = new ProdInfo();
    	product.setProductId(NORMAL_SEC_MKT_SUC_PRODUCT_ID);
    	product.setAnnualYieldRate(new BigDecimal("0.12"));
    	product.setClearAccount("123456788");
    	product.setCloseType(ProductCloseType.CLOSE);
    	product.setDueDate(DateUtils.addDays(curDate, 345));
    	product.setInceptionDate(curDate);
    	product.setMarketLevel(MarketLevel.SECOND);
    	product.setOrderExpireTime(600);
    	product.setPerYearDate(365);
    	product.setProductName("测试产品-二级市场");
    	product.setSupportTransfer(true);
    	product.setProductLimit(345);
    	product.setRevenuesOnRedemptionDay(false);
    	
    	return product;
    }

    public static void main(String[] args) {
//    	Date curDate = new Date();
//    	System.out.println(curDate.toString());
//    	System.out.println(DateUtils.addDays(curDate, 355).toString());
//    	System.out.println(DateUtils.addDays(curDate, -5).toString());
//    	System.out.println(DateUtils.addDays(curDate, 5).toString());
//    	System.out.println(DateUtils.addDays(DateUtils.addDays(curDate, -5),360));
    	
//    	BigDecimal totalAmount = new BigDecimal("1504.81");
//    	BigDecimal surplusDays = new BigDecimal("10");
//    	BigDecimal annualYield = new BigDecimal("0.08");
//    	BigDecimal productYearDays = new BigDecimal("365");
//    	BigDecimal transferPrice = totalAmount.divide(BigDecimal.ONE.add(
//				annualYield.multiply(surplusDays).divide(
//						productYearDays, MoneyUtil.ACCURACY, BigDecimal.ROUND_DOWN)), 
//				MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
//    	System.out.println(transferPrice.toString());
    }
    
}
