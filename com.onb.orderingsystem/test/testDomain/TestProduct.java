package testDomain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import domain.Product;


public class TestProduct {
	@Test
	public void testProductValue(){
		Product product = new Product(1, "iPod", new BigDecimal("2500"));
		assertEquals(1, product.getSkuNumber());
		assertEquals("iPod", product.getName());
		assertEquals(new BigDecimal("2500"), product.getPrice());
		
		Product product2 = new Product(1, "PSP", new BigDecimal("24000"));
		assertEquals(1, product2.getSkuNumber());
		assertEquals("PSP", product2.getName());
		assertEquals(new BigDecimal("24000"), product2.getPrice());
	}
}
