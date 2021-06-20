package com.cloud.kjetboy.app.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 微服务应用服务启动类
 * @author jet
 *,"com.cloud.kjetboy.server.audit.mapper"
 * ,"com.cloud.kjetboy.server.audit"
 */
@ServletComponentScan
@EnableCaching
@SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.sso"})
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}