package testdomain;
import java.math.BigDecimal;
import java.util.*;

import junit.framework.Assert;

import org.junit.*;

import domainmodel.Customer;
import domainmodel.Order;
import domainmodel.OrderItem;
import domainmodel.Product;



public class TestOrder {
	
	Customer testCustomer;
	@Before
	public void setUp(){
		testCustomer = new Customer(10000,"Lena");
	}
	
	@Test
	public void testOrderDeclaration(){
		Order o = new Order(testCustomer);
		Assert.assertEquals(1000000, o.getOrderNumber());
		Calendar c = new GregorianCalendar();
		c.clear(Calendar.MILLISECOND);
		Assert.assertEquals(c, o.getDateofOrderCreation());
		o = new Order(testCustomer);
		Assert.assertEquals(1000001, o.getOrderNumber());		
		o = new Order(testCustomer);
		Assert.assertEquals(1000002, o.getOrderNumber());
		o = new Order(testCustomer);
		Assert.assertEquals(1000003, o.getOrderNumber());
	}
	
	@Test
	public void testDateModification(){
		Order o = new Order(testCustomer);
		o.setDate(new GregorianCalendar(1991, 0, 22));
		Assert.assertEquals(new GregorianCalendar(1991, 0, 22), o.getDateofOrderCreation());
	}
	
	@Test
	public void testAddingAndDeletingOrderItems(){
		Order o = new Order(testCustomer);
		Product testProduct1 = new Product(1929428, "Lotion", new BigDecimal("10.50"));
		o.addItem(new OrderItem(5, testProduct1));
		Assert.assertEquals(1, o.getNumberofItems());
		Product testProduct2 = new Product(1929455, "Powder", new BigDecimal("70.50"));
		o.addItem(new OrderItem(2, testProduct2));
		Assert.assertEquals(2, o.getNumberofItems());
		o.addItem(new OrderItem(3, testProduct2));
		Assert.assertEquals(3, o.getNumberofItems());
		o.update();
		Assert.assertEquals(2, o.getNumberofItems());
		o.deleteItem(new OrderItem(5, testProduct1));
		Assert.assertEquals(1, o.getNumberofItems());
	}
	
	@Test
	public void testGettingTotalCost(){
		Order o = new Order(testCustomer);
		Product testProduct1 = new Product(1929428, "Lotion", new BigDecimal("10.50"));
		o.addItem(new OrderItem(5, testProduct1));
		o.update();
		Assert.assertEquals(0,new BigDecimal("52.5000").compareTo(o.getTotalCost()));
		o.addItem(new OrderItem(10, testProduct1));
		o.update();
		Assert.assertEquals(0,new BigDecimal("157.5").compareTo(o.getTotalCost()));
	}
}
