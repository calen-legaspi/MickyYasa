package controller;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");

	public List<Customer> getCustomerList(){
		List<Customer> customerList = customerDao.retrieveCustomerList();
		return customerList;
	}
	
	public List<Customer> getCustomerWithUnpaidOrders(){
		List<Customer> customerList = customerDao.retrieveUnpaidCustomerList();
		return customerList;
	}
}
