package com.cloud.kjetboy.server.common.exception;


/**
 * 代表一个业务错误编码。并配合异常 {@link BaseException} 一起使用。
 * 
 * 在REST接口中 {@link #getCode()}会作为异常响应的属性返回给调用者。
 * 
 * @author jet
 * 
 *
 */
public class ErrorCode {
	
	public static final ErrorCode ILLEGAL_ARGUMENT = new ErrorCode("cloud-core-001", "非法参数");
	
	public static final ErrorCode DATA_ACCESS_ERROR = new ErrorCode("cloud-core-002", "数据访问异常");
	
	public static final ErrorCode OBJECT_EXISTS = new ErrorCode("cloud-core-003", "对象已存在");

	/**
	 * 异常编码，一般采用 “cloud-user-001”这种格式。
	 * 
	 * acloud是项目名称，user是功能模块名称，001是异常的序列号。
	 */
	private final String code;
	
	/**
	 * 异常编码的可视化描述信息
	 */
	private final String message;

	/**
	 * @param code	异常编码
	 * @param message	异常编码的可视化描述信息
	 */
	public ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
