package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;

public class TransferResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer status_code;
    
    private String message;

	/**
	 * @return the status_code
	 */
	public Integer getStatus_code() {
		return status_code;
	}

	/**
	 * @param status_code the status_code to set
	 */
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
    
}
