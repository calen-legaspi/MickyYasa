package dao;

import java.util.List;

import domainmodel.InventoryItem;

public interface InventoryDAO {
	public void addInventoryItemToInventory(InventoryItem inventoryItem);
	
	public void deleteInventoryItemFromInventory(InventoryItem inventoryItem);
	
	public List<InventoryItem> retrieveInventoryItemList();
	
	public List<InventoryItem> retrieveAvailableProducts();
	
	public void updateInventory(InventoryItem inventoryItem);
}
