package resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import dao.ProductDAOInterface;
import domainmodel.InventoryItem;

public class InventoryRowMapper implements RowMapper{

	public Object mapRow(ResultSet inventoryset, int line) throws SQLException{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customerconfig.xml");
		ProductDAOInterface productDAO = (ProductDAOInterface)ctx.getBean("ProductDao");
		InventoryItem inventoryItem = new InventoryItem(inventoryset.getInt("Quantity"), productDAO.getProduct(inventoryset.getInt("SKU_Number")));
		
		return inventoryItem;
	}

}
