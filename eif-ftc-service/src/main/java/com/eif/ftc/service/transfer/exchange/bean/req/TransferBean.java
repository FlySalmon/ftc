package com.eif.ftc.service.transfer.exchange.bean.req;

import java.io.Serializable;
import java.math.BigInteger;

public class TransferBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

    /**
     * 转让请求Id
     */
    private String apply_id;
    
    /**
     * 转让产品份额
     */
    private BigInteger amount;

    /**
     * 转让报价
     */
    private String price;

    /**
     * 转让的预期年化收益
     */
    private String annual_rate;
    
    /**
     * 转让人信息
     */
    private TransferorBean transferor;

	/**
	 * @return the apply_id
	 */
	public String getApply_id() {
		return apply_id;
	}

	/**
	 * @param apply_id the apply_id to set
	 */
	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}

	/**
	 * @return the amount
	 */
	public BigInteger getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the annual_rate
	 */
	public String getAnnual_rate() {
		return annual_rate;
	}

	/**
	 * @param annual_rate the annual_rate to set
	 */
	public void setAnnual_rate(String annual_rate) {
		this.annual_rate = annual_rate;
	}

	/**
	 * @return the transferor
	 */
	public TransferorBean getTransferor() {
		return transferor;
	}

	/**
	 * @param transferor the transferor to set
	 */
	public void setTransferor(TransferorBean transferor) {
		this.transferor = transferor;
	}
    
}
