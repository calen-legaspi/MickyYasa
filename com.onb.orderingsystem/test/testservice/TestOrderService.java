package testservice;

import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.*;

public class TestOrderService {
	Customer testCustomer;
	Order testOrder;
	
	@Before
	public void setUp(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
		testCustomer  = customerDao.retrieveCustomerList().get(0);
		testOrder = new Order(testCustomer);
	}

	@Test
	public void testAddOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testPayOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveOrder() {
		fail("Not yet implemented");
	}

}
