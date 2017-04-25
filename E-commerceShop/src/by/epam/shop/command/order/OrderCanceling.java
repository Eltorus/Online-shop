package by.epam.shop.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class OrderCanceling implements Command {
    private final static Logger logger = Logger.getLogger(OrderCanceling.class);

    /*
     * Get order id from request, creates new order object, and pass it to
     * service layer for canceling
     * 
     * @param javax.servlet.http.HttpServletRequest
     * 
     * @param javax.servlet.http.HttpServletResponse
     * 
     * @throws by.epam.shop.command.exception.CommandException
     * 
     * @return String page, which will be passed to client
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.ORDER_ID));
	if (id == 0) {
	    return PageList.PG_USER_ORDERS_R;
	}

	User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);

	try {
	    Order order = new Order();
	    order.setId(id);
	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    Order result = orderService.getOrder(order);

	    if (result.getUser().equals(user)) {
		orderService.deleteOrder(result);
	    } else {
		return PageList.PG_ERROR;
	    }

	    UserService userService = ServiceFactory.getInstance().getUserService();
	    user = userService.signIn(user);

	    request.getSession().setAttribute(AttributeList.ATTR_USER, user);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during OrderCanceling command",e);
	}
	return PageList.PG_USER_ORDERS_R;
    }

}
