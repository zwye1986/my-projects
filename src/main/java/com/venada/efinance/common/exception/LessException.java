package com.venada.efinance.common.exception;

import java.text.MessageFormat;

public class LessException extends BaseException{
	private static final long serialVersionUID = 6795159147881502331L;

	public LessException(String errorCode) {
		super(errorCode);
		
	}

	public LessException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public LessException(String errorCode, Object[] args) {
		super(errorCode, args);
	}

	public LessException(String errorCode, Object arg) {
		super(errorCode, arg);
	}

	public String getMessage() {
		return MessageFormat.format(super.getMessage(), super.getArgs());
	}
}
