package com.cloud.kjetboy.server.audit.collector;

import com.cloud.kjetboy.server.audit.entity.AuditLogEventData;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.core.env.Environment;

/**
 * @author jet
 */
public class CommonAuditLogCollector extends AbstractAuditLogCollector {
    public static final String SPRING_APPLICATION_NAME = "spring.application.name";

    public static final String SERVER_PORT = "server.port";

    private final Environment environment;

    private final InetUtils inetUtils;

    public CommonAuditLogCollector(InetUtils inetUtils, Environment environment) {
        this.inetUtils = inetUtils;
        this.environment = environment;
    }

    @Override
    protected AuditLogEventData wrapAuditLogEventData(AuditLogEventData auditLogEventData) {
//        auditLogEventData.setAppCode(getAppCode());
//        auditLogEventData.setAppName(getAppName());
//        auditLogEventData.setAppPort(getPort());
        auditLogEventData.addCommonAttribute("threadName", Thread.currentThread().getName());
        auditLogEventData.addCommonAttribute("threadId", Long.valueOf(Thread.currentThread().getId()));
        auditLogEventData.addCommonAttribute("serviceIp", getIpAddress());
        return super.wrapAuditLogEventData(auditLogEventData);
    }

//    private String getAppCode() {
//        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver((PropertyResolver)this.environment, "spring.application.");
//        return resolver.getProperty("name");
//    }
//
//    private String getAppName() {
//        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver((PropertyResolver)this.environment, "info.");
//        return resolver.getProperty("service-name", getAppCode());
//    }
//
//    private String getPort() {
//        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver((PropertyResolver)this.environment, "server.");
//        return resolver.getProperty("port", "8080");
//    }

    private String getIpAddress() {
        if (this.inetUtils == null){
            return null;}
        return this.inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
    }
}
