package com.eif.ftc.facade;

import com.eif.ftc.facade.fund.transfer.request.*;
import com.eif.ftc.facade.fund.transfer.response.*;

/**
 * 基金转让（二级市场）交易接口类
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransferFacade {
	
	/**
	 * @brief 用户资产交易规则校验
	 * @tag MTP使用
	 * @param request
	 * @return 
     */
	CheckAssetTransactionRuleResponse checkAssetTransactionRule(CheckAssetTransactionRuleRequest request);
	
	/**
	 * @brief 根据预期年化收益计算转让定价等
	 * @tag MTP使用
	 * @param request
	 * @return 定价、手续费等信息
	 */
	CalculateTransferPriceAndFeeResponse calculateTransferPriceAndFee(CalculateTransferPriceAndFeeRequest request);
	
	/**
	 * @brief 转让申请接口
	 * @tag MTP使用
	 * @param request
	 * @return 转让单单号信息
	 */
	CreateTransferorOrderResponse createTransferorOrder(CreateTransferorOrderRequest request);

	/**
	 * @brief 转让申请撤销接口
	 * @tag MTP使用
	 * @param request
	 * @return
	 */
	CancelTransferorOrderResponse cancelTransferorOrder(CancelTransferorOrderRequest request);
	
	/**
	 * @brief 受让单创建接口
	 * @tag MTP使用
	 * @param request
	 * @return 受让单单号信息
	 */
	CreateTransfereeOrderResponse createTransfereeOrder(CreateTransfereeOrderRequest request);

	/**
	 * @brief 受让单支付接口
	 * @tag MTP使用
	 * @param request
	 * @return 支付结果
	 */
	PayTransfereeOrderResponse payTransfereeOrder(PayTransfereeOrderRequest request);
	
	/**
	 * @brief 受让单OTP支付接口
	 * @tag MTP使用
	 * @param request
	 * @return 支付结果
	 */
	ResumePayTransfereeOrderResponse resumePayTransfereeOrder(ResumePayTransfereeOrderRequest request);
	
}
