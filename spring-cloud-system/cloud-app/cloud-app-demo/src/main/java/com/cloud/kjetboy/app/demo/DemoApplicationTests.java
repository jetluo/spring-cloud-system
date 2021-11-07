package com.cloud.kjetboy.app.demo;

import com.cloud.kjetboy.server.demo.controller.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName DemoApplicationTests
 * @Description TODO
 * @Author jet
 * @Date 2021/6/27 15:05
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
    @Autowired
    private Sender sender;
    @Test
    public void sender(){
        sender.send();
    }
}
