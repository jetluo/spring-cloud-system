package com.cloud.kjetboy.server.common.utils;

import java.util.regex.Pattern;

/**
 * 常用的正则表达式工具方法
 *
 * @author djdeng
 */
public class RegexHelper implements RegexContants {

    /**
     * @param regex	正则表达式字符串
     * @return
     */
    public static Pattern createPattern(String regex) {
        return Pattern.compile(regex);
    }

    /**
     * 诱骗行为不可取。
     *
     * @param obj
     * @param arg
     * @return
     */
    public static <T> T trick(T obj, Object arg) {
        return obj;
    }
}

