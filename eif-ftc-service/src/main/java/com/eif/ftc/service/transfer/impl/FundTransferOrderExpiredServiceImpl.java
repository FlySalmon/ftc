package com.eif.ftc.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.framework.pagination.pagehelper.PageInfo;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransfereeOrderStatusInfoMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderStatusInfoMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransfereeOrderExample;
import com.eif.ftc.dal.model.FundTransfereeOrderStatusInfo;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.dal.model.FundTransferorOrderStatusInfo;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.mq.dto.MQBusinessOrderItemMessage;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.service.transfer.FundTransferOrderExpiredService;
import com.eif.ftc.service.transfer.action.CancelTransferorOrderActionService;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.member.facade.pkg.dto.MemberUpdateBean;
import com.lts.core.commons.utils.DateUtils;

@Service("fundTransferOrderExpiredService")
public class FundTransferOrderExpiredServiceImpl implements FundTransferOrderExpiredService {

    private static final Logger logger = LoggerFactory.getLogger(FundTransferorServiceImpl.class);

    private int orderScanPageSize = 100;

    @Resource
    private FisIntService fisIntService;

    @Resource
    private MemberIntService memberIntService;

    @Resource
    private OfcIntService ofcIntService;

    @Autowired
    private CancelTransferorOrderActionService cancelTransferorOrderActionService;
    
    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Autowired
    private FundTransfereeOrderStatusInfoMapper fundTransfereeOrderStatusInfoMapper;
    
    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;

    @Autowired
    private FundTransferorOrderStatusInfoMapper fundTransferorOrderStatusInfoMapper;

	@Override
	public void orderExpiredScan() {
		Date curDate = DateUtils.now();
		
        PageInfo<FundTransfereeOrder> pageOrder1 = null;
        try {
	        do {
	            // 分页获取
	            pageOrder1 = processTransfereeOrderByPage(1, orderScanPageSize, curDate, false);
	        	
	        }
	        while (pageOrder1 != null && pageOrder1.getSize() > 0);
        } catch (Exception e) {
    		logger.error("scan expired transferee order failed", e);
    	}
        
        PageInfo<FundTransferorOrder> pageOrder2 = null;
        do {
            // 分页获取
            pageOrder2 = processTransferorOrderByPage(1, orderScanPageSize, curDate, false);
        }
        while (pageOrder2 != null && pageOrder2.getSize() > 0);
	}

