package testdao;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import dao.*;
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

	@Test (expected = DataIntegrityViolationException.class)
	public void testAddOrderWithEntryAlreadyInTheDatabase() {
		orderDao.addOrder(testOrder);
		assertEquals(testOrder.getOrderNumber(), orderDao.retrieveOrder(testOrder.getOrderNumber()).getOrderNumber());
	}



	@Test
	public void testRetrieveOrders() {
		List<Order> orders = new ArrayList<Order>();
		for(int i = 0 ;i< 5;i++){
			Order o = new Order(testCustomer);
			List<InventoryItem> items = inventoryDao.retrieveInventoryItemList();
			for(InventoryItem item:items){
				o.addItem(new OrderItem(1, item.getProduct()));
			}
			orders.add(o);
			orderDao.addOrder(o);
		}
		Set<Order> dbOrders = orderDao.retrieveOrders(testCustomer);
		for(Order o:orders){
			assertTrue(dbOrders.contains(o));
		}
	}


}
