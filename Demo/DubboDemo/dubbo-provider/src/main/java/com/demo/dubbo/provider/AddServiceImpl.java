package com.demo.dubbo.provider;

import com.demo.dubbo.protocol.AddService;

public class AddServiceImpl implements AddService {
	
	public int add(int x, int y) {
		System.out.println("��������յ�����:"+x+":"+y);
		System.out.println("�˷����ṩ�����������ļӷ�����");
		return x+y;
	}
	
}
