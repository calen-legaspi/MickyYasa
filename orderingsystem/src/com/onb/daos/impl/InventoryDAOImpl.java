package com.onb.daos.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.onb.daos.InventoryDAO;
import com.onb.domainmodel.InventoryItem;
import com.onb.domainmodel.Product;
import com.onb.resultretrieval.CustomerRowMapper;
import com.onb.resultretrieval.InventoryRowMapper;



public class InventoryDAOImpl extends AbstractDAO implements InventoryDAO {
		
	/**
	 * Adds inventory item to the Inventory
	 */
	public void addInventoryItemToInventory(InventoryItem inventoryItem) {
		String sql = "insert into Inventory values (?,?)";
		Object[] params = {inventoryItem.getSKUNumber(), inventoryItem.getQuantity()};
		this.getJdbcTemplate().update(sql, params);
	}

	/**
	 * Deletes a specified Inventory Item
	 */
	public void deleteInventoryItemFromInventory(InventoryItem inventoryItem) {
		String sql = "delete from Inventory where SKU_Number = ?";
		Object[] params = new Object[]{inventoryItem.getSKUNumber()};
		this.getJdbcTemplate().update(sql, params);
	}
	
	/**
	 * Retrieves all the inventory items from the Inventory
	 */
	public List<InventoryItem> retrieveInventoryItemList(){
		return this.getJdbcTemplate().query("select * from Inventory", new InventoryRowMapper());
	}
	
	/**
	 * Retrieves all inventory items with quantity greater than 0
	 */
	public List<InventoryItem> retrieveAvailableProducts(){
		return this.getJdbcTemplate().query("select * from Inventory where quantity > 0", new InventoryRowMapper());
	}
	
	/**
	 * Updates the quantity of the Inventory Item
	 */
	public void updateInventory(InventoryItem inventoryItem){
		String sql = "update Inventory set quantity = ? where SKU_Number = ?";
		Object[] params = {inventoryItem.getQuantity(), inventoryItem.getSKUNumber()};
		this.getJdbcTemplate().update(sql, params);
	}

}
