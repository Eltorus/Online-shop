package by.epam.shop.service.factory;

import by.epam.shop.service.OrderService;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.impl.OrderServiceImpl;
import by.epam.shop.service.impl.ProductServiceImpl;
import by.epam.shop.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance;
	
	private ServiceFactory() {}
	
	private final UserService userService = new UserServiceImpl();
	private final ProductService productService = new ProductServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();
	
	public static ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

}