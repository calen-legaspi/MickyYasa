package domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Order {
	private int orderNumber;
	private String date;
	private int year;
	private int month;
	private int dayOfMonth;
	private List<OrderItem> orderList;
	
	public Order(int orderNumber, String date){
		this.orderNumber = orderNumber;
		if(checkIfNull(date) == 0){
			this.date = setDate();
		}
		else{
			this.date = date;
		}
		
		this.orderList = new ArrayList<OrderItem>();
	}
	
	public void addOrderItem(OrderItem orderItem){
		orderList.add(orderItem);
	}
	
	public List<OrderItem> getOrderList(){
		return orderList;
	}
	
	public int checkIfNull(String date){
		return date.compareTo("");
	}
	
	private String setDate(){
		Calendar calendar = new GregorianCalendar();		
		month = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		year = calendar.get(Calendar.YEAR);
		
		return (month + 1) + "/" + dayOfMonth + "/" + year;
	}
	
	public int getOrderNumber(){
		return orderNumber;
	}
	
	public String getDate(){
		return date;
	}

	
}
