package com.eif.ftc.service.transfer.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.eif.fis.facade.constant.MarketLevel;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.ftc.InvestProperty;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.ExchangeType;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.integration.contract.ContractIntService;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.FundTransferAmountBean;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.service.transfer.action.TransferApplyActionService;
import com.eif.ftc.service.transfer.ruleengine.FeeRuleEngine;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.risk.facade.constant.CheckPointList;
import com.github.knightliao.apollo.utils.time.DateUtils;

@Service("transferApplyActionService")
public class TransferApplyActionServiceImpl implements TransferApplyActionService {

	private static Logger logger = LoggerFactory.getLogger(TransferApplyActionServiceImpl.class);

    @Resource
    private RiskIntService riskIntService;

    @Resource
    private OfcIntService ofcIntService;

    @Resource
    private FisIntService fisIntService;

    @Resource
    private MemberIntService memberIntService;

    @Resource
    private TranscoreIntService transcoreIntService;
    
    @Resource
    private ContractIntService contractIntService;

    @Autowired
    private FundAccountService fundAccountService;

    @Autowired
    private MemberAssetService memberAssetService;
    
    @Autowired
    private FundTransferOrderActionService fundTransferOrderActionService;

    @Resource
    private GoutongIntService goutongIntService;

    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;

    public final static long DEFAULT_MKT_PRODUCT_ID = -1L;		  // 初始二级市场产品Id
    
