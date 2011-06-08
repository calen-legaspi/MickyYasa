package testservice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDao;
import domainmodel.Customer;


public class TestCustomerService {
	@Test
	public void testCreateCustomer(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		CustomerDao customerDao = (CustomerDao)ctx.getBean("CustomerDao");
		
		Customer customer = new Customer(2, "yasah");
		
		customer.setFirstName("yasah");
		customer.setLastName("loresco");
		customer.setMiddleName("frialde");
		customerDao.createCustomer(customer);
		
	}
}
