package _java._ee._02._service.impl;

import _java._ee._02._bean.User;
import _java._ee._02._dao.UserDAO;
import _java._ee._02._dao.exception.DAOException;
import _java._ee._02._dao.factory.DAOFactory;
import _java._ee._02._service.UserService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.validation.Validation;

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
	public boolean rechargeBalance(User user) throws ServiceException {
		if(!userIsValid(user)) {
			System.out.println(user);
			throw new ServiceException("User is not valid");
		}
		boolean isBalanceChanged;
		try{
			isBalanceChanged = userDAO.rechargeBalance(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isBalanceChanged;
	}
	
	public boolean userIsValid(User user) {
		boolean result = true;
		if(user == null) {
			result =  false;
			System.out.println("here");
		}
		if(Validation.isStringEmpty(user.getEmail()) || Validation.isStringEmpty(user.getPasswordHash())) {
			System.out.println("there");
			result = false;
		}
		return result;
	}

}
