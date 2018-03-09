package com.ceh.myproducer.producerDemo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by enHui.Chen on 2018/2/24 0024.
 */
public class ProducerDemo1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建,设置连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setHost("192.168.31.128");
        connectionFactory.setConnectionTimeout(60000);
        connectionFactory.setPort(5672); // 默认
        // 获取连接
        Connection connection = connectionFactory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();
//        //指定一个队列
//        channel.queueDeclare("hello-msg", false, false, false, null);
//        //发送的消息
//        String message = "hello world!";
//        //往队列中发出一条消息
//        channel.basicPublish("", "hello-msg", null, message.getBytes());
        // 声明交换器 , 交换器名称，交换器类型,true
        channel.exchangeDeclare("directExchange","direct",true);
        // 发布消息 交换器名称，消息的路由key,null,消息内容
        channel.basicPublish("directExchange","hello-msg",null,"hello producerDemo1".getBytes());
        channel.close();
        connection.close();
    }
}
