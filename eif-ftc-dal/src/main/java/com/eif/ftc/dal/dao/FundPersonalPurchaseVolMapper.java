package com.eif.ftc.dal.dao;

import com.eif.ftc.dal.model.FundPersonalPurchaseVol;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;

public interface FundPersonalPurchaseVolMapper extends Mapper<FundPersonalPurchaseVol> {

    int updateAmountForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount, @Param("limitBuyAmt") BigDecimal limitBuyAmt);

    int updateSharesForLimitation(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares, @Param("limitBuyAmt") BigDecimal limitBuyAmt);

    int deductShares(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares);

    int deductAmount(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount);

    int insertAmount(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransAmount") BigDecimal fundTransAmount);

    int insertShares(@Param("memberNo") String memberNo, @Param("productId") Long productId, @Param("fundTransShares") BigDecimal fundTransShares);
}