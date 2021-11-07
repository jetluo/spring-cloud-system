package com.cloud.kjetboy.server.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Receiver
 * @Description 消费repair的消息
 * @Author jet
 * @Date 2021/6/27 14:59
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "repair")
public class Receiver {
    @RabbitHandler
    public void process(String repair){
        System.out.println(repair);
    }

}