    public PageInfo<FundTransfereeOrder> processTransfereeOrderByPage(
    		int pageNum, int pageSize, Date curDate, boolean isCount) {
    	FundTransfereeOrderExample example = new FundTransfereeOrderExample();
    	FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusEqualTo(FundTransfereeOrderStatus.PAYING);
        criteria.andStatusExpiryTimeLessThanOrEqualTo(curDate);
        
        PageHelper.startPage(pageNum, pageSize, isCount);
    	Page<FundTransfereeOrder> orderPage = (Page<FundTransfereeOrder>) fundTransfereeOrderMapper.selectByExample(example);
    	PageInfo<FundTransfereeOrder> pageOrder = new PageInfo<FundTransfereeOrder>(orderPage);
    	if (orderPage.size() == 0) {
    		return null;
    	}

    	// 解冻（取消）库存
    	List<String> tokenList = new ArrayList<>();
    	for (FundTransfereeOrder transfereeOrder : orderPage) {
    		tokenList.add(transfereeOrder.getFrozenToken());
    	}
    	List<String> failedList = fisIntService.batchUnfreezeMktProductInventory(tokenList);
    	Set<String> failedTokenSet = new HashSet<>();
    	if (!CollectionUtils.isEmpty(failedList)) {
    		failedTokenSet.addAll(failedList);
    	}

    	// 更新订单状态
    	List<Long> orderIdList = new ArrayList<>();
    	for (FundTransfereeOrder transfereeOrder : orderPage) {
    		if (!failedTokenSet.contains(transfereeOrder.getFrozenToken())) {
        		orderIdList.add(transfereeOrder.getId());
    		}
    	}
    	List<FundTransfereeOrder> updateOrderList = updateTransferOrders(orderIdList);
    	if (CollectionUtils.isEmpty(updateOrderList)) {
    		return pageOrder;
    	}

    	// 新手更新回滚
        List<MemberUpdateBean> memberUpdateBeanList = new ArrayList<>();
    	for (FundTransfereeOrder transfereeOrder : updateOrderList) {
    		MemberUpdateBean memberUpdateBean = new MemberUpdateBean();
            memberUpdateBean.setLockStatus(-1);
            memberUpdateBean.setMemberNo(transfereeOrder.getMemberNo());
//            memberUpdateBean.setIsRookie(0);
            memberUpdateBeanList.add(memberUpdateBean);
    	}
    	memberIntService.batchUpdateRookie(memberUpdateBeanList);
    	
    	return pageOrder;
    }

	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
    private List<FundTransfereeOrder> updateTransferOrders(List<Long> orderIdList) {
		Date curDate = DateUtils.now();
    	// 更新受让单过期关闭
    	FundTransfereeOrderExample example = new FundTransfereeOrderExample();
    	FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusEqualTo(FundTransfereeOrderStatus.PAYING);
    	criteria.andIdIn(orderIdList);
    	FundTransfereeOrder updateOrder = new FundTransfereeOrder();
    	updateOrder.setStatus(FundTransfereeOrderStatus.CLOSE_BY_EXPIRY);
    	updateOrder.setUpdateTime(curDate);
    	fundTransfereeOrderMapper.updateByExampleSelective(updateOrder, example);
    	
    	// 查询更新成功的订单
    	example.clear();
    	criteria = example.createCriteria();
    	criteria.andStatusEqualTo(FundTransfereeOrderStatus.CLOSE_BY_EXPIRY);
    	criteria.andIdIn(orderIdList);
    	List<FundTransfereeOrder> updateOrderList = fundTransfereeOrderMapper.selectByExample(example);
    	if (CollectionUtils.isEmpty(updateOrderList)) {
    		return null;
    	}
    	List<String> transferorOrderNoList = new ArrayList<>();
    	List<FundTransfereeOrderStatusInfo> transfereeStatusList = new ArrayList<>();
        List<MQBusinessOrderItemMessage> bizTransfereeOrderItemList = new ArrayList<>();
    	for (FundTransfereeOrder transfereeOrder : updateOrderList) {
    		transferorOrderNoList.add(transfereeOrder.getRefFundTransferorOrderNo());
    		
    		FundTransfereeOrderStatusInfo status = new FundTransfereeOrderStatusInfo();
    		status.setOrderId(transfereeOrder.getId());
    		status.setStatus(transfereeOrder.getStatus());
    		status.setUpdateTime(transfereeOrder.getUpdateTime());
    		transfereeStatusList.add(status);
            
            bizTransfereeOrderItemList.add(ofcIntService.buildBusinessOrderItemMessage(transfereeOrder));
    	}
    	//插入状态迁移
    	fundTransfereeOrderStatusInfoMapper.batchInsert(transfereeStatusList);
    	
    	// 更新转让单状态
    	FundTransferorOrderExample example1 = new FundTransferorOrderExample();
    	FundTransferorOrderExample.Criteria criteria1 = example1.createCriteria();
    	criteria1.andFundTransferorOrderNoIn(transferorOrderNoList);
    	criteria1.andStatusEqualTo(FundTransferorOrderStatus.TRANSFERING);
    	FundTransferorOrder updateOrder1 = new FundTransferorOrder();
    	updateOrder1.setStatus(FundTransferorOrderStatus.NEED_TRANSFER);
    	updateOrder1.setUpdateTime(curDate);
    	fundTransferorOrderMapper.updateByExampleSelective(updateOrder1, example1);

    	// 查询更新成功的订单
    	example1.clear();
    	criteria1 = example1.createCriteria();
    	criteria1.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
    	criteria1.andFundTransferorOrderNoIn(transferorOrderNoList);
    	List<FundTransferorOrder> updateTransferorOrderList = fundTransferorOrderMapper.selectByExample(example1);
    	if (CollectionUtils.isEmpty(updateTransferorOrderList)) {
    		return null;
    	}
    	List<FundTransferorOrderStatusInfo> transferorStatusList = new ArrayList<>();
    	List<MQBusinessOrderItemMessage> bizTransferorOrderItemList = new ArrayList<>();
    	for (FundTransferorOrder transferorOrder : updateTransferorOrderList) {
    		FundTransferorOrderStatusInfo status = new FundTransferorOrderStatusInfo();
    		status.setOrderId(transferorOrder.getId());
    		status.setStatus(transferorOrder.getStatus());
    		status.setUpdateTime(transferorOrder.getUpdateTime());
    		transferorStatusList.add(status);
            
    		bizTransferorOrderItemList.add(ofcIntService.buildBusinessOrderItemMessage(transferorOrder, null));
    	}
    	//插入状态迁移
    	fundTransferorOrderStatusInfoMapper.batchInsert(transferorStatusList);
    	
    	try {
	    	// 更新OFC业务受让单信息
	        ofcIntService.batchUpdateBusinessOrderItemAsync(bizTransfereeOrderItemList);
	    	// 更新OFC业务转让单信息
	        ofcIntService.batchUpdateBusinessOrderItemAsync(bizTransferorOrderItemList);
    	} catch (Exception e) {
    		logger.error("sync transfer order status to ofc failed by mq");
    	}
        
        return updateOrderList;
    }

