package com.onb.daos.impl;

import com.onb.daos.ProductDAO;
import com.onb.domainmodel.Product;
import com.onb.resultretrieval.ProductRowMapper;



public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
	@Override
	public void createProduct(Product object) {
		String sql = "insert into Product (SKU_Number, Name, PRICE) values (?,?,?)";
		Object[] params = new Object[]{object.getSKUNumber(), object.getName(), object.getPrice().doubleValue()};
		this.getJdbcTemplate().update(sql,params);
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
		Object product = this.getJdbcTemplate().queryForObject(sql, params, new ProductRowMapper());
		if(product!=null)
			return (Product) product;
		else return null;
	}

}
