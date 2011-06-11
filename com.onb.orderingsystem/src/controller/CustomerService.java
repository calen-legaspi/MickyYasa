package controller;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");

	/**
	 * 
	 * @return the entire customer list
	 */
	public List<Customer> getCustomerList(){
		return customerDao.retrieveCustomerList();
	}
	
	/**
	 * 
	 * @return the list of customers with unpaid orders
	 */
	public List<Customer> getCustomerWithUnpaidOrders(){
		return customerDao.retrieveUnpaidCustomerList();
	}
}
