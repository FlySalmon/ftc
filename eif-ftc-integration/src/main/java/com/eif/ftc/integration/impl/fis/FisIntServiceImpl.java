package com.eif.ftc.integration.impl.fis;

import com.eif.fis.facade.biz.amc.AmcFacade;
import com.eif.fis.facade.biz.common.FisWorkingDayFacade;
import com.eif.fis.facade.biz.ftc.FtcFacade;
import com.eif.fis.facade.biz.job.FisJobFacade;
import com.eif.fis.facade.constant.MqTopic;
import com.eif.fis.facade.dto.ftc.InvestProperty;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdSummary;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.fis.facade.request.amc.QueryProdNetWorthReq;
import com.eif.fis.facade.request.common.QueryWorkingDayReq;
import com.eif.fis.facade.request.ftc.*;
import com.eif.fis.facade.request.job.QueryAvaliableCurrentProdReq;
import com.eif.fis.facade.response.amc.QueryProdNetWorthResp;
import com.eif.fis.facade.response.common.FisResponseCode;
import com.eif.fis.facade.response.common.QueryWorkingDayResp;
import com.eif.fis.facade.response.ftc.*;
import com.eif.fis.facade.response.job.QueryAvaliableCurrentProdResp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.fis.bean.ProductNetWorthBean;
import com.eif.ftc.integration.fis.bean.QueryProductNetWorthBean;
import com.eif.ftc.integration.util.ResponseMapper;
import com.eif.ftc.util.exception.BusinessException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("fisIntService")
public class FisIntServiceImpl implements FisIntService {

    @Resource(name = "ftcFacade")
    private FtcFacade ftcFacade;

    @Resource(name = "fisJobFacade")
    private FisJobFacade fisJobFacade;

    @Resource(name = "fisAmcFacade")
    AmcFacade amcFacade;

    @Resource(name = "fisWorkingDayFacade")
    FisWorkingDayFacade fisWorkingDayFacade;

