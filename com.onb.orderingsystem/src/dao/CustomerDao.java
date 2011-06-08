package dao;

import java.util.Set;

import domainmodel.Customer;

public interface CustomerDao {
	public void createCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public Set<Customer> getCustomer();
}
