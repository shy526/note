package com.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.dubbo.protocol.AddService;

public class ConSumerThr {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		//��ȡ����Ĵ������
		AddService proxy=(AddService) context.getBean("AddService");
		//ͨ�����������÷��񷽷�
		int result=proxy.add(2, 3);
		for (;;) {
		System.out.println("�ͻ����յ����:"+proxy.add(1, 3));
		Thread.sleep(500);
		
		}
	}

}
