package com.cloud.kjetboy.server.audit.collector;

import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.support.AuditMethodMapping;
import com.cloud.kjetboy.server.common.utils.RegexHelper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;

/**
 * @author jet
 */
@Aspect
public class ControllerAuditLogCollector extends SpringAOPAuditLogCollector {
    private static Logger logger = LoggerFactory.getLogger(ControllerAuditLogCollector.class);

    protected ErrorProperties errorProperties;

    public ControllerAuditLogCollector(ErrorProperties errorProperties, AuditProperties auditLogClientProperties, AuditMethodMapping auditMethodMapping) {
        super(auditLogClientProperties, auditMethodMapping);
        this.errorProperties = errorProperties;
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void webRequestLog() {
    }

    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            if (!isRequestMappingMethod(getMethod(joinPoint))) {
                return;
            }
            AuditLogEventData eventData = collect();
            if (eventData != null) {
                if (isErrorPath(eventData)) {
                    return;
                }
                beforeMethodInvoke(eventData, joinPoint);
            }
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doBefore()***:{}", (Throwable) RegexHelper.trick(e, null));
        }
    }

    @After("webRequestLog()")
    public void doAfter(JoinPoint joinPoint) {
        afterMethodInvoke(getAuditLogEventData(), joinPoint);
    }

    @AfterReturning(returning = "result", pointcut = "webRequestLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        AuditLogEventData eventData = collect();
        if (eventData != null) {
            if (isErrorPath(eventData)) {
                return;
            }
            if (null != result) {
                eventData.setOutput(resolveResult(result));
            }
        }
    }

    @AfterThrowing(pointcut = "webRequestLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        AuditLogEventData eventData = collect();
        if (eventData != null) {
            eventData.setExceptionTrace(e.getMessage());
            if (e instanceof Exception) {
                eventData.getCommonAttribute().put("errorCode", ((Exception) e));
            }
            eventData.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    protected boolean isErrorPath(AuditLogEventData auditLogEventData) {
        return (this.errorProperties != null && StringUtils.equals(auditLogEventData.getUrl(), this.errorProperties.getPath()));
    }
}
