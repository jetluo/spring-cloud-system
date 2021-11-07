package com.cloud.kjetboy.server.audit.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jet
 */
@Component
@ConfigurationProperties(prefix = "cloud.audit")
public class AuditProperties implements InitializingBean {
    private boolean enabled = true;
    private static final String[] DEFAULT_EXCLUDE_URL = new String[] {
            "/swagger-ui.html", "/**/swagger-resources/**", "/**/error/**", "/health", "/info", "/favicon.ico", "/**/webjars/**", "/**/v2/api-docs/**", "/**/applications/**", "/**/*.css",
            "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.map" };
    private List<String> ignored = new ArrayList<>();

    public List<String> getIgnored() {
        return this.ignored;
    }

    public void setIgnored(List<String> ignored) {
        this.ignored = ignored;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        this.ignored.addAll(Arrays.asList(DEFAULT_EXCLUDE_URL));
        Set<String> set = new HashSet<>(this.ignored);
        this.ignored = new ArrayList<>(set);
    }

}
