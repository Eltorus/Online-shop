package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.dao.exception.DAOException;

public interface UserDAO {
	public User getUser(User user) throws DAOException;
	public List<User> getAllUsers() throws DAOException;
	public void addUser(User user) throws DAOException;
	public void deleteUser(User user) throws DAOException;
	public void updateUser(User user) throws DAOException;
}
