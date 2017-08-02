package com.eif.ftc.dal.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundAcctOrder;

import tk.mybatis.mapper.common.Mapper;

public interface FundAcctOrderMapper extends Mapper<FundAcctOrder> {
	
	//查询开户单
	List<FundAcctOrder> queryTAFundCreateAcctOrderBetweenDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") int status);
	
	//更新TA信息
	int batchUpdateTAInfo(List<FundAcctOrder> orderList);
	
//	//更新开户单状态
//	int batchUpdateTAStatus(List<FundAcctOrder> orderList);
	
	//根据TA申请单号获取开户单信息
	List<FundAcctOrder> selectByTaSerialNo(List<String> taSerialNoList);

}