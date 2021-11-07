package com.cloud.kjetboy.server.audit.collector;


import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.common.utils.HttpRequestUtil;
import com.cloud.kjetboy.server.common.utils.RegexHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jet
 */
public class RequestAuditLogCollector extends AbstractAuditLogCollector {
    @Override
    protected AuditLogEventData wrapAuditLogEventData(AuditLogEventData auditLogEventData) {
        HttpServletRequest request = HttpRequestUtil.getHttpServletRequestFromThreadLocal();
        auditLogEventData.setIp(getRemoteAddr(request));
        auditLogEventData.setMethod(request.getMethod());
        auditLogEventData.setUrl(HttpRequestUtil.getRequestURL(request));
        Enumeration<String> enu = request.getParameterNames();
        Map<String, Object> input = new LinkedHashMap<>();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            input.put(paraName, HttpRequestUtil.getParameterValue(request, paraName));
        }
        auditLogEventData.getInput().put("reqParam", RegexHelper.trick(input, null));
        return super.wrapAuditLogEventData(auditLogEventData);
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String addr = HttpRequestUtil.getRemoteAddr(request);
        return HttpRequestUtil.isLocalhostAddr(addr) ? "127.0.0.1" : addr;
    }
}

