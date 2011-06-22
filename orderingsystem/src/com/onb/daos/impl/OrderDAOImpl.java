package com.onb.daos.impl;

import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.*;
import com.onb.domainmodel.*;
import com.onb.resultretrieval.OrderRowMapper;


public class OrderDAOImpl extends AbstractDAO implements OrderDAO {
	@Override
	public void addOrder(Order o) {
	String sql = "insert into CustomerOrder (Order_Number, Customer_ID, Date, Paid) values (?,?,?,?)";
		java.sql.Date date = new java.sql.Date(o.getDateofOrderCreation().getTimeInMillis());
		Object[] params = new Object[]{o.getOrderNumber(), o.getCustomerID(), date, 0};
		this.getJdbcTemplate().update(sql,params);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
		for(OrderItem item:o.getItems()){
			orderItemDAO.createOrderItem(item, o.getOrderNumber());
		}
	}

	@Override
	public void payOrder(Order o) {
		String sql = "update CustomerOrder set Paid=1 where Order_Number=?";
		Object[] params = {o.getOrderNumber()};
		this.getJdbcTemplate().update(sql, params);
	}

	@Override
	public List<Order> retrieveOrders(Customer c) {
		String sql = "select * from CustomerOrder where Customer_ID = ?";
		Object[] params = new Object[]{c.getID()};
		List<Order> orders = this.getJdbcTemplate().query(sql, params, new OrderRowMapper());
		List<Order> customerOrders = new ArrayList<Order>();
		for(Order o:orders){
			customerOrders.add(o);
		}
		return customerOrders;
	}

	@Override
	public Order retrieveOrder(int id) {
		String sql = "select * from CustomerOrder where Order_Number = ?";
		Object[] params = new Object[]{id};
		List<Order> orders = this.getJdbcTemplate().query(sql, params, new OrderRowMapper());
		return orders.get(0);
	}

	@Override
	public int getLastOrderNumber() {
		String sql = "select MAX(Order_Number) from CustomerOrder";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	
	public List<Order> retrieveUnpaidOrders(int customerId){
		String sql = "select * from CustomerOrder where Paid = 0 and Customer_ID = ?";
		Object[] params = new Object[]{customerId};
		List<Order> unpaidOrders = (List<Order>) this.getJdbcTemplate().query(sql, params, new OrderRowMapper());
		return unpaidOrders;
	}
}
