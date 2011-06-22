package com.onb.daos;

import com.onb.domainmodel.Product;

public interface ProductDAO{

	public void createProduct(Product object);

	public void deleteProduct(Product object);

	public Product getProduct(int id);

}