    @Resource(name = "defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;

    private static Logger logger = LoggerFactory.getLogger(FisIntServiceImpl.class);

//    @Cacheable(value = "productCache")
//    public QueryProdTransInfoResp queryProdTransInfo(Long productID) {
//        QueryProdTransInfoReq queryProdTransInfoReq = new QueryProdTransInfoReq();
//        queryProdTransInfoReq.setProductId(productID);
//        QueryProdTransInfoResp queryProdTransInfoResp = ftcFacade.queryProdTransInfo(queryProdTransInfoReq);
//
//        if (!queryProdTransInfoResp.getRespCode().equals(FisResponseCode.SUCCESS)) {
//            throw new BusinessException(queryProdTransInfoResp.getMsg(), queryProdTransInfoResp.getRespCode());
//        }
//        return queryProdTransInfoResp;
//    }

    //TODO: mq provider
//    @CachePut(value = "productCache", key = "#queryProdTransInfo.getProductId()")
    public void updateProdTransInfo(QueryProdTransInfoResp queryProdTransInfo) {
    }

    public List<String> unfreezeProdInventoryBatch(List<String> tokens) {
        UnfreezeProdInventoryBatchReq request = new UnfreezeProdInventoryBatchReq();
        request.setTokens(tokens);
        UnfreezeProdInventoryBatchResp resp = ftcFacade.unfreezeProdInventoryBatch(request);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
        
        return resp.getFailedList();
    }

    public FreezeProdInventoryResp freezeProdInventory(FreezeProdInventoryReq freezeRequest) {
        // 如果库存不足则抛异常
        FreezeProdInventoryResp freezeProdInventoryResp = ftcFacade.freezeProdInventory(freezeRequest);
        if (!freezeProdInventoryResp.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            freezeRequest.setToken(""); // 清空设置的token
            ResponseMapper.wrapBusinessException(freezeProdInventoryResp.getRespCode(), freezeProdInventoryResp.getMsg());
        }
        return freezeProdInventoryResp;
    }

    public void unfreezeProdInventory(UnfreezeProdInventoryReq unfreezeProdInventoryReq) {
        UnfreezeProdInventoryResp unfreezeProdInventoryResp = ftcFacade.unfreezeProdInventory(unfreezeProdInventoryReq);
        if (!unfreezeProdInventoryResp.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            ResponseMapper.wrapBusinessException(unfreezeProdInventoryResp.getRespCode(), unfreezeProdInventoryResp.getMsg());
        }
    }

    public void deductionMonetaryInstrumentBalance(DeductionMonetaryInstrumentBalanceReq dreq) {
        DeductionMonetaryInstrumentBalanceResp resp = ftcFacade.deductionMonetaryInstrumentBalance(dreq);
        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_REDEEM_AMT_HUGE_REFUSE.getMessage(),
                    FTCRespCode.ERR_FUND_TRANS_REDEEM_AMT_HUGE_REFUSE.getCode());
        }
    }

    public void deductionMonetaryInstrumentBalanceAsync(DeductionMonetaryInstrumentBalanceReq dreq) {
        MessageWrapper msg = new MessageWrapper(MqTopic.DEDUCT_MONETARY_INSTRUMENT_BALANCE, null, dreq.getToken(), dreq);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send FIN_TXN_QUOTA_INCREASE message err, exception is", e);
        }
    }

    public void returnDeduction(String token,Long productId) {
        DeductionMonetaryInstrumentBalanceReq returnRequest = new DeductionMonetaryInstrumentBalanceReq();
        returnRequest.setToken(token);
        returnRequest.setProductId(productId);
        try {
            DeductionMonetaryInstrumentBalanceResp deductionMonetaryInstrumentBalanceResp = ftcFacade.deductionMonetaryInstrumentBalance(returnRequest);
            if(!deductionMonetaryInstrumentBalanceResp.getRespCode().equals(CommonRspCode.SUCCESS.getCode()))
            {
                logger.info("return deduction failed:Message is : " + deductionMonetaryInstrumentBalanceResp);
            }
        }catch (Exception ex) {
            logger.info("return deduction failed:Message is : " + ex.toString());
        }
    }

    public void unfreezeAndDeductionProdInventory(UnfreezeAndDeductionProdInventoryReq unFreezeRequest) {
        UnfreezeAndDeductionProdInventoryResp unfreezeAndDeductionProdInventoryResp = ftcFacade.unfreezeAndDeductionProdInventory(unFreezeRequest);
        if (!unfreezeAndDeductionProdInventoryResp.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            ResponseMapper.wrapBusinessException(unfreezeAndDeductionProdInventoryResp.getRespCode(), unfreezeAndDeductionProdInventoryResp.getMsg());
        }
    }

    public void unfreezeAndDeductionProdInventoryBatch(UnfreezeAndDeductionProdInventoryBatchReq unfreezeAndDeductionProdInventoryBatchReq) {
        UnfreezeAndDeductionProdInventoryBatchResp unfreezeAndDeductionProdInventoryBatchResp = ftcFacade.unfreezeAndDeductionProdInventoryBatch(unfreezeAndDeductionProdInventoryBatchReq);
        if (!unfreezeAndDeductionProdInventoryBatchResp.isSuccess()) {
            ResponseMapper.wrapBusinessException(unfreezeAndDeductionProdInventoryBatchResp.getRespCode(), unfreezeAndDeductionProdInventoryBatchResp.getMsg());
        }

    }

    public QueryProdInfoByProdIdsResp queryProdInfoByProdIds(List<Long> productIdList) {
        QueryProdInfoByProdIdsReq request = new QueryProdInfoByProdIdsReq();
        request.setProdIds(productIdList);
        QueryProdInfoByProdIdsResp resp = ftcFacade.queryProdInfoByProdIds(request);
        if (!resp.isSuccess()) {// ||
//                ((resp.getInfos() == null || resp.getInfos().size() < 1) && 
//                (resp.getCurrentProdInfos() == null || resp.getCurrentProdInfos().size() < 1))) {
            logger.error("query product info from fis failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

    public QueryAvaliableCurrentProdResp queryAvaliableCurrentProd() {
        QueryAvaliableCurrentProdReq request = new QueryAvaliableCurrentProdReq();
        QueryAvaliableCurrentProdResp resp = fisJobFacade.queryAvaliableCurrentProd(request);
        if (!resp.isSuccess()) {
            logger.error("query active product info failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

    public ProductNetWorthBean queryProductNetWorth(QueryProductNetWorthBean queryProductNetWorthBean) {
        QueryProdNetWorthReq request = new QueryProdNetWorthReq();
        request.setProductId(queryProductNetWorthBean.getProductNo());
        request.setValueDate(queryProductNetWorthBean.getValueDate());
        QueryProdNetWorthResp resp = amcFacade.queryProdNetWorth(request);
        if (resp.getRespCode().equals(CommonRspCode.SUCCESS.getCode()) && resp.getNetWorth() != null && resp.getNetWorth().compareTo(BigDecimal.ZERO) == 1) {
            ProductNetWorthBean productNetWorthBean = new ProductNetWorthBean();
            productNetWorthBean.setNetWorth(resp.getNetWorth());
            return productNetWorthBean;
        } else
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_NETWORTH.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_CALL_NETWORTH.getCode());
    }

    public QueryWorkingDayResp queryWorkingDayIgnoreError(Date date, Integer n) {
        QueryWorkingDayReq queryWorkingDayReq = new QueryWorkingDayReq();
        queryWorkingDayReq.settDay(date);
        queryWorkingDayReq.setN(n);
        return fisWorkingDayFacade.queryWorkingDay(queryWorkingDayReq);
    }

    public QueryProdInfoByProdNameResp queryProdInfoByProdName(String prodName) {
        QueryProdInfoByProdNameReq request = new QueryProdInfoByProdNameReq();
        request.setProdName(prodName);
        QueryProdInfoByProdNameResp resp = ftcFacade.queryProdInfoByProdName(request);

        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_BY_NAME.getMessage(), FTCRespCode.ERR_FUND_QUERY_BY_NAME.getCode());
        }

        return resp;
    }


    public ProdTaTransInfo queryProdTaTransInfo(Long productId)
    {
        QueryProdTaTransInfoReq request = new QueryProdTaTransInfoReq();
        Long[] productIds = new Long[1];
        productIds[0] = productId;
        request.setProductIds(productIds);
        QueryProdTaTransInfoResp result = ftcFacade.queryProdTaTransInfo(request);
        if (!result.isSuccess())
            throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_BY_NAME.getMessage(), FTCRespCode.ERR_FUND_QUERY_BY_NAME.getCode());

        List<ProdTaTransInfo> prodTaTransInfos = result.getInfos();
        return prodTaTransInfos.get(0);
    }

    @Override
    public QueryProdInvenInfoResp queryProdInvenInfo(Long productId) {
        QueryProdInvenInfoReq req = new QueryProdInvenInfoReq();
        req.setProductId(productId);
        QueryProdInvenInfoResp resp = ftcFacade.queryProdInvenInfo(req);

        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FIS_CALL_FAILED.getMessage(), FTCRespCode.ERR_FIS_CALL_FAILED.getCode());
        }

        return resp;
    }

    public void unfreezeProdInventoryAsync(FundTransOrder transOrder, UnfreezeProdInventoryReq unfreezeProdInventoryReq) {

        MessageWrapper msg = new MessageWrapper(MqTopic.UNFREEZE_PROD_INVENTORY, null, transOrder.getFundTransOrderNo(), unfreezeProdInventoryReq);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send UNFREEZE_PROD_INVENTORY message err, exception is", e);
        }
    }

    public void compensateProdInventory(String frozenCode) {
        CompensateProdInventoryReq req = new CompensateProdInventoryReq();
        req.setToken(frozenCode);
        CompensateProdInventoryResp resp = ftcFacade.compensateInventory(req);
        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FIS_CALL_FAILED.getMessage(), FTCRespCode.ERR_FIS_CALL_FAILED.getCode());
        }
    }

    public void compensateProdInventoryAsync(String frozenCode, String fundTransOrderNo) {
        CompensateProdInventoryReq req = new CompensateProdInventoryReq();
        req.setToken(frozenCode);
        MessageWrapper msg = new MessageWrapper(MqTopic.COMPENSATE_PROD_INVENTORY, null, fundTransOrderNo, req);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send COMPENSATE_PROD_INVENTORY message err, exception is", e);
        }
    }

    public QueryProdTransInfoV2Resp queryProdTransInfoV2(Long productID) {
        QueryProdTransInfoV2Req req = new QueryProdTransInfoV2Req();
        req.setProductId(productID);
        QueryProdTransInfoV2Resp resp = ftcFacade.queryProdTransInfoV2(req);
        if (!resp.getRespCode().equals(FisResponseCode.SUCCESS)) {
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }
        return resp;
    }


    @Override
    public FreezeCurrentProdInventoryResp freezeCurrentProdInventory(FreezeCurrentProdInventoryReq freezeRequest) {
        // 如果库存不足则抛异常
        FreezeCurrentProdInventoryResp freezeProdInventoryResp = ftcFacade.freezeCurrentProdInventory(freezeRequest);
        if (!freezeProdInventoryResp.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            freezeRequest.setToken(""); // 清空设置的token
            ResponseMapper.wrapBusinessException(freezeProdInventoryResp.getRespCode(), freezeProdInventoryResp.getMsg());
        }
        return freezeProdInventoryResp;
    }

    @Override
    public UnfreezeCurrentProdInventoryResp unfreezeCurrentProdInventory(UnfreezeCurrentProdInventoryReq req) {
        UnfreezeCurrentProdInventoryResp  unfreezeCurrentProdInventory = ftcFacade.unfreezeCurrentProdInventory(req);
        if (!unfreezeCurrentProdInventory.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
//            req.setToken(""); // 清空设置的token
            ResponseMapper.wrapBusinessException(unfreezeCurrentProdInventory.getRespCode(), unfreezeCurrentProdInventory.getMsg());
        }
        return unfreezeCurrentProdInventory;
    }


    @Override
    public void unfreezeCurrentProdInventoryAsync(FundTransOrder transOrder, UnfreezeCurrentProdInventoryReq unfreezeProdInventoryReq) {

        MessageWrapper msg = new MessageWrapper(MqTopic.UNFREEZE_CURRENT_PROD_INVENTORY, null, transOrder.getFundTransOrderNo(), unfreezeProdInventoryReq);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send UNFREEZE_CURRENT_PROD_INVENTORY message err, exception is", e);
        }
    }


    @Override
    public void unfreezeAndDeductCurrentProdInventory(UnfreezeAndDeductCurrentProdInventoryReq unFreezeRequest) {
        UnfreezeAndDeductCurrentProdInventoryResp unfreezeAndDeductCurrentProdInventoryResp = ftcFacade.unfreezeAndDeductCurrentProdInventory(unFreezeRequest);
        if (!unfreezeAndDeductCurrentProdInventoryResp.getRespCode()
                .equals(CommonRspCode.SUCCESS.getCode())) {
            ResponseMapper.wrapBusinessException(unfreezeAndDeductCurrentProdInventoryResp.getRespCode(), unfreezeAndDeductCurrentProdInventoryResp.getMsg());
        }
    }


    @Override
    public void compensateCurrentProdInventory(String frozenCode) {
        CompensateCurrentProdInventoryReq req = new CompensateCurrentProdInventoryReq();
        req.setToken(frozenCode);
        CompensateCurrentProdInventoryResp resp = ftcFacade.compensateCurrentProdInventory(req);
        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FIS_CALL_FAILED.getMessage(), FTCRespCode.ERR_FIS_CALL_FAILED.getCode());
        }
    }


    @Override
    public void compensateCurrentProdInventoryAsync(String frozenCode, String fundTransOrderNo) {
        CompensateCurrentProdInventoryReq req = new CompensateCurrentProdInventoryReq();
        req.setToken(frozenCode);
        MessageWrapper msg = new MessageWrapper(MqTopic.COMPENSATE_CURRENT_PROD_INVENTORY, null, fundTransOrderNo, req);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send COMPENSATE_CURRENT_PROD_INVENTORY message err, exception is", e);
        }
    }


    @Override
    public List<String> unfreezeCurrentProdInventoryBatch(List<String> tokens) {
        UnfreezeCurrentProdInventoryBatchReq request = new UnfreezeCurrentProdInventoryBatchReq();
        request.setTokens(tokens);
        UnfreezeCurrentProdInventoryBatchResp resp = ftcFacade.unfreezeCurrentProdInventoryBatch(request);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }

        return resp.getFailedList();
    }

	@Override
	public ProdInfo queryProductCommonInfo(Long productId) {
		Set<Long> productIds = new HashSet<>();
		productIds.add(productId);
		
		Map<Long, ProdInfo> productMap = queryProductCommonInfos(productIds);
		if (productMap == null || CollectionUtils.isEmpty(productMap.values())) {
			logger.error("queryProductCommonInfo from fis failed, productId: " + String.valueOf(productId));
			throw new BusinessException(FTCRespCode.ERR_FIS_CALL_FAILED.getMessage(), 
					FTCRespCode.ERR_FIS_CALL_FAILED.getCode());
		}
		
		return productMap.get(productId);
	}

	@Override
	public Map<Long, ProdInfo> queryProductCommonInfos(Set<Long> productIds) {
		if (CollectionUtils.isEmpty(productIds)) 
			return null;
		
		QueryImportantInfosByProdIdsReq request = new QueryImportantInfosByProdIdsReq();
		request.setProdIds(new ArrayList<>(productIds));
		QueryImportantInfosByProdIdsResp resp = ftcFacade.queryImportantInfosByProdIds(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
		
		Map<Long, ProdInfo> productMap = new HashMap<>();
		for (ProdInfo product : resp.getProdInfos()) {
			productMap.put(product.getProductId(), product);
		}
		return productMap;
	}

	@Override
	public Map<Long, ProdSummary> getCloseProductMix(List<Long> productIds) {
		QueryProdSummaryByIdsReq req = new QueryProdSummaryByIdsReq();
		req.setProdIds(productIds);
		QueryProdSummaryByIdsResp resp = ftcFacade.queryProdSummaryByIds(req);
		if (!resp.isSuccess()) {
			logger.error("call fis queryProdSummaryByIds failed.");
			throw new BusinessException(resp.getMsg(), resp.getRespCode());
		}

		Map<Long, ProdSummary> productMixMap = new HashMap<>();
		for (ProdSummary productMix : resp.getProdSummaries()) {
			productMixMap.put(productMix.getProductId(), productMix);
		}
		return productMixMap;
	}

	@Override
	public ProdSummary getCloseProductMix(Long productId) {
		List<Long> productIds = new ArrayList<>();
		productIds.add(productId);
		
		return getCloseProductMix(productIds).get(productId);
	}


	@Override
	public SecMarketRule queryMktProductTransactionRule(Long productId) {
		Set<Long> productIds = new HashSet<Long>();
		productIds.add(productId);
		
		Map<Long, SecMarketRule> productRuleMap = queryMktProductTransactionRule(productIds);
		if (productRuleMap == null || CollectionUtils.isEmpty(productRuleMap.values())) {
			logger.error("queryMktProductTransactionRule from fis failed, productId: " + String.valueOf(productId));
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_RULE_NOT_FUND.getMessage(), 
					FTCRespCode.ERR_FUND_TRANSFER_RULE_NOT_FUND.getCode());
		}
		
		return productRuleMap.get(productId);
	}

	@Override
	public Map<Long, SecMarketRule> queryMktProductTransactionRule(Set<Long> productIds) {
		if (CollectionUtils.isEmpty(productIds)) 
			return null;
		
		QuerySecMarketRuleByProdIdsReq request = new QuerySecMarketRuleByProdIdsReq();
		request.setProdIds(new ArrayList<Long>(productIds));
		QuerySecMarketRuleByProdIdsResp resp = ftcFacade.querySecMarketRuleByProdIds(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
		
		Map<Long, SecMarketRule> productRuleMap = new HashMap<>();
		for (SecMarketRule rule : resp.getSecMarketTransRules()) {
			productRuleMap.put(rule.getProdId(), rule);
		}
		return productRuleMap;
	}

	@Override
	public Long createMktProduct(String transferorOrderNo, Long productId, 
			List<InvestProperty> investPropertyList, 
    		Date orderExpiredTime, BigDecimal feeDiscount,
    		BigDecimal origionalPrincipal) {
		CreateSecMarketProdReq request = new CreateSecMarketProdReq();
		request.setParentId(productId);
		request.setInvestProperties(investPropertyList);
		request.setTransferEndTime(orderExpiredTime);
		request.setTransferOrderNo(transferorOrderNo);
		request.setChargeDiscountRate(feeDiscount);
		request.setOriginalInvestAmt(origionalPrincipal);
		
		CreateSecMarketProdResp resp = ftcFacade.createSecMarketProd(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
		
		return resp.getId();
	}
	
	@Override
	public void createMktProductAsync(String transferorOrderNo, Long productId, 
			List<InvestProperty> investPropertyList, 
    		Date orderExpiredTime, BigDecimal feeDiscount,
    		BigDecimal origionalPrincipal) {
		CreateSecMarketProdReq request = new CreateSecMarketProdReq();
		request.setParentId(productId);
		request.setInvestProperties(investPropertyList);
		request.setTransferEndTime(orderExpiredTime);
		request.setTransferOrderNo(transferorOrderNo);
		request.setChargeDiscountRate(feeDiscount);
		request.setOriginalInvestAmt(origionalPrincipal);
		
		MessageWrapper msg = new MessageWrapper(MqTopic.CREATE_SEC_MARKET_PRODUCT, 
				null, 
				transferorOrderNo, 
				request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send COMPENSATE_SEC_MARKET_PROD_INVENTORY message err, exception is", e);
        }
	}
	
	@Override
	public void cancelMktProduct(Long mktProductId) {
		InvalidateSecMarketProdReq request = new InvalidateSecMarketProdReq();
		request.setProdId(mktProductId);
		InvalidateSecMarketProdResp resp = ftcFacade.invalidateSecMarketProd(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
	}

	@Override
	public void cancelMktProductAsync(Long mktProductId) {
		InvalidateSecMarketProdReq request = new InvalidateSecMarketProdReq();
		request.setProdId(mktProductId);
		MessageWrapper msg = new MessageWrapper(MqTopic.INVALIDATE_SEC_MARKET_PROD, null, null, request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send INVALIDATE_SEC_MARKET_PROD message err, exception is", e);
        }
	}
	
	@Override
	public void compensateCancelMktProductAsync(Long mktProductId) {
//		RollbackInvalidateSecMarketProdReq request = new RollbackInvalidateSecMarketProdReq();
//		request.setProdId(mktProductId);
//        MessageWrapper msg = new MessageWrapper(MqTopic.ROLLBACK_INVALIDATE_SEC_MARKET_PROD, null, null, request);
//        try {
//            defaultRMQProducer.send(msg);
//        } catch (Exception e) {
//            logger.error("send ROLLBACK_INVALIDATE_SEC_MARKET_PROD message err, exception is", e);
//        }
	}

	@Override
	public List<Long> cancelMktProducts(Set<Long> mktProductIds) {
		InvalidateSecMarketProdBatchReq request = new InvalidateSecMarketProdBatchReq();
		request.setProdIds(new ArrayList<Long>(mktProductIds));
		InvalidateSecMarketProdBatchResp resp = ftcFacade.invalidateSecMarketProdBatch(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
		
		return resp.getFailedList();
	}

	@Override
	public void cancelMktProductsAsync(Set<Long> mktProductIds) {
		InvalidateSecMarketProdBatchReq request = new InvalidateSecMarketProdBatchReq();
		request.setProdIds(new ArrayList<Long>(mktProductIds));
		MessageWrapper msg = new MessageWrapper(MqTopic.INVALIDATE_SEC_MARKET_PROD_BATCH, null, null, request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send INVALIDATE_SEC_MARKET_PROD_BATCH message err, exception is", e);
        }
	}
	
	@Override
	public void freezeMktProductInventory(Long productId, BigDecimal amount, String frozenCode, Date curDate) {
		FreezeSecMarketProdInventoryReq request = new FreezeSecMarketProdInventoryReq();
		request.setAmount(amount);
		request.setProductId(productId);
		request.setToken(frozenCode);
		request.setHeadCount(1);
		request.setSysId(BizSysCode.FTC_SYSTEM);
		request.setFreezeDate(curDate);
		
		FreezeSecMarketProdInventoryResp resp = ftcFacade.freezeSecMarketProdInventory(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
	}

	@Override
	public void unfreezeMktProductInventory(String frozenCode) {
		UnfreezeSecMarketProdInventoryReq request = new UnfreezeSecMarketProdInventoryReq();
		request.setToken(frozenCode);
		
		UnfreezeSecMarketProdInventoryResp resp = ftcFacade.unfreezeSecMarketProdInventory(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
	}

	@Override
	public void unfreezeMktProductInventoryAsync(String frozenCode) {
		UnfreezeSecMarketProdInventoryReq request = new UnfreezeSecMarketProdInventoryReq();
		request.setToken(frozenCode);
        MessageWrapper msg = new MessageWrapper(MqTopic.UNFREEZE_SEC_MARKET_PROD_INVENTORY, null, frozenCode, request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send UNFREEZE_SEC_MARKET_PROD_INVENTORY message err, exception is", e);
        }
	}
	
	@Override
	public List<String> batchUnfreezeMktProductInventory(List<String> frozenCodeList) {
		UnfreezeSecMarketProdInventoryBatchReq request = new UnfreezeSecMarketProdInventoryBatchReq();
		request.setTokens(frozenCodeList);
		UnfreezeSecMarketProdInventoryBatchResp resp = ftcFacade.unfreezeSecMarketProdInventoryBatch(request);
		if (!resp.isSuccess() && (!FisResponseCode.PARTIAL_SUCCESS.equals(resp.getRespCode()))) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
		
		return resp.getFailedList();
	}

	@Override
	public void unfreezeAndDeductMktProductInventory(String frozenCode) {
		UnfreezeAndDeductionSecMarketProdInventoryReq request = new UnfreezeAndDeductionSecMarketProdInventoryReq();
		request.setToken(frozenCode);
		UnfreezeAndDeductionSecMarketProdInventoryResp resp = ftcFacade.unfreezeAndDeductionSecMarketProdInventory(request);
		if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
	}

	@Override
	public void unfreezeAndDeductMktProductInventoryForCompensate(String frozenCode) {
		CompensateSecMarketProdInventoryReq request = new CompensateSecMarketProdInventoryReq();
		request.setToken(frozenCode);
        MessageWrapper msg = new MessageWrapper(MqTopic.COMPENSATE_SEC_MARKET_PROD_INVENTORY, null, frozenCode, request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send COMPENSATE_SEC_MARKET_PROD_INVENTORY message err, exception is", e);
        }
	}

}
