package com.onb.services.impl;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.daos.CustomerDAO;
import com.onb.domainmodel.Customer;
import com.onb.services.CustomerService;


public class CustomerServiceImpl implements CustomerService{
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	private CustomerDAO customerDAO = (CustomerDAO)ctx.getBean("customerDAO");

	/**
	 * 
	 * @return the entire list of customer
	 */
	public List<Customer> getCustomerList(){
		return customerDAO.retrieveCustomerList();
	}
	
	/**
	 * 
	 * @return the list of customers with Unpaid orders
	 */
	public List<Customer> getCustomerWithUnpaidOrders(){
		return customerDAO.retrieveUnpaidCustomerList();
	}
	
	/**
	 * 
	 * @param id
	 * @return a specific customer
	 */
	public Customer getCustomer(int id){
		return customerDAO.retrieveCustomer(id);
	}
}
