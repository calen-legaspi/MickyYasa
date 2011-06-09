package testservice;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.*;
import domainmodel.*;


public class TestOrderItemService {
	Product testProduct;
	Order testOrder;
	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDao = (ProductDAOInterface)ctx.getBean("ProductDao");
		testProduct = productDao.getProduct(10234242);
		OrderDAOInterface orderDao = (OrderDAOInterface)ctx.getBean("ProductDao");
		testOrder = orderDao.retrieveOrder();
	}
	
	
	@Test
	public void testAddOrderItem(){
		OrderItemDAOInterface orderitemDao = (OrderItemDAOInterface)ctx.getBean("OrderItemDao");
		OrderItem  item = new OrderItem(15,testProduct);
		orderitemDao.createOrderItem(item);
	}
	
	@Test
	public void testGetOrderItems(){
		ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		OrderItemDAOInterface orderitemDao = (OrderItemDAOInterface)ctx.getBean("OrderItemDao");
		orderitemDao.getOrderItems(testOrder);
	}

}
