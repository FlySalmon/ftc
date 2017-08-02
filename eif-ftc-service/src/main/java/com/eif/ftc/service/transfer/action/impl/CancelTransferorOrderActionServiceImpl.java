package com.eif.ftc.service.transfer.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderStatusInfoMapper;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.dal.model.FundTransferorOrderStatusInfo;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.mq.dto.MQBusinessOrderItemMessage;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.transfer.action.CancelTransferorOrderActionService;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;

import org.slf4j.Logger;

@Service("cancelTransferorOrderService")
public class CancelTransferorOrderActionServiceImpl implements CancelTransferorOrderActionService {

    private static final Logger logger = LoggerFactory.getLogger(CancelTransferorOrderActionServiceImpl.class);

    @Resource
    private FisIntService fisIntService;
    
    @Resource
    private OfcIntService ofcIntService;

    @Resource
    private MemberIntService memberIntService;
    
    @Resource
    private GoutongIntService goutongIntService;

    @Autowired
	private MemberAssetService memberAssetService;
    
    @Autowired
	private FundTransferorOrderMapper fundTransferorOrderMapper;
    
    @Autowired
	private FundTransferorOrderStatusInfoMapper fundTransferorOrderStatusInfoMapper;

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public FundTransferorOrder updateFundTransferorOrder(String businessOrderItemNo, Date curDateTime, Integer status,
			String reason) {
		FundTransferorOrder transferorOrder = new FundTransferorOrder();
		transferorOrder.setStatus(status);
		transferorOrder.setCancelTime(curDateTime);
		transferorOrder.setRemark(reason);
		transferorOrder.setUpdateTime(curDateTime);
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessOrderItemNoEqualTo(businessOrderItemNo);
		criteria.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
		int count = fundTransferorOrderMapper.updateByExampleSelective(transferorOrder, example);
		//查询订单
		FundTransferorOrder queryOrder = new FundTransferorOrder();
		queryOrder.setBusinessOrderItemNo(businessOrderItemNo);
		transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
		if (count > 0) {
			//插入订单迁移状态
			FundTransferorOrderStatusInfo statusInfo = new FundTransferorOrderStatusInfo();
			statusInfo.setOrderId(transferorOrder.getId());
			statusInfo.setStatus(status);
			statusInfo.setUpdateTime(curDateTime);
			statusInfo.setRemark(reason);
			
			fundTransferorOrderStatusInfoMapper.insertSelective(statusInfo);
		}
		 
		return transferorOrder;
	}

	@Override
	public void cancelMarketProduct(Long mktProductId) {
		try {
			fisIntService.cancelMktProduct(mktProductId);
		} catch (Exception e) {
			logger.info("cancel mkt product failed by sync mode, mktProductId: " + mktProductId, e);
			fisIntService.cancelMktProductAsync(mktProductId); //同步失败转异步
//			// 取消失败，rollback
//			fisIntService.compensateCancelMktProductAsync(mktProductId);
//			throw e;
		}
	}

	@Override
	public void unfreezeMemberProductAsset(String memberNo, Long productId) {
		memberAssetService.unfreezeMemberProductAsset(memberNo, productId);
	}

	@Override
	public void updateBusinessOrderItem(FundTransferorOrder transferorOrder) {
		ofcIntService.updateBusinessOrderItemAsync(transferorOrder, null);
	}

	@Override
	public void notifyCancelInboxMessage(int businessId, FundTransferorOrder transferorOrder) {
		//获取产品信息
		ProdInfo product = fisIntService.queryProductCommonInfo(transferorOrder.getProductId());
		
//		List<String> inboxParams = new ArrayList<>();
//		inboxParams.add(product.getProductName());
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(ParamKeys.PRODUCT_NAME.getName(), product.getProductName());
		
		goutongIntService.sendInboxMessage(transferorOrder.getBizChannel(),
				businessId, paramMap, transferorOrder.getMemberNo());
	}
	
	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public List<FundTransferorOrder> updateFundTransferorOrders(List<String> memberNos, 
			Date curDateTime, Integer status, String reason) {
		// 查询订单信息
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andMemberNoIn(memberNos);
		criteria.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
		List<FundTransferorOrder> queryOrderList = fundTransferorOrderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(queryOrderList)) {
			logger.info("no transferor order for members: " + JSON.toJSONString(memberNos));
			return null;
		}
		List<Long> ids = new ArrayList<>();
		for (FundTransferorOrder order : queryOrderList) {
			ids.add(order.getId());
		}

		//更新并获取订单最新信息
		example.clear();
		criteria = example.createCriteria();
		criteria.andIdIn(ids);
		criteria.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
		
