package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;

public class TransferIntentResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 本次受让响应状态码
	 */
	private Integer status_code;
	
	/**
	 * 执行转让的交易Id（开通）
	 */
	private String transac_id;
	
	/**
	 * 转让的交易事务状态
	 */
	private Integer transac_status;
	
	/**
	 * 转让交易错误信息
	 */
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
	 * @return the transac_id
	 */
	public String getTransac_id() {
		return transac_id;
	}

	/**
	 * @param transac_id the transac_id to set
	 */
	public void setTransac_id(String transac_id) {
		this.transac_id = transac_id;
	}

	/**
	 * @return the transac_status
	 */
	public Integer getTransac_status() {
		return transac_status;
	}

	/**
	 * @param transac_status the transac_status to set
	 */
	public void setTransac_status(Integer transac_status) {
		this.transac_status = transac_status;
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
