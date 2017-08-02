package com.eif.ftc.facade;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.fund.transfer.dto.FundTransfereeOrderBean;
import com.eif.ftc.facade.fund.transfer.dto.FundTransferorOrderBean;
import com.eif.ftc.facade.fund.transfer.oper.request.*;
import com.eif.ftc.facade.fund.transfer.oper.response.*;

/**
 * 基金转让（二级市场）订单查询接口类
 * 
 * @author jiangweifeng
 *
 */
public interface FundTransferOperFacade {

	/**
	 * @brief 查询转让单
	 * @tag MTP、运营平台等使用
	 * @param request
	 * @return
	 */
	GetFundTransferorOrderResponse getFundTransferorOrder(GetFundTransferorOrderRequest request);

	/**
	 * @brief 查询转让单列表
	 * @tag MTP、运营平台等使用
	 * @param request
	 * @return
	 */
	GetFundTransferorOrderListResponse getFundTransferorOrderList(GetFundTransferorOrderListRequest request);
	
	/**
	 * @brief 分页查询转让单
	 * @tag 运营平台等使用
	 * @param request
	 * @return
	 */
	PageableResponse<FundTransferorOrderBean> getFundTransferorOrderByPage(GetFundTransferorOrderByPageRequest request);

	/**
	 * @brief 根据受让交易单号获取关联转让订单
	 * @tag 运营平台等使用
	 * @param request
	 * @return
	 */
	GetRelatedTransferorOrderResponse getRelatedTransferorOrder(GetRelatedTransferorOrderRequest request);

	/**
	 * @brief 根据转让交易单号获取关联受让订单
	 * @tag 运营平台等使用
	 * @param request
	 * @return
	 */
	GetRelatedTransfereeOrderResponse getRelatedTransfereeOrder(GetRelatedTransfereeOrderRequest request);
	
	/**
	 * @brief 查询受让单
	 * @tag MTP、运营平台等使用
	 * @param request
	 * @return
	 */
	GetFundTransfereeOrderResponse getFundTransfereeOrder(GetFundTransfereeOrderRequest request);

	/**
	 * @brief 查询受让单列表
	 * @tag MTP、运营平台等使用
	 * @param request
	 * @return
	 */
	GetFundTransfereeOrderListResponse getFundTransfereeOrderList(GetFundTransfereeOrderListRequest request);
	
	/**
	 * @brief 分页查询受让单
	 * @tag 运营平台等使用
	 * @param request
	 * @return
	 */
	PageableResponse<FundTransfereeOrderBean> getFundTransfereeOrderByPage(GetFundTransfereeOrderByPageRequest request);
	
	/**
	 * @brief 原始转让单查询接口
	 * @tag MTP、运营平台等使用
	 * @param request
	 * @return
	 */
	GetOriginalTransferorOrderResponse getOriginalTransferorOrder(GetOriginalTransferorOrderRequest request);
	
}
