package com.cloud.kjetboy.server.common.exception;

public class BusinessException extends BaseException {
	private static final long serialVersionUID = 2087985250386141377L;

	private String errorCode;
	
	private String message;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super();
		this.message = message;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message , String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
