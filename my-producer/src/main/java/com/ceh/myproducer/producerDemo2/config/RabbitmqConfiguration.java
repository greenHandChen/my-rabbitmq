package com.ceh.myproducer.producerDemo2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
        return new RabbitTemplate(connectionFactory());
    }

    // =====================设置交换器 start=====================
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
}
