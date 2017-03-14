package _java._ee._02._dao;

import _java._ee._02._bean.User;
import _java._ee._02._dao.exception.DAOException;

public interface UserDAO {
	public User getUser(User user) throws DAOException;
	public boolean addUser(User user) throws DAOException;
	public boolean deleteUser(User user) throws DAOException;
	public boolean rechargeBalance (User user) throws DAOException;
}
