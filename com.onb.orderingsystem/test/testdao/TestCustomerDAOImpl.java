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
		customerDao.deleteCustomer(new Customer(0));
		customerDao.deleteCustomer(new Customer(1));
	}
	
	
	@Test
	public void testCreateCustomer(){		
		Customer customer = new Customer(0);
		customer.setFirstName("Mickey");
		customer.setLastName("Espiritu");
		customer.setMiddleName("Dianzon");
		customerDao.createCustomer(customer);
		
		Customer customer1 = new Customer(1);
		customer1.setFirstName("Eleasah");
		customer1.setLastName("Loresco");
		customer1.setMiddleName("Frialde");
		customerDao.createCustomer(customer1);
		
	}
	
	@Test
	public void testRetrieveCustomerList(){
		Customer customer = new Customer(0);
		customer.setFirstName("Mickey");
		customer.setLastName("Espiritu");
		customer.setMiddleName("Dianzon");
		
		Customer customer1 = new Customer(1);
		customer1.setFirstName("Eleasah");
		customer1.setLastName("Loresco");
		customer1.setMiddleName("Frialde");
		
		List<Customer> customerList = customerDao.retrieveCustomerList();
		
		assertTrue(customerList.contains(customer));
		assertTrue(customerList.contains(customer1));
	}
	
	@Test
	public void DeleteCustomer(){
		customerDao.deleteCustomer(new Customer(0));
		customerDao.deleteCustomer(new Customer(1));
	}
}
