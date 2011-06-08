package testDomain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import domain.InventoryItem;
import domain.Product;


public class testInventoryItem {

	@Test
	public void testInvetoryItem(){
		Product product = new Product(1, "iPod", new BigDecimal("2500"));
		InventoryItem inventoryItem = new InventoryItem(new BigDecimal("100"), product);
		assertEquals(new BigDecimal("100"), inventoryItem.getQuantity());
		assertEquals(1, inventoryItem.getProductSkuNumber());
		assertEquals("iPod", inventoryItem.getProductName());
		assertEquals(new BigDecimal("2500"), inventoryItem.getProductPrice());
		
		Product product2 = new Product(2, "PSP", new BigDecimal("24000"));
		InventoryItem inventoryItem2 = new InventoryItem(new BigDecimal("100"), product2);
		assertEquals(new BigDecimal("100"), inventoryItem2.getQuantity());
		assertEquals(2, inventoryItem2.getProductSkuNumber());
		assertEquals("PSP", inventoryItem2.getProductName());
		assertEquals(new BigDecimal("24000"), inventoryItem2.getProductPrice());
	}
}
