package by.epam.shop.service.impl;

import java.util.List;

import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.entity.bean.Product;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;

/* ProductServiceImpl implements by.epam.shop.service.ProductService */
public class ProductServiceImpl implements ProductService {
    
    /* Get Product objects {@link by.epam.shop.bean.Product} from DAO layer
     * @throws by.epam.shop.service.exception.ServiceException
     * @return List<by.epam.shop.bean.Product>
     */
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
	    throw new ServiceException("Exception during getAllProducts prodecure",e);
	}
	return products;
    }

    /* Get Product object {@link by.epam.shop.bean.Product} from DAO layer
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.service.exception.ServiceException
     * @return by.epam.shop.bean.Product
     */
    @Override
    public Product getProductWithId(Product product) throws ServiceException {
	if (product == null || product.getId() == 0) {
	    throw new ServiceException("Product is null");
	}
	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	Product result = null;
	try {
	    result= productDAO.getProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during getProductWithId prodecure",e);
	}
	return result;
    }

    /* Passes Product object {@link by.epam.shop.bean.Product} from DAO layer for updating
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.service.exception.ServiceException if Product fields don't correctly filled
     */
    @Override
    public void changeProduct(Product product) throws ServiceException {
	if (!isProductValid(product)) {
	    throw new ServiceException("Product is not valid");
	}

	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	try {
	    productDAO.updateProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during changeProduct prodecure",e);
	}

    }

    /* Passes Product object {@link by.epam.shop.bean.Product} from DAO layer for adding
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.service.exception.ServiceException if Product fields don't correctly filled
     */
    @Override
    public void addProduct(Product product) throws ServiceException {
	if (!isProductValid(product)) {
	    throw new ServiceException("Product is not valid");
	}

	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	try {
	    productDAO.addProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during addProduct prodecure",e);
	}
    }

    /* Passes Product object {@link by.epam.shop.bean.Product} from DAO layer for deleting
     * @param by.epam.shop.bean.Product, should contain id of Product 
     * @throws by.epam.shop.service.exception.ServiceException
     */
    @Override
    public void deleteProduct(Product product) throws ServiceException {
	if(product == null || product.getId() == 0) {
	    throw new ServiceException("Parameters are null");
	}
	
	try {
	    ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	    productDAO.deleteProduct(product);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during deleteProduct prodecure",e);
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
	if (product.getCategoryID() <= 0 || product.getCategoryID()>4) {
	    return false;
	}
	if(product.getDescription()==null || product.getDescription().trim().isEmpty()) {
	    return false;
	}
	if(product.getImgPath()==null || product.getImgPath().trim().isEmpty()) {
	    return false;
	}

	return true;
    }

    /* Get total amount of Products from DAO layer
     * @throws by.epam.shop.service.exception.ServiceException
     * @return int amount of products
     */
    @Override
    public int getTotalProductAmount() throws ServiceException {
	int amount = 0;
	ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	try {
	     amount = productDAO.getTotalProductAmount();
	} catch (DAOException e) {
	    throw new ServiceException("Exception during getTotalProductAmount prodecure",e);
	}
	return amount;
    }

    /* Get part of total amount of products from DAO layer
     * @param int offset - index of first product
     * @param int limit - number of products to get
     * @throws by.epam.shop.service.exception.ServiceException
     * @return List<by.epam.shop.bean.Product>
     */
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
	    throw new ServiceException("Exception during getProducts prodecure",e);
	}
	
	return productList;
    }

}
