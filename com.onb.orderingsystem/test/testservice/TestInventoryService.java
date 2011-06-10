package testservice;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.InventoryDAO;
import dao.ProductDAOInterface;
import domainmodel.Customer;
import domainmodel.InventoryItem;
import domainmodel.Product;


public class TestInventoryService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
	ProductDAOInterface productDao = (ProductDAOInterface)ctx.getBean("ProductDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	
	@Test
	public void testDeleteInventoryItemFromInventory(){
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.deleteInventoryItemFromInventory(inventoryItem);
		productDao.deleteProduct(product);
	}
	
	@Test
	public void testAddInventoryItemToInventory(){
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		productDao.createProduct(product);
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.addInventoryItemToInventory(inventoryItem);	
	}
	
	@Test
	public void testRetrieveInventoryList(){
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);	
		List<InventoryItem> inventoryList = inventoryDao.retrieveInventoryItemList();
		assertTrue(inventoryList.contains(inventoryItem));
	}

	
	
}
