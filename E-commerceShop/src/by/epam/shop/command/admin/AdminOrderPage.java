package by.epam.shop.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Order;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.UserValidation;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class AdminOrderPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response)) {
			return PageList.PG_SIGNIN;
		}
		if(!UserValidation.isUserAdmin(request, response)) {
			////
		}
		
		try {
			OrderService orderService = ServiceFactory.getInstance().getOrderService();
			List<Order> orderList = orderService.getAllOrders();
			System.out.println("Order List size - "+orderList.size());
			request.setAttribute(AttributeList.ATTR_ORDERLIST, orderList);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageList.PG_ADMIN_ORDER;
	}

}
