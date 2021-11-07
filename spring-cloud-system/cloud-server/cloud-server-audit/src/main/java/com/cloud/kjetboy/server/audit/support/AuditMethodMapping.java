package com.cloud.kjetboy.server.audit.support;

import com.cloud.kjetboy.server.audit.annotation.Audit;
import com.cloud.kjetboy.server.audit.entity.AuditInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jet
 */
public class AuditMethodMapping extends WebApplicationObjectSupport implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Method, AuditInfo> auditMapping = new HashMap<>();

    public AuditInfo findAuditInfo(Method method) {
        return this.auditMapping.get(method);
    }

    /**
     * 入口
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initAuditMethods();
    }

    private void initAuditMethods() {
        String[] beanNames = getApplicationContext().getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            if (!beanName.startsWith("scopedTarget.")) {
                Class<?> beanType = null;
                try {
                    beanType = getApplicationContext().getType(beanName);
                } catch (Throwable ex) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("Could not resolve target class for bean with name '" + beanName + "'", ex);
                    }
                }
                if (beanType != null && isHandler(beanType)) {
                    detectAuditMethods(beanName);
                }
            }
        }
    }

    private boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
    }

    private void detectAuditMethods(Object handler) {
        Class<?> handlerType = (handler instanceof String) ? getApplicationContext().getType((String) handler) : handler.getClass();
        final Class<?> userType = ClassUtils.getUserClass(handlerType);
        Map<Method, AuditInfo> methods = MethodIntrospector.selectMethods(userType, new MethodIntrospector.MetadataLookup<AuditInfo>() {
            @Override
            public AuditInfo inspect(Method method) {
                try {
                    return AuditMethodMapping.this.getAuditForMethod(method, userType);
                } catch (Throwable ex) {
                    throw new IllegalStateException("Invalid mapping on handler class [" + userType
                            .getName() + "]: " + method, ex);
                }
            }
        });
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(methods.size() + " request handler methods found on " + userType + ": " + methods);
        }
        for (Map.Entry<Method, AuditInfo> entry : methods.entrySet()) {
            Method invocableMethod = AopUtils.selectInvocableMethod(entry.getKey(), userType);
            AuditInfo mapping = entry.getValue();
            registerAuditMethod(handler, invocableMethod, mapping);
        }

    }

    private AuditInfo getAuditForMethod(Method method, Class<?> userType) {
        AuditInfo auditInfo = createAuditInfo(method);
        if (auditInfo != null) {
            String parentName = findAuditParentName(userType);
            auditInfo.setCategoryName(parentName + auditInfo.getCategoryName());
        }
        return auditInfo;
    }
    private AuditInfo createAuditInfo(Method method) {
        Audit audit = (Audit)AnnotatedElementUtils.findMergedAnnotation(method, Audit.class);
        return (audit == null) ? null : createAuditInfo(audit);
    }
    private AuditInfo createAuditInfo(Audit audit) {
        AuditInfo auditInfo = new AuditInfo();
        auditInfo.setCategoryCode(audit.categoryCode());
        auditInfo.setCategoryName(audit.categoryName());
        auditInfo.setParentName(audit.parentName());
        auditInfo.setEventLevel(audit.eventLevel());
        auditInfo.setEventType(audit.eventType());
        auditInfo.setMessage(audit.message());
        auditInfo.setAnalysis(audit.analysis());
        auditInfo.setMessageTemplate(audit.messageTemplate());
        auditInfo.setMessageTemplateCode(audit.messageTemplateCode());
        auditInfo.setOptType(audit.optType());
        auditInfo.setDefaultAuditItem(audit.defaultAuditItem());
        return auditInfo;
    }
    private String findAuditParentName(Class<?> userType) {
        Audit pAuditDesc = (Audit)AnnotatedElementUtils.findMergedAnnotation(userType, Audit.class);
        return (pAuditDesc == null || StringUtils.isBlank(pAuditDesc.parentName())) ? "" : pAuditDesc.parentName();
    }
    private void registerAuditMethod(Object handler, Method invocableMethod, AuditInfo mapping) {
        if (mapping != null) {
            this.auditMapping.put(invocableMethod, mapping);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(invocableMethod + ":" + mapping);
            }
        }
    }

}
