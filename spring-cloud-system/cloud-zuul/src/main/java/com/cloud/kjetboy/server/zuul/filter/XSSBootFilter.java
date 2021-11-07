package com.cloud.kjetboy.server.zuul.filter;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName XSSBootFilter
 * @Description TODO
 * @Author jet
 * @Date 2021/11/5 16:19
 * @Version 1.0
 **/
public class XSSBootFilter extends  SafeBootFilterHandler{
    @Override
    public int doFilter(HttpServletRequest paramHttpServletRequest) {
        return 0;
    }
}
