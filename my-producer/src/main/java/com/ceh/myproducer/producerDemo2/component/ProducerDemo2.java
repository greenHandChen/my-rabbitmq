package com.ceh.myproducer.producerDemo2.component;

import com.ceh.myproducer.producerDemo2.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by enHui.Chen on 2018/2/26 0026.
 */
@Component
public class ProducerDemo2 {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendDirectMsg() throws JsonProcessingException {
        User user = new User();
        user.setName("direct");
        user.setSex("male");
        user.setAge(25);
        // 交换器,消息routing key(需要与direct交换器绑定的binding key 一致),消息内容
        this.rabbitTemplate.convertAndSend("directExchange", "direct.1", objectMapper.writeValueAsString(user));
    }

    public void sendTopicMsg() throws JsonProcessingException {
        User user = new User();
        user.setName("topic");
        user.setSex("male");
        user.setAge(24);
        // 交换器,消息routing key(需要与direct交换器绑定的binding key 一致),消息内容
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", objectMapper.writeValueAsString(user));
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.2", objectMapper.writeValueAsString(user));
    }

    public void sendFanoutMsg() throws JsonProcessingException {
        User user = new User();
        user.setName("fanout");
        user.setSex("male");
        user.setAge(24);
        // 交换器,消息routing key(需要与direct交换器绑定的binding key 一致),消息内容
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", objectMapper.writeValueAsString(user));
    }
}
