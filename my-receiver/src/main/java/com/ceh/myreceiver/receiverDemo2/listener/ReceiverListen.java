package com.ceh.myreceiver.receiverDemo2.listener;

import com.ceh.myreceiver.receiverDemo2.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by enHui.Chen on 2018/2/26 0026.
 */
@Component
public class ReceiverListen {
    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    @RabbitListener(queues = "direct.1")
    public void handlerMsg(String user) throws IOException {
        System.out.println("direct.1:" + objectMapper.readValue(user, User.class));
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.1")
    public void handlerTopicMsg1(String user) throws IOException {
        System.out.println("topic.1:" + objectMapper.readValue(user, User.class));
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.2")
    public void handlerTopicMsg2(String user) {
        try {
            System.out.println("topic.2:" + objectMapper.readValue(user, User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.1")
    public void handlerFanoutMsg1(String user) throws IOException {
        System.out.println("fanout.1:" + objectMapper.readValue(user, User.class));
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.2")
    public void handlerFanoutMsg2(String user) throws IOException {
        System.out.println("fanout.2:" + objectMapper.readValue(user, User.class));
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.3")
    public void handlerFanoutMsg3(String user) throws IOException {
        System.out.println("fanout.3:" + objectMapper.readValue(user, User.class));
    }

}
