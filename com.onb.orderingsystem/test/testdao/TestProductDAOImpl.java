package testdao;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.InventoryDAO;
import dao.ProductDAO;
import domainmodel.InventoryItem;
import domainmodel.Product;


public class TestProductDAOImpl {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	ProductDAO productDao = (ProductDAO)ctx.getBean("ProductDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	
	@Test
	public void testDeleteProduct(){	
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		InventoryItem inventoryItem = new InventoryItem(10, product);
		inventoryDao.deleteInventoryItemFromInventory(inventoryItem);
		productDao.deleteProduct(product);
	}
	
	@Test
	public void testCreateProduct(){		
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		productDao.createProduct(product);
	}
	
	@Test
	public void testGetProduct(){
		Assert.assertTrue(productDao.getProduct(10234242).getName().equals("Coke"));
	}

}
