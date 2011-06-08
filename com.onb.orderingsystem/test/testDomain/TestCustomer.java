package testDomain;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Customer;

public class TestCustomer {
	
	@Test
	public void testCustomer(){
		Customer customer1 = new Customer(1, "Yasah Loresco");
		assertEquals(1, customer1.getId());
		assertEquals("Yasah Loresco", customer1.getName());
		
		Customer customer2 = new Customer(2, "Eleasah Frialde");
		assertEquals(2, customer2.getId());
		assertEquals("Eleasah Frialde", customer2.getName());
	}	
	
}
