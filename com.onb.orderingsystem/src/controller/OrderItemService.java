package controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderItemDAO;

public class OrderItemService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
	
	
}
