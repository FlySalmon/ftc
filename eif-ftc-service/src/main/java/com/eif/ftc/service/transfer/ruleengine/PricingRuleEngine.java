package com.eif.ftc.service.transfer.ruleengine;

import java.math.BigDecimal;

import com.eif.fis.facade.dto.ftc.SecMarketPricingRule;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.util.exception.BusinessException;

/**
 * 二级市场定价规则引擎
 * 
 * @author jiangweifeng
 *
 */
public class PricingRuleEngine implements TransferRuleEngine {

	/**
	 * 校验产品定价
	 * @param pricingRule
	 * @param productRate
	 * @param expectRate
	 */
	public static void isPriceAndRateValid(SecMarketPricingRule pricingRule, 
			BigDecimal productRate, BigDecimal expectRate) {
		// 最低成交收益率
//		BigDecimal minDealRate = productRate.multiply(pricingRule.getMinTransPriceRatio());
//		minDealRate = minDealRate.setScale(MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
		if (expectRate.compareTo(pricingRule.getMinTransRate()) < 0) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_RATE_TOO_LOW.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_RATE_TOO_LOW.getCode());
		}
		
		// 最高成交收益率
//		BigDecimal maxDealRate = productRate.multiply(pricingRule.getMaxTransPriceRatio());
//		maxDealRate = maxDealRate.setScale(MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
		if (expectRate.compareTo(pricingRule.getMaxTransRate()) > 0) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_RATE_TOO_HIGH.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_RATE_TOO_HIGH.getCode());
		}
	}
	
}
