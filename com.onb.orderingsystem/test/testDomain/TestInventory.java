package testDomain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import domain.Inventory;
import domain.InventoryItem;
import domain.Product;


public class TestInventory {
	
	@Test
	public void testInventory(){		
		Product product = new Product(1, "iPod", new BigDecimal("2500"));
		InventoryItem inventoryItem = new InventoryItem(new BigDecimal("100"), product);
		Inventory inventory = new Inventory();
		inventory.addInventoryItem(inventoryItem);
		assertTrue(inventory.getInventory().contains(inventoryItem));
		
		Product product2 = new Product(2, "PSP", new BigDecimal("24000"));
		InventoryItem inventoryItem2 = new InventoryItem(new BigDecimal("100"), product2);
		Inventory inventory2 = new Inventory();
		inventory2.addInventoryItem(inventoryItem2);
		assertTrue(inventory.getInventory().contains(inventoryItem));		
	}

	
}
