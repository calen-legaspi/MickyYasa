package testdao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import dao.InventoryDAO;
import dao.OrderDAO;
import domainmodel.*;

public class TestOrderDAOImpl {
	Customer testCustomer;
	Order testOrder;
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
	
	@Before
	public void setUp(){
		testCustomer  = customerDao.retrieveCustomerList().get(0);
		testOrder = new Order(testCustomer);
		
		List<InventoryItem> items = inventoryDao.retrieveInventoryItemList();
		for(InventoryItem i:items){
			testOrder.addItem(new OrderItem(1, i.getProduct()));
		}
	}

	@Test
	public void testAddOrder() {
		orderDao.addOrder(testOrder);
	}

	//@Test
	public void testPayOrder() {
		fail("Not yet implemented");
	}

	//@Test
	public void testRetrieveOrders() {
		fail("Not yet implemented");
	}

//	@Test
	public void testRetrieveOrder() {
		fail("Not yet implemented");
	}

}
