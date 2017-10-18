package com.demo.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
	
	
	public static void main(String[] args) throws Exception {
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationProvider.xml" });
				context.start();
				System.out.println("服务启动");
				while(true);//保持服务线程一直开启

	}
}
