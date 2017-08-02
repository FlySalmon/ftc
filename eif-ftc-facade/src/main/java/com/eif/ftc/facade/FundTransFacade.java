package com.eif.ftc.facade;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.fund.trans.request.*;
import com.eif.ftc.facade.fund.trans.response.CreateFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.PayFundBuyingOrderResponse;
import com.eif.ftc.facade.fund.trans.response.RedeemFundResponse;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;

/**
 * @tag FundTransFacade
 *
 */
public interface FundTransFacade {
	/**
	 * @brief 基金赎回(正常,当日)
	 * @param redeemFundRequest 基金赎回请求
	 * @return 基金业务结果
     */
	 RedeemFundResponse redeemFund(RedeemFundRequest redeemFundRequest);

	/**
	 * @brief 下基金业务单
	 * @tag 销售系统使用
	 * @param createFundBuyingOrderRequest 下基金业务单请求
	 * @return 结果
     */
	 CreateFundBuyingOrderResponse createFundBuyingOrder(CreateFundBuyingOrderRequest createFundBuyingOrderRequest);

	/**
	 * @brief 支付已有的基金交易单
	 * @tag 销售系统使用
	 * @param payFundBuyingOrderRequest 支付已下单的请求
	 * @return 结果
     */
	 PayFundBuyingOrderResponse payFundBuyingOrder(PayFundBuyingOrderRequest payFundBuyingOrderRequest);

	/**
	 * @brief 短信验证码支付
	 * @tag 销售系统使用
	 * @param request
	 * @return
     */
	 ResumePayResponse resumePay(ResumePayRequest request);

	/**
	 * @brief 购买限制判断
	 * @param commonBuyFundLimitJustifyRequest
	 * @return
     */
	 BaseResponse commonBuyFundLimitJustify(CommonBuyFundLimitJustifyRequest commonBuyFundLimitJustifyRequest);



}
