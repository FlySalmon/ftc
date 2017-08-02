package com.eif.ftc.service.transfer.exchange.bean.resp;

import java.io.Serializable;
import java.util.Date;

public class TransferApplyResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回的错误码
	 */
	private Integer status_code;
	
	/**
	 * 返回的错误信息
	 */
	private String message;
	
	/**
	 * 交易所代码
	 */
	private String exchange_code;
	
	/**
	 * 产品代码
	 */
	private String product_code;

	/**
	 * 最低转让线
	 */
	private Integer amount_lower_limit;
	
	/**
	 * 转让申请操作Id
	 */
	private String op_id;
	
	/**
	 * 确认转让的执行时间
	 */
	private Date executed_at;
	
	/**
	 * 执行请求的响应
	 */
	private TransferExecutionResponse execution_response;

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
	 * @return the amount_lower_limit
	 */
	public Integer getAmount_lower_limit() {
		return amount_lower_limit;
	}

	/**
	 * @param amount_lower_limit the amount_lower_limit to set
	 */
	public void setAmount_lower_limit(Integer amount_lower_limit) {
		this.amount_lower_limit = amount_lower_limit;
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
	public Date getExecuted_at() {
		return executed_at;
	}

	/**
	 * @param executed_at the executed_at to set
	 */
	public void setExecuted_at(Date executed_at) {
		this.executed_at = executed_at;
	}

	/**
	 * @return the execution_response
	 */
	public TransferExecutionResponse getExecution_response() {
		return execution_response;
	}

	/**
	 * @param execution_response the execution_response to set
	 */
	public void setExecution_response(TransferExecutionResponse execution_response) {
		this.execution_response = execution_response;
	}
	
}
