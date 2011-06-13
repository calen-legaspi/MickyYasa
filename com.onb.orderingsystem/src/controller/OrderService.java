package controller;

import java.util.Set;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderDAO;
import domainmodel.*;


public class OrderService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
	

	private OrderService(){
		
	}

	public static void addAllOfCustomersOrdersToDB(Customer c){
	}
	
	public static void addOrderToDB(Order o) {
		orderDao.addOrder(o);
	}

	public static void updateStatusOfOrderInDB(Order o) {
		orderDao.payOrder(o);
	}

	public static Customer addOrdersFromDB(Customer c) {
		Customer newCustomer = new Customer(c.getID());
		Set<Order> orders = orderDao.retrieveOrders(c);
		for(Order o: orders){
			newCustomer.addOrder(o);
		}
		return newCustomer;
	}
}
