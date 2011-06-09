package dao;

import domainmodel.Product;

public interface ProductDAOInterface{

	public void createProduct(Product object);

	public void deleteProduct(Product object);

	public Product getProduct(int id);

}
