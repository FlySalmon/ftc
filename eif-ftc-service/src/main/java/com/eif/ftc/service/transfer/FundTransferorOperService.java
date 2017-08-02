package com.eif.ftc.service.transfer;

import java.util.List;

import com.eif.framework.pagination.pagehelper.Page;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.fund.transfer.oper.request.GetFundTransferorOrderByPageRequest;

/**
 * 转让单查询接口
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransferorOperService {

	/**
	 * 根据转让业务单号查询转让单
	 * @param businessOrderItemNo
	 * @return
	 */
	FundTransferorOrder getByBusinessOrderItemNo(String businessOrderItemNo);

	/**
	 * 根据转让交易单号查询转让单
	 * @param fundTransferorOrderNo
	 * @return
	 */
	FundTransferorOrder getByFundTransferorOrderNo(String fundTransferorOrderNo);
	
	/**
	 * 根据转让业务单号列表查询转让单列表
	 * @param businessOrderItemNoList
	 * @return
	 */
	List<FundTransferorOrder> getByBusinessOrderItemNoList(List<String> businessOrderItemNoList);

	/**
	 * 根据转让交易单号列表号查询转让单列表
	 * @param fundTransferorOrderNo
	 * @return
	 */
	List<FundTransferorOrder> getByFundTransferorOrderNoList(List<String> fundTransferorOrderNoList);

	/**
	 * 根据转让单的其他信息查询转让单
	 * @param memberNo
	 * @param productIds
	 * @param mktProductIds
	 * @param status
	 * @return
	 */
	List<FundTransferorOrder> getByOrderBasicInfo(String memberNo, 
			List<Long> productIds, List<Long> mktProductIds, Integer status);
	
	/**
	 * 分页获取转让单
	 * @param pageRequest
	 * @return
	 */
	Page<FundTransferorOrder> getByPage(GetFundTransferorOrderByPageRequest pageRequest);
	
	/**
	 * 根据受让交易单号查找转让单
	 * @param refFundTransfereeOrderNo
	 * @return
	 */
	FundTransferorOrder getByRefFundTransfereeOrderNo(String refFundTransfereeOrderNo);
	
}
