package com.onb.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.OrderItemDAO;


public class OrderItemServiceImpl {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
	
	
}
