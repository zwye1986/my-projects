package com.venada.efinance.common.exception;

import java.text.MessageFormat;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1338925885159995206L;

	public ServiceException(String errorCode) {
		super(errorCode);
	}

	public ServiceException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public ServiceException(String errorCode, Object[] args) {
		super(errorCode, args);
	}

	public ServiceException(String errorCode, Object arg) {
		super(errorCode, arg);
	}

	public String getMessage() {
		return MessageFormat.format(super.getMessage(), super.getArgs());
	}

}
