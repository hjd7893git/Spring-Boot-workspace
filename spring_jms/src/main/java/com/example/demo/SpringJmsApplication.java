package com.example.demo;

import com.example.demo.jms.Msg;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class SpringJmsApplication implements CommandLineRunner {
	/**
	 * Spring Boot为我们提供了CommandLineRunner接口，用
	 于程序启动后执行的代码，通过重写其run方法执行
	 */
	@Autowired
	JmsTemplate jmsTemplate; //2 注入Spring Boot为我们配置好的JmsTemplate的Bean。
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.convertAndSend("my-destination","hahah");
		jmsTemplate.convertAndSend(new ActiveMQQueue("my-destination"),"21211r");
		jmsTemplate.send("my-destination", new Msg());
		//3通过JmsTemplate的send方法向my-destination目的地发送Msg的消息，这里也等于在消息代理上定义了一个目的地叫my-destination。
	}

}
