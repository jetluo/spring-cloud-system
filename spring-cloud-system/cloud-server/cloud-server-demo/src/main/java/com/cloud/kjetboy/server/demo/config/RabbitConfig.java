package com.cloud.kjetboy.server.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author jet
 * @Date 2021/6/27 15:02
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public Queue repairQueue(){
        return  new Queue("repair");
    }
}
