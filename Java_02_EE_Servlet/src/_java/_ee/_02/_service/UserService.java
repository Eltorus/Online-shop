package _java._ee._02._service;

import _java._ee._02._bean.User;
import _java._ee._02._service.exception.ServiceException;

public interface UserService {
	public boolean signUp(User user) throws ServiceException;
	public boolean rechargeBalance(User user) throws ServiceException;
	public User signIn(User user) throws ServiceException;
}
