package com.cloud.kjetboy.server.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jet
 */
@ConfigurationProperties(prefix = "cloud.audit")
@Component
public class AuditLogProperties implements InitializingBean {

    private boolean enabled = true;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
