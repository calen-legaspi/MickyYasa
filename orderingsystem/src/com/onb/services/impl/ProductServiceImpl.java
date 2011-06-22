package com.onb.services.impl;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.OrderDAO;
import com.onb.daos.ProductDAO;
import com.onb.domainmodel.InventoryItem;
import com.onb.domainmodel.Product;



public class ProductServiceImpl {
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	private ProductDAO productDAO = (ProductDAO)ctx.getBean("productDAO");
	
	public void createProduct(Product product){		
		productDAO.createProduct(product);
	}
	
	public Product getProduct(int productID){
		return productDAO.getProduct(productID);
	}

	public void deleteProduct(Product product){	
		productDAO.deleteProduct(product);
	}

}
