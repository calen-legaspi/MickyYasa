package controller;

import java.util.List;

import domainmodel.Customer;
import domainmodel.Order;

public interface OrderService {

	public abstract int getLastOrderNumber();

	public abstract void addOrderToDB(Order o);

	public abstract void updateStatusOfOrderInDB(Order o);

	public abstract Order retrieveOrderFromDB(int orderNumber);

	public abstract List<Order> retrieveOrdersFromDB(Customer c);

	public abstract void deleteOrderItem(Order order, int itemIndex);

	public abstract Customer addOrdersFromDB(Customer c);

	public abstract List<Order> retrieveUnpaidOrders(Customer customer);

}