package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.Product;

public interface ProductDAO {
	public void addProduct(Product product) throws DAOException;
	public void deleteProduct(Product product) throws DAOException;
	public List<Product> getAllProducts() throws DAOException;
	public Product getProduct(Product product) throws DAOException;
	public void updateProduct(Product product) throws DAOException;
	public int getTotalProductAmount() throws DAOException;
	public List<Product> getProducts(int offset, int limit) throws DAOException;
}
