package dao;

import java.util.*;

import domainmodel.*;

public interface OrderDAO {

	public void addOrder(Order o);
	
	public void payOrder(Order o);
	
	public int getLastOrderNumber();
	
	public Order retrieveOrder(int id);
	
	public List<Order> retrieveOrders(Customer c);
	
	public List<Order> retrieveUnpaidOrders(int id);
	
}
