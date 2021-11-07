package com.cloud.kjetboy.server.audit.collector;


import com.cloud.kjetboy.server.audit.config.AuditProperties;
import com.cloud.kjetboy.server.audit.entity.AuditInfo;
import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import com.cloud.kjetboy.server.audit.entity.EventType;
import com.cloud.kjetboy.server.audit.support.AuditMethodMapping;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jet
 */
public class SpringAOPAuditLogCollector extends AbstractAuditLogCollector {
    private InputParamResolver inputParamResolver = (InputParamResolver)new DefaultInputParamResolver();

    private OutputResultResolver outputResultResolver = (OutputResultResolver)new DefaultOutputResultResolver();

    private AuditProperties AuditProperties;

    private AuditMethodMapping auditMethodMapping;

    public SpringAOPAuditLogCollector(AuditProperties AuditProperties, AuditMethodMapping auditMethodMapping) {
        this.AuditProperties = AuditProperties;
        this.auditMethodMapping = auditMethodMapping;
    }

    @Override
    protected AuditLogEventData wrapAuditLogEventData(AuditLogEventData auditLogEventData) {
        return super.wrapAuditLogEventData(auditLogEventData);
    }

    protected Map<String, Object> getBodyObject(Method method, Object[] args) {
        return this.inputParamResolver.resolve(method, args);
    }



    protected String resolveResult(Object result) {
//        if (!this.AuditProperties.getOutput().isEnabled()){
//            return null;}
        return this.outputResultResolver.resolve(result);
    }

    protected void beforeMethodInvoke(AuditLogEventData auditLogEventData, JoinPoint joinPoint) {
        auditLogEventData.setEventType(EventType.METHOD);
        auditLogEventData.setMethodName(joinPoint.getSignature().getName());
        auditLogEventData.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        Method method = getMethod(joinPoint);
        Map<String, Object> input = getBodyObject(method, joinPoint.getArgs());
        if (input != null){
            auditLogEventData.getInput().putAll(input);}
        AuditInfo auditInfo = findAuditDescInfo(method);
        if (auditInfo != null) {
            auditLogEventData.setAuditInfo(auditInfo);
            if (StringUtils.isNotBlank(auditInfo.getMessage())){
                auditLogEventData.setMessageText(auditInfo.getMessage());}
        }
    }

    protected void afterMethodInvoke(AuditLogEventData auditLogEventData, JoinPoint joinPoint) {
        if (auditLogEventData != null && auditLogEventData.getCommonAttribute() != null && auditLogEventData
                .getCommonAttribute().get("objectId") == null) {
            Method method = getMethod(joinPoint);
            String id = "";// getIdValue(method, joinPoint.getArgs());
            auditLogEventData.addCommonAttribute("objectId", id);
        }
    }

    protected Map<String, Object> getSignature(JoinPoint joinPoint) {
        Map<String, Object> maps = new LinkedHashMap<>();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        maps.put("className", className);
        maps.put("methodName", methodName);
        String classTemp = "." + joinPoint.getStaticPart().getSignature().getDeclaringType().getSimpleName();
        String packageName = className.replace(classTemp, "");
        maps.put("packageName", packageName);
        return maps;
    }

    protected boolean isRequestMappingMethod(Method method) {
        return (AnnotationUtils.getAnnotation(method, RequestMapping.class) != null);
    }

    protected Method getMethod(JoinPoint joinPoint) {
        MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint)joinPoint;
        MethodSignature methodSignature = (MethodSignature)methodInvocationProceedingJoinPoint.getSignature();
        return methodSignature.getMethod();
    }

    protected AuditInfo findAuditDescInfo(Method method) {
        return (this.auditMethodMapping != null) ? this.auditMethodMapping.findAuditInfo(method) : null;
    }

    public void setInputParamResolver(InputParamResolver inputParamResolver) {
        this.inputParamResolver = inputParamResolver;
    }

    public void setOutputResultResolver(OutputResultResolver outputResultResolver) {
        this.outputResultResolver = outputResultResolver;
    }
}