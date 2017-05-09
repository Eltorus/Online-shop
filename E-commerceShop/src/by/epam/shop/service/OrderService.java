package by.epam.shop.service;

import java.util.List;

import by.epam.shop.entity.Cart;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface OrderService {
	void addOrder(Order order) throws ServiceException;
	void deleteOrder(Order order) throws ServiceException;
	Order createOrder(Cart cart, User user) throws ServiceException;
	void updateOrder(Order order) throws ServiceException;
	Order getOrder(Order order) throws ServiceException;
	List<Order> getUserOrders(User user) throws ServiceException;
	List<Order> getAllOrders() throws ServiceException;
}
