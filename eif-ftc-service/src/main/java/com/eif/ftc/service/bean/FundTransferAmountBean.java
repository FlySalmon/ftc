package com.eif.ftc.service.bean;

import java.math.BigDecimal;

/**
 * 转让相关金额类
 * 
 * @author jiangweifeng
 *
 */
public class FundTransferAmountBean {

    /**
     * 原资产份额总价值
     */
    private BigDecimal originalAssetTotalValue;
    
    /**
     * 原资产份额残值
     */
    private BigDecimal originalAssetSurplusValue;
    
    /**
     * 转让金额，即转让定价价格
     */
    private BigDecimal fundTransferAmount;
    
    /**
     * 转让价格中的本金金额
     */
    private BigDecimal fundTransferPrincipal;
    
    /**
     * 转让价格中的利息金额
     */
    private BigDecimal fundTransferInterest;
    
    /**
     * 订单让利金额
     */
    private BigDecimal discountAmount;
    
    /**
     * 转让年化收益率
     */
    private BigDecimal annualYield;
    
    /**
     * 转让方手续费
     */
    private BigDecimal transferorFee;
    
    /**
     * 受让方手续费
     */
    private BigDecimal transfereeFee;

	/**
	 * @return the originalAssetTotalValue
	 */
	public BigDecimal getOriginalAssetTotalValue() {
		return originalAssetTotalValue;
	}

	/**
	 * @param originalAssetTotalValue the originalAssetTotalValue to set
	 */
	public void setOriginalAssetTotalValue(BigDecimal originalAssetTotalValue) {
		this.originalAssetTotalValue = originalAssetTotalValue;
	}

	/**
	 * @return the originalAssetSurplusValue
	 */
	public BigDecimal getOriginalAssetSurplusValue() {
		return originalAssetSurplusValue;
	}

	/**
	 * @param originalAssetSurplusValue the originalAssetSurplusValue to set
	 */
	public void setOriginalAssetSurplusValue(BigDecimal originalAssetSurplusValue) {
		this.originalAssetSurplusValue = originalAssetSurplusValue;
	}

	/**
	 * @return the fundTransferAmount
	 */
	public BigDecimal getFundTransferAmount() {
		return fundTransferAmount;
	}

	/**
	 * @param fundTransferAmount the fundTransferAmount to set
	 */
	public void setFundTransferAmount(BigDecimal fundTransferAmount) {
		this.fundTransferAmount = fundTransferAmount;
	}

	/**
	 * @return the fundTransferPrincipal
	 */
	public BigDecimal getFundTransferPrincipal() {
		return fundTransferPrincipal;
	}

	/**
	 * @param fundTransferPrincipal the fundTransferPrincipal to set
	 */
	public void setFundTransferPrincipal(BigDecimal fundTransferPrincipal) {
		this.fundTransferPrincipal = fundTransferPrincipal;
	}

	/**
	 * @return the fundTransferInterest
	 */
	public BigDecimal getFundTransferInterest() {
		return fundTransferInterest;
	}

	/**
	 * @param fundTransferInterest the fundTransferInterest to set
	 */
	public void setFundTransferInterest(BigDecimal fundTransferInterest) {
		this.fundTransferInterest = fundTransferInterest;
	}

	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the annualYield
	 */
	public BigDecimal getAnnualYield() {
		return annualYield;
	}

	/**
	 * @param annualYield the annualYield to set
	 */
	public void setAnnualYield(BigDecimal annualYield) {
		this.annualYield = annualYield;
	}

	/**
	 * @return the transferorFee
	 */
	public BigDecimal getTransferorFee() {
		return transferorFee;
	}

	/**
	 * @param transferorFee the transferorFee to set
	 */
	public void setTransferorFee(BigDecimal transferorFee) {
		this.transferorFee = transferorFee;
	}

	/**
	 * @return the transfereeFee
	 */
	public BigDecimal getTransfereeFee() {
		return transfereeFee;
	}

	/**
	 * @param transfereeFee the transfereeFee to set
	 */
	public void setTransfereeFee(BigDecimal transfereeFee) {
		this.transfereeFee = transfereeFee;
	}
    
}
