package com.onb.resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.onb.daos.ProductDAO;
import com.onb.domainmodel.InventoryItem;


public class InventoryRowMapper implements RowMapper{

	public Object mapRow(ResultSet inventoryset, int line) throws SQLException{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		ProductDAO productDAO = (ProductDAO)ctx.getBean("productDAO");
		InventoryItem inventoryItem = new InventoryItem(inventoryset.getInt("Quantity"), productDAO.getProduct(inventoryset.getInt("SKU_Number")));
		return inventoryItem;
	}

}
