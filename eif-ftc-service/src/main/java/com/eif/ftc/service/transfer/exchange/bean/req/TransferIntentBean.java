package com.eif.ftc.service.transfer.exchange.bean.req;

import java.io.Serializable;
import java.math.BigInteger;

public class TransferIntentBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

    /**
     * 转让请求Id
     */
    private String apply_id;
    
    /**
     * 受让请求Id
     */
    private String intent_id;
    
    /**
     * 受让购买份额
     */
    private BigInteger amount;
    
    /**
     * 转让价格
     */
    private String price;
    
    /**
     * 手续费
     */
    private String fee;

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
	 * @return the intent_id
	 */
	public String getIntent_id() {
		return intent_id;
	}

	/**
	 * @param intent_id the intent_id to set
	 */
	public void setIntent_id(String intent_id) {
		this.intent_id = intent_id;
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
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}
    
}
