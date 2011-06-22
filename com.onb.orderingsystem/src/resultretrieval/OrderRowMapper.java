package resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import dao.*;
import domainmodel.Order;

public class OrderRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		Order o = new Order(arg0.getInt("Order_Number"),arg0.getInt("Customer_ID"), arg0.getDate("Date"),  arg0.getBoolean("Paid"));
		OrderItemDAO orderItemDAO = (OrderItemDAO)ctx.getBean("orderItemDAO");
		o.setOrderItems(orderItemDAO.getOrderItems(o));
		return o;
	}

}
