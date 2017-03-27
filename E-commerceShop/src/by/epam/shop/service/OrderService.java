package by.epam.shop.service;

import java.util.List;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface OrderService {
	public void addOrder(Order order) throws ServiceException;
	public boolean deleteOrder(Order order) throws ServiceException;
	public Order createOrder(Cart cart, User user) throws ServiceException;
	public void updateOrder(Order order) throws ServiceException;
	public Order getOrder(Order order) throws ServiceException;
	public List<Order> getAllOrders() throws ServiceException;
}
