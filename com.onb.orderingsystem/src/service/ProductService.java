package service;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.ProductDAOInterface;
import domainmodel.Product;

public class ProductService implements ProductDAOInterface {
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createProduct(Product object) {
		String sql = "insert into Product (SKU_Number, Name, PRICE) values (?,?,?)";
		Object[] params = new Object[]{object.getSKUNumber(), object.getName(), object.getPrice().doubleValue()};
		this.getJdbcTemplate().update(sql,params);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void deleteProduct(Product object) {
		String sql = "delete from Product where SKU_Number=? and Name=? and PRICE = ?";
		Object[] params = new Object[]{object.getSKUNumber(), object.getName(), object.getPrice().doubleValue()};
		this.getJdbcTemplate().update(sql,params);
	}

	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
