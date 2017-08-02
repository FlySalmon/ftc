package com.eif.ftc.service.transfer.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.oper.request.GetFundTransferorOrderByPageRequest;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.service.bean.FundTransferAmountBean;
import com.eif.ftc.service.transfer.FundTransferorOperService;
import com.eif.ftc.service.transfer.action.TransferApplyActionService;

import tk.mybatis.mapper.util.StringUtil;

@Service("fundTransferorOperService")
public class FundTransferorOperServiceImpl implements FundTransferorOperService {

    private static final Logger logger = LoggerFactory.getLogger(FundTransferorOperServiceImpl.class);

    @Resource
    private FisIntService fisIntService;
    
    @Autowired
	private TransferApplyActionService transferApplyActionService;
    
    @Autowired
	private FundTransferorOrderMapper fundTransferorOrderMapper;

	@Override
	public FundTransferorOrder getByBusinessOrderItemNo(String businessOrderItemNo) {
		if (StringUtil.isEmpty(businessOrderItemNo)) {
			return null;
		}
		
		FundTransferorOrder queryOrder = new FundTransferorOrder();
		queryOrder.setBusinessOrderItemNo(businessOrderItemNo);
		return updatePriceForOrder(fundTransferorOrderMapper.selectOne(queryOrder));
	}

	@Override
	public FundTransferorOrder getByFundTransferorOrderNo(String fundTransferorOrderNo) {
		if (StringUtil.isEmpty(fundTransferorOrderNo)) {
			return null;
		}

		FundTransferorOrder queryOrder = new FundTransferorOrder();
		queryOrder.setFundTransferorOrderNo(fundTransferorOrderNo);
		return updatePriceForOrder(fundTransferorOrderMapper.selectOne(queryOrder));
	}

	@Override
	public List<FundTransferorOrder> getByBusinessOrderItemNoList(List<String> businessOrderItemNoList) {
		if (CollectionUtils.isEmpty(businessOrderItemNoList)) {
			return null;
		}
		
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessOrderItemNoIn(businessOrderItemNoList);
		return updatePriceForOrders(fundTransferorOrderMapper.selectByExample(example));
	}

	@Override
	public List<FundTransferorOrder> getByFundTransferorOrderNoList(List<String> fundTransferorOrderNoList) {
		if (CollectionUtils.isEmpty(fundTransferorOrderNoList)) {
			return null;
		}
		
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoIn(fundTransferorOrderNoList);
		return updatePriceForOrders(fundTransferorOrderMapper.selectByExample(example));
	}

