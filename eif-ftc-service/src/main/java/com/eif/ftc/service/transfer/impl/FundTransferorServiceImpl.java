package com.eif.ftc.service.transfer.impl;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.eif.contract.facade.response.ftc.AssignorTransferorContractResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.fis.facade.response.ftc.CreateSecMarketProdResp;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.request.CalculateTransferPriceAndFeeRequest;
import com.eif.ftc.facade.fund.transfer.request.CancelTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.CheckAssetTransactionRuleRequest;
import com.eif.ftc.facade.fund.transfer.request.CreateTransferorOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CalculateTransferPriceAndFeeResponse;
import com.eif.ftc.facade.fund.transfer.response.CreateTransferorOrderResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.FundTransferAmountBean;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.ftc.service.transfer.action.CancelTransferorOrderActionService;
import com.eif.ftc.service.transfer.action.TransferApplyActionService;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.service.transfer.ruleengine.PricingRuleEngine;
import com.eif.ftc.service.transfer.ruleengine.TransactionRuleEngine;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.risk.facade.bean.UserBlockingBean;
import com.eif.risk.facade.constant.UserBlockingType;
import com.lts.core.commons.utils.DateUtils;

import ma.glasnost.orika.MapperFacade;

@Service("fundTransferorService")
public class FundTransferorServiceImpl implements FundTransferorService {

    private static final Logger logger = LoggerFactory.getLogger(FundTransferorServiceImpl.class);

    @Autowired
	private TransferApplyActionService transferApplyActionService;
    
    @Autowired
	private CancelTransferorOrderActionService cancelTransferorOrderService;
    
    @Autowired
	private FundTransferOrderActionService fundTransferOrderActionService;

    @Resource
    private FisIntService fisIntService;
    
    @Autowired
    private MapperFacade mapperFacade;

    @Resource
    private RedisConcurrentLock redisConcurrentLock;

    @Resource
    private SequenceGenerator sequenceGenerator;

    public final static int KEY_EXPIRED_TIME_IN_SECOND = 30 * 60; // 30分钟

	@Override
	public void checkAssetTransactionRule(CheckAssetTransactionRuleRequest request) {
		// 获取并校验用户产品资产份额
		transferApplyActionService.getAndCheckMemberProductAsset(
				request.getMemberNo(), request.getProductId(), false);
		
		// 获取产品交易规则
		SecMarketRule marketRule = fisIntService.queryMktProductTransactionRule(request.getProductId());
		// 获取产品基本信息
		ProdInfo product = fisIntService.queryProductCommonInfo(request.getProductId());
		
		// 校验交易规则
		TransactionRuleEngine.isProductShareCanBeTransferred(marketRule.getSecMarketTransRule(), product);
	}

	@Override
	public void calculateTransferPriceAndFee(CalculateTransferPriceAndFeeRequest request,
			CalculateTransferPriceAndFeeResponse response) {
		// 获取并校验用户产品资产份额
		FundTotalAssetBean memberProductShare = transferApplyActionService.getAndCheckMemberProductAsset(
				request.getMemberNo(), request.getProductId(), true);
		BigDecimal totalAmount = memberProductShare.getConfirmedAmount().add(//本金
				memberProductShare.getExpectProfitAmount()).add(//加息收益
						memberProductShare.getExpectBonusAmount()).add(//预期红利收益
								memberProductShare.getGrouponAmount());//团购奖励

		// 获取产品交易规则
		SecMarketRule marketRule = fisIntService.queryMktProductTransactionRule(request.getProductId());
		// 获取产品基本信息
		ProdInfo product = fisIntService.queryProductCommonInfo(request.getProductId());
		
		// 校验交易规则
		TransactionRuleEngine.isProductShareCanBeTransferred(marketRule.getSecMarketTransRule(), product);
		
		// 校验定价规则
		PricingRuleEngine.isPriceAndRateValid(marketRule.getSecMarketPricingRule(), 
				product.getAnnualYieldRate(), request.getRate());
		
		// 计算定价及手续费
		Date curDate = DateUtils.now();
		FundTransferAmountBean transferAmountBean = transferApplyActionService.calculateFundTransferAmountInfo(
				request.getRate(), totalAmount, memberProductShare.getConfirmedAmount(), curDate,
				marketRule.getSecMarketChargeRule(), product);
		// 校验最后一天到帐金额是否为负
		Date expiredDate = DateUtils.addHour(curDate, marketRule.getSecMarketTransRule().getTransValidTime());
		transferApplyActionService.calculateFundTransferAmountInfo(
				request.getRate(), totalAmount, memberProductShare.getConfirmedAmount(), expiredDate,
				marketRule.getSecMarketChargeRule(), product);
		
		// 时效性
		response.setLimitation(marketRule.getSecMarketTransRule().getTransValidTime());
		response.setExpectedAmount(transferAmountBean.getFundTransferAmount().subtract(transferAmountBean.getTransferorFee()));
		response.setExpectedFee(transferAmountBean.getTransferorFee());
	}

