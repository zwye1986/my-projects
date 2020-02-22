package com.venada.efinance.common.exception;

@SuppressWarnings("serial")
public class SmsException extends BaseException {

	public SmsException(String errorCode) {
		super(errorCode);
	}

}
