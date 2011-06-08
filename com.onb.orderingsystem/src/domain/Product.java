package domain;

import java.math.BigDecimal;

public class Product {
	final int skuNumber;
	final String name;
	BigDecimal price;
	
	public Product(int skuNumber, String name, BigDecimal price){
		this.skuNumber = skuNumber;
		this.name = name;
		this.price = price;
	}
	
	public int getSkuNumber(){
		return skuNumber;
	}
	
	public String getName(){
		return name;
	}
	
	public BigDecimal getPrice(){
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + skuNumber;
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
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (skuNumber != other.skuNumber)
			return false;
		return true;
	}
}
