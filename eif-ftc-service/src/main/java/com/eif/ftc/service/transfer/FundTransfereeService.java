package com.eif.ftc.service.transfer;

import com.eif.contract.facade.response.ftc.AssignorTransfereeContractResp;
import com.eif.contract.facade.response.ftc.InsertContractResp;
import com.eif.ftc.facade.fund.transfer.request.CreateTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.PayTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.ResumePayTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CreateTransfereeOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.PayTransfereeOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.ResumePayTransfereeOrderResponse;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.BatchReportTransEventResponse;
import com.eif.transcore.facade.response.CreateTransResponse;

/**
 * 受让交易服务
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransfereeService {

	/**
	 * 创建受让单
	 * @param request
	 * @param response
	 */
	void createTransfereeOrder(CreateTransfereeOrderRequest request, CreateTransfereeOrderResponse response);
	
	/**
	 * 受让单发起支付扣款
	 * @param request
	 * @param response
	 */
	void payTransfereeOrder(PayTransfereeOrderRequest request, PayTransfereeOrderResponse response);
	
	/**
	 * OTP支付
	 * @param request
	 * @param response
	 */
	void resumePayTransfereeOrder(ResumePayTransfereeOrderRequest request, ResumePayTransfereeOrderResponse response);

	/**
	 * 处理创建交易核心消息
	 * @param createTransResp
	 */
	void handleCreateTranscoreMessage(CreateTransResponse createTransResp);
	
	/**
	 * 处理交易核心状态消息
	 * @param transStatusInfo
	 */
	void handleTranscoreStatusMessage(MQTransInfoBean transStatusInfo);
	
	/**
	 * 处理退款结果消息
	 * @param refundResp
	 */
	void handleRefundResultMessage(CusTransResultRequest refundResp);
	
	/**
	 * 处理签合同结果
	 * @param signContractResp
	 */
	void handleSignContractResult(InsertContractResp signContractResp);
	
	/**
	 * 处理交易核心状态通知消息
	 * @param batchReportEventReuslt
	 */
	void handleTranscoreReportEventResult(BatchReportTransEventResponse batchReportEventReuslt);
	
	/**
	 * 处理签订转让协议通知消息
	 * @param signAgreementResp
	 */
	void handleSignTransferAgreementResult(AssignorTransfereeContractResp signAgreementResp);
	
}
