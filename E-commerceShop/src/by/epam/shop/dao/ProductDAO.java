package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.bean.Product;
import by.epam.shop.dao.exception.DAOException;

public interface ProductDAO {
	public boolean addProduct(Product product) throws DAOException;
	public boolean deleteProduct(Product product) throws DAOException;
	public List<Product> getAllProducts() throws DAOException;
	public Product getProductWithId(Product product) throws DAOException;
	public List<Product> getProductWithTitle(Product product) throws DAOException;
	public List<Product> getProductWithCategory(Product product) throws DAOException;
}
