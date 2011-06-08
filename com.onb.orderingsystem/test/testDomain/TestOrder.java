package testDomain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import domain.Order;
import domain.OrderItem;
import domain.Product;


public class TestOrder {
	
	@Test
	public void testOrder(){
		Order order = new Order(0, "06/01/2011");
		assertEquals(0, order.getOrderNumber());
		assertEquals("06/01/2011", order.getDate());
		
		Order order2 = new Order(1, "");
		assertEquals(1, order2.getOrderNumber());
		assertEquals("6/2/2011", order2.getDate());
	}
	
	@Test
	public void testOrderList(){
		Product product = new Product(1, "iPod", new BigDecimal("2500"));
		OrderItem orderItem = new OrderItem(new BigDecimal("2"), product);
		Order order = new Order(1, "06/02/2011");
		order.addOrderItem(orderItem);
		assertTrue(order.getOrderList().contains(orderItem));
		
		Product product2 = new Product(2, "PSP", new BigDecimal(24000));
		OrderItem orderItem2 = new OrderItem(new BigDecimal("1"), product2);
		Order order2 = new Order(2, "06/02/2011");
		order2.addOrderItem(orderItem2);
		assertTrue(order2.getOrderList().contains(orderItem2));
	}
}
