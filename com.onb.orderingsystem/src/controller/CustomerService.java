package controller;

import java.util.List;

import domainmodel.Customer;

public interface CustomerService {

	/**
	 * 
	 * @return the entire list of customer
	 */
	public abstract List<Customer> getCustomerList();

	/**
	 * 
	 * @return the list of customers with Unpaid orders
	 */
	public abstract List<Customer> getCustomerWithUnpaidOrders();

	/**
	 * 
	 * @param id
	 * @return a specific customer
	 */
	public abstract Customer getCustomer(int id);

}