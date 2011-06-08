package testdomain;

import java.math.BigDecimal;

import org.junit.*;

import domainmodel.Customer;
import domainmodel.Order;
import domainmodel.OrderItem;
import domainmodel.Product;
import junit.framework.Assert;


public class TestCustomer {
	
	Customer testCustomer;
	Order tempOrder;
	
	@Before
	public void setUp(){
		testCustomer = new Customer(10000,"Lena");
		tempOrder = new Order(testCustomer);
		tempOrder.addItem(new OrderItem(5, new Product(555, "Pepsi", new BigDecimal("15.00"))));
		tempOrder.addItem(new OrderItem(200, new Product(777, "Coke", new BigDecimal("15.00"))));
		tempOrder.update();
	}
	
	@After
	public void breakUp(){
	}
	
	//@Test
	public void testAddingOrders(){
		testCustomer.addOrder(tempOrder);
		Assert.assertEquals(1, testCustomer.getUnpaidOrders().size());
	}

	//@Test
	public void testAddingTheSameOrderMultipleTimes(){;
		testCustomer.addOrder(tempOrder);
		Assert.assertEquals(1, testCustomer.getUnpaidOrders().size());
		Assert.assertTrue(testCustomer.getUnpaidOrders().contains(tempOrder));
		testCustomer.addOrder(tempOrder);
		Assert.assertEquals(1, testCustomer.getUnpaidOrders().size());
	}
	
	//@Test
	public void testAddingAnOrderWithHigherTotalCostThanCreditLimit(){
		tempOrder.addItem(new OrderItem(1000,new Product(222,"Explosives", new BigDecimal("7000.50"))));
		tempOrder.update();
		testCustomer.addOrder(tempOrder);
		Assert.assertFalse(testCustomer.getUnpaidOrders().contains(tempOrder));
	}
	
	//@Test
	public void customerPaymentTransaction(){
		testCustomer.addOrder(tempOrder);
		Assert.assertEquals(1, testCustomer.getUnpaidOrders().size());
		Assert.assertTrue(testCustomer.getUnpaidOrders().contains(tempOrder));
		testCustomer.payOrder(tempOrder);
		Assert.assertEquals(0, testCustomer.getUnpaidOrders().size());	
		Assert.assertFalse(testCustomer.getUnpaidOrders().contains(tempOrder));
	}
	
	//@Test
	public void customerPaymentTransactionWhileIncreasingCreditLimit(){
		for(int i = 0; i< 25; i++){
			tempOrder = new Order(testCustomer);
			tempOrder.addItem(new OrderItem(100, new Product(555+i, "Pepsi", new BigDecimal("100.00"))));
			tempOrder.update();
			testCustomer.addOrder(tempOrder);
			testCustomer.payOrder(tempOrder);
		}	
		Assert.assertEquals(new BigDecimal(30000), testCustomer.getCreditLimit());
	}
	
	@Test
	public void returnTotalUnpaidOrders(){
		for(int i = 0; i< 25; i++){
			tempOrder = new Order(testCustomer);
			tempOrder.addItem(new OrderItem(10, new Product(555+i, "Pepsi", new BigDecimal("100.00"))));
			tempOrder.update();
			testCustomer.addOrder(tempOrder);
			if(i%2 ==1)
				testCustomer.payOrder(tempOrder);
		}	
		Assert.assertEquals(new BigDecimal("10000.00"), testCustomer.getTotalUnpaidOrders());
	}
}
