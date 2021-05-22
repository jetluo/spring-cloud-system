package com.cloud.kjetboy.server.demo.annotation;

import com.cloud.kjetboy.server.demo.entity.AuditInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
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

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
