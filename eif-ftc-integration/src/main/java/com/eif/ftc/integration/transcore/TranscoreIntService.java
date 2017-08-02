package com.eif.ftc.integration.transcore;


import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.transcore.facade.dto.bean.*;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.request.ReportTransEventRequest;
import com.eif.transcore.facade.request.ResumePayTransResquest;
import com.eif.transcore.facade.request.RoutePaymentProviderInfoRequest;
import com.eif.transcore.facade.response.*;

import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
public interface TranscoreIntService {
    CreateTransResponse createTransCoreOrder(FundTransOrder transOrder, String transCode, List<TransactionPaymentOptionBean> paymentOptions, String transAttri,String productName);

    CreateTransResponse createTransCoreOrder(FundTransOrder transOrder, String transCode,
                                             List<TransactionPaymentOptionBean> paymentOptions,
                                             String transAttri, String extField,
                                             String refTransNo,
                                             RoutePaymentProviderInfoResponse paymentRoutingResp,String productName);

    void reportTranEvent(ReportTransEventRequest transEventReq);

    void closeTransaction(String transCoreOrderNo);

    List<TransactionDTO> getAuthPendingRechargeOrder(String fundTransOrderNo);

    public ResumePayTransResponse resumePayTrans(ResumePayTransResquest request);

    /**
     * 支付预路由
     *
     * @param request
     * @return
     */
    RoutePaymentProviderInfoResponse paymentRouting(RoutePaymentProviderInfoRequest request);

    void createTransAsync(FundTransOrder transOrder, String transCode,
                          List<TransactionPaymentOptionBean> paymentOptions,
                          String transAttri, String extField, String refTransNo,
                          RoutePaymentProviderInfoResponse paymentRoutingResp,String productName);

    void createTransAsync(FundTransOrder transOrder, String transCode,
                          List<TransactionPaymentOptionBean> paymentOptions, String transAttri,String productName);
    
    /**
     * 同步创建交易核心受让支付单
     * @param transfereeOrder
     * @param transCode
     * @param transAttri
     * @param paymentOptions
     * @param productName
     * @return
     */
    CreateTransResponse createTransCoreOrder(FundTransfereeOrder transfereeOrder, 
    		String transCode, String transAttri, 
    		List<TransactionPaymentOptionBean> paymentOptions,
            String productName, String extField, 
            RoutePaymentProviderInfoResponse paymentRoutingResp);
    
    /**
     * 异步创建交易核心交易单
     * @param createTransBean
     * @param smsStrategy
     * @param operatorNo
     */
    void createTranscoreOrderAsync(CreateTransDTO createTransBean, SmsStrategy smsStrategy, String operatorNo);
    
    /**
     * 生成交易核心受让交易信息
     * @param transfereeOrder
     * @param transCode
     * @param transAttri
     * @param paymentOptions
     * @param productName
     * @param extField
     * @param refTransNo
     * @param paymentRoutingResp
     * @return
     */
    CreateTransDTO buildTranscoreCreateTransDTO(FundTransfereeOrder transfereeOrder, 
    		String transCode, String transAttri, 
            List<TransactionPaymentOptionBean> paymentOptions,
            String productName, String extField, 
            String refTransNo, RoutePaymentProviderInfoResponse paymentRoutingResp);
    
    /**
     * 生成交易核心转让交易信息
     * @param transferorOrder
     * @param transCode
     * @param transAttri
     * @param paymentOptions
     * @param productName
     * @param extField
     * @return
     */
    CreateTransDTO buildTranscoreCreateTransDTO(FundTransferorOrder transferorOrder, 
    		String transCode, String transAttri,
    		List<TransactionPaymentOptionBean> paymentOptions,
            String productName, String extField);
    
    /**
     * 通知交易单状态
     * @param businessTransType
     * @param reportTransEventBeanList
     */
    void batchReportTransEventAsync(int businessTransType, List<ReportTransEventBean> reportTransEventBeanList);

}
