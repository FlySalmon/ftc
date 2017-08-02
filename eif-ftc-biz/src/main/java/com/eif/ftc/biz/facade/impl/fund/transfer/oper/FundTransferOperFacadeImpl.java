package com.eif.ftc.biz.facade.impl.fund.transfer.oper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.framework.pagination.pagehelper.Page;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.FundTransferOperFacade;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.transfer.dto.*;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.oper.request.*;
import com.eif.ftc.facade.fund.transfer.oper.response.*;
import com.eif.ftc.service.transfer.FundTransfereeOperService;
import com.eif.ftc.service.transfer.FundTransferorOperService;

import ma.glasnost.orika.MapperFacade;
import tk.mybatis.mapper.util.StringUtil;

public class FundTransferOperFacadeImpl implements FundTransferOperFacade {
	
    @Autowired
	private FundTransfereeOperService fundTransfereeOperService;

    @Autowired
	private FundTransferorOperService fundTransferorOperService;

    @Autowired
    private MapperFacade mapperFacade;
    
	@Override
	public GetFundTransferorOrderResponse getFundTransferorOrder(GetFundTransferorOrderRequest request) {
		GetFundTransferorOrderResponse response = new GetFundTransferorOrderResponse();
		
		// 参数校验
		if (StringUtil.isEmpty(request.getBusinessOrderItemNo()) &&
				StringUtil.isEmpty(request.getFundTransferorOrderNo())) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		//获取转让单
		FundTransferorOrder order = null;
		if (!StringUtil.isEmpty(request.getBusinessOrderItemNo())) {
			order = fundTransferorOperService.getByBusinessOrderItemNo(request.getBusinessOrderItemNo());
		} else {
			order = fundTransferorOperService.getByFundTransferorOrderNo(request.getFundTransferorOrderNo());
		}
		
		if (order != null) {
			FundTransferorOrderBean fundTransferorOrderBean = new FundTransferorOrderBean();
			mapperFacade.map(order, fundTransferorOrderBean);
			response.setFundTransferorOrderBean(fundTransferorOrderBean);

			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} else {
			response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage());
		}

