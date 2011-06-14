package testdao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.InventoryDAO;
import dao.ProductDAO;
import domainmodel.Customer;
import domainmodel.InventoryItem;
import domainmodel.Product;


public class TestInventoryDAOImpl {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	ProductDAO productDao = (ProductDAO)ctx.getBean("ProductDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	
	@Test
	public void testAddInventoryItemToInventory(){
		Product  product = new Product(2, "Coke", new BigDecimal("1.00"));
		productDao.createProduct(product);
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.addInventoryItemToInventory(inventoryItem);	
	}
	
	@Test
	public void testRetrieveInventoryList(){
		Product  product = new Product(2, "Coke", new BigDecimal("1.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);	
		List<InventoryItem> inventoryList = inventoryDao.retrieveInventoryItemList();
		assertTrue(inventoryList.contains(inventoryItem));
	}

	@Test
	public void testUpdateInventory(){
		Product  product = new Product(2, "Coke", new BigDecimal("1.00"));
		InventoryItem inventoryItem = new InventoryItem(5, product);	
		inventoryDao.updateInventory(inventoryItem);
		
		InventoryItem inventoryItem2 = new InventoryItem(100, product);
		inventoryDao.updateInventory(inventoryItem2);
		
	}
	
	
	@Test
	public void testDeleteInventoryItemFromInventory(){
		Product  product = new Product(2, "Coke", new BigDecimal("1.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.deleteInventoryItemFromInventory(inventoryItem);
		productDao.deleteProduct(product);
	}
}
