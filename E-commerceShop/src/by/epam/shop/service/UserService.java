package by.epam.shop.service;

import java.util.List;

import by.epam.shop.entity.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface UserService {
	boolean addUser(User user) throws ServiceException;
	User signIn(User user) throws ServiceException;
	User getUserWithId(User user) throws ServiceException;
	List<User> getAllUsers() throws ServiceException;
	void deleteUser(User user) throws ServiceException;
	User updateUser(User user) throws ServiceException;
	void updateUserBanStatus(User user) throws ServiceException;
	void updateUserDiscount(User user) throws ServiceException;
}
