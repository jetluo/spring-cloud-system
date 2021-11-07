package com.cloud.kjetboy.server.zuul.filter;

import com.sgcc.uap.safe.config.SafeConsts;
import com.sgcc.uap.safe.filter.proxy.SQLInjectFilterProxy;
import com.sgcc.uap.safe.util.proxy.AuditUtilsProxy;
import com.sgcc.uap.safe.util.proxy.ServiceFactoryUtilsProxy;
import com.sgcc.uap.safe.util.proxy.ServletUtilsProxy;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SQLInjectBootFilter
 * @Description TODO
 * @Author jet
 * @Date 2021/11/5 16:18
 * @Version 1.0
 **/
public class SQLInjectBootFilter extends SafeBootFilterHandler {
    @Override
    public int doFilter(HttpServletRequest request) {
        // sql注入 检查
        if (SQLInjectFilterProxy.checkInputStreamSqlChar(request)) {
            /**
             * 写入审计系统和日志系统
             **/
            AuditUtilsProxy.sendAudit(request, SafeConsts.ERROR_CODE.SQLINJECT.getValue());
            if (ServiceFactoryUtilsProxy.getServiceFactory().getLog().isErrorEnabled()) {
                ServiceFactoryUtilsProxy
                        .getServiceFactory()
                        .getLog()
                        .error(
                                "SAFE_ERROR_CODE: " + SafeConsts.ERROR_CODE.SQLINJECT.getValue()
                                        + ", SAFE_ERROR_INFO: Request incloud SQL Words!\n" +
                        ServletUtilsProxy.toString(request), new Object[0]);
            }
            return SafeConsts.ERROR_CODE.SQLINJECT.getValue();
        }
        return (this.nextSafeFilterHandler != null) ? this.nextSafeFilterHandler.doFilter(request) : this.successStatus;
    }
}
