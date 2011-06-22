package com.onb.daos;

import java.util.List;

import com.onb.domainmodel.InventoryItem;


public interface InventoryDAO {
	public void addInventoryItemToInventory(InventoryItem inventoryItem);
	
	public void deleteInventoryItemFromInventory(InventoryItem inventoryItem);
	
	public List<InventoryItem> retrieveInventoryItemList();
	
	public List<InventoryItem> retrieveAvailableProducts();
	
	public void updateInventory(InventoryItem inventoryItem);
}
