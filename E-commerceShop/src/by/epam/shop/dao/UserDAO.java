package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.User;

public interface UserDAO {
	User getUserWithId(User user) throws DAOException;
	User getUserWithEmail(User user) throws DAOException;
	User getUserWithLoginInf(User user) throws DAOException;
	List<User> getAllUsers() throws DAOException;
	void addUser(User user) throws DAOException;
	void deleteUser(User user) throws DAOException;
	void updateUser(User user) throws DAOException;
}
