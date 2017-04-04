package by.epam.shop.service.impl;

import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
	public User signIn(User user) throws ServiceException {
		User output = null;
		
		if(user == null) {
			throw new ServiceException();
		}
		
		if(user.getEmail() == null || user.getEmail().isEmpty() || user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
			throw new ServiceException("Fields are empty");
		}
		try {
			UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
			output = userDAO.getUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return output;
	}

    @Override
    public boolean addUser(User user) throws ServiceException {
	if (user == null) {
	    throw new ServiceException();
	}
	User result = null;
	if (!isUserPropValid(user)) {
	    throw new ServiceException("Fields are empty");
	}
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    result = userDAO.getUser(user);
	    if (result == null) {
		userDAO.addUser(user);
		return true;
	    } else {
		return false;
	    }
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
    }
    
    private boolean isUserPropValid(User user) {
	if(user.getEmail() == null || user.getEmail().isEmpty()) {
	    return false;
	}
	if(user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
	    return false;
	}
	if(user.getName() == null || user.getName().isEmpty()) {
	    return false;
	}
	if(user.getSurname() == null || user.getSurname().isEmpty()) {
	    return false;
	}
	if(user.getPhonenumber() == null || user.getPhonenumber().isEmpty()) {
	    return false;
	}
	return true;
    }

    @Override
    public User changeUser(User user) throws ServiceException {
	User output = null;

	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    userDAO.updateUser(user);
	    
	    output = userDAO.getUser(user);
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return output;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
	List<User> userList = null;
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    userList = userDAO.getAllUsers();
	    if (userList.isEmpty()) {
		return null;
	    }
	} catch (DAOException e) {
	    throw new ServiceException(e);
	}
	return userList;
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
	// TODO Auto-generated method stub

    }
}
