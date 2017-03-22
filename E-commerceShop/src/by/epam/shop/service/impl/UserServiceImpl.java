package by.epam.shop.service.impl;

import by.epam.shop.bean.User;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.validation.Validation;

public class UserServiceImpl implements UserService {
	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	@Override
	public User signIn(User user) throws ServiceException {
		User output = null;
		
		if(user == null) {
			throw new ServiceException();
		}
		
		if(Validation.isStringEmpty(user.getEmail()) || Validation.isStringEmpty(user.getPasswordHash())) {
			throw new ServiceException("Fields are empty");
		}
		try {
			output = userDAO.getUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return output;
	}
	@Override
	public boolean signUp(User user) throws ServiceException {
		if(user == null) {
			throw new ServiceException();
		}
		User result = null;
		if(Validation.isStringEmpty(user.getEmail()) || Validation.isStringEmpty(user.getPasswordHash()) || Validation.isStringEmpty(user.getName()) ||
				Validation.isStringEmpty(user.getSurname()) || Validation.isStringEmpty(user.getPhonenumber())) {
			throw new ServiceException("Fields are empty");
		}
		try {
			result = userDAO.getUser(user);
			if(result == null) {
				userDAO.addUser(user);
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			System.out.println(e);
			throw new ServiceException(e);
		}
	}
	@Override
	public User changeUser(User user) throws ServiceException {
		User output = null;
		if(!Validation.userIsValid(user)) {
			throw new ServiceException("User is not valid");
		}
		try{
			userDAO.updateUser(user);
			output = userDAO.getUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return output;
	}
}
