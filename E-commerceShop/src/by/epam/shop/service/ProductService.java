package by.epam.shop.service;

import java.util.List;

import by.epam.shop.entity.bean.Product;
import by.epam.shop.service.exception.ServiceException;

public interface ProductService {
	List<Product> getAllProducts() throws ServiceException;
	int getTotalProductAmount() throws ServiceException;
	List<Product> getProducts(int offset, int limit) throws ServiceException;
	Product getProductWithId(Product product) throws ServiceException;
	void changeProduct(Product product) throws ServiceException;
	void addProduct(Product product) throws ServiceException;
	void deleteProduct(Product product) throws ServiceException;
}
