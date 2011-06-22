package com.onb.services.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.OrderItemDAO;


public class OrderItemServiceImpl {
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	private OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
	
	
}
