package com.cloud.kjetboy.app.query;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


/**
 * 微服务应用服务启动类
 * @author jet
 *,"com.cloud.kjetboy.server.audit.mapper"
 * ,"com.cloud.kjetboy.server.audit"
 */
@ServletComponentScan
@MapperScan(basePackages= {"com.cloud.kjetboy.server.query.mapper"})
@SpringBootApplication(scanBasePackages={"com.cloud.kjetboy.server.query"})
@EnableElasticsearchRepositories(basePackages = {"com.cloud.kjetboy.server.query.repositories"})
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

}