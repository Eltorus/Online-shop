package by.epam.shop.dao;

import by.epam.shop.bean.Order;
import by.epam.shop.dao.exception.DAOException;

public interface OrderDAO {
	public void addOrder(Order order) throws DAOException;
	public boolean changeOrder(Order order) throws DAOException;
}
