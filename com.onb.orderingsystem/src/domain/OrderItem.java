package domain;

import java.math.BigDecimal;

public class OrderItem {
	private final BigDecimal quantity;
	private Product product;
	
	public OrderItem(BigDecimal quantity, Product product){
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
	
	public BigDecimal calculatePrice(){
		return product.price.multiply(quantity);
	}
}
