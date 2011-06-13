package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import resultretrieval.OrderRowMapper;
import resultretrieval.ProductRowMapper;

import dao.*;
import domainmodel.*;

public class OrderDAOImpl implements OrderDAO {
	private	JdbcTemplate jdbcTemplate;
	
	@Override
	public void addOrder(Order o) {
	String sql = "insert into CustomerOrder (Order_Number, Customer_ID, Date, Paid) values (?,?,?,?)";
		java.sql.Date date = new java.sql.Date(o.getDateofOrderCreation().getTimeInMillis());
		Object[] params = new Object[]{o.getOrderNumber(), o.getCustomerID(), date, 0};
		jdbcTemplate.update(sql,params);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		OrderItemDAO orderitemDao = (OrderItemDAO)ctx.getBean("OrderItemDao");
		for(OrderItem item:o.getItems()){
			orderitemDao.createOrderItem(item, o.getOrderNumber());
		}
	}

	@Override
	public void payOrder(Order o) {
		String sql = "update CustomerOrder set Paid=1 where Order_Number=?";
		Object[] params = {o.getOrderNumber()};
		jdbcTemplate.update(sql, params);
	}

	@Override
	public Set<Order> retrieveOrders(Customer c) {
		String sql = "select * from CustomerOrder where Customer_ID = ?";
		Object[] params = new Object[]{c.getID()};
		List<Order> orders = jdbcTemplate.query(sql, params, new OrderRowMapper());
		Set<Order> customerOrders = new HashSet<Order>();
		for(Order o:orders){
			customerOrders.add(o);
		}
		return customerOrders;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public Order retrieveOrder(int id) {
		String sql = "select * from CustomerOrder where Order_Number = ?";
		Object[] params = new Object[]{id};
		List<Order> orders = jdbcTemplate.query(sql, params, new OrderRowMapper());
		return orders.get(0);
	}

	@Override
	public int getLastOrderNumber() {
		String sql = "select MAX(Order_Number) from CustomerOrder";
		return jdbcTemplate.queryForInt(sql);
	}

}
