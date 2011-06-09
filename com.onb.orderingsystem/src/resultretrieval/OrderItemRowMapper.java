package resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import dao.ProductDAOInterface;
import domainmodel.OrderItem;

public class OrderItemRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDAO = (ProductDAOInterface)ctx.getBean("ProductDao");
		return new OrderItem(rs.getInt("quantity"),productDAO.getProduct(rs.getInt("SKU_Number")));
	}

}
