package dao;

import java.util.Set;

import domainmodel.*;

public interface OrderDAO {

	public void addOrder(Order o);
	
	public void payOrder(Order o);
	
	public Order retrieveOrder(int id);
	
	public Set<Order> retrieveOrders(Customer c);
	
}
