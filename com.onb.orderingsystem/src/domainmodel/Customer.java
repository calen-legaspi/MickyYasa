package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


public class Customer implements Serializable{
	
	private final int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private BigDecimal totalPaidOrders = BigDecimal.ZERO;
	private BigDecimal totalPriceOfOrders = BigDecimal.ZERO;	
	private List<Order> orders;
	
	/**
	 * Creates a new instance of Object Customer with identifier id
	 * @param id - the id of the customer
	 */
	public Customer(int id){
		this.id = id;
		setOrders(new ArrayList<Order>());
	}
	

	/**
	 * Adds a new order to the customer's list of orders
	 * @param order - the order to be added
	 */
	public void addOrder(Order o){
		if(checkOrder(o)){
			getOrders().add(o);
			totalPriceOfOrders = totalPriceOfOrders.add(o.computeTotalCost(getCreditLimit().intValue()));
		}
	}
	
	private boolean checkOrder(Order o){
		BigDecimal sum = getTotalUnpaidOrders().add(o.computeTotalCost(getCreditLimit().intValue()));
		if(getOrders().contains(o)){
			return false;
		}else if (sum.compareTo(getCreditLimit()) == 1){
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 *  Sets the order's status to paid. It checks first if the order is in the customer's set of orders before doing anything else.
	 * @param order - the order to be added 
	 */
	public void payOrder(Order o){
		if(getOrders().contains(o)){
			o.consolidateItems();
			getOrders().remove(o);
			o.pay();
			getOrders().add(o);
			addToTotalPaidOrders(o.computeTotalCost(getCreditLimit().intValue()));
		}
	}
	
	private void addToTotalPaidOrders(BigDecimal amount){
		totalPaidOrders = totalPaidOrders.add(amount);
	}
	
	/**
	 * Gets the customer's current credit limit based on the accumulated amount he/she has paid 
	 * @return the credit limit
	 */
	public BigDecimal getCreditLimit(){
		if (totalPaidOrders.compareTo(new BigDecimal(100000)) ==-1){
			return new BigDecimal(10000);
		}else if (totalPaidOrders.compareTo(new BigDecimal(500000)) ==-1){
			return new BigDecimal(30000);
		}else if (totalPaidOrders.compareTo(new BigDecimal(1000000)) ==-1){
			return new BigDecimal(75000);
		}else{
			return new BigDecimal(150000);
		}
	}
	
	/**
	 * 
	 * @return the id
	 */
	public int getID(){
		return id;
	}

	/**
	 * Gets all of the unpaid orders the customer currently has
	 * @return sorted list of unpaid orders
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getUnpaidOrders(){
		List<Order> unpaidOrders = new ArrayList<Order>();
		for(Order o:getOrders()){
			if(!o.hasPaid()){
				unpaidOrders.add(o);
			}
		}
		Collections.sort(unpaidOrders);
		return unpaidOrders;
	}
	
	/**
	 * Gets the total amount of the unpaid orders
	 * @return the 
	 */
	public BigDecimal getTotalUnpaidOrders(){
		return totalPriceOfOrders.subtract(totalPaidOrders);
	}

	/**
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Assigns the customer's last name
	 * @param lastName - the customer's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Assigns the customer's first name
	 * @param firstName - the customer's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Assigns the customer's middle name
	 * @param middleName - the customer's middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Sets the Customer's list of orders. Note: this should only be called immediately after you create a new Customer and the customer and list of orders are retrieved from a database. 
	 * @param orders - the list of orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	
	
	
}
