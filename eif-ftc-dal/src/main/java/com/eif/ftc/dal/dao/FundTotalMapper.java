package com.eif.ftc.dal.dao;

import com.eif.ftc.dal.bean.FundTotalUpdateBean;
import com.eif.ftc.dal.bean.FundUpdateBean;
import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundTotal;

import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FundTotalMapper extends Mapper<FundTotal> {
	FundTotal selectByFundAccountNo(@Param("fundAccountNo") String fundAccountNo);
	FundTotal selectByFundTotalUUID(@Param("fundTotalUUID") String fundTotalUUID);
	FundTotal selectInfoByFundAccountNo(@Param("fundAccountNo") String fundAccountNo);
	FundTotal selectInfoByFundTotalUUID(@Param("fundTotalUUID") String fundTotalUUID);

	int updateFundTotalBySubscriptionConfirm(@Param("fundTotalUpdateBeanList") List<FundTotalUpdateBean> fundTotalUpdateBeanList);
	int updateFundTotalByPurchaseConfirm(@Param("fundTotalUpdateBeanList") List<FundTotalUpdateBean> fundTotalUpdateBeanList);
	int updateFundTotalByRedemptionConfirm(@Param("fundTotalUpdateBeanList") List<FundTotalUpdateBean> fundTotalUpdateBeanList);

	int updateFundTotalByRedemption(@Param("redemptionRecord") List<FundTotalUpdateBean> redemptionRecord);

	int updateFundTotalByHalfOpenRedeemConfirm(@Param("fundUpdateBeanList")List<FundUpdateBean> fundUpdateBeen);
}