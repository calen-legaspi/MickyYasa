package controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.InventoryDAO;
import domainmodel.InventoryItem;

public class InventoryService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
		
	public List<InventoryItem>  getAllAvailableProductsInDB(){
		List<InventoryItem>  inventoryItem = inventoryDao.retrieveAvailableProducts();
		return inventoryItem;
	}
}
