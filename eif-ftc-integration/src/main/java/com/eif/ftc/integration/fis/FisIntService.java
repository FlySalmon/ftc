package com.eif.ftc.integration.fis;

import com.eif.fis.facade.dto.ftc.InvestProperty;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdSummary;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.fis.facade.request.ftc.*;
import com.eif.fis.facade.response.common.QueryWorkingDayResp;
import com.eif.fis.facade.response.ftc.*;
import com.eif.fis.facade.response.job.QueryAvaliableCurrentProdResp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.integration.fis.bean.ProductNetWorthBean;
import com.eif.ftc.integration.fis.bean.QueryProductNetWorthBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FisIntService {
//    QueryProdTransInfoResp queryProdTransInfo(Long productID);
    List<String> unfreezeProdInventoryBatch(List<String> tokens);
    //TODO: mq provider
    void updateProdTransInfo(QueryProdTransInfoResp queryProdTransInfo);
    FreezeProdInventoryResp freezeProdInventory(FreezeProdInventoryReq freezeRequest);
    void unfreezeProdInventory(UnfreezeProdInventoryReq unfreezeProdInventoryReq);
    void deductionMonetaryInstrumentBalance(DeductionMonetaryInstrumentBalanceReq dreq);
    void deductionMonetaryInstrumentBalanceAsync(DeductionMonetaryInstrumentBalanceReq dreq);
    void unfreezeAndDeductionProdInventory(UnfreezeAndDeductionProdInventoryReq unFreezeRequest);
    void unfreezeAndDeductionProdInventoryBatch(UnfreezeAndDeductionProdInventoryBatchReq unfreezeAndDeductionProdInventoryBatchReq);
    QueryProdInfoByProdIdsResp queryProdInfoByProdIds(List<Long> productIdList);
    QueryAvaliableCurrentProdResp queryAvaliableCurrentProd();
    ProductNetWorthBean queryProductNetWorth(QueryProductNetWorthBean queryProductNetWorthBean);
    QueryWorkingDayResp queryWorkingDayIgnoreError(Date date, Integer n);
    QueryProdInfoByProdNameResp queryProdInfoByProdName(String prodName);
    ProdTaTransInfo queryProdTaTransInfo(Long productId);
    QueryProdInvenInfoResp queryProdInvenInfo(Long productId);
    void returnDeduction(String token,Long productId);
    void unfreezeProdInventoryAsync(FundTransOrder transOrder, UnfreezeProdInventoryReq unfreezeProdInventoryReq);
    void compensateProdInventory(String frozenCode);
    void compensateProdInventoryAsync(String frozenCode, String fundTransOrderNo);
    QueryProdTransInfoV2Resp queryProdTransInfoV2(Long productID);
    FreezeCurrentProdInventoryResp freezeCurrentProdInventory(FreezeCurrentProdInventoryReq freezeRequest);
    UnfreezeCurrentProdInventoryResp unfreezeCurrentProdInventory(UnfreezeCurrentProdInventoryReq req);
    void unfreezeCurrentProdInventoryAsync(FundTransOrder fundTransOrder,UnfreezeCurrentProdInventoryReq unfreezeProdInventoryReq);
    void unfreezeAndDeductCurrentProdInventory(UnfreezeAndDeductCurrentProdInventoryReq unFreezeRequest);
    void compensateCurrentProdInventory(String frozenCode);
    void compensateCurrentProdInventoryAsync(String frozenCode, String fundTransOrderNo);

    List<String> unfreezeCurrentProdInventoryBatch(List<String> tokens);
    
    /**
     * 获取产品通用信息
     * @param productId
     * @return
     */
    ProdInfo queryProductCommonInfo(Long productId);
    
    /**
     * 获取产品通用信息
     * @param productIds
     * @return
     */
    Map<Long, ProdInfo> queryProductCommonInfos(Set<Long> productIds);
    
    /**
     * 根据产品ID获取定期产品结构信息
     * @param productIds
     * @return
     */
    Map<Long, ProdSummary> getCloseProductMix(List<Long> productIds);

    /**
     * 根据产品ID获取定期产品结构信息
     * @param productId
     * @return
     */
    ProdSummary getCloseProductMix(Long productId);
    
    
    /*******************/
    /**** 二级市场接口 ****/
    /*******************/
    
    /**
     * 获取产品规则信息
     * @param productId
     * @return
     */
    SecMarketRule queryMktProductTransactionRule(Long productId);

    /**
     * 获取产品规则信息
     * @param productIds
     * @return
     */
    Map<Long, SecMarketRule> queryMktProductTransactionRule(Set<Long> productIds);
    
    /**
     * 创建二级市场产品
     * @param transferorOrderNo
     * @param productId
     * @param investPropertyList
     * @param orderExpiredTime
     * @param feeDiscount
     * @param origionalPrincipal
     * @return
     */
    Long createMktProduct(String transferorOrderNo, Long productId, 
    		List<InvestProperty> investPropertyList, 
    		Date orderExpiredTime, BigDecimal feeDiscount,
    		BigDecimal origionalPrincipal);
    
    /**
     * 异步创建二级市场产品
     * @param transferorOrderNo
     * @param productId
     * @param investPropertyList
     * @param orderExpiredTime
     * @param feeDiscount
     * @param origionalPrincipal
     */
    void createMktProductAsync(String transferorOrderNo, Long productId, 
    		List<InvestProperty> investPropertyList, 
    		Date orderExpiredTime, BigDecimal feeDiscount,
    		BigDecimal origionalPrincipal);
    
    /**
     * 撤销二级市场产品
     * @param mktProductId
     */
    void cancelMktProduct(Long mktProductId);

    /**
     * 异步撤销二级市场产品
     * @param mktProductId
     */
    void cancelMktProductAsync(Long mktProductId);

    /**
     * 回滚撤销二级市场产品
     * @param mktProductId
     */
    void compensateCancelMktProductAsync(Long mktProductId);
    
    /**
     * 批量撤销二级市场产品
     * @param mktProductIds
     * @return 
     */
    List<Long> cancelMktProducts(Set<Long> mktProductIds);

    /**
     * 异步批量撤销二级市场产品
     * @param mktProductIds
     */
    void cancelMktProductsAsync(Set<Long> mktProductIds);
    
    /**
     * 冻结二级市场产品份额
     * @param productId
     * @param amount
     * @param frozenCode
     * @param curDate
     */
    void freezeMktProductInventory(Long productId, BigDecimal amount, String frozenCode, Date curDate);

    /**
     * 解冻（取消）二级市场产品份额
     * @param frozenCode
     */
    void unfreezeMktProductInventory(String frozenCode);
    
    /**
     * 解冻（取消）二级市场产品份额 异步
     * @param frozenCode
     */
    void unfreezeMktProductInventoryAsync(String frozenCode);
    
    /**
     * 批量解冻（取消）二级市场产品份额
     * @param frozenCodeList
     * @return
     */
    List<String> batchUnfreezeMktProductInventory(List<String> frozenCodeList);
    
    /**
     * 解冻并扣减二级市场产品份额
     * @param frozenCode
     */
    void unfreezeAndDeductMktProductInventory(String frozenCode);
    
    /**
     * 解冻并扣减二级市场产品份额 超时补偿
     * @param frozenCode
     */
    void unfreezeAndDeductMktProductInventoryForCompensate(String frozenCode);
    
}
