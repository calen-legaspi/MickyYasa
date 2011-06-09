package service;

import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.OrderRowMapper;
import resultretrieval.ProductRowMapper;

import dao.*;
import domainmodel.*;

public class OrderService implements OrderDAOInterface {
	private	JdbcTemplate jdbcTemplate;
	
	@Override
	public void addOrder(Order o) {
		String sql = "insert into Order (Order_Number, Customer_ID, Date, Paid) values (?,?,?,?)";
		Object[] params = new Object[]{o.getOrderNumber(), o.getCustomerID(), o.getDateofOrderCreation().getTime(), false};
		jdbcTemplate.update(sql,params);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		OrderItemDAOInterface orderitemDao = (OrderItemDAOInterface)ctx.getBean("OrderItemDao");
		for(OrderItem item:o.getItems()){
			orderitemDao.createOrderItem(item, o.getOrderNumber());
		}
	}

	@Override
	public void payOrder(Order o) {
		String sql = "update Order set Paid=true where Order_Number=?";
		Object[] params = {o.getOrderNumber()};
		jdbcTemplate.update(sql, params);
	}

	@Override
	public Set<Order> retrieveOrders(Customer c) {
		String sql = "select * from Order where CustomerID = ?";
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
		String sql = "select * from Order where Order_Number = ?";
		Object[] params = new Object[]{id};
		List<Order> orders = jdbcTemplate.query(sql, params, new OrderRowMapper());
		return orders.get(0);
	}

}
