package _java._ee._02._dao.factory;

import _java._ee._02._dao.GoodDAO;
import _java._ee._02._dao.UserDAO;
import _java._ee._02._dao.impl.GoodDAOImpl;
import _java._ee._02._dao.impl.UserDAOImpl;

public class DAOFactory {
	private static DAOFactory instance;
	private DAOFactory() {}
	
	private final UserDAO userDAO = new UserDAOImpl();
	private final GoodDAO goodDAO = new GoodDAOImpl();
	
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public GoodDAO getGoodDAO() {
		return goodDAO;
	}
}
