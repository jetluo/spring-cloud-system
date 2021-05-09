package com.cloud.kjetboy.server.common.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 功能描述：公共常量类
 */
public class CommonConstants {

	/**
	 * 成功的响应码
	 */
	public static final String SUCCESS_RESPONSE_CODE = "200";
	
	
	/**
	 * 失败的响应码
	 */
	public static final String FAIL_RESPONSE_CODE = "304";
	
	/**
	 * 错误的响应码
	 */
	public static final String ERROR_RESPONSE_CODE = "500";

	public static final String HARBOR_PROJECT = "/api/v2.0/projects";
	
	public static final String EMAIL_DESNES = "****";
	
	public static final String PAWD_DESNES = "***";
	
	public static final String YML_HEADER = "5b7b2265";
	
	public static final String JAR_HEADER = "504b0304";
	
	public static final String WAR_HEADER = "504b0304";
	
	public static final String TAR_HEADER = "30643266";
	
	public static final String ZIP_HEADER = "504b0304";
	
	public static final String BINARY_TYPE = "application/octet-stream";
	
	public static final String YML_TYPE = "application/x-yaml";
	
	public static final String JAR_TYPE = "application/java-archive";
	
	public static final String TAR_TYPE = "application/x-tar";
	
	public static final String ZIP_TYPE = "application/x-zip-compressed";
	
	public static final String APPLE_ZIP_TYPE = "application/zip";
	
	public static final String VIRTUALSERVICE= "-route";
	
	public static final String DESTINALTIONRULE= "-destination";
	
	public static final String GATEWAY= "-gateway";
	
	public static final String CARRAY= "-carray";
	
	public static final String GRAY= "-gray";
	
	/**
	 * 默认日志格式化
	 * 由于SimpleDateFormat是线程不安全的，因此采用ThreadLocal方式
	 * 调用方式示例：
	 *   解析日期：    CommonConstants。DEFAULT_DATE_FORMAT.get().parse("2017-12-13 15:17:27")
	 *   格式化日期：CommonConstants。DEFAULT_DATE_FORMAT.get().format(new Date())
	 */


	public final static ThreadLocal<DateFormat> DEFAULT_DATE_FORMAT = new ThreadLocal<DateFormat>(){
        @Override 
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
}
