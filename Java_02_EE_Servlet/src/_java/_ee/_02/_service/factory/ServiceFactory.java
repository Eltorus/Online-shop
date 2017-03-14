package _java._ee._02._service.factory;

import _java._ee._02._service.GoodService;
import _java._ee._02._service.UserService;
import _java._ee._02._service.impl.GoodServiceImpl;
import _java._ee._02._service.impl.UserServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance;
	
	private ServiceFactory() {}
	
	private final UserService userService = new UserServiceImpl();
	private final GoodService goodService = new GoodServiceImpl();
	
	public static ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public GoodService getGoodService() {
		return goodService;
	}

}