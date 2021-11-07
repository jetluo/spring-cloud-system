package com.cloud.kjetboy.server.zuul.config;

import com.cloud.kjetboy.server.zuul.entity.SafeProperties;
import com.cloud.kjetboy.server.zuul.filter.SafeZuulFilter;
import com.sgcc.uap.safe.filter.proxy.SafeFilterDelegateProxy;
import com.sgcc.uap.safe.service.CommonServiceFactory;
import com.sgcc.uap.safe.service.IServiceFactory;
import com.sgcc.uap.safe.util.proxy.ParamReaderUtilsProxy;
import com.sgcc.uap.safe.util.proxy.ServiceFactoryUtilsProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author jet
 * @Description 安全配置加载类
 * @Date 11:00 2021/6/25
 **/
@Configuration
@ConditionalOnClass({SafeFilterDelegateProxy.class})
@ConditionalOnProperty(prefix = "cloud.safe", name = {"enabled"}, matchIfMissing = false)
@EnableConfigurationProperties({SafeProperties.class})
public class SafeAutoConfiguration implements ApplicationContextAware, InitializingBean {

    @Autowired
    private SafeProperties safeProperties;

    private ApplicationContext applicationContext;


    /**
     * @return void
     * @Author jet
     * @Description //读取配置信息
     * @Date 2021/6/26
     * @Param []
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        String safeConfigFilePath = "classpath:/safeFilterConfig_model.properties";
        Properties properties = this.safeProperties.createProperties();
        properties.setProperty("SAFE_SPRINGBOOT_ENABLED", Boolean.toString(this.applicationContext.containsBean("safeZuulFilter")));
        ParamReaderUtilsProxy.setParamReader(new SafeParamReader(safeConfigFilePath, properties));
        ServiceFactoryUtilsProxy.setServiceFactory((IServiceFactory) new CommonServiceFactory());


    }

    /**
     * @return void
     * @Author jet
     * @Description 获取上下文
     * @Date 2021/6/25  11:08
     * @Param [applicationContext]
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @Author jet
     * @Description  SafeFilterDelegateProxy filter注入
     * @Date 2021/11/4
     * @Param []
     * @return FilterRegistrationBean
     **/
    @Bean
    public FilterRegistrationBean safeFilterDelegateProxyFilterBean(){
        FilterRegistrationBean fRegistrationBean = new FilterRegistrationBean();
        SafeFilterDelegateProxy safeFilterDelegateProxy= new SafeFilterDelegateProxy();
        fRegistrationBean.setFilter(safeFilterDelegateProxy);
        fRegistrationBean.setOrder(this.safeProperties.getFilter().getOrder().intValue());
        return  fRegistrationBean;
    }

    @Configuration
    @ConditionalOnClass(name = {"com.netflix.zuul.ZuulFilter"})
    static class SafeZuulConfiguration {
        @Bean({"safeZuulFilter"})
        public SafeZuulFilter safeZuulFilter() {
            return new SafeZuulFilter();
        }
    }

}
