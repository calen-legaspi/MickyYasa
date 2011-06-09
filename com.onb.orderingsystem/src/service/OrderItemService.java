package service;

import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.*;

import dao.OrderItemDAOInterface;
import domainmodel.Order;
import domainmodel.OrderItem;
import domainmodel.Product;

public class OrderItemService implements OrderItemDAOInterface {
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createOrderItem(OrderItem object) {
		String sql = "insert into OrderItem (Order_Number, SKU_Number, PRICE, Quantity) values (?,?,?)";
		Object[] params = new Object[]{object.getOrderNumber(), object.getPriceSKUNumber(), object.getProductPrice().doubleValue(), object.getQuantity()};
		this.getJdbcTemplate().update(sql,params);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<OrderItem> getOrderItems(Order rootOrder) {
		String sql = "select * from OrderItem  where Order_Number = ?";
		Object[] params = new Object[]{rootOrder.getOrderNumber()};
		List<OrderItem> items = jdbcTemplate.query(sql, params, new OrderItemRowMapper());
		for(OrderItem item: items){
			item.setOrder(rootOrder);
		}
		return items;
	}

}
