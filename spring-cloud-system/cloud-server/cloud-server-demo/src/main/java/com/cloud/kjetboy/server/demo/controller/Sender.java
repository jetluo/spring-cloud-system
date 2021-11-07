package com.cloud.kjetboy.server.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Sender
 * @Description 消息生产者
 * @Author jet
 * @Date 2021/6/27 14:54
 * @Version 1.0
 **/
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * @Author jet
     * @Description 发送消息
     * @Date 2021/6/27
     * @Param []
     * @return void
     **/
    public void send(){
        amqpTemplate.convertAndSend("repair", "{\"cluster_state\":{\"rabbit@58b8f5746f26\":\"running\"},\"description\":\"Default virtual host\",\"metadata\":{\"description\":\"Default virtual host\",\"tags\":[]},\"name\":\"/\",\"tags\":[],\"tracing\":false},{\"cluster_state\":{\"rabbit@58b8f5746f26\":\"running\"},\"description\":\"repair\",\"metadata\":{\"description\":\"repair\",\"tags\":[\"repair\"]},\"name\":\"repair\",\"tags\":[\"repair\"],\"tracing\":false}");

    }
}
