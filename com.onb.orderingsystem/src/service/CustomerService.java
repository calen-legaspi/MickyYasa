package service;

import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.CustomerRowMapper;

import dao.CustomerDAO;
import domainmodel.Customer;

public class CustomerService implements CustomerDAO{
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Create a customer
	 */
	public void createCustomer(Customer customer){
		String sql = "insert into Customer (id, last_name, first_name, middle_name) values (?,?,?,?)";
		Object[] params = new Object[]{customer.getID(), customer.getLastName(), customer.getFirstName(), customer.getMiddleName()};
		this.getJdbcTemplate().update(sql,params);
	}
	
	
	/**
	 * Delete a specified Customer
	 */
	public void deleteCustomer(Customer customer){
		String sql = "delete from Customer where ID = ?";
		Object[] params = new Object[]{customer.getID()};
		this.getJdbcTemplate().update(sql, params);
	}
	
	
	/**
	 * Retrieves the entire list of Customer
	 */
	public List<Customer> retrieveCustomerList(){
		return this.getJdbcTemplate().query("select * from Customer", new CustomerRowMapper());
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

		


}
