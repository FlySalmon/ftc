package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundTransOrderLiveStatus;

import tk.mybatis.mapper.common.Mapper;

public interface FundTransOrderLiveStatusMapper extends Mapper<FundTransOrderLiveStatus> {
    
	/**
	 * 批量更插入记录
	 * @param statusList
	 * @return
	 */
	int batchInsertStatus(List<FundTransOrderLiveStatus> statusList);
	
	/**
	 * 批量更新记录
	 * @param statusList
	 * @return
	 */
	int batchUpdateStatus(List<FundTransOrderLiveStatus> statusList);
	
	/**
	 * 根据业务交易单号查找状态信息
	 * @param transOrderNoList
	 * @return
	 */
	List<FundTransOrderLiveStatus> selectByTransOrderNos(List<String> transOrderNoList);
	
}