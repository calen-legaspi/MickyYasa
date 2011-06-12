package controller;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.InventoryDAO;
import dao.ProductDAO;
import domainmodel.InventoryItem;
import domainmodel.Product;

public class InventoryService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
	
	public void testDeleteInventoryItemFromInventory(InventoryItem i){
		inventoryDao.deleteInventoryItemFromInventory(i);
	}
	
	public void testAddInventoryItemToInventory(InventoryItem i){
		inventoryDao.addInventoryItemToInventory(i);	
	}
	
	public List<InventoryItem> testRetrieveInventoryList(){
		
		return inventoryDao.retrieveInventoryItemList();
	}

	public void testUpdateInventory(InventoryItem i, int newQuantity){
		InventoryItem inventoryItem = new InventoryItem(newQuantity, i.getProduct());	
		inventoryDao.updateInventory(inventoryItem);
	}
}
