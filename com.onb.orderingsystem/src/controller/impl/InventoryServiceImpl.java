package controller.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.InventoryService;

import dao.InventoryDAO;
import domainmodel.*;

public class InventoryServiceImpl implements InventoryService {
	private InventoryDAO inventoryDAO;
	
	/* (non-Javadoc)
	 * @see controller.InventoryService#setInventoryDAO(dao.InventoryDAO)
	 */
	@Override
	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
		
	/* (non-Javadoc)
	 * @see controller.InventoryService#getAllAvailableProductsInDB()
	 */
	@Override
	public List<InventoryItem>  getAllAvailableProductsInDB(){
		List<InventoryItem>  inventoryItem = inventoryDAO.retrieveAvailableProducts();
		return inventoryItem;
	}
	
	/* (non-Javadoc)
	 * @see controller.InventoryService#checkQuantity(domainmodel.InventoryItem, int)
	 */
	@Override
	public boolean checkQuantity(InventoryItem item, int numOfItemsToBeTaken){
		return item.getQuantity()>=numOfItemsToBeTaken;
	}
	
	/* (non-Javadoc)
	 * @see controller.InventoryService#getInventoryItem(domainmodel.Inventory, domainmodel.Product)
	 */
	@Override
	public InventoryItem getInventoryItem(Inventory inventory, Product product){
		return inventory.getItem(product);
	}
	
	/* (non-Javadoc)
	 * @see controller.InventoryService#deductFromInventory(domainmodel.Inventory, domainmodel.InventoryItem, int)
	 */
	@Override
	public Inventory deductFromInventory(Inventory inventory,InventoryItem item, int numOfItemsToBeTaken){
		if(checkQuantity(item, numOfItemsToBeTaken)){
			inventory.deduct(item, numOfItemsToBeTaken);
			//item.deduct(numOfItemsToBeTaken);
			inventoryDAO.updateInventory(item);
		}else throw new IllegalArgumentException("Number of items to be taken is greater than the number of items available for that product.");
		return inventory;
	}
	
}
