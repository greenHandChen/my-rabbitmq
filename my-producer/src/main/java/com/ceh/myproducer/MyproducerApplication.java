package com.ceh.myproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 总结：发布方
// 1.创建连接
// 2.指明交换器
// 3.发送消息
@SpringBootApplication
public class MyproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyproducerApplication.class, args);
	}
}
