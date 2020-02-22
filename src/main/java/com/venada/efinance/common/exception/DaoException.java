package com.venada.efinance.common.exception;

import java.text.MessageFormat;

public class DaoException extends BaseException {
	private static final long serialVersionUID = 2476388532488988962L;

	public DaoException(String errorCode) {
		super(errorCode);
	}

	public DaoException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public DaoException(String errorCode, Object[] args) {
		super(errorCode, args);
	}

	public DaoException(String errorCode, Object arg) {
		super(errorCode, arg);
	}

	public String getMessage() {
		return MessageFormat.format(super.getMessage(), super.getArgs());
	}

}
