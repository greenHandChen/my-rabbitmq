package com.ceh.myreceiver.receiverDemo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by enHui.Chen on 2018/2/24 0024.
 */
@SuppressWarnings("deprecation")
public class ReceiverDemo1 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建，设置连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setHost("192.168.31.128");
        connectionFactory.setPort(5672);
        connectionFactory.setConnectionTimeout(60000);
        // 获取连接
        Connection connection = connectionFactory.newConnection();
        // 获取信道
        final Channel channel = connection.createChannel();
        // 设置交换器
        channel.exchangeDeclare("directExchange", "direct", true);
        // 获取队列
        String queueName = channel.queueDeclare().getQueue();
        System.out.println(queueName);
        // 将交换器与队列绑定 队列，交换器名，绑定规则
        channel.queueBind(queueName, "directExchange", "hello-msg");
        // 创建接收者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 队列名称，开启确认消息（确保该消息一定被接收，若未被处理，则将该消息移交其他接收方），接收者
        channel.basicConsume(queueName, true, consumer);
        // 接收消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("Received '" + message + "'");
        }

    }
}
