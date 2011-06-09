package domainmodel;

import java.math.BigDecimal;

public class OrderItem {
	
	private Order order;
	private int quantity;
	private Product product;
	
	public OrderItem(int quantity, Product product){
		if(quantity <= 0)
			throw new IllegalArgumentException("Quantity parameter is invalid. Quantity parameter should be greater than 0");
		else if (product == null)
			throw new NullPointerException("Product parameter is null");
		this.quantity = quantity;
		this.product = product;
	}
	
	public void addToQuantity(int amount){
		quantity += amount;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public BigDecimal getCost(){
		return getProductPrice().multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getProductPrice(){
		return product.getPrice();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getOrderNumber() {
		return order.getOrderNumber();
	}
	
	public int getPriceSKUNumber(){
		return product.getSKUNumber();
	}
	
	
	
}
