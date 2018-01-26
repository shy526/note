package com.demo.dubbo.provider;

import com.demo.dubbo.protocol.AddService;

public class AddServiceImpl implements AddService {
	
	public int add(int x, int y) {
		System.out.println("服务端已收到数据:"+x+":"+y);
		System.out.println("此服务提供的是两个数的加法运算");
		return x+y;
	}
	
}
