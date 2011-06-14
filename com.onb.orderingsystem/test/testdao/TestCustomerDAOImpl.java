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
	public void testCreateCustomer(){		
		Customer customer = new Customer(2);
		customer.setFirstName("Eleasah");
		customer.setLastName("Loresco");
		customer.setMiddleName("Frialde");
		customerDao.createCustomer(customer);
		
		Customer customer1 = new Customer(3);
		customer1.setFirstName("Mickey");
		customer1.setLastName("Espiritu");
		customer1.setMiddleName("Dianzon");
		customerDao.createCustomer(customer1);
	}
	
	@Test
	public void testRetrieveCustomerList(){
		Customer customer = new Customer(0);
		customer.setFirstName("Eleasah");
		customer.setLastName("Loresco");
		customer.setMiddleName("Frialde");
		List<Customer> customerList = customerDao.retrieveCustomerList();
		assertTrue(customerList.contains(customer));
		
		Customer customer1 = new Customer(1);
		customer.setFirstName("Mickey");
		customer.setLastName("Espiritu");
		customer.setMiddleName("Dianzon");
		List<Customer> customerList1 = customerDao.retrieveCustomerList();
		assertTrue(customerList1.contains(customer1));
	}
	
	@Test
	public void testRetrieveCustomer(){
		Customer customer = new Customer(0);
		Customer customerList = customerDao.retrieveCustomer(customer.getID());
		assertEquals(customer, customerList);
		
		Customer customer1 = new Customer(1);
		Customer customerList1 = customerDao.retrieveCustomer(customer1.getID());
		assertEquals(customer1, customerList1);
	}
	
	@Test
	public void testDeleteCustomer(){
		Customer customer = new Customer(2);
		customerDao.deleteCustomer(customer);
		
		Customer customer1 = new Customer(3);
		customerDao.deleteCustomer(customer1);
	}
}
