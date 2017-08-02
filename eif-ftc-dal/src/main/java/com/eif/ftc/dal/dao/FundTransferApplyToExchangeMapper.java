package com.eif.ftc.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundTransferApplyToExchange;
import tk.mybatis.mapper.common.Mapper;

public interface FundTransferApplyToExchangeMapper extends Mapper<FundTransferApplyToExchange> {

	/**
	 * 获取交易所产品对应的二级市场产品Id
	 * @param exchangeProdNo
	 * @return
	 */
	List<Long> queryMktProductIdsByExchangeProdNo(@Param("exchangeProdNo") String exchangeProdNo);
	
}