package com.cloud.kjetboy.server.common.bean;


import com.cloud.kjetboy.server.common.constants.CommonConstants;

import java.io.Serializable;


/**
 * 功能描述：Rest结果数据封装类
 */
public class RestResult implements Serializable{

	private static final long serialVersionUID = -1280612301030187904L;

	/**
	 * 提示编码
	 */
	private String code;
	
	/**
	 * 是否成功标志
	 */
	private Boolean successful;
	
	/**
	 * 提示消息
	 */
	private String message;
	
	/**
	 * 数据信息
	 */
	private Object data;
	
	public RestResult(String code, Boolean successful, String message,
			Object data) {
		this.code = code;
		this.successful = successful;
		this.message = message;
		this.data = data;
	}
	
	public RestResult() {
		
	}

	/**
	 * 功能描述：表示成功的结果数据
	 * @param code    提示编码
	 * @param message 提示消息
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restSuccess(String code, String message, Object data){
		return new RestResult(code, true, message, data);
	}
	
	/**
	 * 功能描述：表示成功的结果数据
	 * @param code    提示编码
	 * @param message 提示消息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restSuccess(String code, String message){
		return restSuccess(code, message, null);
	}
	
	/**
	 * 功能描述：表示成功的结果数据
	 * @param code    提示编码
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restSuccess(String code, Object data){
		return restSuccess(code, null, data);
	}
	
	/**
	 * 功能描述：表示成功的结果数据
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restSuccess(Object data){
		return restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, null, data);
	}
	
	/**
	 * 功能描述：表示失败的结果数据
	 * @param code    提示编码
	 * @param message 提示消息
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restFail(String code, String message, Object data){
		return new RestResult(code, false, message, data);
	}
	
	/**
	 * 功能描述：表示失败的结果数据
	 * @param code    提示编码
	 * @param message 提示消息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restFail(String code, String message){
		return restFail(code, message, null);
	}
	
	/**
	 * 功能描述：表示失败的结果数据
	 * @param code    提示编码
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restFail(String code, Object data){
		return restFail(code, null, data);
	}
	
	/**
	 * 功能描述：表示失败的结果数据
	 * @param data    数据信息
	 * @return        返回值：返回Rest结果数据
	 */
	public static RestResult restFail(Object data){
		return restFail(CommonConstants.FAIL_RESPONSE_CODE, null, data);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

    
}
