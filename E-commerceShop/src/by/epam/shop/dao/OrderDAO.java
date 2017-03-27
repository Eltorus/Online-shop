package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.bean.Order;
import by.epam.shop.dao.exception.DAOException;

public interface OrderDAO {
	public void addOrder(Order order) throws DAOException;
	public void updateOrder(Order order) throws DAOException;
	public Order getOrder(Order order) throws DAOException;
	public List<Order> getAllOrders() throws DAOException;
	public void deleteOrder(Order order) throws DAOException;
}
