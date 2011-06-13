package resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import dao.OrderDAO;
import domainmodel.Customer;

public class CustomerRowMapper implements RowMapper {
	public Object mapRow(ResultSet customerset, int line) throws SQLException{
		Customer customer = new Customer(customerset.getInt("ID"));
		customer.setFirstName(customerset.getString("First_Name"));
		customer.setMiddleName(customerset.getString("Middle_Name"));
		customer.setLastName(customerset.getString("Last_Name"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		OrderDAO orderDao = (OrderDAO)ctx.getBean("OrderDao");
		customer.setOrders(orderDao.retrieveOrders(customer));
		return customer;
	}
}
