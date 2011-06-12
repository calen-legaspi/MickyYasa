package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderDAO;
import domainmodel.*;


public class OrderService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");

	private OrderService(){
		
	}

	public static void addOrderToDB(Order o) {
		orderDao.addOrder(o);
	}

	public static void updateStatusOfOrderInDB(Order o) {
		orderDao.payOrder(o);
	}

	public static Set<Order> retrieveOrdersFromDB(Customer c) {
		return orderDao.retrieveOrders(c);
	}
}
