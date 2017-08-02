package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundTransOrderStatusInfo;

import tk.mybatis.mapper.common.Mapper;

public interface FundTransOrderStatusInfoMapper extends Mapper<FundTransOrderStatusInfo> {
    //批量插入记录
    int batchInsertStatus(List<FundTransOrderStatusInfo> statusList);

}