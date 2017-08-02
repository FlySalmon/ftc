package com.eif.ftc.service.transfer.exchange.bean.req;

import java.io.Serializable;

public class TransferPairingsBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

    /**
     * 受让人信息
     */
    private TransfereeBean transferee;
    
    /**
     * 受让信息
     */
    private TransferIntentBean transfer_intent;

	/**
	 * @return the transferee
	 */
	public TransfereeBean getTransferee() {
		return transferee;
	}

	/**
	 * @param transferee the transferee to set
	 */
	public void setTransferee(TransfereeBean transferee) {
		this.transferee = transferee;
	}

	/**
	 * @return the transfer_intent
	 */
	public TransferIntentBean getTransfer_intent() {
		return transfer_intent;
	}

	/**
	 * @param transfer_intent the transfer_intent to set
	 */
	public void setTransfer_intent(TransferIntentBean transfer_intent) {
		this.transfer_intent = transfer_intent;
	}
    
}
