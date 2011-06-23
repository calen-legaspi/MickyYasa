package controller;

import java.util.List;

import dao.InventoryDAO;
import domainmodel.Inventory;
import domainmodel.InventoryItem;
import domainmodel.Product;

public interface InventoryService {

	public abstract void setInventoryDAO(InventoryDAO inventoryDAO);

	/**
	 * 
	 * @return all products in the inventory item
	 */
	public abstract List<InventoryItem> getAllAvailableProductsInDB();

	/**
	 * Checks if the quantity in the inventory is greater than the quantity of items to be taken
	 * @param item
	 * @param numOfItemsToBeTaken
	 * @return 
	 */
	public abstract boolean checkQuantity(InventoryItem item,
			int numOfItemsToBeTaken);

	/**
	 * 
	 * @param inventory
	 * @param product
	 * @return the inventory items in the inventory
	 */
	public abstract InventoryItem getInventoryItem(Inventory inventory,
			Product product);

	/**
	 * update the quantity of products in the inventory based on the items bought
	 * @param inventory
	 * @param item
	 * @param numOfItemsToBeTaken
	 * @return
	 */
	public abstract Inventory deductFromInventory(Inventory inventory,
			InventoryItem item, int numOfItemsToBeTaken);

}