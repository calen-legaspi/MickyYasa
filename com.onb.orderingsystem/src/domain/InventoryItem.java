package domain;

import java.math.BigDecimal;

public class InventoryItem {
	private BigDecimal quantity;
	private Product product;
	private OrderItem orderItemQuantity;
	
	public InventoryItem(BigDecimal quantity, Product product){
		this.quantity = quantity;
		this.product = product;
	}
	
	public BigDecimal getQuantity(){
		return quantity;
	}
	
	public int getProductSkuNumber(){
		return product.skuNumber;
	}
	
	public String getProductName(){
		return product.name;
	}
	
	public BigDecimal getProductPrice(){
		return product.price;
	}
	public BigDecimal updateQuantity(){
		return quantity.subtract(orderItemQuantity.getQuantity());
	}

}
