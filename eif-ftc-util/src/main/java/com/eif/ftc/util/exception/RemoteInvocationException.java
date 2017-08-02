package com.eif.ftc.util.exception;

import com.eif.framework.common.utils.exception.BaseBusinessException;

/**
 * @author jiangweifeng
 */
public class RemoteInvocationException extends BaseBusinessException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoteInvocationException(String msg, String code, Throwable ex, String jsonContent) {
        super(msg, code, ex, jsonContent);
    }

    public RemoteInvocationException(String msg, String code, String jsonContent) {
        super(msg, code, jsonContent);
    }

    public RemoteInvocationException(String msg, String code) {
        super(msg, code);
    }
    
}
