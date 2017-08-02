package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundAcctOrderStatusInfo;

import tk.mybatis.mapper.common.Mapper;

public interface FundAcctOrderStatusInfoMapper extends Mapper<FundAcctOrderStatusInfo> {
    //批量插入记录
    int batchInsertStatus(List<FundAcctOrderStatusInfo> statusList);

}