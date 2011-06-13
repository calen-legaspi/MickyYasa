package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Order implements Comparable,Serializable{
	
	private int orderNumber;

	public static int increment = 0;
	private Calendar date;
	private boolean paid;
	private int payerID;
	private List<OrderItem> items;
	
	public Order(Customer payerID){
		orderNumber = 1000000+increment;
		increment++;
		this.payerID = payerID.getID();
		date = new GregorianCalendar();
		date.clear(Calendar.MILLISECOND);		//The milliseconds variable makes one unit test fail
		items = new ArrayList<OrderItem>();
		paid = false;
	}
	
	public Order(int orderNumber, int payerID, Date date, boolean paid){
		this.orderNumber = orderNumber;
		this.date = new GregorianCalendar();
		this.payerID = payerID;
		this.date.setTime(date);
		items = new ArrayList<OrderItem>();
		this.paid = paid;
	}
	
	public void addItem(OrderItem o){
		items.add(o);
	}
	
	public void deleteItem(OrderItem o){
		if(items.contains(o)){
			items.remove(o);
		}
	}
	
	public void update(){
		ArrayList<OrderItem> newOrderList = new ArrayList<OrderItem>();
		for(OrderItem i: items){
			if(newOrderList.contains(i))
				updateOrderItem(newOrderList.indexOf(i), i.getQuantity());
			else newOrderList.add(i);
		}
		items = newOrderList;
	}
	
	private void updateOrderItem(int index, int amount){
		items.get(index).addToQuantity(amount);
	}
	
	public BigDecimal computeTotalCost(int creditLimit){
		BigDecimal totalCost = new BigDecimal(0);
		for(OrderItem o: items)
			totalCost = totalCost.add(o.getCost());
		if(creditLimit == 150000)
			totalCost = totalCost.subtract(totalCost.multiply(new BigDecimal("0.10")));
		return totalCost;
	}

	
	public void pay(){
		paid = true;
	}
	
	public boolean hasPaid(){
		return paid;
	}

	public int getOrderNumber(){
		return orderNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderNumber;
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
		Order other = (Order) obj;
		if (orderNumber != other.orderNumber)
			return false;
		return true;
	}

	public void setDate(Calendar newDate){
		date = newDate;
	}
	
	public Calendar getDateofOrderCreation() {
		return date;
	}

	public int getNumberofItems() {
		return items.size();
	}
	
	public void setOrderItems(List<OrderItem> items){
		this.items = items;
	}
	
	public List<OrderItem> getItems(){
		return items;
	}

	public int getCustomerID(){
		return payerID;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Order o = (Order) arg0;
		int comparison = this.date.compareTo(o.date);
		if(comparison == 0){
			if(this.orderNumber > o.orderNumber)
				return 1;
			else if (this.orderNumber < o.orderNumber)
				return -1;
			else return 0;
		}else return comparison;
	}
	
}
