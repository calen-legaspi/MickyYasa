package domainmodel;

import java.math.BigDecimal;
import java.util.*;


public class Customer {
	
	private int id;
	private String name;
	private BigDecimal totalPaidOrders;
	private BigDecimal totalPriceOfOrders;	
	private Set<Order> orders;
	
	
	public Customer(int id, String name){
		this.id = id;
		this.name = name;
		totalPaidOrders = new BigDecimal(0);
		totalPriceOfOrders = new BigDecimal(0);
		orders = new HashSet<Order>();
	}
	
	public void addOrder(Order o){
		if(checkOrder(o)){
			orders.add(o);
			totalPriceOfOrders = totalPriceOfOrders.add(o.getTotalCost());
		}
	}
	
	private boolean checkOrder(Order o){
		BigDecimal sum = getTotalUnpaidOrders().add(o.getTotalCost());
		if(orders.contains(o)){
			return false;
		}else if (sum.compareTo(getCreditLimit()) == 1){
			return false;
		}else {
			return true;
		}
	}
	
	public void payOrder(Order o){
		if(orders.contains(o)){
			o.update();
			orders.remove(o);
			o.pay();
			orders.add(o);
			addToTotalPaidOrders(o.getTotalCost());
		}
	}
	
	private void addToTotalPaidOrders(BigDecimal amount){
		totalPaidOrders = totalPaidOrders.add(amount);
	}
	
	public BigDecimal getCreditLimit(){
		if (totalPaidOrders.compareTo(new BigDecimal(100000)) ==-1)
			return new BigDecimal(10000);
		else if (totalPaidOrders.compareTo(new BigDecimal(500000)) ==-1)
			return new BigDecimal(30000);
		else if (totalPaidOrders.compareTo(new BigDecimal(1000000)) ==-1)
			return new BigDecimal(75000);
		else return new BigDecimal(150000);
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getUnpaidOrders(){
		List<Order> unpaidOrders = new ArrayList<Order>();
		for(Order o:orders){
			if(!o.hasPaid())
				unpaidOrders.add(o);
		}
		Collections.sort(unpaidOrders);
		return unpaidOrders;
	}
	
	public BigDecimal getTotalUnpaidOrders(){
		return totalPriceOfOrders.subtract(totalPaidOrders);
	}

	
}
