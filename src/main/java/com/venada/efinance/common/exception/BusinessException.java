package com.venada.efinance.common.exception;

import java.text.MessageFormat;

public class BusinessException extends BaseException{
	private static final long serialVersionUID = 6795159147881502331L;

	public BusinessException(String errorCode) {
		super(errorCode);
		
	}

	public BusinessException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public BusinessException(String errorCode, Object[] args) {
		super(errorCode, args);
	}

	public BusinessException(String errorCode, Object arg) {
		super(errorCode, arg);
	}

	public String getMessage() {
		return MessageFormat.format(super.getMessage(), super.getArgs());
	}
}
