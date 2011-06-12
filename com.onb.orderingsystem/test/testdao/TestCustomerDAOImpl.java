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
		Customer customer = new Customer(2);
		customerDao.deleteCustomer(customer);
	}
	
	
	@Test
	public void testCreateCustomer(){		
		Customer customer = new Customer(2);
		customer.setFirstName("Mickey");
		customer.setLastName("Espiritu");
		customer.setMiddleName("Dianzon");
		customerDao.createCustomer(customer);
		
	}
	
	@Test
	public void testRetrieveCustomerList(){
		Customer customer = new Customer(2);
		customer.setFirstName("Mickey");
		customer.setLastName("Espiritu");
		customer.setMiddleName("Dianzon");
		List<Customer> customerList = customerDao.retrieveCustomerList();
		assertTrue(customerList.contains(customer));
	}
}
