package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundTransfereeOrderStatusInfo;

import tk.mybatis.mapper.common.Mapper;

public interface FundTransfereeOrderStatusInfoMapper extends Mapper<FundTransfereeOrderStatusInfo> {

	public int batchInsert(List<FundTransfereeOrderStatusInfo> statusInfoList);
	
}