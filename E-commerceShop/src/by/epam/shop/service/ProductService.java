package by.epam.shop.service;

import java.util.List;

import by.epam.shop.bean.Product;
import by.epam.shop.service.exception.ServiceException;

public interface ProductService {
	public List<Product> getAllProducts() throws ServiceException;
	public Product getProduct(Product product) throws ServiceException;
	public List<Product> getProductWithTitle(Product product) throws ServiceException;
	public List<Product> getProductWithCategory(Product product) throws ServiceException;
	public List<Product> getProductWithPrice(Product product) throws ServiceException;
	public Product getProductWithId(Product product) throws ServiceException;
	public void changeProduct(Product product) throws ServiceException;
	public void addProduct(Product product) throws ServiceException;
	public void deleteProduct(Product product) throws ServiceException;
}