		FundTransferorOrder transferorOrder = new FundTransferorOrder();
		transferorOrder.setStatus(status);
		transferorOrder.setCancelTime(curDateTime);
		transferorOrder.setRemark(reason);
		transferorOrder.setUpdateTime(curDateTime);

		List<FundTransferorOrder> orderList = null;
		int count = fundTransferorOrderMapper.updateByExampleSelective(transferorOrder, example);
		if (count > 0) {
			example.clear();
			criteria = example.createCriteria();
			criteria.andStatusEqualTo(status);
			criteria.andIdIn(ids);
			orderList = fundTransferorOrderMapper.selectByExample(example);
			
			//批量插入订单迁移状态
			List<FundTransferorOrderStatusInfo> statusInfos = new ArrayList<>();
			for (FundTransferorOrder order : orderList) {
				FundTransferorOrderStatusInfo statusInfo = new FundTransferorOrderStatusInfo();
				statusInfo.setOrderId(order.getId());
				statusInfo.setStatus(status);
				statusInfo.setUpdateTime(order.getUpdateTime());
				statusInfo.setRemark(reason);
				statusInfo.setOperatorNo(order.getOperatorNo());
				
				statusInfos.add(statusInfo);
			}
			fundTransferorOrderStatusInfoMapper.batchInsert(statusInfos);
		}
		return orderList;
	}

	@Override
	public void cancelMarketProducts(Set<Long> mktProductIds) {
		// 取消二级市场产品
		try {
			List<Long> failedList = fisIntService.cancelMktProducts(mktProductIds);
			if (!CollectionUtils.isEmpty(failedList)) {
				logger.error("cancel second market products failed, failedList: " + JSON.toJSONString(failedList));
			}
		} catch (Exception e) {
			logger.error("cancel second market products failed for risk, mktProductIds: " +
					JSON.toJSONString(mktProductIds));
			// 异步撤销二级市场产品
			fisIntService.cancelMktProductsAsync(mktProductIds);
		}
	}

	@Override
	public void unfreezeMemberProductAsset(Set<String> memberProductSet) {
		// 解冻资产
		for (String item : memberProductSet) {
			String[] arr = item.split(",");
			memberAssetService.unfreezeMemberProductAsset(arr[0], Long.parseLong(arr[1]));
		}
	}

	@Override
	public void updateBusinessOrderItems(List<FundTransferorOrder> transferorOrderList) {
		List<MQBusinessOrderItemMessage> businessOrderItemList = new ArrayList<>();
		for (FundTransferorOrder order: transferorOrderList) {
			businessOrderItemList.add(ofcIntService.buildBusinessOrderItemMessage(order, null));
		}
		
		ofcIntService.batchUpdateBusinessOrderItemAsync(businessOrderItemList);
	}

	@Override
	public void notifyCancelSMSMessage(int businessId, 
			List<FundTransferorOrder> transferorOrderList, 
			Set<Long> productIds, Set<String> memberNos) {
		//获取产品信息
		Map<Long, ProdInfo> productMap = fisIntService.queryProductCommonInfos(productIds);
		//获取会员信息
		Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
		
		for (FundTransferorOrder order: transferorOrderList) {
			Map<String, String> smsParams = new HashMap<>();
			smsParams.put(ParamKeys.PRODUCT_NAME.getName(), productMap.get(order.getProductId()).getProductName());
			goutongIntService.sendSmsAsync(order.getBizChannel(), businessId, 
					smsParams, memberMap.get(order.getMemberNo()).getVerifiedMobile());
		}
	}

	@Override
	public void notifyCancelInboxMessages(int businessId, List<FundTransferorOrder> transferorOrderList) {
		Set<Long> productIds = new HashSet<>();
		for (FundTransferorOrder transferorOrder : transferorOrderList) {
			productIds.add(transferorOrder.getProductId());
		}
		//获取产品信息
		Map<Long, ProdInfo> productMap = fisIntService.queryProductCommonInfos(productIds);
		
		//发送站内信
		for (FundTransferorOrder transferorOrder : transferorOrderList) {
//			List<String> inboxParams = new ArrayList<>();
//			inboxParams.add(productMap.get(transferorOrder.getProductId()).getProductName());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put(ParamKeys.PRODUCT_NAME.getName(), productMap.get(transferorOrder.getProductId()).getProductName());
			
			goutongIntService.sendInboxMessage(transferorOrder.getBizChannel(),
					businessId, paramMap, transferorOrder.getMemberNo());
		}
	}

}
