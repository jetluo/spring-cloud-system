package com.cloud.kjetboy.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.sgcc.uap.safe.config.proxy.SafeConstsProxy;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SafeZuulFilter
 * @Description TODO
 * @Author jet
 * @Date 2021/11/5 15:29
 * @Version 1.0
 **/
public class SafeZuulFilter extends ZuulFilter {


    private List<SafeBootFilterHandler> SAFE_BOOT_FILTER_LIST = new ArrayList<>();

    private Field requestField;

    public SafeZuulFilter() {
        initSafeBootFilterHandler();
        this.requestField = ReflectionUtils.findField(HttpServletRequestWrapper.class, "req", HttpServletRequest.class);
        Assert.notNull(this.requestField, "HttpServletRequestWrapper.req field not found");
        this.requestField.setAccessible(true);
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }


    private void initSafeBootFilterHandler() {
        if (SafeConstsProxy.SAFE_SQLINJECT_ENABLED) {
            this.SAFE_BOOT_FILTER_LIST.add((new SQLInjectBootFilter()).setSequence(SafeConstsProxy.SAFE_SQLINJECT_ORDER.intValue()));
        }
        if (SafeConstsProxy.SAFE_XSS_ENABLED) {
            this.SAFE_BOOT_FILTER_LIST.add((new XSSBootFilter()).setSequence(SafeConstsProxy.SAFE_XSS_ORDER.intValue()));
        }
        if (SafeConstsProxy.SAFE_KEYWORD_ENABLED) {
            this.SAFE_BOOT_FILTER_LIST.add((new KeywordBootFilter()).setSequence(SafeConstsProxy.SAFE_KEYWORD_ORDER.intValue()));
        }
        if (SafeConstsProxy.SAFE_UPLOAD_ENABLED) {
            this.SAFE_BOOT_FILTER_LIST.add((new UploadBootFilter()).setSequence(SafeConstsProxy.SAFE_UPLOAD_ORDER.intValue()));
        }
        Collections.sort(this.SAFE_BOOT_FILTER_LIST);
        for (SafeBootFilterHandler obj : this.SAFE_BOOT_FILTER_LIST) {
            if (this.SAFE_BOOT_FILTER_LIST.size() > 0) {
                SafeBootFilterHandler temp = null;
                for (int i = 0; i < this.SAFE_BOOT_FILTER_LIST.size(); i++) {
                    if (i != 0) {
                        temp.setNext(this.SAFE_BOOT_FILTER_LIST.get(i), SafeConstsProxy.SAFE_GLOBAL_PARAM_MAP);
                    }
                    temp = this.SAFE_BOOT_FILTER_LIST.get(i);
                }
            }
        }
    }
}
