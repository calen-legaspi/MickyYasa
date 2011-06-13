package controller;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;


import dao.OrderDAO;
import dao.ProductDAO;
import domainmodel.InventoryItem;
import domainmodel.Product;

public class ProductService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static ProductDAO productDao = (ProductDAO)ctx.getBean("ProductDao");
	
	public static void createProduct(Product product){		
		productDao.createProduct(product);
	}
	
	public static Product getProduct(int productID){
		return productDao.getProduct(productID);
	}

	public static void deleteProduct(Product product){	
		productDao.deleteProduct(product);
	}

}
