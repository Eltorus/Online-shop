package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.Order;

public interface OrderDAO {
	void addOrder(Order order) throws DAOException;
	void updateOrder(Order order) throws DAOException;
	Order getOrder(Order order) throws DAOException;
	List<Order> getOrders(Order order) throws DAOException;
	List<Order> getAllOrders() throws DAOException;
	void deleteOrder(Order order) throws DAOException;
}
