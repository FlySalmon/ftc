package com.eif.ftc.service.transfer.action;

import java.util.List;

import com.eif.contract.facade.response.ftc.InsertContractResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.transcore.facade.dto.bean.ReportTransEventResult;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;

public interface PayTransfereeOrderActionService {

	/**
	 * 校验用户风控状态
	 * @param transfereeOrder
	 * @param deviceInfo
	 * @param devId
	 * @param ip
	 */
	void checkUserRiskStatus(FundTransfereeOrder transfereeOrder,
			String deviceInfo, String devId, String ip);
	
	/**
	 * 获取并检验订单
	 * @param transfereeOrder
	 * @param transfereeOrderStatus
	 * @param transferorOrder
	 * @param transferorOrderStatus
	 * @param product
	 */
	void getAndCheckTransferOrders(FundTransfereeOrder transfereeOrder, int transfereeOrderStatus,
			FundTransferorOrder transferorOrder, int transferorOrderStatus, ProdInfo product);
	
	/**
	 * 校验是否有银行卡支付
	 * @param paymentOptions
	 * @return
	 */
	boolean checkDCPPaymentOption(List<TransactionPaymentOptionBean> paymentOptions);
	
	/**
	 * 支付预路由
	 * @param paymentOptions
	 * @param smsSendControl
	 * @return
	 */
	RoutePaymentProviderInfoResponse doPaymentRouting(
            List<TransactionPaymentOptionBean> paymentOptions, Integer smsSendControl,String memberNo);
	
	/**
	 * 直接支付受让单
	 * @param transfereeOrder
	 * @param paymentRoutingResp
	 * @param paymentOptions
	 * @param productName
	 * @param orderExpireTime
	 * @param extField
	 */
	void payTransfereeOrderWithoutOTP(FundTransfereeOrder transfereeOrder, 
    		RoutePaymentProviderInfoResponse paymentRoutingResp,
            List<TransactionPaymentOptionBean> paymentOptions, 
            String productName, Integer orderExpireTime, String extField);
	
	/**
	 * OTP支付受让单
	 * @param transfereeOrder
	 * @param paymentRoutingResp
	 * @param paymentOptions
	 * @param productName
	 * @param extField
	 * @return
	 */
	String payTransfereeOrderWithOTP(FundTransfereeOrder transfereeOrder, 
    		RoutePaymentProviderInfoResponse paymentRoutingResp,
            List<TransactionPaymentOptionBean> paymentOptions, 
            String productName, String extField);
	
	/**
	 * OTP验证支付受让单
	 * @param transfereeOrder
	 * @param productName
	 * @param orderExpireTime
	 * @param pin
	 */
	void resumePayTransfereeOrder(FundTransfereeOrder transfereeOrder,
			String productName, Integer orderExpireTime, String pin);
	
	/**
	 * 处理创建交易核心消息
	 * @param createTransResp
	 * @param transferorOrder
	 * @param transfereeOrder
	 */
	void handleCreateTranscoreMessage(CreateTransResponse createTransResp, 
			FundTransferorOrder transferorOrder, FundTransfereeOrder transfereeOrder);

	/**
	 * 处理交易核心状态消息
	 * @param transStatusInfo
	 * @param transfereeOrder
	 * @param product
	 */
	void handleTranscoreStatusMessage(MQTransInfoBean transStatusInfo,
			FundTransfereeOrder transfereeOrder, ProdInfo product);

	/**
	 * 处理退款结果消息
	 * @param refundResp
	 * @param transfereeOrder
	 */
	void handleRefundResultMessage(CusTransResultRequest refundResp,
			FundTransfereeOrder transfereeOrder);
	
	/**
	 * 处理交易核心状态通知消息
	 * @param reportEventReuslt
	 * @param transfereeOrder
	 */
	void handleTranscoreReportResult(ReportTransEventResult reportEventReuslt,
			FundTransfereeOrder transfereeOrder);
	
	/**
	 * 处理合同消息
	 * @param signContractResp
	 * @param transfereeOrder
	 */
	void handleSignContractResult(InsertContractResp signContractResp, FundTransfereeOrder transfereeOrder);
	
}
