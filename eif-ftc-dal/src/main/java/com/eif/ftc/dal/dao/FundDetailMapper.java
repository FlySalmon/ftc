package com.eif.ftc.dal.dao;

import java.util.ArrayList;
import java.util.List;

import com.eif.ftc.dal.bean.*;

import org.apache.ibatis.annotations.Param;
import com.eif.ftc.dal.model.FundDetail;
import tk.mybatis.mapper.common.Mapper;

public interface FundDetailMapper extends Mapper<FundDetail> {
    FundDetail selectByTotalUUIDAndProductId(@Param("totalUUID") String totalUUID, @Param("productId") Long productId);
    ArrayList<FundDetail> selectFundListByTotalUUID(@Param("totalUUID") String totalUUID);
    FundDetail selectByFundDetailUUID(@Param("fundDetailUUID") String fundDetailUUID);
    FundAssetBean selectFundAssetInfoByProductId(@Param("productId") Long productId);
    List<ProductMemberAssetBean> selectMemberFundAssetByProductId(@Param("productId") Long productId);
    FundDetail selectByTotalUUIDAndProductIdForUpdate(@Param("totalUUID") String totalUUID, @Param("productId") Long productId);
    FundDetail selectInfoByFundDetailUUID(@Param("fundDetailUUID") String fundDetailUUID);


    int updateFundDetailBySubscriptionConfirm(@Param("fundDetailUpdateBeanList") List<FundDetailUpdateBean> fundDetailUpdateBeanList);
    int updateFundDetailByPurchaseConfirm(@Param("fundDetailUpdateBeanList") List<FundDetailUpdateBean> fundDetailUpdateBeanList);
    int updateFundDetailByRedemptionConfirm(@Param("fundDetailUpdateBeanList") List<FundDetailUpdateBean> fundDetailUpdateBeanList);
    List<FundDetailNeedSendMsg> selectFundDetailNeedSendSMSByUUIDList(@Param("fundDetailUpdateBeanList") List<FundDetailUpdateBean> fundDetailUpdateBeanList);

    //05文件在用
    List<MemberCurrentAssetBean> selectAssetByTransAccountAndProductIds(@Param("productIds") List<Long> productIds,@Param("transAccountNos") List<String> transAccoutnNos);

    //用于计算红利清分分配
    List<MemberProductAssetForDividend> selectAssetDividendByTransAccountList(@Param("transAccountNos") List<String> transAccoutNos);

    //活包定赎回确认
    int updateFundDetailByHalfOpenRedeemConfirm(@Param("fundUpdateBeanList")List<FundUpdateBean> fundUpdateBeen);


    List<ProductMemberAssetBean> selectMemberFundAssetBySecMarketProductIds(@Param("productIds") List<Long> productIds);

}