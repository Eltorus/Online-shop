package by.epam.shop.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class OrderCanceling implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_SIGNIN;
	}

	int orderId = Integer.parseInt(request.getParameter(ParameterList.CMD_ORDER_ID));

	User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);

	try {
	    Order order = new Order();
	    order.setId(orderId);
	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    Order result = orderService.getOrder(order);

	    if (result.getUser().equals(user)) {
		orderService.deleteOrder(result);
	    } else {
		return PageList.PG_ERROR;
	    }

	} catch (ServiceException e) {
	    throw new CommandException(e);
	}
	return PageList.PG_USER_ORDERS_R;
    }

}
