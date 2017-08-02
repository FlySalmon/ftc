package com.eif.ftc.service.transfer;

import java.util.Date;

import com.eif.contract.facade.response.ftc.AssignorTransferorContractResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.response.ftc.CreateSecMarketProdResp;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.fund.transfer.request.CalculateTransferPriceAndFeeRequest;
import com.eif.ftc.facade.fund.transfer.request.CancelTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.CheckAssetTransactionRuleRequest;
import com.eif.ftc.facade.fund.transfer.request.CreateTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CalculateTransferPriceAndFeeResponse;
import com.eif.ftc.facade.fund.transfer.response.CreateTransferorOrderResponse;
import com.eif.ftc.service.bean.FundTransferAmountBean;
import com.eif.risk.facade.bean.UserBlockingBean;

/**
 * 转让交易服务
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransferorService {

	/**
	 * 用户资产交易规则校验
	 * @param request
	 * @param response
	 */
	void checkAssetTransactionRule(CheckAssetTransactionRuleRequest request);
	
	/**
	 * 转让定价计算
	 * @param request
	 * @param response
	 */
	void calculateTransferPriceAndFee(CalculateTransferPriceAndFeeRequest request,
			CalculateTransferPriceAndFeeResponse response);
	
	/**
	 * 创建转让单，发起转让申请
	 * @param request
	 * @param response
	 */
	void createTransferorOrder(CreateTransferorOrderRequest request, CreateTransferorOrderResponse response);
	
	/**
	 * 撤销转让申请
	 * @param request
	 * @param response
	 */
	void cancelTransferorOrder(CancelTransferorOrderRequest request);
	
	/**
	 * 风控锁定用户、加黑名单消息处理
	 */
	void handleRiskLockUserMessage(UserBlockingBean blockingBean);
	
	/**
	 * 根据订单信息计算转让费用信息
	 * @tag 供内部调用
	 * @param transferorOrder
	 * @param product
	 * @param valueDate
	 * @return
	 */
	FundTransferAmountBean calculateFundTransferAmountInfo(FundTransferorOrder transferorOrder, 
			ProdInfo product, Date valueDate);
	
	/**
	 * 处理创建二级市场产品结果
	 * @param createMktProductResp
	 */
	void handleCreateMktProductResult(CreateSecMarketProdResp createMktProductResp);

	/**
	 * 处理签订二级市场转让协议结果
	 * @param signAgreementResp
	 */
	void handleSignTransferorAgreementResult(AssignorTransferorContractResp signAgreementResp);
	
}
