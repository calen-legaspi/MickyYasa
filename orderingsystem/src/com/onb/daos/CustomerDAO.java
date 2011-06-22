package com.onb.daos;

import java.util.List;

import com.onb.domainmodel.Customer;


public interface CustomerDAO {
	public void createCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public Customer retrieveCustomer(int id);
	public List<Customer> retrieveCustomerList();
	public List<Customer> retrieveUnpaidCustomerList();
}
