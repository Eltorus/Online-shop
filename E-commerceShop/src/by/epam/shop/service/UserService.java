package by.epam.shop.service;

import by.epam.shop.bean.User;
import by.epam.shop.service.exception.ServiceException;

public interface UserService {
	public boolean signUp(User user) throws ServiceException;
	public User changeUser(User user) throws ServiceException;
	public User signIn(User user) throws ServiceException;
}
