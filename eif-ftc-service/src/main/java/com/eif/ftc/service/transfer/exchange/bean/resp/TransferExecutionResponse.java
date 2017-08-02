package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;

public class TransferExecutionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 转让结果
	 */
	private TransferResultBean transfer;
	
	/**
	 * 受让结果
	 */
	private TransferPairingsResultBean transfer_pairings;

	/**
	 * @return the transfer
	 */
	public TransferResultBean getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(TransferResultBean transfer) {
		this.transfer = transfer;
	}

	/**
	 * @return the transfer_pairings
	 */
	public TransferPairingsResultBean getTransfer_pairings() {
		return transfer_pairings;
	}

	/**
	 * @param transfer_pairings the transfer_pairings to set
	 */
	public void setTransfer_pairings(TransferPairingsResultBean transfer_pairings) {
		this.transfer_pairings = transfer_pairings;
	}
	
}
