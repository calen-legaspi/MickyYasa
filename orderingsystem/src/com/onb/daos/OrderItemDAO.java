package com.onb.daos;

import java.util.List;

import com.onb.domainmodel.*;


public interface OrderItemDAO {
	
	public void createOrderItem(OrderItem object, int orderNumber);

	public List<OrderItem> getOrderItems(Order rootOrder);

}
