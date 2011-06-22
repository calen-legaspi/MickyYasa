package com.onb.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.OrderItemDAO;


public class OrderItemService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
	
	
}
