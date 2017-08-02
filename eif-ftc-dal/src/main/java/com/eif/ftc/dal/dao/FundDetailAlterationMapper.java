package com.eif.ftc.dal.dao;

import java.util.ArrayList;
import java.util.List;

import com.eif.ftc.dal.bean.*;
import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundDetailAlteration;

import tk.mybatis.mapper.common.Mapper;

public interface FundDetailAlterationMapper extends Mapper<FundDetailAlteration> {
    FundDetailAlteration selectByFTCOrderNo(@Param("ftcOrderNo") String ftcOrderNo, @Param("status") Integer status);

    List<MemberAssetUpdateBean> selectMemberAssetByFTCOrderNoList(@Param("list") List<String> ftcOrderNos,@Param("status") Integer status);
    List<FundTotalUpdateBean> selectFundTotalByFTCOrderNoList(@Param("list") List<String> ftcOrderNos,@Param("status") Integer status);
    List<FundDetailUpdateBean> selectFundDetailByFTCOrderNoList(@Param("list") List<String> ftcOrderNos,@Param("status") Integer status);
    int updateFundDetailAlternationStatus(@Param("list") List<String> ftcOrderNos,@Param("status") Integer status);
    List<FundDetailGroupOnBean> selectGroupOnAndDetailMapper(@Param("list")List<String> ftcOrderNos);
    List<ConvertUUIDBean> mapFtcOrderToDetailUUID(@Param("list")List<String> ftcOrderNos);

    List<ConvertUUIDBean> mapFtcOrderToFundTotalUUID(@Param("list")List<String> ftcOrderNos);

    List<ConvertUUIDBean> mapFtcOrderToMemberAssetUUID(@Param("list")List<String> ftcOrderNos);

}