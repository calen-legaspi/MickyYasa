package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.CustomerRowMapper;
import resultretrieval.InventoryRowMapper;

import domainmodel.InventoryItem;
import domainmodel.Product;

public class InventoryDAOImpl implements InventoryDAO {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
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
	
	public void updateInventory(InventoryItem inventoryItem){
		String sql = "update Inventory set quantity = ? where SKU_Number = ?";
		Object[] params = {inventoryItem.getQuantity(), inventoryItem.getSKUNumber()};
		this.getJdbcTemplate().update(sql, params);
	}

}
