package com.onb.domainmodel;

import java.io.Serializable;
import java.util.*;

public class Inventory implements Serializable{
	
	private List<InventoryItem> items = new ArrayList<InventoryItem>();
	
	public Inventory(){
	
	}
	
	public Inventory(List<InventoryItem> items){
		if(items.size() == 0){
			throw new IllegalArgumentException("Items parameter has an empty arraylist");
		}
		this.items = items;
	}
	
	public InventoryItem getItem(Product product){
		for(InventoryItem i:items){
			if(i.getProduct().equals(product)){
				return i;
			}
		}
		return null;
	}
	
	public void deduct(InventoryItem i, int amount){
		if(items.contains(i)){
			int index = items.indexOf(i);
			items.get(index).deduct(amount);
		}else throw new NoSuchElementException("Inventory item is ambiguous");
	}
	
	public List<InventoryItem> getCompleteInventory(){
		return items;
	}
	
	public List<InventoryItem> getAllItemsInStock(){
		ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
		for(InventoryItem i:items){
			if(i.getQuantity()>0){
				inventory.add(i);
			}
		}
		return inventory;
	}

}
