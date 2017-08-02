package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundTransferorOrderStatusInfo;
import tk.mybatis.mapper.common.Mapper;

public interface FundTransferorOrderStatusInfoMapper extends Mapper<FundTransferorOrderStatusInfo> {
	
	public int batchInsert(List<FundTransferorOrderStatusInfo> statusInfoList);
	
}