package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import resultretrieval.ProductRowMapper;

import domainmodel.Product;

public class ProductDAOImpl implements ProductDAO {
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
		String sql = "select SKU_Number, Name, PRICE from Product p where p.SKU_Number = ?";
		Object[] params = new Object[]{id};
		Object product = jdbcTemplate.queryForObject(sql, params, new ProductRowMapper());
		if(product!=null)
			return (Product) product;
		else return null;
	}

}
