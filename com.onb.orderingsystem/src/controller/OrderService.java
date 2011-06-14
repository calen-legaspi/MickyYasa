package controller;


import java.util.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.*;
import domainmodel.*;


public class OrderService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
	

	public OrderService(){
		
	}

	public static void addAllOfCustomersOrdersToDB(Customer c){
	}
	
	public static int getLastOrderNumber(){
		return orderDao.getLastOrderNumber();
	}
	
	public static void addOrderToDB(Order o) {
		orderDao.addOrder(o);
		List<OrderItem> items = o.getItems();
		Inventory inventory = new Inventory(InventoryService.getAllAvailableProductsInDB());
		for(OrderItem i: items){
			Product product = ProductService.getProduct(i.getItemSKUNumber());
			InventoryItem inventoryitem = InventoryService.getInventoryItem(inventory,product);
			InventoryService.deductFromInventory(inventory, inventoryitem, i.getQuantity());
		}
	}

	public static void updateStatusOfOrderInDB(Order o) {
		orderDao.payOrder(o);
	}
	
	public static Order retrieveOrderFromDB(int orderNumber){
		return orderDao.retrieveOrder(orderNumber);
	}
	
	public List<Order> retrieveOrdersFromDB(Customer c){
		return orderDao.retrieveOrders(c);
	}
	
	public static void deleteOrderItem(Order order, int itemIndex){
		List<OrderItem> items = order.getItems();
		OrderItem itemToBeRemoved = items.get(itemIndex);
		order.deleteItem(itemToBeRemoved);
	}

	public static Customer addOrdersFromDB(Customer c) {
		Customer newCustomer = new Customer(c.getID());
		List<Order> orders = orderDao.retrieveOrders(c);
		for(Order o: orders){
			newCustomer.addOrder(o);
		}
		return newCustomer;
	}
	
	public static List<Order> retrieveUnpaidOrders(Customer customer){
		List<Order> orders = orderDao.retrieveUnpaidOrders(customer.getID());
		return orders;
	}
}
