package com.cloud.kjetboy.server.common.exception;


/**
 * 平台异常基类，平台所有的异常都应该使用或者继承该类，其他项目的异常也应该继承于该类。
 * 
 * 通过异常编码对象 {@link ErrorCode} 创建该异常。
 * 
 * @author jet
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5713414039581184812L;
	
	/**
	 * 异常编码在响应对象中的KEY值。
	 */
	public static final String CODE_ATTR_NAME = "code";
	
	/**
	 * 默认的异常编码
	 */
	public static final String DEFAULT_CODE = "unknown";
	
	private String code = DEFAULT_CODE;
	
	public BaseException() {
		super();
	}
	
	public BaseException(ErrorCode errorCode) {
		this(errorCode.getMessage(), errorCode.getCode());
	}
	public BaseException(Throwable cause, ErrorCode errorCode) {
		this(errorCode.getMessage(), cause, errorCode.getCode());
	}
	
	public BaseException(String code) {
		super();
		this.code = code;
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message, Throwable cause, String code) {
		super(message, cause);
		this.code = code;
	}
	
	public BaseException(String message, String code) {
		super(message);
		this.code = code;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
	public BaseException(Throwable cause, String code) {
		super(cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
