package by.epam.shop.service;

import java.util.List;

import by.epam.shop.entity.Cart;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface OrderService {
	public void addOrder(Order order) throws ServiceException;
	public void deleteOrder(Order order) throws ServiceException;
	public Order createOrder(Cart cart, User user) throws ServiceException;
	public void updateOrder(Order order) throws ServiceException;
	public Order getOrder(Order order) throws ServiceException;
	public List<Order> getUserOrders(User user) throws ServiceException;
	public List<Order> getAllOrders() throws ServiceException;
}
