package com.cloud.kjetboy.server.audit.config;

import com.cloud.kjetboy.server.audit.collector.*;
import com.cloud.kjetboy.server.audit.filter.AuditFilter;
import com.cloud.kjetboy.server.audit.logger.AuditFactory;
import com.cloud.kjetboy.server.audit.logger.AuditLogger;
import com.cloud.kjetboy.server.audit.logger.ClientAuditLogger;
import com.cloud.kjetboy.server.audit.support.AuditMethodMapping;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author jet
 */
@Configuration
@EnableConfigurationProperties({AuditProperties.class})
@ConditionalOnProperty(prefix = "cloud.audit", name = {"enabled"}, matchIfMissing = true)
public class AuditLogCollectorAutoConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public AuditLogCollectors auditLogCollectors(ObjectProvider<InetUtils> inetUtils, Environment environment) {
        AuditLogCollectors auditLogCollectors = new AuditLogCollectors();
        auditLogCollectors.setAuditLogCollectors(createDefultAuditLogCollectors((InetUtils)inetUtils.getIfAvailable(), environment));
        return auditLogCollectors;
    }

    protected List<AuditLogCollector> createDefultAuditLogCollectors(InetUtils inetUtils, Environment environment) {
        return Arrays.asList(new AuditLogCollector[] { (AuditLogCollector)new RequestAuditLogCollector(), (AuditLogCollector)new CurrentPrincipalAuditLogCollector(), (AuditLogCollector)new CommonAuditLogCollector(inetUtils, environment) });
    }

    @Bean({"auditFilterFilterRegistrationBean"})
    @ConditionalOnProperty(prefix = "cloud.audit.filter", name = {"enabled"}, matchIfMissing = true)
    @ConditionalOnMissingBean(name = {"auditFilterFilterRegistrationBean"})
    public FilterRegistrationBean auditFilterFilterRegistrationBean(AuditProperties auditProperties, AuditLogCollectors auditLogCollectors) {
        AuditFilter auditFilter = new AuditFilter(auditProperties, auditLogCollectors);
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter((Filter)auditFilter);
        frBean.addUrlPatterns(new String[] { "/*" });
        frBean.setOrder(10);
        frBean.setName("cloudAuditFilter");
        return frBean;
    }

    @Bean
    public AuditFactory auditFactory(AuditLogger auditLogger) {
        return new AuditFactory(auditLogger);
    }

    @Bean
    public AuditMethodMapping auditMethodMapping() {
        return new AuditMethodMapping();
    }

//    @Configuration
//    public static class HTTPAuditLoggerConfiguration {
//        @Autowired(required = false)
//        @Qualifier("auditExecutorService")
//        private ExecutorService executorService;
//
//        @Bean
//        @ConditionalOnMissingBean
//        public AuditLogger auditLogger(AuditProperties auditProperties, RestTemplate restTemplates) {
//            HTTPAuditLogger auditLogger = new HTTPAuditLogger(auditProperties, restTemplates);
//            if (this.executorService != null){
//                auditLogger.setThreadPool(this.executorService);
//            }
//            return (AuditLogger)auditLogger;
//        }
//    }
    @Configuration
    public static class ClientAuditLoggerConfiguration {
        @Autowired(required = false)
        @Qualifier("clientAuditExecutorService")
        private ExecutorService executorService;

        @Bean
        @ConditionalOnMissingBean
        public AuditLogger auditLogger(AuditProperties auditProperties, RestTemplate restTemplates) {
            ClientAuditLogger auditLogger = new ClientAuditLogger(auditProperties, restTemplates);
            if (this.executorService != null){
                auditLogger.setThreadPool(this.executorService);
            }
            return (AuditLogger)auditLogger;
        }
    }

    @Configuration
    public static class ControllerAuditLogCollectorConfiguration {
        @Autowired(required = false)
        private ErrorProperties errorProperties;

        @Autowired(required = false)
        private InputParamResolver inputParamResolver;

        @Autowired(required = false)
        private OutputResultResolver outputResultResolver;

        @Bean
        @ConditionalOnMissingBean
        public ControllerAuditLogCollector controllerAuditLogCollector(AuditProperties auditProperties, AuditMethodMapping auditMethodMapping) {
            ControllerAuditLogCollector controllerAuditLogCollector = new ControllerAuditLogCollector(this.errorProperties, auditProperties, auditMethodMapping);
            if (this.inputParamResolver != null){
                controllerAuditLogCollector.setInputParamResolver(this.inputParamResolver);}
            if (this.outputResultResolver != null){
                controllerAuditLogCollector.setOutputResultResolver(this.outputResultResolver);}
            return controllerAuditLogCollector;
        }
    }
}
