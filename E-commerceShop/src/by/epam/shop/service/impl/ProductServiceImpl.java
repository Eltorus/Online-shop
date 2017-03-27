package by.epam.shop.service.impl;

import java.util.List;

import by.epam.shop.bean.Product;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getAllProducts() throws ServiceException {
		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
		List<Product> products = null;
		try {
			products = productDAO.getAllProducts();
			if(products.isEmpty()) {
				return null;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return products;
	}

	@Override
	public Product getProduct(Product product) throws ServiceException {
		if(product == null) {
			throw new ServiceException("Object is null");
		}
		///
		return null;
	}
	
	@Override
	public Product getProductWithId(Product product) throws ServiceException {
		ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
		Product result = null;
		if(product == null) {
			throw new ServiceException("Product is null");
		}
		if (product.getId() == 0) {
			throw new ServiceException("Id is null");
		}
		try {
			List<Product> productList = productDAO.getProduct(product);
			result = productList.get(0);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<Product> getProductWithTitle(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductWithCategory(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductWithPrice(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeProduct(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
