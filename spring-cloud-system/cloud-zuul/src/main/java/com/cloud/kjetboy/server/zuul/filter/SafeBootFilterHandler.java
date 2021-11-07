package com.cloud.kjetboy.server.zuul.filter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SafeBootFilterHandler
 * @Description TODO
 * @Author jet
 * @Date 2021/11/5 15:52
 * @Version 1.0
 **/
public abstract class SafeBootFilterHandler implements Comparable<SafeBootFilterHandler> {
    protected SafeBootFilterHandler nextSafeFilterHandler;

    protected int sequence = 1;

    protected int successStatus = 0;

    protected Map<String, Boolean> globalParamMap = new HashMap<>();

    public void setNext(SafeBootFilterHandler safeFilterHandler, Map<String, Boolean> globalParamMap) {
        this.nextSafeFilterHandler = safeFilterHandler;
        this.globalParamMap = globalParamMap;
    }

    @Override
    public int compareTo(SafeBootFilterHandler o) {
        if (this.sequence > o.sequence) {
            return 1;
        }
        return -1;
    }

    public SafeBootFilterHandler setSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    public abstract int doFilter(HttpServletRequest paramHttpServletRequest);
}
