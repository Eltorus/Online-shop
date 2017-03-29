package by.epam.shop.command.admin;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Order;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class AdminOrderPage implements Command{
    private final static Logger logger = Logger.getLogger(AdminOrderPage.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response)|| !UserValidation.isUserAdmin(request, response)) {
			return PageList.PG_SIGNIN;
		}
		
		try {
			OrderService orderService = ServiceFactory.getInstance().getOrderService();
			List<Order> orderList = orderService.getAllOrders();

			request.setAttribute(AttributeList.ATTR_ORDERLIST, orderList);
		} catch (ServiceException e) {
		    	logger.error(e);
			throw new CommandException(e);
		}
		return PageList.PG_ADMIN_ORDER;
	}

}
