package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import domainmodel.*;

public interface OrderItemDAO {
	
	public void createOrderItem(OrderItem object, int orderNumber);

	public List<OrderItem> getOrderItems(Order rootOrder);

}
