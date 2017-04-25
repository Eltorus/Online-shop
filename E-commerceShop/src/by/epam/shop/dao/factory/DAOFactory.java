package by.epam.shop.dao.factory;

import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.impl.OrderDAOImpl;
import by.epam.shop.dao.impl.ProductDAOImpl;
import by.epam.shop.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory instance;

    private DAOFactory() {
    }

    private final UserDAO userDAO = new UserDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public static DAOFactory getInstance() {
	DAOFactory localInstance = instance;
	if (localInstance == null) {
	    synchronized (DAOFactory.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new DAOFactory();
		}
	    }
	}
	return instance;
    }

    public UserDAO getUserDAO() {
	return userDAO;
    }

    public ProductDAO getProductDAO() {
	return productDAO;
    }

    public OrderDAO getOrderDAO() {
	return orderDAO;
    }
}
