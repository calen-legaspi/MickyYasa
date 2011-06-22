package com.onb.daos.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.onb.daos.OrderItemDAO;
import com.onb.domainmodel.*;
import com.onb.resultretrieval.*;



public class OrderItemDAOImpl extends AbstractDAO implements OrderItemDAO {
	@Override
	public void createOrderItem(OrderItem object, int orderNumber) {
		String sql = "insert into OrderItem (Order_Number, SKU_Number, PRICE, Quantity) values (?,?,?,?)";
		Object[] params = new Object[]{orderNumber, object.getItemSKUNumber(), object.getProductPrice().doubleValue(), object.getQuantity()};
		this.getJdbcTemplate().update(sql,params);
	}

	@Override
	public List<OrderItem> getOrderItems(Order rootOrder) {
		String sql = "select * from OrderItem  where Order_Number = ?";
		Object[] params = new Object[]{rootOrder.getOrderNumber()};
		List<OrderItem> items = this.getJdbcTemplate().query(sql, params, new OrderItemRowMapper());
		return items;
	}

}
