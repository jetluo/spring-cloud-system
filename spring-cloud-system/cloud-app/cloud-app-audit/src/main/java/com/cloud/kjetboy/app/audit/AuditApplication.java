package com.cloud.kjetboy.app.audit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 微服务应用服务启动类
 * @author jet
 *
 */
@ServletComponentScan
@MapperScan(basePackages= {"com.cloud.kjetboy.server.audit.mapper"})
@SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.audit"})
public class AuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditApplication.class, args);
    }

}
