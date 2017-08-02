package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;
import java.util.List;

public class TransferPairingsResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受让交易匹配Id（开通）
	 */
	private String transfer_pairing_id;
	
	/**
	 * 受让结果信息
	 */
	private List<TransferIntentResultBean> intents;

	/**
	 * @return the transfer_pairing_id
	 */
	public String getTransfer_pairing_id() {
		return transfer_pairing_id;
	}

	/**
	 * @param transfer_pairing_id the transfer_pairing_id to set
	 */
	public void setTransfer_pairing_id(String transfer_pairing_id) {
		this.transfer_pairing_id = transfer_pairing_id;
	}

	/**
	 * @return the intents
	 */
	public List<TransferIntentResultBean> getIntents() {
		return intents;
	}

	/**
	 * @param intents the intents to set
	 */
	public void setIntents(List<TransferIntentResultBean> intents) {
		this.intents = intents;
	}
	
}
