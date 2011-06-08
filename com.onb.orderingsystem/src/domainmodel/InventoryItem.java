package domainmodel;

public class InventoryItem {
	
	private int quantity;
	private Product product;
	
	public InventoryItem(int initialQuantity, Product product){
		if(initialQuantity <0)
			throw new IllegalArgumentException("Initial Quantity parameter should be more than zero. Found: "+initialQuantity);
		else if (product==null)
			throw new NullPointerException("Product parameter is null");		
		this.quantity = initialQuantity;
		this.product = product;
	}
	
	public void deduct(int amount){
		if(quantity< amount)
			throw new ArithmeticException("Trying to subtract a larger number from the quantity instance variable");
		quantity -= amount;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getSKUNumber(){
		return product.getSKUNumber();
	}
	
	public String getProductName(){
		return product.getName();
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
		InventoryItem other = (InventoryItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	

}
