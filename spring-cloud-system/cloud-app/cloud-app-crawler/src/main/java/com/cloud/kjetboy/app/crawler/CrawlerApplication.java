package com.cloud.kjetboy.app.crawler;

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
@MapperScan(basePackages= {"com.cloud.kjetboy.server.crawler.mapper"})
@SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.crawler"})

public class CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }

}
