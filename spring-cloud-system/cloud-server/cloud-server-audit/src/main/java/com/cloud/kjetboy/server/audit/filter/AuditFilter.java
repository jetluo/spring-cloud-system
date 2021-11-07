package com.cloud.kjetboy.server.audit.filter;

import com.cloud.kjetboy.server.audit.collector.AuditLogCollectors;
import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.logger.AuditFactory;
import com.cloud.kjetboy.server.audit.logger.AuditLogEventHolder;
import com.cloud.kjetboy.server.audit.util.MatcherUtil;
import com.cloud.kjetboy.server.common.utils.RegexHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author jet
 * 采集那个方法，用什么数据，返回了什么结果
 * 1. AuditMethodMapping 注解标记数据。
 * 2. ControllerAuditLogCollector 采集标注的切面数据。
 * 3. AuditFilter 存储采集的数据。
 * 4. AuditLogCollectorAutoConfiguration 完成bean注入，Filter注入。
 * 5. 完成整个审计过程。
 */
public class AuditFilter extends OncePerRequestFilter {
    private final AuditProperties auditProperties;

    private final AuditLogCollectors auditLogCollectors;

    public AuditFilter(AuditProperties auditProperties, AuditLogCollectors auditLogCollectors) {
        this.auditProperties = auditProperties;
        this.auditLogCollectors = auditLogCollectors;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        AuditLogEventData auditLogEventData = buildAuditLogEventData(request);
        if (excludeUrl(requestUri, request)) {
            filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
            return;
        }
        try {
            filterChain.doFilter((ServletRequest) request, (ServletResponse) response);
        }finally {
            onComplete(request, response, auditLogEventData);
            removeKey();
            sendAuditLog(request, auditLogEventData);
        }

    }
    protected void sendAuditLog(HttpServletRequest request, AuditLogEventData auditLogEventData) {
        try {
            AuditFactory.getAudit().send(auditLogEventData);
        } catch (Exception e) {
            this.logger.error("审计保存失败", (Throwable) RegexHelper.trick(e, null));
        }
    }

    protected void onComplete(HttpServletRequest request, HttpServletResponse response, AuditLogEventData auditLogEventData) {
        auditLogEventData.onComplete();
        if (auditLogEventData.getStatus() != null){
            return;}
        auditLogEventData.setStatus(Integer.valueOf(response.getStatus()));
        if (auditLogEventData.getStatus().intValue() >= 400 && StringUtils.isBlank(auditLogEventData.getExceptionTrace())) {
            try {
                auditLogEventData.setExceptionTrace(HttpStatus.valueOf(auditLogEventData.getStatus().intValue()).getReasonPhrase());
            } catch (IllegalArgumentException ex) {
                auditLogEventData.setExceptionTrace("Http Status " + auditLogEventData.getStatus());
            }
        }
    }
    protected AuditLogEventData buildAuditLogEventData(HttpServletRequest request) {
        AuditLogEventData auditLogEventData = new AuditLogEventData();
        auditLogEventData.setStartTime(Long.valueOf(System.currentTimeMillis()));
        AuditLogEventHolder.set(auditLogEventData);
        auditLogEventData = aggregate(auditLogEventData);
        return auditLogEventData;
    }

    protected AuditLogEventData aggregate(AuditLogEventData auditLogEventData) {
        return this.auditLogCollectors.aggregate(auditLogEventData);
    }

    private void removeKey() {
        AuditLogEventHolder.removeAll();
    }
    protected boolean excludeUrl(String reqUrl, HttpServletRequest request) {

        return MatcherUtil.matcher(this.auditProperties.getIgnored(), reqUrl);
    }

}
