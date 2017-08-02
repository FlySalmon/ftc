package com.eif.ftc.service.transfer.action;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.SecMarketChargeRule;
import com.eif.fis.facade.dto.ftc.SecMarketRule;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.fund.amc.dto.response.FundTotalAssetBean;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.FundTransferAmountBean;

/**
 * 转让单创建
 * 
 * @author jiangweifeng
 *
 */
public interface TransferApplyActionService {

	/**
	 * 获取并校验用户产品资产
	 * @param memberNo
	 * @param productId
	 * @param isCalc
	 * @return
	 */
	FundTotalAssetBean getAndCheckMemberProductAsset(String memberNo, 
			Long productId, boolean isCalc);
	
	/**
	 * 校验会员资产账户
	 * @param transfereeOrder
	 * @return
	 */
	FundAccountBean checkAndGetMemberAccountInfo(String memberNo);
	
	/**
	 * 校验用户风控状态
	 * @param transferorOrder
	 * @param deviceInfo
	 * @param devId
	 * @param ip
	 */
	void checkUserRiskStatus(FundTransferorOrder transferorOrder,
			String deviceInfo, String devId, String ip);
	
	/**
	 * 计算并校验转让定价及费用信息
	 * @param transferorOrder
	 * @param feeRule
	 * @param product
	 * @param expectAmount
	 * @param expectFee
	 */
	void calcAndCheckTransferAmountInfo(FundTransferorOrder transferorOrder, 
			SecMarketChargeRule feeRule, ProdInfo product,
			BigDecimal expectAmount, BigDecimal expectFee);
	
	/**
	 * 计算转让定价、手续费等费用
	 * @param annualYield
	 * @param totalAmount
	 * @param principal
	 * @param valueDate
	 * @param feeRule
	 * @param product
	 * @return
	 */
	FundTransferAmountBean calculateFundTransferAmountInfo(
			BigDecimal annualYield, BigDecimal totalAmount, 
			BigDecimal principal, Date valueDate,
			SecMarketChargeRule feeRule, ProdInfo product);

	/**
	 * 下转让单
	 * @param transferorOrder
	 * @param product
	 * @param marketRule
	 */
	void createTransferorOrder(FundTransferorOrder transferorOrder, ProdInfo product, 
			SecMarketRule marketRule);
	
	/**
	 * 处理创建二级市场结果
	 * @param transferorOrderNo
	 * @param mktProductId
	 */
	void handleCreateMktProductResult(String transferorOrderNo, Long mktProductId);
	
	/**
	 * 处理签订转让协议结果
	 * @param transferorOrderNo
	 * @param agreementNo
	 */
	void handleSignTransferorAgreementResult(String transferorOrderNo, String agreementNo);
	
}
