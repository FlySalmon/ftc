package com.eif.ftc.dal.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.ftc.dal.model.FundPersonalRedeemDailyVol;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface FundPersonalRedeemDailyVolMapper extends Mapper<FundPersonalRedeemDailyVol> {

	/**
	 * 行锁避免并发，更新会员单日赎回金额
	 * 
	 * @param memberNo
	 * @param amount
	 * @param perUdsLimitAmt
	 * @return
	 */
	int updateAmountForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("amount") BigDecimal amount, @Param("perUdsLimitAmt") BigDecimal perUdsLimitAmt, @Param("dailyDate") Date dailyDate);

	/**
	 * 行锁避免并发，更新会员单日赎回份额
	 *
	 * @param memberNo
	 * @param productId
	 * @param shares
	 * @param perUdsLimitAmt
     * @return
     */
	int updateSharesForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("shares") BigDecimal shares, @Param("perUdsLimitAmt") BigDecimal perUdsLimitAmt, @Param("dailyDate") Date dailyDate);

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

	int insertShares(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("dailyDate") Date dailyDate);

	int insertAmount(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("dailyDate") Date dailyDate);
}