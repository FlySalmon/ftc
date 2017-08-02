package com.eif.ftc.dal.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundPersonalPurchaseDailyVol;

import tk.mybatis.mapper.common.Mapper;

public interface FundPersonalPurchaseDailyVolMapper extends Mapper<FundPersonalPurchaseDailyVol> {
	/**
	* 行锁避免并发性，更新个人基金交易量
	* 
	* @param memberNo：会员号
	* @param fundTransAmount：更新的基金交易金额
	* @param perUdbLimitAmt：个人基金交易限额
	* @return
	*/

	int updateAmountForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("perUdbLimitAmt") BigDecimal perUdbLimitAmt, @Param("dailyDate") Date dailyDate);

	/**
	* 行锁避免并发性，更新个人基金交易量
	* 
	* @param memberNo：会员号
	* @param fundTransShares：更新的基金交易份额
	* @param perUdbLimitAmt：个人基金交易份额限额
	* @return
	*/
	int updateSharesForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("perUdbLimitAmt") BigDecimal perUdbLimitAmt, @Param("dailyDate") Date dailyDate);

	/**
	 * 扣减累积份额
	 *
	 * @param memberNo
	 * @param productId
	 * @param fundTransShares
     * @return
     */
	int deductShares(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("dailyDate") Date dailyDate);

	/**
	 * 扣减累积金额
	 *
	 * @param memberNo
	 * @param productId
	 * @param fundTransAmount
     * @return
     */
	int deductAmount(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("dailyDate") Date dailyDate);

	int insertAmount(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("dailyDate") Date dailyDate);

	int insertShares(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("dailyDate") Date dailyDate);
}