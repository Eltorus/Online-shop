package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.bean.Product;
import by.epam.shop.dao.exception.DAOException;

public interface ProductDAO {
	public void addProduct(Product product) throws DAOException;
	public void deleteProduct(Product product) throws DAOException;
	public List<Product> getAllProducts() throws DAOException;
	public List<Product> getProduct(Product product) throws DAOException;
	public void updateProduct(Product product) throws DAOException;
	public int getTotalProductAmount() throws DAOException;
	public List<Product> getProducts(int offset, int limit) throws DAOException;
}
