package com.cloud.kjetboy.server.zuul;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RestController;




/**
 * 微服务应用服务启动类
 * @author jet
 */
@SpringBootApplication
@EnableZuulProxy
@RestController
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
