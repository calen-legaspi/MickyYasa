package domain;

import java.util.HashSet;
import java.util.Set;

public class Inventory {	
	private Set<InventoryItem> inventory = new HashSet<InventoryItem>();
	
		
	public void addInventoryItem(InventoryItem inventoryItem){
		inventory.add(inventoryItem);
	}

	public Set<InventoryItem> getInventory(){
		return inventory;
	}
		
}