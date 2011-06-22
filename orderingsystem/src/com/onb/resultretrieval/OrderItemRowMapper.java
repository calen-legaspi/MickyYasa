package com.onb.resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.onb.daos.ProductDAO;
import com.onb.domainmodel.OrderItem;


public class OrderItemRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		ProductDAO productDAO = (ProductDAO)ctx.getBean("productDAO");
		return new OrderItem(rs.getInt("quantity"),productDAO.getProduct(rs.getInt("SKU_Number")));
	}

}
