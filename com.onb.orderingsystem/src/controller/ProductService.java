package controller;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;


import dao.OrderDAO;
import dao.ProductDAO;
import domainmodel.InventoryItem;
import domainmodel.Product;

public class ProductService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static ProductDAO productDAO = (ProductDAO)ctx.getBean("productDAO");
	
	public static void createProduct(Product product){		
		productDAO.createProduct(product);
	}
	
	public static Product getProduct(int productID){
		return productDAO.getProduct(productID);
	}

	public static void deleteProduct(Product product){	
		productDAO.deleteProduct(product);
	}

}
