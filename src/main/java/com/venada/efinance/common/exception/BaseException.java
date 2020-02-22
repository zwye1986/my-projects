package com.venada.efinance.common.exception;

import com.venada.efinance.common.util.PropertiesUtil;

/**
 * 异常基类
 * 
 * @author yma
 * 
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode = "";

	private Object[] args = null;

	public BaseException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, Object arg) {
		this.errorCode = errorCode;
		this.args = new Object[1];
		this.args[0] = arg;
	}

	public BaseException(String errorCode, Object[] args) {
		this.errorCode = errorCode;
		this.args = args;
	}

	public BaseException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public Object[] getArgs() {
		return this.args;
	}

	public String getMessage() {

		return PropertiesUtil.getProperty(this.errorCode);
	}

}
