package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundDividendOrder;
import com.eif.ftc.dal.model.FundDividendOrderQueryModel;

import tk.mybatis.mapper.common.Mapper;

public interface FundDividendOrderQueryMapper extends Mapper<FundDividendOrder> {
   
	/**
	 * 根据条件查询订单
	 * @param queryModel
	 * @return
	 */
	List<FundDividendOrder> queryOrder(FundDividendOrderQueryModel queryModel);
	
}