	@Override
	public List<FundTransferorOrder> getByOrderBasicInfo(String memberNo, 
			List<Long> productIds, List<Long> mktProductIds, Integer status) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(memberNo)) {
			criteria.andMemberNoEqualTo(memberNo);
		}
		if (!CollectionUtils.isEmpty(productIds)) {
			criteria.andProductIdIn(productIds);
		}
		if (!CollectionUtils.isEmpty(mktProductIds)) {
			criteria.andMktProductIdIn(mktProductIds);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		return updatePriceForOrders(fundTransferorOrderMapper.selectByExample(example));
	}

	@Override
	public Page<FundTransferorOrder> getByPage(GetFundTransferorOrderByPageRequest pageRequest) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(pageRequest.getMemberNo())) {
			criteria.andMemberNoEqualTo(pageRequest.getMemberNo());
			example.setOrderByClause("create_time desc");
		}
		if (pageRequest.getProductId() != null) {
			criteria.andProductIdEqualTo(pageRequest.getProductId());
		}
		if (pageRequest.getMktProductId() != null) {
			criteria.andMktProductIdEqualTo(pageRequest.getMktProductId());	
		}
		if (pageRequest.getStatus() != null) {
			criteria.andStatusEqualTo(pageRequest.getStatus());
		}
		if (pageRequest.getCreateStartTime() != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(pageRequest.getCreateStartTime());
		}
		if (pageRequest.getCreateEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(pageRequest.getCreateEndTime());
		}
		if (!StringUtil.isEmpty(pageRequest.getFundTransferorOrderNo())) {
			criteria.andFundTransferorOrderNoEqualTo(pageRequest.getFundTransferorOrderNo());
		}

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize(), pageRequest.isCalCount());
		return (Page<FundTransferorOrder>) updatePriceForOrders(fundTransferorOrderMapper.selectByExample(example));
	}

	@Override
	public FundTransferorOrder getByRefFundTransfereeOrderNo(String refFundTransfereeOrderNo) {
		if (StringUtil.isEmpty(refFundTransfereeOrderNo)) {
			return null;
		}
		
		return updatePriceForOrder(fundTransferorOrderMapper.queryByRefFundTransfereeOrderNo(refFundTransfereeOrderNo));
	}

	/**
	 * 更新订单中的价格信息
	 * @param transferorOrder
	 * @return
	 */
	private FundTransferorOrder updatePriceForOrder(FundTransferorOrder transferorOrder) {
		if (transferorOrder != null && 
				transferorOrder.getStatus() == FundTransferorOrderStatus.NEED_TRANSFER &&
				!DateUtils.isSameDay(new Date(), transferorOrder.getCreateTime())) {
			// 解析手续费规则
			SecMarketChargeRule feeRule = (SecMarketChargeRule) JSON.parseObject(
					transferorOrder.getFeeRule(), SecMarketChargeRule.class);
			// 获取产品基本信息
			ProdInfo product = fisIntService.queryProductCommonInfo(transferorOrder.getProductId());
			if (product == null) {
				logger.error("cannot get product info by productId: " + transferorOrder.getProductId());
				return transferorOrder;
			}
			// 计算定价信息
			FundTransferAmountBean transferAmountBean = transferApplyActionService.calculateFundTransferAmountInfo(
					transferorOrder.getAnnualYield(), transferorOrder.getOriginalAssetTotalValue(), 
					transferorOrder.getFundTransferPrincipal(), transferorOrder.getCreateTime(),
					feeRule, product);
			
			// 更新金额
			transferorOrder.setOriginalAssetSurplusValue(transferAmountBean.getOriginalAssetSurplusValue());
			transferorOrder.setFundTransferAmount(transferAmountBean.getFundTransferAmount());
			transferorOrder.setFundTransferInterest(transferAmountBean.getFundTransferInterest());
			transferorOrder.setTransferorFee(transferAmountBean.getTransferorFee());
			transferorOrder.setTransfereeFee(transferAmountBean.getTransfereeFee());
			transferorOrder.setDiscountAmount(transferAmountBean.getDiscountAmount());
		}
		
		return transferorOrder;
	}

	/**
	 * 更新订单中的价格信息
	 * @param transferorOrders
	 * @return
	 */
	private List<FundTransferorOrder> updatePriceForOrders(List<FundTransferorOrder> transferorOrders) {
		Set<Long> productIds = new HashSet<>();
		Date curDate = new Date();
		for (FundTransferorOrder transferorOrder : transferorOrders) {
			if (transferorOrder.getStatus() == FundTransferorOrderStatus.NEED_TRANSFER &&
					!DateUtils.isSameDay(curDate, transferorOrder.getCreateTime())) {
				productIds.add(transferorOrder.getProductId());
			}
		}
		
		if (productIds.size() > 0) {
			Map<Long, ProdInfo> productMap = fisIntService.queryProductCommonInfos(productIds);
			for (FundTransferorOrder transferorOrder : transferorOrders) {
				if (transferorOrder.getStatus() == FundTransferorOrderStatus.NEED_TRANSFER &&
						!DateUtils.isSameDay(curDate, transferorOrder.getCreateTime())) {
					// 解析手续费规则
					SecMarketChargeRule feeRule = (SecMarketChargeRule) JSON.parseObject(
							transferorOrder.getFeeRule(), SecMarketChargeRule.class);
					// 获取产品基本信息
					ProdInfo product = productMap.get(transferorOrder.getProductId());
					if (product == null) {
						logger.error("cannot get product info by productId: " + transferorOrder.getProductId());
						continue;
					}
					// 计算定价信息
					FundTransferAmountBean transferAmountBean = transferApplyActionService.calculateFundTransferAmountInfo(
							transferorOrder.getAnnualYield(), transferorOrder.getOriginalAssetTotalValue(), 
							transferorOrder.getFundTransferPrincipal(), transferorOrder.getCreateTime(),
							feeRule, product);
					
					// 更新金额
					transferorOrder.setOriginalAssetSurplusValue(transferAmountBean.getOriginalAssetSurplusValue());
					transferorOrder.setFundTransferAmount(transferAmountBean.getFundTransferAmount());
					transferorOrder.setFundTransferInterest(transferAmountBean.getFundTransferInterest());
					transferorOrder.setTransferorFee(transferAmountBean.getTransferorFee());
					transferorOrder.setTransfereeFee(transferAmountBean.getTransfereeFee());
					transferorOrder.setDiscountAmount(transferAmountBean.getDiscountAmount());
				}
			}
		}
		
		return transferorOrders;
	}

}