    public PageInfo<FundTransferorOrder> processTransferorOrderByPage(
    		int pageNum, int pageSize, Date curDate, boolean isCount) {
    	FundTransferorOrderExample example = new FundTransferorOrderExample();
    	FundTransferorOrderExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
        criteria.andExpiryTimeLessThanOrEqualTo(curDate);
        
        PageHelper.startPage(pageNum, pageSize, isCount);
    	Page<FundTransferorOrder> orderPage = (Page<FundTransferorOrder>) fundTransferorOrderMapper.selectByExample(example);
    	PageInfo<FundTransferorOrder> pageOrder = new PageInfo<FundTransferorOrder>(orderPage);
    	if (orderPage.size() == 0) {
    		return null;
    	}

    	List<Long> orderIdList = new ArrayList<>();
    	for (FundTransferorOrder transferorOrder : orderPage) {
    		orderIdList.add(transferorOrder.getId());
    	}

    	// 更新转让单状态
    	updateTransferorOrders(orderIdList);
        
    	return pageOrder;
    }

	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
    private void updateTransferorOrders(List<Long> orderIdList) {
		Date curDate = DateUtils.now();
    	// 更新转让单状态
    	FundTransferorOrderExample example = new FundTransferorOrderExample();
    	FundTransferorOrderExample.Criteria criteria = example.createCriteria();
    	criteria.andIdIn(orderIdList);
    	criteria.andStatusEqualTo(FundTransferorOrderStatus.NEED_TRANSFER);
    	FundTransferorOrder updateOrder = new FundTransferorOrder();
    	updateOrder.setStatus(FundTransferorOrderStatus.CLOSE_BY_EXPIRY);
    	updateOrder.setUpdateTime(curDate);
    	fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);

    	// 查询更新成功的订单
    	example.clear();
    	criteria = example.createCriteria();
    	criteria.andStatusEqualTo(FundTransferorOrderStatus.CLOSE_BY_EXPIRY);
    	criteria.andIdIn(orderIdList);
    	List<FundTransferorOrder> updateOrderList = fundTransferorOrderMapper.selectByExample(example);
    	if (CollectionUtils.isEmpty(updateOrderList)) {
    		return;
    	}
    	Set<Long> mktProductIds = new HashSet<>();
    	Set<String> memberProductSet = new HashSet<>();	//会员产品pair
    	List<FundTransferorOrderStatusInfo> transferorStatusList = new ArrayList<>();
    	List<MQBusinessOrderItemMessage> bizTransferorOrderItemList = new ArrayList<>();
    	for (FundTransferorOrder transferorOrder : updateOrderList) {
    		mktProductIds.add(transferorOrder.getMktProductId());
    		memberProductSet.add(transferorOrder.getMemberNo() + "," + transferorOrder.getProductId());
    		bizTransferorOrderItemList.add(ofcIntService.buildBusinessOrderItemMessage(transferorOrder, null));
    		
    		FundTransferorOrderStatusInfo status = new FundTransferorOrderStatusInfo();
    		status.setOrderId(transferorOrder.getId());
    		status.setStatus(transferorOrder.getStatus());
    		status.setUpdateTime(transferorOrder.getUpdateTime());
    		transferorStatusList.add(status);
    	}
    	//插入状态迁移
    	fundTransferorOrderStatusInfoMapper.batchInsert(transferorStatusList);

        // 解冻资产份额
        cancelTransferorOrderActionService.unfreezeMemberProductAsset(memberProductSet);

        // 取消二级市场产品
        cancelTransferorOrderActionService.cancelMarketProducts(mktProductIds);
        
        try {
	    	// 更新OFC业务转让单信息
	        ofcIntService.batchUpdateBusinessOrderItemAsync(bizTransferorOrderItemList);
	        
	        // 发送站内信
	        cancelTransferorOrderActionService.notifyCancelInboxMessages(
	        		BusinessIDType.PM_TRANSFER_ORDER_TIMEOUT_CLOSED.getId(), updateOrderList);
        } catch (Exception e) {
        	logger.error("sync transferor order status to ofc failed by mq");
        }
    }
    
}
