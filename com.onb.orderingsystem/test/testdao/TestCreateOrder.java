package testdao;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.*;
import domainmodel.*;

public class TestCreateOrder {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
	OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
	ProductDAO productDao = (ProductDAO)ctx.getBean("ProductDao");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	
	@Test
	public void TestCreateOrder(){
		Customer customer = new Customer(0);
		customer.setFirstName("Eleasah");
		customer.setMiddleName("Frialde");
		customer.setLastName("Loresco");
		customerDao.createCustomer(customer);
		
		
		
		Product  product = new Product(1, "Coke", new BigDecimal("100.00"));
		productDao.createProduct(product);
		
		
	}
}
