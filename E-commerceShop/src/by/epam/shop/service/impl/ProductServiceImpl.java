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
	    if (products.isEmpty()) {
		return null;
	    }
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return products;
    }

    @Override
    public Product getProduct(Product product) throws ServiceException {
	if (product == null) {
	    throw new ServiceException("Object is null");
	}
	///
	return null;
    }

    @Override
    public Product getProductWithId(Product product) throws ServiceException {
	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	Product result = null;
	if (product == null) {
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
	if (!isProductValid(product)) {
	    throw new ServiceException("Object is not valid");
	}

	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	try {
	    productDAO.updateProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}

    }

    @Override
    public void addProduct(Product product) throws ServiceException {
	if (!isProductValid(product)) {
	    throw new ServiceException("Object is not valid");
	}

	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	try {
	    productDAO.addProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void deleteProduct(Product product) throws ServiceException {
	if(product == null || product.getId() == 0) {
	    throw new ServiceException("Parameters are null");
	}
	
	try {
	    ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	    productDAO.deleteProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
    }

    private boolean isProductValid(Product product) {
	if (product == null) {
	    return false;
	}
	if (product.getTitle() == null || product.getTitle().isEmpty()) {
	    return false;
	}
	if (product.getPrice() == 0) {
	    return false;
	}
	if (product.getCategoryID() == 0) {
	    return false;
	}
	if(product.getAmount() == 0 ) {
	    return false;
	}
	if(product.getImgPath() == null || product.getImgPath().isEmpty()) {
	    return false;
	}

	return true;
    }

    @Override
    public int getTotalProductAmount() throws ServiceException {
	int amount = 0;
	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	try {
	     amount = productDAO.getTotalProductAmount();
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return amount;
    }

    @Override
    public List<Product> getProducts(int offset, int limit) throws ServiceException {
	if(limit == 0) {
	    throw new ServiceException("Values are null");
	}
	
	List<Product> productList = null;
	try {
	    ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	    productList = productDAO.getProducts(offset, limit);
	    
	    if(productList.isEmpty()) {
		return null;
	    }
	    
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	
	return productList;
    }

}
