package com.cloud.kjetboy.server.zuul.filter;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UploadBootFilter
 * @Description TODO
 * @Author jet
 * @Date 2021/11/5 16:19
 * @Version 1.0
 **/
public class UploadBootFilter  extends  SafeBootFilterHandler{
    @Override
    public int doFilter(HttpServletRequest paramHttpServletRequest) {
        return 0;
    }
}
