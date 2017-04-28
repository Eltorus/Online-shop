package by.epam.shop.service.impl;

import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;

/*UserServiceImpl implements by.epam.shop.service.UserService */
public class UserServiceImpl implements UserService {

    /*
     * Get User object {@link by.epam.shop.bean.User} by email and password from
     * DAO layer
     * 
     * @param by.epam.shop.bean.User, should contain email and password
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     * 
     * @return by.epam.shop.bean.User
     */
    @Override
    public User signIn(User user) throws ServiceException {
	if (user == null) {
	    throw new ServiceException("User is null");
	}

	User output = null;
	if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPasswordHash() == null
		|| user.getPasswordHash().isEmpty()) {
	    throw new ServiceException("Fields are empty");
	}
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    output = userDAO.getUserWithLoginInf(user);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during signIn prodecure", e);
	}
	return output;
    }

    /*
     * Pass User object {@link by.epam.shop.bean.User} to DAO layer for adding.
     * If User with this email already exist, then method return false
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     * 
     * @return boolean
     */
    @Override
    public boolean addUser(User user) throws ServiceException {
	if (user == null) {
	    throw new ServiceException("Object is null");
	}

	User result = null;
	if (!isUserPropValid(user)) {
	    throw new ServiceException("Fields are empty");
	}
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    result = userDAO.getUserWithEmail(user);

	    if (result == null) {
		userDAO.addUser(user);
	    }
	    return result == null;
	} catch (DAOException e) {
	    throw new ServiceException("Exception during addUser prodecure", e);
	}
    }

    private boolean isUserPropValid(User user) {
	if (user.getEmail() == null || user.getEmail().isEmpty()) {
	    return false;
	}
	if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
	    return false;
	}
	if (user.getName() == null || user.getName().isEmpty()) {
	    return false;
	}
	if (user.getSurname() == null || user.getSurname().isEmpty()) {
	    return false;
	}
	if (user.getPhonenumber() == null || user.getPhonenumber().isEmpty()) {
	    return false;
	}
	return true;
    }

    /*
     * Get User objects {@link by.epam.shop.bean.User} from DAO layer
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     * 
     * @return List<by.epam.shop.bean.User>
     */
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
	    throw new ServiceException("Exception during getAllUsers prodecure", e);
	}
	return userList;
    }

    /*
     * Pass User object {@link by.epam.shop.bean.User} to DAO layer for deleting
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     */
    @Override
    public void deleteUser(User user) throws ServiceException {
	if (user == null) {
	    throw new ServiceException("Object is null");
	}
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    userDAO.deleteUser(user);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during deleteUser prodecure", e);
	}
    }

    /*
     * Pass User object {@link by.epam.shop.bean.User} to DAO layer for updating
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     * 
     * @return updated by.epam.shop.bean.User
     */
    @Override
    public User updateUser(User user) throws ServiceException {
	if (user == null) {
	    throw new ServiceException("Object is null");
	}
	User output = null;
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    output = userDAO.getUserWithLoginInf(user);
	    output.setBalance(user.getBalance());
	    output.setImgPath(user.getImgPath());
	    userDAO.updateUser(output);

	    output = userDAO.getUserWithLoginInf(user);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during updateUser prodecure", e);
	}
	return output;
    }

    /*
     * Pass User object {@link by.epam.shop.bean.User} to DAO layer for changing
     * ban status of User
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     */
    @Override
    public void updateUserBanStatus(User user) throws ServiceException {
	if (user == null || user.getId() == 0) {
	    throw new ServiceException("Object is null");
	}
	User userForWrite = getUserWithId(user);
	try {
	    userForWrite.setBanned(user.isBanned());

	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    userDAO.updateUser(userForWrite);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during updateUserBanStatus prodecure", e);
	}
    }

    /*
     * Pass User object {@link by.epam.shop.bean.User} to DAO layer for updating
     * discount of User
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     */
    @Override
    public void updateUserDiscount(User user) throws ServiceException {
	if (user == null || user.getId() == 0) {
	    throw new ServiceException("Object is null");
	}
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    User userForWrite = userDAO.getUserWithId(user);
	    userForWrite.setDiscountCoefficient(user.getDiscountCoefficient());

	    userDAO.updateUser(userForWrite);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during updateUserDiscount prodecure", e);
	}
    }

    /*
     * Get User object {@link by.epam.shop.bean.User} from DAO with id
     * 
     * @param by.epam.shop.bean.User
     * 
     * @throws by.epam.shop.service.exception.ServiceException
     * 
     * @return by.epam.shop.bean.User}
     */
    @Override
    public User getUserWithId(User user) throws ServiceException {
	if (user == null || user.getId() == 0) {
	    throw new ServiceException("Object is null");
	}
	User result = null;
	try {
	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    result = userDAO.getUserWithId(user);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during getUserWithId prodecure", e);
	}
	return result;
    }
}
