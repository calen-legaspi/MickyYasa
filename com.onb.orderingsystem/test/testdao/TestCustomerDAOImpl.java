package testdao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.Customer;


public class TestCustomerDAOImpl {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
	
	@Test
	public void testDeleteCustomer(){
		Customer customer = new Customer(1);
		customerDao.deleteCustomer(customer);
	}
	
	
	@Test
	public void testCreateCustomer(){		
		Customer customer = new Customer(1);
		customer.setFirstName("Eleasah");
		customer.setLastName("Loresco");
		customer.setMiddleName("Frialde");
		customerDao.createCustomer(customer);
		
	}
	
	@Test
	public void testRetrieveCustomerList(){
		Customer customer = new Customer(1);
		customer.setFirstName("Eleasah");
		customer.setLastName("Loresco");
		customer.setMiddleName("Frialde");
		List<Customer> customerList = customerDao.retrieveCustomerList();
		assertTrue(customerList.contains(customer));
	}
}
