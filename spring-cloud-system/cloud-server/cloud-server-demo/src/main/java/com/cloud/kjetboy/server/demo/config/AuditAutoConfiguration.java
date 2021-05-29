package com.cloud.kjetboy.server.demo.config;


import com.cloud.kjetboy.server.demo.annotation.AuditMethodMapping;
import com.cloud.kjetboy.server.demo.collector.ControllerAuditLogCollector;
import com.cloud.kjetboy.server.demo.filter.AuditFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author jet
 */
@Configuration
@EnableConfigurationProperties({AuditLogProperties.class})
@ConditionalOnProperty(prefix = "cloud.audit", name = {"enabled"}, matchIfMissing = true)
public class AuditAutoConfiguration {

    @Bean({"auditFilterFilterRegistrationBean"})
    @ConditionalOnProperty(prefix = "cloud.audit.filter", name = {"enabled"}, matchIfMissing = true)
    @ConditionalOnMissingBean(name = {"auditFilterFilterRegistrationBean"})
    public FilterRegistrationBean auditFilterFilterRegistrationBean(AuditLogProperties auditLogProperties) {
        AuditFilter auditFilter = new AuditFilter();
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter((Filter)auditFilter);
        frBean.addUrlPatterns(new String[] { "/*" });
        frBean.setOrder(10);
        frBean.setName("cloudAuditFilter");
        return frBean;
    }

    @Bean
    public AuditMethodMapping auditDescMethodMapping() {
        return new AuditMethodMapping();
    }

    @Configuration
    public static class ControllerAuditLogCollectorConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ControllerAuditLogCollector controllerAuditLogCollector(AuditMethodMapping auditMethodMapping) {
            ControllerAuditLogCollector controllerAuditLogCollector = new ControllerAuditLogCollector(auditMethodMapping);

            return controllerAuditLogCollector;
        }
    }
}
