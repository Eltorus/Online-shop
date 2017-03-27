package by.epam.shop.service;

import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface UserService {
	public boolean addUser(User user) throws ServiceException;
	public User changeUser(User user) throws ServiceException;
	public User signIn(User user) throws ServiceException;
	public List<User> getAllUsers() throws ServiceException;
	public void deleteUser(User user) throws ServiceException;
}
