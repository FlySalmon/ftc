package com.eif.ftc.service.transfer.ruleengine;

import java.util.Date;

import com.eif.fis.facade.constant.MarketLevel;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketTransRule;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.util.exception.BusinessException;
import com.github.knightliao.apollo.utils.time.DateUtils;

/**
 * 二级市场交易规则引擎
 * 
 * @author jiangweifeng
 *
 */
public class TransactionRuleEngine implements TransferRuleEngine {

	/**
	 * 校验产品是否符合交易规则
	 * @param transRule
	 * @param product
	 */
	public static void isProductShareCanBeTransferred(SecMarketTransRule transRule, ProdInfo product) {
		if (!product.isSupportTransfer()) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_PRODUCT_CANNOT_TRANSFER.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_PRODUCT_CANNOT_TRANSFER.getCode());
		}
		
		Date curDateTime = new Date();
		Date curDate = DateUtils.getRoundedDay(curDateTime);
		// 产品是否成立
		if (product.getInceptionDate().after(curDate)) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH.getCode());
		}
		// 有效期是否在到期日之前
		Date expiredDateTime = new Date(curDateTime.getTime() + transRule.getTransValidTime() * 60 * 60 * 1000l);
		Date expiredDate = DateUtils.getRoundedDay(expiredDateTime);
		Date dueDate = DateUtils.getRoundedDay(product.getDueDate());
		if (!dueDate.after(expiredDate)) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_EXPIRED_DATE.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_EXPIRED_DATE.getCode());
		}
		
		// 持有天数校验
		int minHoldDays = 0;
		if (product.getMarketLevel() == MarketLevel.FIRST) {
			minHoldDays = transRule.getFirstTransHoldDay();
		} else {
			minHoldDays = transRule.getNextTransHoldDay();
		}
		long holdDays = DateUtils.getDaysBetweenIgnoreTime(product.getInceptionDate(), curDateTime) - 1; 	//持有天数，不包括当天
		if (holdDays < minHoldDays) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH.getCode());
		}
		
		// 到期天数校验
		long maturityDays = DateUtils.getDaysBetweenIgnoreTime(curDateTime, product.getDueDate()) - 2;		//离到期天数(间隔天数)
		if (maturityDays < transRule.getDaysBeforeDueDate()) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_EXPIRED_NOT_ENOUGH.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_EXPIRED_NOT_ENOUGH.getCode());
		}
	}

	/**
	 * 获取订单有效期时间
	 * @param curDate
	 * @param expiredTime
	 * @param dueDate
	 * @param daysBeforeDueDate
	 * @return
	 */
	public static Date getExpiryTime(Date curDate, Date expiredTime, Date dueDate, int daysBeforeDueDate) {
		Date expiryTime = expiredTime;
		
		//离到期天数(间隔天数)
		daysBeforeDueDate += 1;
		Date lastValidDate = DateUtils.addDays(dueDate, -daysBeforeDueDate);
		Date lastValidDateTime = DateUtils.parseDate(
				DateUtils.format(lastValidDate, "yyyyMMdd") + "235959");
		if (expiredTime.after(lastValidDate)) {
			expiryTime = lastValidDateTime;
		}
		
		return expiryTime;
	}
	
//	public static void main(String[] args) {
//		Date dt1 = DateUtils.parseDate("20161220010203");
//		Date dt2 = DateUtils.parseDate("20161223010203");
//		Date dt3 = DateUtils.parseDate("20161230000203");
//		System.out.println(TransactionRuleEngine.getExpiryTime(dt1, dt2, dt3, 7));
//		System.out.println(DateUtils.getDaysBetweenIgnoreTime(dt1, dt2));
//	}
	
}
