package com.eif.ftc.dal.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.FundTransferorOrder;
import tk.mybatis.mapper.common.Mapper;

public interface FundTransferorOrderMapper extends Mapper<FundTransferorOrder> {

	/**
	 * 获取取消的订单信息
	 * @param memberNos
	 * @param cancelTime
	 * @param status
	 * @return
	 */
	public List<FundTransferorOrder> queryCanceledOrders(
			@Param("memberNos")List<String> memberNos, //会员号列表
			@Param("cancelTime")Date cancelTime, 	   //取消时间
			@Param("status")Integer status);		   //订单状态
	
	/**
	 * 根据受让单号获取关联转让单
	 * @param transfereeOrderNo
	 * @return
	 */
	public FundTransferorOrder queryByRefFundTransfereeOrderNo(
			@Param("transfereeOrderNo") String transfereeOrderNo);
	
}