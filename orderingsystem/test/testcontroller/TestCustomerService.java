package testcontroller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.onb.domainmodel.Customer;
import com.onb.services.impl.CustomerServiceImpl;



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
