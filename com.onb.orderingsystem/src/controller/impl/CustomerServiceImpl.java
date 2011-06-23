package controller.impl;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.CustomerService;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO customerDAO;
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	/* (non-Javadoc)
	 * @see controller.impl.CustomerService#getCustomerList()
	 */
	@Override
	public List<Customer> getCustomerList(){
		return customerDAO.retrieveCustomerList();
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.CustomerService#getCustomerWithUnpaidOrders()
	 */
	@Override
	public List<Customer> getCustomerWithUnpaidOrders(){
		return customerDAO.retrieveUnpaidCustomerList();
	}
	
	/* (non-Javadoc)
	 * @see controller.impl.CustomerService#getCustomer(int)
	 */
	@Override
	public Customer getCustomer(int id){
		return customerDAO.retrieveCustomer(id);
	}
}
