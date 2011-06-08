package service;

import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.CustomerDao;
import domainmodel.Customer;

public class CustomerService implements CustomerDao{
	private JdbcTemplate jdbcTemplate;
	
	public void createCustomer(Customer customer){
		String sql = "insert into Customer (id, last_name, first_name, middle_name) values (?,?,?,?)";
		Object[] params = new Object[]{customer.getID(), customer.getLastName(), customer.getFirstName(), customer.getMiddleName()};
		this.getJdbcTemplate().update(sql,params);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
