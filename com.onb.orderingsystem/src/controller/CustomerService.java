package controller;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerService {
	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	static CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");

	/**
	 * 
	 * @return the entire list of customer
	 */
	public static List<Customer> getCustomerList(){
		return customerDao.retrieveCustomerList();
	}
	
	/**
	 * 
	 * @return the list of customers with Unpaid orders
	 */
	public static List<Customer> getCustomerWithUnpaidOrders(){
		return customerDao.retrieveUnpaidCustomerList();
	}
	
	/**
	 * 
	 * @param id
	 * @return a specific customer
	 */
	public static Customer getCustomer(int id){
		return customerDao.retrieveCustomer(id);
	}
}
