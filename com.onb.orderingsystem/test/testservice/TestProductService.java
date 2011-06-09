package testservice;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ProductDAOInterface;
import domainmodel.Product;


public class TestProductService {
	
	@Test
	public void testDeleteProduct(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDao = (ProductDAOInterface)ctx.getBean("ProductDao");
		
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		productDao.deleteProduct(product);
	}
	
	@Test
	public void testCreateProduct(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDao = (ProductDAOInterface)ctx.getBean("ProductDao");
		
		Product  product = new Product(10234242, "Coke", new BigDecimal("100.00"));
		productDao.createProduct(product);
	}
	
	@Test
	public void testGetProduct(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDao = (ProductDAOInterface)ctx.getBean("ProductDao");
		
		Assert.assertTrue(productDao.getProduct(10234242).getName().equals("Coke"));
	}

}
