package by.epam.shop.service;

import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface UserService {
	public boolean addUser(User user) throws ServiceException;
	public User signIn(User user) throws ServiceException;
	public User getUserWithId(User user) throws ServiceException;
	public List<User> getAllUsers() throws ServiceException;
	public void deleteUser(User user) throws ServiceException;
	public User updateUser(User user) throws ServiceException;
	public void updateUserBanStatus(User user) throws ServiceException;
	public void updateUserDiscount(User user) throws ServiceException;
}
