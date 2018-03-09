package com.ceh.myreceiver.receiverDemo2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by enHui.Chen on 2018/2/26 0026.
 */
@Configuration
public class RabbitmqConfiguration {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    // 在此种连接模式下，连接和信道都是被缓存的
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    // 连接和信道是缓存的操作模版
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }
    // =====================设置交换器 start=====================
    // 1.direct交换器
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    // 2.topic交换器
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    // 3.fanout交换器
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
    // =====================设置交换器 end=====================


    // =====================设置队列 start=====================
    // 1.用于direct交换器的队列
    @Bean
    public Queue directQueue() {
        return new Queue("direct.1");
    }

    // 2.用于topic交换器的队列
    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.2");
    }

    // 3.用于fanout交换器的队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.2");
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout.3");
    }
    // =====================设置队列 end=====================


    // =====================设置绑定规则 start=====================
    // 1.direct交换器默认
    @Bean
    public Binding bindingDirect(Queue directQueue, DirectExchange directExchange) {
        // 完全匹配
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct.1");
    }

    // 2.topic交换器的绑定，交换器接收消息后将根据消息的routing key去匹配binding key,若匹配上了将消息路由分发到对应的队列
    @Bean
    public Binding bindingTopicX(Queue topicQueue1, TopicExchange topicExchange) {
        // 完全匹配
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("topic.1");
    }

    @Bean
    public Binding bindingTopicXX(Queue topicQueue2, TopicExchange topicExchange) {
        // 模糊匹配，将匹配所有以topic.开头的消息
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("topic.#");
}

    // 3.fanout交换器的绑定，交换器会将接收到的消息路由分发到所有与自身绑定的队列上
    @Bean
    public Binding bindingFanout1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanout2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanout3(Queue fanoutQueue3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
    // =====================设置绑定规则 end=====================

}
