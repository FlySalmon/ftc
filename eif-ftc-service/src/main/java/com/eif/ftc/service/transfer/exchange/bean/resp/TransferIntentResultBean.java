package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;

public class TransferIntentResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 受让请求Id
     */
    private String intent_id;
    
    /**
     * 转让请求Id
     */
    private String apply_id;
    
    /**
     * 受让结果
     */
    private TransferIntentResponse response;

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
	public TransferIntentResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(TransferIntentResponse response) {
		this.response = response;
	}
    
}
