package com.cloud.kjetboy.server.common.utils;


import java.util.Arrays;
import java.util.List;

/**
 * 常用正则表达式字符串
 *
 * @author jet
 *
 */
public interface RegexContants {

    /**
     * 只允许英文字母、数字、下划线、英文句号、以及中划线组成
     */
    public static final String EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 换行符
     */
    public static final String CRLF = "(\r\n|\r|\n|\n\r)";

    /**
     * HTTP，URL特殊字符串
     */
    public static final List<Character> HTTP_SPECIAL_CHARS = Arrays.asList('.', '\\', '/', '?', '&', '=', ':', '#', '@', ';');
}

