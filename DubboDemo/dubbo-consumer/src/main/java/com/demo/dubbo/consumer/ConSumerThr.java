package com.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.dubbo.protocol.AddService;

public class ConSumerThr {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		//获取服务的代理对象
		AddService proxy=(AddService) context.getBean("AddService");
		//通过代理对象调用服务方法
		int result=proxy.add(2, 3);
		for (;;) {
		System.out.println("客户端收到结果:"+proxy.add(1, 3));
		Thread.sleep(500);
		
		}
	}

}
