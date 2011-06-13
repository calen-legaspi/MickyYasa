package controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderItemDAO;

public class OrderItemService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderItemDAO orderItem = (OrderItemDAO)ctx.getBean("OrderItemDao");
	
	
}
