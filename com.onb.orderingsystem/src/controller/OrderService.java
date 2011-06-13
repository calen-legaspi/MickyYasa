package controller;


import java.util.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderDAO;
import domainmodel.*;


public class OrderService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
	

	public OrderService(){
		
	}

	public static void addAllOfCustomersOrdersToDB(Customer c){
	}
	
	public static void addOrderToDB(Order o) {
		orderDao.addOrder(o);
	}

	public static void updateStatusOfOrderInDB(Order o) {
		orderDao.payOrder(o);
	}
	
	public Set<Order> retrieveOrdersFromDB(Customer c){
		return orderDao.retrieveOrders(c);
	}
	
	public static void deleteOrderItem(Order order, int itemIndex){
		List<OrderItem> items = order.getItems();
		OrderItem itemToBeRemoved = items.get(itemIndex);
		order.deleteItem(itemToBeRemoved);
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
