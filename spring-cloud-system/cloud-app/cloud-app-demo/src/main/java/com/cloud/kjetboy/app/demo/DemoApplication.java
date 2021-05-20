package com.cloud.kjetboy.app.demo;

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
@MapperScan(basePackages= {"com.cloud.kjetboy.server.demo.mapper"})
@SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.demo"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}