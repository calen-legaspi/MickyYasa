package com.onb.services.impl;


import java.util.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.*;
import com.onb.domainmodel.*;



public class OrderServiceImpl {
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	private OrderDAO orderDAO = (OrderDAO)ctx.getBean("orderDAO");
	

	public OrderServiceImpl(){
		
	}

	
	public int getLastOrderNumber(){
		return orderDAO.getLastOrderNumber();
	}
	
	public void addOrderToDB(Order o) {
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		InventoryServiceImpl inventoryServiceImpl = new InventoryServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		
		Customer customer = customerServiceImpl.getCustomer(o.getCustomerID());
		customer.setOrders(orderServiceImpl.retrieveUnpaidOrders(customer));
		customer.addOrder(o);
		if(customer.getOrders().contains(o)){
			orderDAO.addOrder(o);
			List<OrderItem> items = o.getItems();
			Inventory inventory = new Inventory(inventoryServiceImpl.getAllAvailableProductsInDB());
			for(OrderItem i: items){
				Product product = productServiceImpl.getProduct(i.getItemSKUNumber());
				InventoryItem inventoryitem = inventoryServiceImpl.getInventoryItem(inventory,product);
				inventoryServiceImpl.deductFromInventory(inventory, inventoryitem, i.getQuantity());
			}
		}
	}

	public void updateStatusOfOrderInDB(Order o) {
		orderDAO.payOrder(o);
	}
	
	public Order retrieveOrderFromDB(int orderNumber){
		return orderDAO.retrieveOrder(orderNumber);
	}
	
	public List<Order> retrieveOrdersFromDB(Customer c){
		return orderDAO.retrieveOrders(c);
	}
	
	public void deleteOrderItem(Order order, int itemIndex){
		List<OrderItem> items = order.getItems();
		OrderItem itemToBeRemoved = items.get(itemIndex);
		order.deleteItem(itemToBeRemoved);
	}

	public Customer addOrdersFromDB(Customer c) {
		Customer newCustomer = new Customer(c.getID());
		List<Order> orders = orderDAO.retrieveOrders(c);
		for(Order o: orders){
			newCustomer.addOrder(o);
		}
		return newCustomer;
	}
	
	public List<Order> retrieveUnpaidOrders(Customer customer){
		List<Order> orders = orderDAO.retrieveUnpaidOrders(customer.getID());
		return orders;
	}
}