	@Override
	public void createTransferorOrder(CreateTransferorOrderRequest request, 
			CreateTransferorOrderResponse response) {
		// 生成转让交易单
		FundTransferorOrder transferorOrder = new FundTransferorOrder();
		mapperFacade.map(request, transferorOrder);
		transferorOrder.setAnnualYield(request.getRate());
		// 生成转让单号
		String transferorOrderNo = sequenceGenerator.transOrderGen(FundTransType.TRANSFEROR);
		transferorOrder.setFundTransferorOrderNo(transferorOrderNo);
		transferorOrder.setStatus(FundTransferorOrderStatus.NEED_TRANSFER);

		// 资产账户状态
		FundAccountBean fundAccountBean = transferApplyActionService.checkAndGetMemberAccountInfo(transferorOrder.getMemberNo());
		if (fundAccountBean == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACC_STATUS_ILLEGUE.getMessage(),
                    FTCRespCode.ERR_FUND_ACC_STATUS_ILLEGUE.getCode());
		}
		transferorOrder.setAssetAccountNo(fundAccountBean.getFundAccountNo());
		transferorOrder.setTransAccountNo(fundAccountBean.getTransactionAccount());
		
		// 校验用户风控状态
		transferApplyActionService.checkUserRiskStatus(transferorOrder, 
				request.getDeviceInfo(), request.getDevId(), request.getIp());
		
		// 获取并校验用户产品资产份额
		FundTotalAssetBean memberProductShare = transferApplyActionService.getAndCheckMemberProductAsset(
				request.getMemberNo(), request.getProductId(), false);
		BigDecimal totalAmount = memberProductShare.getConfirmedAmount().add(//本金
				memberProductShare.getExpectProfitAmount()).add(//加息收益
						memberProductShare.getExpectBonusAmount()).add(//预期红利收益
								memberProductShare.getGrouponAmount());//团购奖励
		transferorOrder.setFundTransferPrincipal(memberProductShare.getConfirmedAmount());
		transferorOrder.setOriginalAssetTotalValue(totalAmount);

		// 获取产品交易规则
		SecMarketRule marketRule = fisIntService.queryMktProductTransactionRule(request.getProductId());
		// 获取产品基本信息
		ProdInfo product = fisIntService.queryProductCommonInfo(request.getProductId());
		
		// 校验交易规则
		TransactionRuleEngine.isProductShareCanBeTransferred(marketRule.getSecMarketTransRule(), product);
		
		// 设置时间
		Date curDate = DateUtils.now();
		transferorOrder.setCreateTime(curDate);
		transferorOrder.setUpdateTime(curDate);
//		transferorOrder.setExpiryTime(DateUtils.addHour(curDate, marketRule.getSecMarketTransRule().getTransValidTime()));
		transferorOrder.setExpiryTime(TransactionRuleEngine.getExpiryTime(curDate, 
				DateUtils.addHour(curDate, marketRule.getSecMarketTransRule().getTransValidTime()),
				product.getDueDate(), marketRule.getSecMarketTransRule().getDaysBeforeDueDate()));
		transferorOrder.setFeeRule(JSON.toJSONString(marketRule.getSecMarketChargeRule()));

		// 计算定价及手续费并校验
		transferApplyActionService.calcAndCheckTransferAmountInfo(
				transferorOrder, marketRule.getSecMarketChargeRule(), product,
				request.getExpectedAmount(), request.getExpectedFee());
		
		// 并发控制
        redisConcurrentLock.acquire(transferorOrder.getTrackingNo(), KEY_EXPIRED_TIME_IN_SECOND);
        try {
			// 下转让单
			transferApplyActionService.createTransferorOrder(transferorOrder, product, marketRule);
        } finally {
        	redisConcurrentLock.release(transferorOrder.getTrackingNo());
        }
		
