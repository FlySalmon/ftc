package com.eif.ftc.service.transfer.exchange.bean.req;

import java.io.Serializable;
import java.util.List;

public class TransferApplicationBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

	/**
	 * 交易所代码
	 */
	private String exchange_code;

	/**
	 * 产品代码
	 */
	private String product_code;
	
	/**
	 * 操作Id
	 */
	private String op_id;
	
	/**
	 * 转让执行时间
	 */
	private String executed_at;
	
	/**
	 * 转让信息
	 */
	private TransferBean transfer;
	
	/**
	 * 受让信息
	 */
	private List<TransferPairingsBean> transfer_pairings;

	/**
	 * @return the exchange_code
	 */
	public String getExchange_code() {
		return exchange_code;
	}

	/**
	 * @param exchange_code the exchange_code to set
	 */
	public void setExchange_code(String exchange_code) {
		this.exchange_code = exchange_code;
	}

	/**
	 * @return the product_code
	 */
	public String getProduct_code() {
		return product_code;
	}

	/**
	 * @param product_code the product_code to set
	 */
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	/**
	 * @return the op_id
	 */
	public String getOp_id() {
		return op_id;
	}

	/**
	 * @param op_id the op_id to set
	 */
	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	/**
	 * @return the executed_at
	 */
	public String getExecuted_at() {
		return executed_at;
	}

	/**
	 * @param executed_at the executed_at to set
	 */
	public void setExecuted_at(String executed_at) {
		this.executed_at = executed_at;
	}

	/**
	 * @return the transfer
	 */
	public TransferBean getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(TransferBean transfer) {
		this.transfer = transfer;
	}

	/**
	 * @return the transfer_pairings
	 */
	public List<TransferPairingsBean> getTransfer_pairings() {
		return transfer_pairings;
	}

	/**
	 * @param transfer_pairings the transfer_pairings to set
	 */
	public void setTransfer_pairings(List<TransferPairingsBean> transfer_pairings) {
		this.transfer_pairings = transfer_pairings;
	}
	
}
