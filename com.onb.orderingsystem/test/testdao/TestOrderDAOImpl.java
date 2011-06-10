package testdao;

import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.*;

public class TestOrderDAOImpl {
	Customer testCustomer;
	Order testOrder;
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
	
	@Before
	public void setUp(){
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