		return response;
	}
	
	@Override
	public GetFundTransferorOrderListResponse getFundTransferorOrderList(GetFundTransferorOrderListRequest request) {
		GetFundTransferorOrderListResponse response = new GetFundTransferorOrderListResponse();
		
		// 参数校验
		if (!checkFundTransferorOrderListParams(request)) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		//查询转让单
		List<FundTransferorOrder> orderList = null;
		if (!CollectionUtils.isEmpty(request.getBusinessOrderItemNoList())) {
			orderList = fundTransferorOperService.getByBusinessOrderItemNoList(
					request.getBusinessOrderItemNoList());
		} else if (!CollectionUtils.isEmpty(request.getFundTransferorOrderNoList())) {
			orderList = fundTransferorOperService.getByFundTransferorOrderNoList(
					request.getFundTransferorOrderNoList());
		} else {
			List<Long> productIds = new ArrayList<Long>();
			List<Long> mktProductIds = new ArrayList<Long>();
			if (!CollectionUtils.isEmpty(request.getProductIds())) {
				productIds.addAll(request.getProductIds());
			} else if (request.getProductId() != null) {
				productIds.add(request.getProductId());
			}
			if (!CollectionUtils.isEmpty(request.getMktProductIds())) {
				mktProductIds.addAll(request.getMktProductIds());
			} else if (request.getMktProductId() != null) {
				mktProductIds.add(request.getMktProductId());
			}
			
			orderList = fundTransferorOperService.getByOrderBasicInfo(
					request.getMemberNo(), productIds, 
					mktProductIds, request.getStatus());
		}
		
		if (!CollectionUtils.isEmpty(orderList)) {
			List<FundTransferorOrderBean> orderBeanList = mapperFacade.mapAsList(
					orderList, FundTransferorOrderBean.class);
			response.setTranferorOrderBeanList(orderBeanList);
		}
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());

		return response;
	}
	
	/**
	 * 查询转让交易单列表参数校验
	 * @param request
	 * @return
	 */
	private boolean checkFundTransferorOrderListParams(GetFundTransferorOrderListRequest request) {
		if (request.getProductId() != null || 
				!CollectionUtils.isEmpty(request.getProductIds())) {
			if (StringUtil.isEmpty(request.getMemberNo())) {
				return false;
			}
			return true;
		}
		
		if (!CollectionUtils.isEmpty(request.getFundTransferorOrderNoList()) ||		//转让订单号列表
				!CollectionUtils.isEmpty(request.getBusinessOrderItemNoList()) ||	//转让业务单号列表
				request.getMktProductId() != null || 								//二级市场产品Id号
				!CollectionUtils.isEmpty(request.getMktProductIds()) ||				//二级市场产品Id号列表
				(!StringUtil.isEmpty(request.getMemberNo()) && request.getStatus() != null)) {
			return true;
		}
		
		return false;
	}

	@Override
	public PageableResponse<FundTransferorOrderBean> getFundTransferorOrderByPage(
			GetFundTransferorOrderByPageRequest request) {
		PageableResponse<FundTransferorOrderBean> response = new PageableResponse<FundTransferorOrderBean>();
		
		// 参数校验
		if (!checkFundTransferorOrderByPageParams(request)) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		//分页查询转让单
		Page<FundTransferorOrder> page = fundTransferorOperService.getByPage(request);
		response.initFromPage(page);
		List<FundTransferorOrderBean> orderBeanList = mapperFacade.mapAsList(
				page, FundTransferorOrderBean.class);
		response.setList(orderBeanList);
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		
		return response;
	}
	
	/**
	 * 分页查询转让单参数校验
	 * @param request
	 * @return
	 */
	private boolean checkFundTransferorOrderByPageParams(GetFundTransferorOrderByPageRequest request) {
		if (request.getPageNum() < 1 && request.getPageSize() < 1) {
			return false;
		}
//		if (!StringUtil.isEmpty(request.getMemberNo()) ||
//				request.getProductId() != null || 
//				request.getMktProductId() != null ||
//				(request.getCreateStartTime() != null && request.getCreateEndTime() != null) ||
//				request.getFundTransferorOrderNo() != null) {
//			return true;
//		}
		
		return true;
	}

	@Override
	public GetRelatedTransferorOrderResponse getRelatedTransferorOrder(GetRelatedTransferorOrderRequest request) {
		GetRelatedTransferorOrderResponse response = new GetRelatedTransferorOrderResponse();
		//参数校验
		if (StringUtil.isEmpty(request.getFundTransfereeOrderNo())) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		FundTransferorOrder order = fundTransferorOperService.getByRefFundTransfereeOrderNo(
				request.getFundTransfereeOrderNo());
		if (order != null) {
			FundTransferorOrderBean fundTransferorOrderBean = new FundTransferorOrderBean();
			mapperFacade.map(order, fundTransferorOrderBean);
			response.setFundTransferorOrderBean(fundTransferorOrderBean);

			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} else {
			response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage());
		}
		
		return response;
	}

	@Override
	public GetRelatedTransfereeOrderResponse getRelatedTransfereeOrder(GetRelatedTransfereeOrderRequest request) {
		GetRelatedTransfereeOrderResponse response = new GetRelatedTransfereeOrderResponse();
		//参数校验
		if (StringUtil.isEmpty(request.getFundTransferorOrderNo())) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		//查询关联受让单
		List<FundTransfereeOrder> orderList = fundTransfereeOperService.getByRefFundTransferorOrderNo(
				request.getFundTransferorOrderNo(), null);
		if (!CollectionUtils.isEmpty(orderList)) {
			List<FundTransfereeOrderBean> orderBeanList = mapperFacade.mapAsList(
					orderList, FundTransfereeOrderBean.class);
			response.setTransfereeOrderBeanList(orderBeanList);
			
			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} else {
			response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage());
		}

		return response;
	}

	@Override
	public GetFundTransfereeOrderResponse getFundTransfereeOrder(GetFundTransfereeOrderRequest request) {
		GetFundTransfereeOrderResponse response = new GetFundTransfereeOrderResponse();
		//参数校验
		if (StringUtil.isEmpty(request.getBusinessOrderItemNo()) &&
				StringUtil.isEmpty(request.getFundTransfereeOrderNo())) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}

		//获取受让单
		FundTransfereeOrder order = null;
		if (!StringUtil.isEmpty(request.getBusinessOrderItemNo())) {
			order = fundTransfereeOperService.getByBusinessOrderItemNo(request.getBusinessOrderItemNo());
		} else {
			order = fundTransfereeOperService.getByFundTransfereeOrderNo(request.getFundTransfereeOrderNo());
		}
		
		if (order != null) {
			FundTransfereeOrderBean fundTransfereeOrderBean = new FundTransfereeOrderBean();
			mapperFacade.map(order, fundTransfereeOrderBean);
			response.setTransfereeOrderBean(fundTransfereeOrderBean);

			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} else {
			response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage());
		}
		
		return response;
	}

	@Override
	public GetFundTransfereeOrderListResponse getFundTransfereeOrderList(GetFundTransfereeOrderListRequest request) {
		GetFundTransfereeOrderListResponse response = new GetFundTransfereeOrderListResponse();
		//参数校验
		if (!checkFundTransfereeOrderListParams(request)) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}

		//查询受让单列表
		List<FundTransfereeOrder> orderList = null;
		if (!CollectionUtils.isEmpty(request.getBusinessOrderItemNoList())) {
			orderList = fundTransfereeOperService.getByBusinessOrderItemNoList(
					request.getBusinessOrderItemNoList());
		} else if (!CollectionUtils.isEmpty(request.getFundTransfereeOrderNoList())) {
			orderList = fundTransfereeOperService.getByFundTransfereeOrderNoList(
					request.getFundTransfereeOrderNoList());
		} else {
			orderList = fundTransfereeOperService.getByOrderBasicInfo(
					request.getMemberNo(), request.getMktProductId(), request.getStatus());
		}
		
		if (!CollectionUtils.isEmpty(orderList)) {
			List<FundTransfereeOrderBean> orderBeanList = mapperFacade.mapAsList(
					orderList, FundTransfereeOrderBean.class);
			response.setTransfereeOrderBeanList(orderBeanList);
		}
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		
		return response;
	}

	/**
	 * 查询受让交易单列表参数校验
	 * @param request
	 * @return
	 */
	private boolean checkFundTransfereeOrderListParams(GetFundTransfereeOrderListRequest request) {
		if (!CollectionUtils.isEmpty(request.getFundTransfereeOrderNoList()) ||		//受让订单号列表
				!CollectionUtils.isEmpty(request.getBusinessOrderItemNoList()) ||	//受让业务单号列表
				(!StringUtil.isEmpty(request.getMemberNo()) && (request.getMktProductId() != null))) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public PageableResponse<FundTransfereeOrderBean> getFundTransfereeOrderByPage(
			GetFundTransfereeOrderByPageRequest request) {
		PageableResponse<FundTransfereeOrderBean> response = new PageableResponse<FundTransfereeOrderBean>();
		//参数校验
		if (!checkFundTransfereeOrderByPageParams(request)) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
				
		//分页查询转让单
		Page<FundTransfereeOrder> page = fundTransfereeOperService.getByPage(request);
		response.initFromPage(page);
		List<FundTransfereeOrderBean> orderBeanList = mapperFacade.mapAsList(
				page, FundTransfereeOrderBean.class);
		response.setList(orderBeanList);
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		
		return response;
	}

	/**
	 * 分页查询受让单参数校验
	 * @param request
	 * @return
	 */
	private boolean checkFundTransfereeOrderByPageParams(GetFundTransfereeOrderByPageRequest request) {
		if (request.getPageNum() < 1 && request.getPageSize() < 1) {
			return false;
		}
//		if (!StringUtil.isEmpty(request.getMemberNo()) ||
//				request.getProductId() != null || 
//				request.getMktProductId() != null ||
//				(request.getCreateStartTime() != null && request.getCreateEndTime() != null) ||
//				request.getRefTransferorOrderNo() != null) {
//			return true;
//		}
		
		return true;
	}

	@Override
	public GetOriginalTransferorOrderResponse getOriginalTransferorOrder(GetOriginalTransferorOrderRequest request) {
		GetOriginalTransferorOrderResponse response = new GetOriginalTransferorOrderResponse();//参数校验
		if (!checkOriginalTransferorOrderRequestParams(request)) {
			response.setRespCode(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_INPUT_OBJ_IS_NULL.getMessage());
			return response;
		}
		
		//获取受让单
		FundTransfereeOrder transfereeOrder = null;
		if (!StringUtil.isEmpty(request.getTransfereeBusinessOrderItemNo())) {
			transfereeOrder = fundTransfereeOperService.getByBusinessOrderItemNo(request.getTransfereeBusinessOrderItemNo());
		} else if (!StringUtil.isEmpty(request.getFundTransfereeOrderNo())) {
			transfereeOrder = fundTransfereeOperService.getByFundTransfereeOrderNo(request.getFundTransfereeOrderNo());
		} else if (!StringUtil.isEmpty(request.getFundTransferorOrderNo())) {
			List<FundTransfereeOrder> li = fundTransfereeOperService.getByRefFundTransferorOrderNo(
					request.getFundTransferorOrderNo(), FundTransfereeOrderStatus.TRANS_SUC);
			if (li != null && li.size() > 0) {
				transfereeOrder = li.get(0);
			}
		} else {
			List<FundTransfereeOrder> li = fundTransfereeOperService.getByOrderBasicInfo(
					"", request.getMktProductId(), FundTransfereeOrderStatus.TRANS_SUC);
			if (li != null && li.size() > 0) {
				transfereeOrder = li.get(0);
			}
		}
		
		//获取原始转让单
		if (transfereeOrder != null) {
			FundTransferorOrder transferorOrder = fundTransferorOperService.getByFundTransferorOrderNo(
					transfereeOrder.getRefOriginFundTransferorOrderNo());
			if (transferorOrder != null) {
				FundTransferorOrderBean fundTransferorOrderBean = new FundTransferorOrderBean();
				mapperFacade.map(transferorOrder, fundTransferorOrderBean);
				
				response.setFundTransferorOrderBean(fundTransferorOrderBean);
				response.setRespCode(CommonRspCode.SUCCESS.getCode());
				response.setMsg(CommonRspCode.SUCCESS.getMessage());
			} else {
				response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
				response.setMsg(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage());
			}
		} else {
			response.setRespCode(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			response.setMsg(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage());
		}
		
		return response;
	}

	/**
	 * 查询受让交易单列表参数校验
	 * @param request
	 * @return
	 */
	private boolean checkOriginalTransferorOrderRequestParams(GetOriginalTransferorOrderRequest request) {
		if (!StringUtil.isEmpty(request.getFundTransfereeOrderNo()) ||
				!StringUtil.isEmpty(request.getTransfereeBusinessOrderItemNo()) ||
				!StringUtil.isEmpty(request.getFundTransferorOrderNo()) ||
				(request.getMktProductId() != null)) {
			return true;
		}
		
		return false;
	}
	
}
