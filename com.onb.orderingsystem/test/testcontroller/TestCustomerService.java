package testcontroller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import controller.CustomerService;
import domainmodel.Customer;


public class TestCustomerService{
	CustomerService customerService = new CustomerService();
	
	@Test
	public void testGetCustomerList(){
		Customer customer = new Customer(0);
		List<Customer> listOfCustomers = (List<Customer>) customerService.getCustomerList();
		assertTrue(listOfCustomers.contains(customer));	
	}
	
	@Test
	public void testGetCustomerWithUnpaidOrder(){
		Customer customer = new Customer(5);
	}
}
