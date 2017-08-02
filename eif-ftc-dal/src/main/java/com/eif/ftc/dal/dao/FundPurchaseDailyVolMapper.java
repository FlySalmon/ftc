package com.eif.ftc.dal.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.ftc.dal.model.FundPurchaseDailyVol;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface FundPurchaseDailyVolMapper extends Mapper<FundPurchaseDailyVol> {

	/**
	 * 行锁避免并发性，更新基金交易量
	 * 
	 * @param amount：更新的基金交易金额
	 * @param limitation：基金交易限额
	 * @return
	 */
	int updateAmountForLimitation(@Param("amount") BigDecimal amount, @Param("productId") Long productId, @Param("limitation") BigDecimal limitation, @Param("dailyDate") Date dailyDate);

	/**
	 * 行锁避免并发性，更新基金交易份额
	 * 
	 * @param shares：更新的基金交易份额
	 * @param limitation：基金交易份额限额
	 * @return
	 */
	int updateSharesForLimitation(@Param("shares") BigDecimal shares, @Param("productId") Long productId, @Param("limitation") BigDecimal limitation, @Param("dailyDate") Date dailyDate);

	/**
	 * 扣减累积份额
	 *
	 * @param productId
	 * @param fundTransShares
     * @return
     */
	int deductShares(@Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("dailyDate") Date dailyDate);

	/**
	 * 扣减累积金额
	 *
	 * @param productId
	 * @param fundTransAmount
     * @return
     */
	int deductAmount(@Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("dailyDate") Date dailyDate);

	int insertShares(@Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("dailyDate") Date dailyDate);

	int insertAmount(@Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("dailyDate") Date dailyDate);
}