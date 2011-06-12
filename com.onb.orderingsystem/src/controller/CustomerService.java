package controller;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerService {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	CustomerDAO customerDao = (CustomerDAO)ctx.getBean("CustomerDao");

	public List<Customer> getCustomerList(){
		return customerDao.retrieveCustomerList();
	}
	
	public List<Customer> getCustomerWithUnpaidOrders(){
		return customerDao.retrieveUnpaidCustomerList();
	}
	
	public Customer getCustomer(int id){
		return customerDao.retrieveCustomer(id);
	}
}
