package controller.impl;


import java.util.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.InventoryService;
import controller.OrderService;

import dao.*;
import daoImpl.ProductDAOImpl;
import domainmodel.*;


public class OrderServiceImpl implements OrderService{
	private OrderDAO orderDAO;
	
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	/* (non-Javadoc)
	 * @see controller.impl.OrderService#getLastOrderNumber()
	 */
	@Override
	public int getLastOrderNumber(){
		return orderDAO.getLastOrderNumber();
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.OrderService#addOrderToDB(domainmodel.Order)
	 */
	@Override
	public void addOrderToDB(Order o) {
		CustomerDAO customerDAO = new CustomerDAOImpl();
		Customer customer = customerDAO.retrieveCustomer(o.getCustomerID());
		customer.setOrders(retrieveUnpaidOrders(customer));
		customer.addOrder(o);
		if(customer.getOrders().contains(o)){
			orderDAO.addOrder(o);
			List<OrderItem> items = o.getItems();
			InventoryService inventoryService = new InventoryServiceImpl();
			Inventory inventory = new Inventory(inventoryService.getAllAvailableProductsInDB());
			for(OrderItem i: items){
				ProductDAO productDAO = new ProductDAOImpl();
				Product product = productDAO.getProduct(i.getItemSKUNumber());
				InventoryItem inventoryitem = inventoryService.getInventoryItem(inventory,product);
				inventoryService.deductFromInventory(inventory, inventoryitem, i.getQuantity());
			}
		}
	}

	/* (non-Javadoc)
	 * @see controller.impl.OrderService#updateStatusOfOrderInDB(domainmodel.Order)
	 */
	@Override
	public void updateStatusOfOrderInDB(Order o) {
		orderDAO.payOrder(o);
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.OrderService#retrieveOrderFromDB(int)
	 */
	@Override
	public Order retrieveOrderFromDB(int orderNumber){
		return orderDAO.retrieveOrder(orderNumber);
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.OrderService#retrieveOrdersFromDB(domainmodel.Customer)
	 */
	@Override
	public List<Order> retrieveOrdersFromDB(Customer c){
		return orderDAO.retrieveOrders(c);
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.OrderService#deleteOrderItem(domainmodel.Order, int)
	 */
	@Override
	public void deleteOrderItem(Order order, int itemIndex){
		List<OrderItem> items = order.getItems();
		OrderItem itemToBeRemoved = items.get(itemIndex);
		order.deleteItem(itemToBeRemoved);
	}

	/* (non-Javadoc)
	 * @see controller.impl.OrderService#addOrdersFromDB(domainmodel.Customer)
	 */
	@Override
	public Customer addOrdersFromDB(Customer c) {
		Customer newCustomer = new Customer(c.getID());
		List<Order> orders = orderDAO.retrieveOrders(c);
		for(Order o: orders){
			newCustomer.addOrder(o);
		}
		return newCustomer;
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.OrderService#retrieveUnpaidOrders(domainmodel.Customer)
	 */
	@Override
	public List<Order> retrieveUnpaidOrders(Customer customer){
		List<Order> orders = orderDAO.retrieveUnpaidOrders(customer.getID());
		return orders;
	}
}
