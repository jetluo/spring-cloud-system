package com.cloud.kjetboy.server.demo.annotation;

import com.cloud.kjetboy.server.demo.entity.AuditInfo;
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
 * v0.1
 *
 */
public class AuditMethodMapping extends WebApplicationObjectSupport implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Method, AuditInfo> auditMapping = new HashMap<>();

    public AuditInfo findAuditInfo(Method method) {
        return this.auditMapping.get(method);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanNames = getApplicationContext().getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            if (!beanName.startsWith("scopedTarget.")) {
                Class<?> beanType = null;
                try {
                    beanType = getApplicationContext().getType(beanName);
                } catch (Throwable ex) {
                    if (this.logger.isDebugEnabled()){
                        this.logger.debug("Could not resolve target class for bean with name '" + beanName + "'", ex);}
                }
                if (beanType != null && isHandler(beanType)){
                    Object handler = beanType;
                    Class<?> handlerType = (handler instanceof String) ? getApplicationContext().getType((String)handler) : handler.getClass();
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
                      //  registerAuditMethod(handler, invocableMethod, mapping);
                    }
                }

            }
        }
    }
    protected boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
    }
    protected AuditInfo getAuditForMethod(Method method, Class<?> userType) {
        AuditInfo auditDescInfo = createAuditDescInfo(method);
        if (auditDescInfo != null) {
            String parentName = findAuditParentName(userType);
            auditDescInfo.setCategoryName(parentName + auditDescInfo.getCategoryName());
        }
        return auditDescInfo;
    }
    protected AuditInfo createAuditDescInfo(Method method) {
        Audit auditDesc = (Audit)AnnotatedElementUtils.findMergedAnnotation(method, Audit.class);
        return (auditDesc == null) ? null : createAuditDescInfo(auditDesc);
    }
    protected String findAuditParentName(Class<?> userType) {
        Audit pAuditDesc = (Audit)AnnotatedElementUtils.findMergedAnnotation(userType, Audit.class);
        return (pAuditDesc == null || StringUtils.isBlank(pAuditDesc.parentName())) ? "" : pAuditDesc.parentName();
    }
    protected AuditInfo createAuditDescInfo(Audit auditDesc) {
        AuditInfo auditDescInfo = new AuditInfo();
        auditDescInfo.setCategoryCode(auditDesc.categoryCode());
        auditDescInfo.setCategoryName(auditDesc.categoryName());
        auditDescInfo.setParentName(auditDesc.parentName());
        auditDescInfo.setEventLevel(auditDesc.eventLevel());
        auditDescInfo.setEventType(auditDesc.eventType());
        auditDescInfo.setMessage(auditDesc.message());
//        auditDescInfo.setAnalysis(auditDesc.analysis());
//        auditDescInfo.setMessageTemplate(auditDesc.messageTemplate());
//        auditDescInfo.setMessageTemplateCode(auditDesc.messageTemplateCode());
        auditDescInfo.setOptType(auditDesc.optType());
        auditDescInfo.setDefaultAuditItem(auditDesc.defaultAuditItem());
//        AuditNotice[] auditNotices = auditDesc.auditNotices();
//        if (auditNotices != null && auditNotices.length > 0) {
//            List<AuditNoticeInfo> auditNoticeInfos = (List<AuditNoticeInfo>) Stream.<AuditNotice>of(auditNotices).map(this::createAuditNoticeInfo).collect(Collectors.toList());
//            auditDescInfo.setAuditNoticeInfos(auditNoticeInfos);
//        }
        return auditDescInfo;
    }
}
