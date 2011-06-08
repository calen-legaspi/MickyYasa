package testDomain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import domain.OrderItem;
import domain.Product;


public class TestOrderItem {
	
	@Test
	public void testOrderItem(){
		Product product = new Product(1, "iPod", new BigDecimal("2500"));
		OrderItem orderItem = new OrderItem(new BigDecimal("2"), product);
		assertEquals(new BigDecimal("2"), orderItem.getQuantity());
		assertEquals(1, orderItem.getProductSkuNumber());
		assertEquals("iPod", orderItem.getProductName());
		assertEquals(new BigDecimal("5000"), orderItem.calculatePrice());
	}
	
	@Test
	public void testCalculatePrice(){
		Product product2 = new Product(2, "PSP", new BigDecimal(24000));
		OrderItem orderItem2 = new OrderItem(new BigDecimal("1"), product2);
		assertEquals(new BigDecimal("24000"), orderItem2.calculatePrice());
	}
}
