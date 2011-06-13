package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

	private int skuNumber;
	private String name;
	private BigDecimal price;
	
	public Product(int skuNumber, String name, BigDecimal price){
		if(skuNumber <0)
			throw new IllegalArgumentException("skuNumber is less than 0. skuNumber should be greater or equal to than 0.");
		else if (name== null)
			throw new NullPointerException("name has no value");
		else if (name.isEmpty())
			throw new IllegalArgumentException("name string is empty");
		else if (price == null)
			throw new NullPointerException("price parameter is null");
		this.skuNumber = skuNumber;
		this.name = name;
		this.price = price;
	}	
	
	public int getSKUNumber(){
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
		if (skuNumber != other.skuNumber)
			return false;
		return true;
	}

	
	
}
