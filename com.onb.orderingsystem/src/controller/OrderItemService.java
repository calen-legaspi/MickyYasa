package controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderItemDAO;

public class OrderItemService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	OrderItemDAO orderItem = (OrderItemDAO)ctx.getBean("OrderItemDao");
	
	
}
