package by.epam.shop.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.util.RoundDouble;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(Cart cart, User user) throws ServiceException {

	Order order = new Order();
	order.setUser(user);
	order.setBill(countOrderBill(cart, user));
	order.setCart(cart);

	return order;
    }

    private double countOrderBill(Cart cart, User user) {
	double bill = 0;

	Iterator<CartLine> itr = cart.getProductList().iterator();
	while (itr.hasNext()) {
	    CartLine cartLine = (CartLine) itr.next();
	    bill += cartLine.getProduct().getPrice() * cartLine.getQuantity();
	}

	double discount = user.getDiscountCoefficient();
	if (discount >= 0) {
	    bill = bill - (bill * discount);
	}

	return RoundDouble.getRoundedDouble(bill);
    }

    @Override
    public void addOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Order is empty");
	}
	OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	try {
	    System.out.println("orderservice: before");
	    orderDAO.addOrder(order);
	    System.out.println("orderservice: after");
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public boolean deleteOrder(Order order) throws ServiceException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void updateOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Object is null");
	}

	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderDAO.updateOrder(order);
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}

    }

    @Override
    public Order getOrder(Order order) throws ServiceException {
	Order result = null;

	if (order == null) {
	    throw new ServiceException("Object is null");
	}

	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    result = orderDAO.getOrder(order);

	    User user = new User();
	    user.setId(result.getUser().getId());

	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    result.setUser(userDAO.getUser(user));

	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return result;
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
	List<Order> orderList = null;
	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderList = orderDAO.getAllOrders();

	    if (orderList.isEmpty()) {
		orderList = null;
	    }
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return orderList;
    }

}
