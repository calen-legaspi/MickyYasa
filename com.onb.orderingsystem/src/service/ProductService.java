package service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.ProductRowMapper;

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
		String sql = "select * from Product p where p.SKU_Number = ?";
		Object[] params = new Object[]{id};
		return (Product)jdbcTemplate.queryForObject(sql, params, new ProductRowMapper());
	}

}
