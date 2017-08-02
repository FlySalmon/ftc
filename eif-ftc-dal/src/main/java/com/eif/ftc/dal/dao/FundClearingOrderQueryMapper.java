package com.eif.ftc.dal.dao;

import java.util.List;

import com.eif.ftc.dal.model.FundClearingOrder;
import com.eif.ftc.dal.model.FundClearingOrderQueryModel;

import tk.mybatis.mapper.common.Mapper;

public interface FundClearingOrderQueryMapper extends Mapper<FundClearingOrder> {
   
	/**
	 * 根据条件查询订单
	 * @param queryModel
	 * @return
	 */
	List<FundClearingOrder> queryOrder(FundClearingOrderQueryModel queryModel);
	
	/**
	 * 根据业务订单号查询清盘兑付单
	 * @param fundClearingOrderNos
	 * @return
	 */
	List<FundClearingOrder> queryByFundClearingOrderNos(List<String> fundClearingOrderNos);
	
}