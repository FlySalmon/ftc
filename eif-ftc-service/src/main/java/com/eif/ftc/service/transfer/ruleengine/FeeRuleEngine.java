package com.eif.ftc.service.transfer.ruleengine;

import java.math.BigDecimal;

import com.eif.fis.facade.constant.ChargeBase;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;

/**
 * 二级市场手续费规则引擎
 * 
 * @author jiangweifeng
 *
 */
public class FeeRuleEngine implements TransferRuleEngine {

	/**
	 * 计算手续费
	 * @param principalAmount	本金金额
	 * @param tradeAmount		交易金额
	 * @param feeRule
	 * @return
	 */
	public static BigDecimal calculateTranferFee(BigDecimal principalAmount, BigDecimal tradeAmount, 
			SecMarketChargeRule feeRule) {
		BigDecimal fee = BigDecimal.ZERO;
		
		// 固定金额收费
		if (feeRule.getChargeBase() == ChargeBase.fixed_amount) {
			if (feeRule.getChargeAmt() != null && feeRule.getChargeAmt().compareTo(BigDecimal.ZERO) > 0) {
				fee = feeRule.getChargeAmt();
				return fee;
			} else {
				throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_RULE_NOT_FUND.getMessage(),
						FTCRespCode.ERR_FUND_TRANSFER_RULE_NOT_FUND.getCode());
			}
		}
		
		// 非固定金额收费
		if (feeRule.getChargeBase() == ChargeBase.investment_amount) {
			fee = principalAmount.multiply(feeRule.getChargeRate());
		} else {
			fee = tradeAmount.multiply(feeRule.getChargeRate());
		}
		
		// 费率优惠
		if (feeRule.getDiscountRate() != null) {// && feeRule.getDiscountRate().compareTo(BigDecimal.ZERO) > 0) {
			fee = fee.multiply(feeRule.getDiscountRate());
		}
		fee = fee.setScale(MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);//截断处理
		
		// 最低收费金额
		if (feeRule.getIsMinCharge()) {
			if (fee.compareTo(feeRule.getMinChargeAmt()) < 0) {
				fee = feeRule.getMinChargeAmt();
			}
		}
		
		// 最高收费金额
		if (feeRule.getIsMaxCharge()) {
			if (fee.compareTo(feeRule.getMaxChargeAmt()) > 0) {
				fee = feeRule.getMaxChargeAmt();
			}
		}
		
		return fee;
	}

}
