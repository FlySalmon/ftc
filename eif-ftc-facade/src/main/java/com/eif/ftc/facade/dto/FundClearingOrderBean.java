package com.eif.ftc.facade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.eif.framework.common.utils.pkg.BaseDTO;

public class FundClearingOrderBean extends BaseDTO {

	private static final long serialVersionUID = 1L;


	private String businessOrderItemNo;

	private String fundClearingOrderNo;

	private Long productId;

	private String productName;

	private String memberNo;

	private BigDecimal amount;
	
	private BigDecimal dividendAmount;

	private BigDecimal currentDividendAmount;
	
	private BigDecimal couponAmount;

    private BigDecimal grouponAmount;

    private BigDecimal marketingDiscount;

    private BigDecimal platformProfit;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	/**
	 * @return 业务订单号
	 * @occurs required
	 */
	public String getBusinessOrderItemNo() {
		return businessOrderItemNo;
	}

	public void setBusinessOrderItemNo(String businessOrderItemNo) {
		this.businessOrderItemNo = businessOrderItemNo;
	}



	/**
	 * @return 清盘业务单号
	 * @occurs required
	 */
	public String getFundClearingOrderNo() {
		return fundClearingOrderNo;
	}

	public void setFundClearingOrderNo(String fundClearingOrderNo) {
		this.fundClearingOrderNo = fundClearingOrderNo;
	}


	/**
	 * @return 用户产品ID
	 * @occurs required
	 */
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


	/**
	 * @return 用户产品名称
	 * @occurs required
	 */
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * @return 会员号
	 * @occurs required
	 */
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	/**
	 * @return 清盘金额（本金+收益）
	 * @occurs required
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return 预期收益金额
	 * @occurs required
	 */
	public BigDecimal getDividendAmount() {
		return dividendAmount;
	}

	public void setDividendAmount(BigDecimal dividendAmount) {
		this.dividendAmount = dividendAmount;
	}

	/**
	 * @return 活期收益金额
	 * @occurs required
	 */
	public BigDecimal getCurrentDividendAmount() {
		return currentDividendAmount;
	}

	/**
	 * @param currentDividendAmount the currentDividendAmount to set
	 */
	public void setCurrentDividendAmount(BigDecimal currentDividendAmount) {
		this.currentDividendAmount = currentDividendAmount;
	}

	/**
	 * @return 优惠券收益
	 * @occurs required
	 */
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	/**
	 * @return 团购收益
	 * @occurs required
	 */
	public BigDecimal getGrouponAmount() {
		return grouponAmount;
	}


	public void setGrouponAmount(BigDecimal grouponAmount) {
		this.grouponAmount = grouponAmount;
	}

	/**
	 * @return 营销贴息
	 * @occurs required
	 */
	public BigDecimal getMarketingDiscount() {
		return marketingDiscount;
	}

	public void setMarketingDiscount(BigDecimal marketingDiscount) {
		this.marketingDiscount = marketingDiscount;
	}

	/**
	 * @return 平台利润金额
	 * @occurs required
	 */
	public BigDecimal getPlatformProfit() {
		return platformProfit;
	}

	public void setPlatformProfit(BigDecimal platformProfit) {
		this.platformProfit = platformProfit;
	}



	/**
	 * @return 兑付单状态
	 * @occurs required
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	/**
	 * @return 创建时间
	 * @occurs required
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 更新时间
	 * @occurs required
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
