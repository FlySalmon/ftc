package com.eif.ftc.facade.fund.transfer.response;

import java.math.BigDecimal;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * 转让定价预算结果类
 * 
 * @author jiangweifeng
 *
 */
public class CalculateTransferPriceAndFeeResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 转让时效（72小时）
	 */
	private Integer limitation;
	
	/**
	 * 预计到帐金额（转让价格 - 手续费）
	 */
	private BigDecimal expectedAmount;
	
	/**
	 * 预计手续费
	 */
	private BigDecimal expectedFee;

	/**
	 * @return 转让时效
     * @occurs required
	 */
	public Integer getLimitation() {
		return limitation;
	}

	/**
	 * @param limitation the limitation to set
	 */
	public void setLimitation(Integer limitation) {
		this.limitation = limitation;
	}

	/**
	 * @return 预计到帐金额
     * @occurs required
	 */
	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	/**
	 * @param expectedAmount the expectedAmount to set
	 */
	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	/**
	 * @return 预计手续费
     * @occurs required
	 */
	public BigDecimal getExpectedFee() {
		return expectedFee;
	}

	/**
	 * @param expectedFee the expectedFee to set
	 */
	public void setExpectedFee(BigDecimal expectedFee) {
		this.expectedFee = expectedFee;
	}
	
}
