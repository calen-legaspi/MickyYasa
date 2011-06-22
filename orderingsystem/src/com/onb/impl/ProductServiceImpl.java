package com.onb.impl;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.OrderDAO;
import com.onb.daos.ProductDAO;
import com.onb.domainmodel.InventoryItem;
import com.onb.domainmodel.Product;



public class ProductServiceImpl {
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
