package com.demo.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
	
	
	public static void main(String[] args) throws Exception {
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationProvider.xml" });
				context.start();
				System.out.println("��������");
				while(true);//���ַ����߳�һֱ����

	}
}
