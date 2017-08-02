package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;

public class TransferResultBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

	/**
	 * 转让交易执行Id（开通）
	 */
	private String transfer_id;
	
	/**
	 * 转让交易申请Id
	 */
	private String apply_id;
	
	/**
	 * 转让执行响应
	 */
	private TransferResponse response;

	/**
	 * @return the transfer_id
	 */
	public String getTransfer_id() {
		return transfer_id;
	}

	/**
	 * @param transfer_id the transfer_id to set
	 */
	public void setTransfer_id(String transfer_id) {
		this.transfer_id = transfer_id;
	}

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
	 * @return the response
	 */
	public TransferResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(TransferResponse response) {
		this.response = response;
	}
	
}
