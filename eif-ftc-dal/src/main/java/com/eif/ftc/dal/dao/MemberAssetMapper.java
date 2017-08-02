package com.eif.ftc.dal.dao;

import com.eif.ftc.dal.bean.FundUpdateBean;
import com.eif.ftc.dal.bean.MemberAssetUpdateBean;
import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.MemberAsset;

import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MemberAssetMapper extends Mapper<MemberAsset> {
    MemberAsset selectByMemberNoAndChannel(@Param("memberNo") String memberNo, @Param("channel") Integer channel);
    MemberAsset selectByMemberAssetUUID(@Param("memberAssetUUID") String memberAssetUUID);
    MemberAsset selectForUpdateByMemberNoAndChannel(@Param("memberNo") String memberNo, @Param("channel") Integer channel);
    MemberAsset selectByMemberAssetUUIDForUpdate(@Param("memberAssetUUID") String memberAssetUUID);

    int updateMemberAssetBySubscriptionConfirm(@Param("memberAssetUpdateBeanList") List<MemberAssetUpdateBean> memberAssetUpdateBeanList);
    int updateMemberAssetByPurchaseConfirm(@Param("memberAssetUpdateBeanList") List<MemberAssetUpdateBean> memberAssetUpdateBeanList);
    int updateMemberAssetByRedemptionConfirm(@Param("memberAssetUpdateBeanList") List<MemberAssetUpdateBean> memberAssetUpdateBeanList);

    int updateMemberAssetByRedemption(@Param("redemptionRecord") List<MemberAssetUpdateBean> redemptionRecord);

    int updateMemberAssetByHalfOpenRedeemConfirm(@Param("fundUpdateBeanList")List<FundUpdateBean> fundUpdateBeen);

}