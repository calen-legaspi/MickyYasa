package testdao;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.*;
import domainmodel.*;



public class TestProductDAOImpl {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	ProductDAO productDao = (ProductDAO)ctx.getBean("ProductDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	Product  product;
	
	@Before
	public void setUp(){
		 product = new Product(10234141, "Coke Zero", new BigDecimal("100.00"));
		productDao.createProduct(product);
	
	}
	
	@After
	public void tearDown(){
		product = new Product(10234141, "Coke Zero", new BigDecimal("100.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.deleteInventoryItemFromInventory(inventoryItem);
		productDao.deleteProduct(product);
		
	}
	
	@Test
	public void testDeleteProduct(){	
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.deleteInventoryItemFromInventory(inventoryItem);
		productDao.deleteProduct(product);
	}
	
	@Test
	public void testCreateProduct(){
		productDao.deleteProduct(product);
		productDao.createProduct(product);
		Assert.assertEquals(product, productDao.getProduct(product.getSKUNumber()));
	}
	
	@Test
	public void testGetProduct(){
		Assert.assertTrue(productDao.getProduct(10234242).getName().equals("Coke"));
	}

	
}