		response.setBusinessOrderItemNo(transferorOrder.getBusinessOrderItemNo());
		response.setFundTransferorOrderNo(transferorOrder.getFundTransferorOrderNo());
	}

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void cancelTransferorOrder(CancelTransferorOrderRequest request) {
		String reason = "user-cancel";
		Date curDateTime = DateUtils.now();
		Integer targetStatus = FundTransferorOrderStatus.CANCEL;
		
		// 更新待转让的转让单
		FundTransferorOrder transferorOrder = cancelTransferorOrderService.updateFundTransferorOrder(
				request.getBusinessOrderItemNo(), curDateTime, targetStatus, reason);

		// 根据订单状态给出错误信息
		fundTransferOrderActionService.checkTransferorOrderStatus(transferorOrder, targetStatus);

		// 解冻（取消）资产份额
		cancelTransferorOrderService.unfreezeMemberProductAsset(transferorOrder.getMemberNo(), 
				transferorOrder.getProductId());
			
		// 取消二级市场产品
		cancelTransferorOrderService.cancelMarketProduct(transferorOrder.getMktProductId());
		
		try {
			// 更新OFC订单
			cancelTransferorOrderService.updateBusinessOrderItem(transferorOrder);
	
			// 发送站内信
			cancelTransferorOrderService.notifyCancelInboxMessage(
					BusinessIDType.PM_TRANSFER_ORDER_REVOKED.getId(), transferorOrder);
		} catch (Exception e) {
			logger.error("send mq error", e);
		}
	}

	@Override
	public void handleRiskLockUserMessage(UserBlockingBean blockingBean) {
		Date curDateTime = DateUtils.now();
		String reason = "";
		if (blockingBean.getBlockType() == UserBlockingType.BLACKLIST) {
			reason = "blacklist";
		} else {
			reason = "risk-lock";
		}
		List<String> memberNoList = new ArrayList<>(blockingBean.getMemberNos());
		
		// 取消用户所有待转让的转让单
		List<FundTransferorOrder> transferorOrderList = cancelTransferorOrderAndUnFreezeAsset(
				memberNoList, curDateTime, reason);
		if (CollectionUtils.isEmpty(transferorOrderList)) {
			logger.warn("update transferor order to cancel-by-risk, return count is 0. memberNos: " 
					+ JSON.toJSONString(blockingBean.getMemberNos()));
			return;
		}
		
		Set<Long> productIds = new HashSet<Long>();				//一级市场产品Id
		Set<Long> mktProductIds = new HashSet<Long>();			//二级市场产品Id
		Set<String> memberNos = new HashSet<String>();			//用户会员号
		for (FundTransferorOrder order : transferorOrderList) {
			productIds.add(order.getProductId());
			mktProductIds.add(order.getMktProductId());
			memberNos.add(order.getMemberNo());
		}

		// 撤销二级市场产品
		cancelTransferorOrderService.cancelMarketProducts(mktProductIds);

		try {
			// 更新OFC订单状态
			cancelTransferorOrderService.updateBusinessOrderItems(transferorOrderList);
	
			// 发送通知短信
			cancelTransferorOrderService.notifyCancelSMSMessage(
					BusinessIDType.PM_TRANSFER_ORDER_RISK_CLOSED.getId(),
					transferorOrderList, productIds, memberNos);
		} catch (Exception e) {
			logger.error("send cancel transferor mq error", e);
		}
	}

	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	private List<FundTransferorOrder> cancelTransferorOrderAndUnFreezeAsset(List<String> memberNoList,
			Date curDateTime, String reason) {
		Integer status = FundTransferorOrderStatus.CANCEL_BY_RISK;
		// 取消用户所有待转让的转让单
		List<FundTransferorOrder> transferorOrderList = cancelTransferorOrderService.updateFundTransferorOrders(
				memberNoList, curDateTime, status, reason);
		if (!CollectionUtils.isEmpty(transferorOrderList)) {
			Set<String> memberProductSet = new HashSet<>();	//会员产品pair
			for (FundTransferorOrder order : transferorOrderList) {
				memberProductSet.add(order.getMemberNo() + "," + order.getProductId());
			}

			// 取消冻结资产
			cancelTransferorOrderService.unfreezeMemberProductAsset(memberProductSet);
		}
		
		return transferorOrderList;
	}

	@Override
	public FundTransferAmountBean calculateFundTransferAmountInfo(FundTransferorOrder transferorOrder, 
			ProdInfo product, Date valueDate) {
		// 解析手续费规则
		SecMarketChargeRule feeRule = (SecMarketChargeRule) JSON.parseObject(
				transferorOrder.getFeeRule(), SecMarketChargeRule.class);
		
		return transferApplyActionService.calculateFundTransferAmountInfo(transferorOrder.getAnnualYield(), 
				transferorOrder.getOriginalAssetTotalValue(),
				transferorOrder.getFundTransferPrincipal(),
				valueDate, feeRule, product);
	}

	@Override
	public void handleCreateMktProductResult(CreateSecMarketProdResp createMktProductResp) {
		if (!createMktProductResp.isSuccess()) {
			throw new BusinessException(createMktProductResp.getMsg(),
					createMktProductResp.getRespCode());
		}
		
		transferApplyActionService.handleCreateMktProductResult(
				createMktProductResp.getTransferOrderNo(), 
				createMktProductResp.getId());
	}

	@Override
	public void handleSignTransferorAgreementResult(AssignorTransferorContractResp signAgreementResp) {
		if (!signAgreementResp.isSuccess()) {
			throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_STATUS.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_STATUS.getCode());
		}

		transferApplyActionService.handleSignTransferorAgreementResult(
				signAgreementResp.getTransferorNum(), signAgreementResp.getTransferNo());
	}

}
