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
		CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");
		Order o = new Order(arg0.getInt("Order_Number"), customerDao.retrieveCustomer(arg0.getInt("Customer_ID")), arg0.getDate("Date"), arg0.getBoolean("Paid"));
		OrderItemDAO orderitemDAO = (OrderItemDAO)ctx.getBean("OrderitemDao");
		o.setOrderItems(orderitemDAO.getOrderItems(o));
		return o;
	}

}