	@Override
	public FundTotalAssetBean getAndCheckMemberProductAsset(String memberNo, Long productId, boolean isCalc) {
		FundTotalAssetRequestBean assetRequest = new FundTotalAssetRequestBean();
		assetRequest.setMemberNo(memberNo);
		assetRequest.setProductId(productId);
		FundTotalAssetBean memberProductShare = memberAssetService.getFundTotalAsset(assetRequest);
		if ((memberProductShare.getTotalAmount().compareTo(BigDecimal.ZERO) != 1) ||
				(memberProductShare.getUnconfirmedAmount().compareTo(BigDecimal.ZERO) > 0)) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_NO_PRODUCT_SHARE.getMessage(),
                    FTCRespCode.ERR_FUND_TRANSFER_NO_PRODUCT_SHARE.getCode());
		}
		// 仅计算用不需要校验资产冻结状态
		if (!isCalc && (memberProductShare.getExchangeStatus() != ExchangeType.NORMAL.getValue())) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_NO_PRODUCT_SHARE.getMessage(),
                    FTCRespCode.ERR_FUND_TRANSFER_NO_PRODUCT_SHARE.getCode());
		}
		
		return memberProductShare;
	}
	
	@Override
	public FundAccountBean checkAndGetMemberAccountInfo(String memberNo) {
		FundAccountBean fundAccountBean = fundAccountService.getFundAccountByMemberNo(memberNo);
		if (fundAccountBean == null) 
			return null;
		
        int fundAcctStatus = fundAccountBean.getAccountStatus();
        if (fundAcctStatus == FundAcctStatus.FROZEN || fundAcctStatus == FundAcctStatus.TA_FAILED
                || fundAcctStatus == FundAcctStatus.TA_FINISH) {
        	logger.error("fund asset account's status is error, status: ", fundAcctStatus);
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_STATUS.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_STATUS.getCode());
        }
        
        return fundAccountBean;
	}

	@Override
	public void checkUserRiskStatus(FundTransferorOrder transferorOrder, String deviceInfo, String devId, String ip) {
		try {
			riskIntService.transferorRiskCheck(transferorOrder, 
					CheckPointList.USER_TRANS_PRE.getCheckPoint(), deviceInfo, devId, ip);
		} catch (BusinessException e) {
			// 记录风控失败订单
			Date curDate = new Date();
			transferorOrder.setCreateTime(curDate);
			transferorOrder.setUpdateTime(curDate);
			transferorOrder.setStatus(FundTransferorOrderStatus.CANCEL_BY_RISK);
			
			fundTransferOrderActionService.insertFundTransferorOrder(transferorOrder);
			throw e;
		}
	}

	@Override
	public void calcAndCheckTransferAmountInfo(FundTransferorOrder transferorOrder, 
			SecMarketChargeRule feeRule, ProdInfo product, 
			BigDecimal expectAmount, BigDecimal expectFee) {
		FundTransferAmountBean transferAmountBean = calculateFundTransferAmountInfo(
				transferorOrder.getAnnualYield(), transferorOrder.getOriginalAssetTotalValue(), 
				transferorOrder.getFundTransferPrincipal(), transferorOrder.getCreateTime(),
				feeRule, product);
		if (expectFee.compareTo(transferAmountBean.getTransferorFee()) != 0 ||//手续费
			expectAmount.compareTo(//预计到帐金额
				transferAmountBean.getFundTransferAmount().subtract(transferAmountBean.getTransferorFee())) != 0) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_AMOUNT_DIFFERENT.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_AMOUNT_DIFFERENT.getCode());
		}
		
		// 更新金额
		transferorOrder.setOriginalAssetSurplusValue(transferAmountBean.getOriginalAssetSurplusValue());
		transferorOrder.setFundTransferAmount(transferAmountBean.getFundTransferAmount());
		transferorOrder.setFundTransferInterest(transferAmountBean.getFundTransferInterest());
		transferorOrder.setTransferorFee(transferAmountBean.getTransferorFee());
		transferorOrder.setTransfereeFee(transferAmountBean.getTransfereeFee());
		transferorOrder.setDiscountAmount(transferAmountBean.getDiscountAmount());
	}

	@Override
	public FundTransferAmountBean calculateFundTransferAmountInfo(
			BigDecimal annualYield, BigDecimal totalAmount,
			BigDecimal principal, Date valueDate, 
			SecMarketChargeRule feeRule, ProdInfo product) {
		// 计算转让价格
		BigDecimal productYearDays = new BigDecimal(String.valueOf(product.getPerYearDate())); 
		// 已经持有天数，不包括转让当天
		long holdDays = DateUtils.getDaysBetweenIgnoreTime(product.getInceptionDate(), valueDate) - 1; 	
		// 剩余计息天数
		BigDecimal surplusDays = new BigDecimal(String.valueOf((long)product.getProductLimit() - holdDays));
		// 转让价格=兑付金额/（1+转让预期年化收益率*计息剩余天数/产品年天数）
		BigDecimal transferPrice = totalAmount.divide(BigDecimal.ONE.add(
				annualYield.multiply(surplusDays).divide(
						productYearDays, MoneyUtil.ACCURACY, BigDecimal.ROUND_DOWN)), 
				MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
		
		// 计算残余红利
		BigDecimal surplusDividend = principal.multiply(
				product.getAnnualYieldRate()).multiply(surplusDays).divide(
						productYearDays, MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
		
		// 设置
		FundTransferAmountBean transferAmountBean = new FundTransferAmountBean();
		transferAmountBean.setAnnualYield(annualYield);				//预计收益率
		transferAmountBean.setFundTransferPrincipal(principal);		//本金
		transferAmountBean.setOriginalAssetTotalValue(totalAmount);	//资产份额总金额
		transferAmountBean.setFundTransferAmount(transferPrice);	//转让价格
		transferAmountBean.setOriginalAssetSurplusValue(principal.add(surplusDividend));//份额残值
		transferAmountBean.setFundTransferInterest(transferPrice.subtract(principal));  //转让价格中的利息金额
		transferAmountBean.setDiscountAmount(totalAmount.subtract(			//资产份额总金额
				transferAmountBean.getOriginalAssetSurplusValue()).subtract(//份额残值
						transferAmountBean.getFundTransferInterest()));		//转让价格中的利息金额
		
		// 计算手续费
		transferAmountBean.setTransferorFee(FeeRuleEngine.calculateTranferFee(principal, transferPrice, feeRule));
		transferAmountBean.setTransfereeFee(BigDecimal.ZERO);
		
		// 到帐金额为负
		if (transferPrice.subtract(transferAmountBean.getTransferorFee()).compareTo(BigDecimal.ZERO) != 1) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_RECEIVED_AMOUNT.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_RECEIVED_AMOUNT.getCode());
		}
		return transferAmountBean;
	}

	@Override
	public void createTransferorOrder(FundTransferorOrder transferorOrder, ProdInfo product, SecMarketRule marketRule) {
		// 获取会员信息
    	Set<String> memberNos = new HashSet<>();
    	memberNos.add(transferorOrder.getMemberNo());
    	Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
    	if (!memberMap.containsKey(transferorOrder.getMemberNo())) {
    		logger.error("cannot get member info by memberNo: " + transferorOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}
    	
		// 下业务单
    	try {
			String businessOrderItemNo = ofcIntService.createBusinessOrderItem(transferorOrder, product.getProductName());
			transferorOrder.setBusinessOrderItemNo(businessOrderItemNo);
			transferorOrder.setMktProductId(DEFAULT_MKT_PRODUCT_ID);
			transferorOrder.setTransferAgreementNo("");
    	} catch (Exception e) {// 超时处理
    		transferorOrder.setStatus(FundTransferorOrderStatus.TRANSFER_FAILED);
    		ofcIntService.updateBusinessOrderItemAsync(transferorOrder, null);
    		
    		throw e;
    	}
    	
		// 获取子产品编号
		String fundSubCode = getFundSubCode(transferorOrder, product);
		transferorOrder.setRefFundSubCode(fundSubCode);
		
		// 获取原始投资本金（一级市场）
		BigDecimal origionalPrincipal = getOrigionalPrincipal(transferorOrder, product);
		
		// 插入订单
		fundTransferOrderActionService.insertFundTransferorOrder(transferorOrder);
		try {
			// 冻结资产
			memberAssetService.freezeMemberProductAsset(transferorOrder.getMemberNo(), 
					transferorOrder.getProductId());
			
			try {
				// 创建二级市场
				List<InvestProperty> investPropertyList = calcInvestProperties(
						transferorOrder, marketRule.getSecMarketChargeRule(), product);
				try {
					Long mktProductId = fisIntService.createMktProduct(transferorOrder.getFundTransferorOrderNo(), 
							transferorOrder.getProductId(), 
							investPropertyList, transferorOrder.getExpiryTime(), 
							marketRule.getSecMarketChargeRule().getDiscountRate(),
							origionalPrincipal);
					// 更新二级市场产品id
					fundTransferOrderActionService.updateFundTransferorOrderMktProductId(
							transferorOrder.getFundTransferorOrderNo(), mktProductId);
					transferorOrder.setMktProductId(mktProductId);
				} catch (Exception e) {
					logger.error("create second market product failed", e);
					
					// 异步创建保证成功
					fisIntService.createMktProductAsync(transferorOrder.getFundTransferorOrderNo(), 
							transferorOrder.getProductId(), 
							investPropertyList, transferorOrder.getExpiryTime(), 
							marketRule.getSecMarketChargeRule().getDiscountRate(),
							origionalPrincipal);
				}
				
				// 签订转让协议
				contractIntService.signTransferorAgreementAsync(transferorOrder, 
						product, memberMap.get(transferorOrder.getMemberNo()));
			} catch (Exception ex) {
				// 解冻资产
				memberAssetService.unfreezeMemberProductAsset(transferorOrder.getMemberNo(), 
					transferorOrder.getProductId());
				
				throw ex;
			}
		} catch (Exception e) {
			// 更新失败状态
			fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.TRANSFER_FAILED);
			
			// 更新业务单
			ofcIntService.updateBusinessOrderItemAsync(transferorOrder, null);
			throw e;
		}
		
		// 发送站内信
		notifyInboxMessage(BusinessIDType.PM_TRANSFER_ORDER_CREATED.getId(), 
				transferorOrder, product);
	}

	/**
	 * 获取子产品编号
	 * @param transferorOrder
	 * @param product
	 * @return
	 */
	private String getFundSubCode(FundTransferorOrder transferorOrder, ProdInfo product) {
		// TODO
		String fundSubCode = "";
		if (product.getCloseType() == ProductCloseType.HALF_OPEN) { // 活包定产品
			if (product.getMarketLevel() == MarketLevel.FIRST) {	//一级市场
				
			} else {//二级市场
				
			}
		}
		
		return fundSubCode;
	}

	/**
	 * 获取原始投资本金
	 * @param transferorOrder
	 * @param product
	 * @return
	 */
	private BigDecimal getOrigionalPrincipal(FundTransferorOrder transferorOrder, ProdInfo product) {
		BigDecimal principal = BigDecimal.ZERO;
		if (product.getMarketLevel() == MarketLevel.FIRST) {	//一级市场
			principal = transferorOrder.getFundTransferPrincipal();
		} else {//二级市场
			// 获取原始转让单号
			FundTransfereeOrder queryOrder = new FundTransfereeOrder();
			queryOrder.setMemberNo(transferorOrder.getMemberNo());
			queryOrder.setMktProductId(transferorOrder.getProductId());
			queryOrder.setStatus(FundTransfereeOrderStatus.TRANS_SUC);
			List<FundTransfereeOrder> transfereeOrderList = fundTransfereeOrderMapper.select(queryOrder);
			if (CollectionUtils.isEmpty(transfereeOrderList)) {
				logger.error("cannot find transfereeOrder by memberNo: " + transferorOrder.getMemberNo() +
						", productId: " + transferorOrder.getProductId());
	    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage(),
	    				FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			}
			
			// 获取原始转让单
			String origionalTransferorOrderNo = transfereeOrderList.get(0).getRefOriginFundTransferorOrderNo();
			FundTransferorOrder queryTransferorOrder = new FundTransferorOrder();
			queryTransferorOrder.setFundTransferorOrderNo(origionalTransferorOrderNo);
			FundTransferorOrder origionalTransferorOrder = fundTransferorOrderMapper.selectOne(queryTransferorOrder);
			if (origionalTransferorOrder == null) {
				logger.error("cannot find transferorOrder by origionalTransferorOrderNo: " 
						+ origionalTransferorOrderNo);
	    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(),
	    				FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			}
			
			principal = origionalTransferorOrder.getFundTransferPrincipal();
		}
		
		return principal;
	}
	
	/**
	 * 计算有效期内的每一天定价
	 * @param transferorOrder
	 * @param feeRule
	 * @param product
	 * @return
	 */
	private List<InvestProperty> calcInvestProperties(FundTransferorOrder transferorOrder, 
			SecMarketChargeRule feeRule, ProdInfo product) {
		List<InvestProperty> investPropertyList = new ArrayList<>();
		
		Date dt = transferorOrder.getCreateTime();
		long days = DateUtils.getDaysBetweenIgnoreTime(transferorOrder.getCreateTime(), transferorOrder.getExpiryTime());
		for (int i = 0; i < days; i++) {
			InvestProperty investProperty = new InvestProperty();
			investProperty.setDate(dt);
			investProperty.setRate(transferorOrder.getAnnualYield());
			if (i == 0) {
				investProperty.setAmt(transferorOrder.getFundTransferAmount());
				investProperty.setYieldAmt(transferorOrder.getOriginalAssetTotalValue().subtract(
						transferorOrder.getFundTransferAmount()));
			} else {
				FundTransferAmountBean transferAmountBean = calculateFundTransferAmountInfo(
						transferorOrder.getAnnualYield(), transferorOrder.getOriginalAssetTotalValue(), 
						transferorOrder.getFundTransferPrincipal(), dt,
						feeRule, product);
				investProperty.setAmt(transferAmountBean.getFundTransferAmount());
				investProperty.setYieldAmt(transferAmountBean.getOriginalAssetTotalValue().subtract(
						transferAmountBean.getFundTransferAmount()));
			}
			investPropertyList.add(investProperty);
			
			dt = DateUtils.getNextDay(dt);
		}
		
		return investPropertyList;
	}
	
	/**
	 * 发送站内信
	 * @param businessId
	 * @param transferorOrder
	 * @param product
	 */
	private void notifyInboxMessage(int businessId, FundTransferorOrder transferorOrder, ProdInfo product) {
		//获取产品信息
//		List<String> inboxParams = new ArrayList<>();
//		inboxParams.add(product.getProductName());
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(ParamKeys.PRODUCT_NAME.getName(), product.getProductName());
		
		goutongIntService.sendInboxMessage(transferorOrder.getBizChannel(),
				businessId, paramMap, transferorOrder.getMemberNo());
	}

	@Override
	public void handleCreateMktProductResult(String transferorOrderNo, Long mktProductId) {
		fundTransferOrderActionService.updateFundTransferorOrderMktProductId(transferorOrderNo, mktProductId);
	}

	@Override
	public void handleSignTransferorAgreementResult(String transferorOrderNo, String agreementNo) {
		fundTransferOrderActionService.updateFundTransferorOrderAgreementNo(transferorOrderNo, agreementNo);
	}

}
