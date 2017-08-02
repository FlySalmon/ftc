package com.eif.ftc.service.transfer;

import java.util.List;

import com.eif.framework.pagination.pagehelper.Page;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.facade.fund.transfer.oper.request.GetFundTransfereeOrderByPageRequest;

/**
 * 受让单查询接口
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransfereeOperService {

	/**
	 * 根据受让业务单号查询受让单
	 * @param businessOrderItemNo
	 * @return
	 */
	FundTransfereeOrder getByBusinessOrderItemNo(String businessOrderItemNo);

	/**
	 * 根据受让交易单号查询受让单
	 * @param fundTransfereeOrderNo
	 * @return
	 */
	FundTransfereeOrder getByFundTransfereeOrderNo(String fundTransfereeOrderNo);

	/**
	 * 根据受让业务单号列表查询受让单列表
	 * @param businessOrderItemNoList
	 * @return
	 */
	List<FundTransfereeOrder> getByBusinessOrderItemNoList(List<String> businessOrderItemNoList);

	/**
	 * 根据受让交易单号列表号查询受让单列表
	 * @param fundTransfereeOrderNoList
	 * @return
	 */
	List<FundTransfereeOrder> getByFundTransfereeOrderNoList(List<String> fundTransfereeOrderNoList);

	/**
	 * 根据受让单的其他信息查询受让单
	 * @param memberNo
	 * @param mktProductId
	 * @param status
	 * @return
	 */
	List<FundTransfereeOrder> getByOrderBasicInfo(String memberNo, Long mktProductId, Integer status);
	
	/**
	 * 分页查询受让单
	 * @param pageRequest
	 * @return
	 */
	Page<FundTransfereeOrder> getByPage(GetFundTransfereeOrderByPageRequest pageRequest);
	
	/**
	 * 根据转让交易单号查找受让单
	 * @param refFundTransferorOrderNo
	 * @param status
	 * @return
	 */
	List<FundTransfereeOrder> getByRefFundTransferorOrderNo(String refFundTransferorOrderNo, Integer status);
	
}
