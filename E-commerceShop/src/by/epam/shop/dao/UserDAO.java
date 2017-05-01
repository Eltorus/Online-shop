package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.User;

public interface UserDAO {
	public User getUserWithId(User user) throws DAOException;
	public User getUserWithEmail(User user) throws DAOException;
	public User getUserWithLoginInf(User user) throws DAOException;
	public List<User> getAllUsers() throws DAOException;
	public void addUser(User user) throws DAOException;
	public void deleteUser(User user) throws DAOException;
	public void updateUser(User user) throws DAOException;
}
