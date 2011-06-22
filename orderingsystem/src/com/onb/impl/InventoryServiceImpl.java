package com.onb.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.InventoryDAO;
import com.onb.domainmodel.*;


public class InventoryServiceImpl {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static InventoryDAO inventoryDAO = (InventoryDAO)ctx.getBean("inventoryDAO");
		
	/**
	 * 
	 * @return all products in the inventory item
	 */
	public static List<InventoryItem>  getAllAvailableProductsInDB(){
		List<InventoryItem>  inventoryItem = inventoryDAO.retrieveAvailableProducts();
		return inventoryItem;
	}
	
	/**
	 * Checks if the quantity in the inventory is greater than the quantity of items to be taken
	 * @param item
	 * @param numOfItemsToBeTaken
	 * @return 
	 */
	public static boolean checkQuantity(InventoryItem item, int numOfItemsToBeTaken){
		return item.getQuantity()>=numOfItemsToBeTaken;
	}
	
	/**
	 * 
	 * @param inventory
	 * @param product
	 * @return the inventory items in the inventory
	 */
	public static InventoryItem getInventoryItem(Inventory inventory, Product product){
		return inventory.getItem(product);
	}
	
	/**
	 * update the quantity of products in the inventory based on the items bought
	 * @param inventory
	 * @param item
	 * @param numOfItemsToBeTaken
	 * @return
	 */
	public static Inventory deductFromInventory(Inventory inventory,InventoryItem item, int numOfItemsToBeTaken){
		if(checkQuantity(item, numOfItemsToBeTaken)){
			inventory.deduct(item, numOfItemsToBeTaken);
			//item.deduct(numOfItemsToBeTaken);
			inventoryDAO.updateInventory(item);
		}else throw new IllegalArgumentException("Number of items to be taken is greater than the number of items available for that product.");
		return inventory;
	}
	
}
