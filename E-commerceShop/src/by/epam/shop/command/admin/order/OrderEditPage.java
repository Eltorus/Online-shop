package by.epam.shop.command.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Order;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class OrderEditPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response) || !UserValidation.isUserAdmin(request, response)) {
			return PageList.PG_SIGNIN;
		}
		
		try {
			Order order = new Order();
			order.setId(Integer.parseInt(request.getParameter(ParameterList.CMD_ORDER_ID)));
			
			OrderService orderService = ServiceFactory.getInstance().getOrderService();
			Order result = orderService.getOrder(order);
			request.setAttribute(AttributeList.ATTR_ORDER, result);

			return PageList.PG_ORDER_INFO;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
	
}
