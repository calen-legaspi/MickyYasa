package testcontroller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import controller.impl.CustomerServiceImpl;
import domainmodel.Customer;


public class TestCustomerService{
	CustomerServiceImpl customerService = new CustomerServiceImpl();
	
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
