package com.eif.ftc.util.exception;

import com.eif.framework.common.utils.exception.BaseBusinessException;

public class BusinessException extends BaseBusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg, String code, Exception ex) {
		super(msg, code, ex, null);
	}

	public BusinessException(String msg, String code) {
		super(msg, code);
	}

	public BusinessException(String msg, String code, String jsonContent) {
		super(msg, code, jsonContent);
	}

}
