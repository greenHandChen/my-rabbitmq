package com.ceh.myreceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 总结：接收方
// 1.创建连接
// 2.声明交换器，队列
// 3.按照规则将交换器与队列绑定
// 4.监听对应队列
@SpringBootApplication
public class MyreceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyreceiverApplication.class, args);
	}
}
