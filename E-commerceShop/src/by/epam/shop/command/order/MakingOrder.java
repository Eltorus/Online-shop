package by.epam.shop.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class MakingOrder implements Command {
    private final static Logger logger = Logger.getLogger(MakingOrder.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_SIGNIN;
	}
	HttpSession session = request.getSession();
	String address = request.getParameter(ParameterList.ADDRESS);
	
	if (address == null || address.isEmpty()) {
	    throw new CommandException("Inputs are empty");
	}

	Order order = (Order) session.getAttribute(AttributeList.ATTR_ORDER);
	if (order.getUser().getBalance() < order.getTotal()) {
	    throw new CommandException("Balance less then bill");
	}

	order.setAddress(address);
	order.setOrderPaid(true);
	try {

	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    orderService.addOrder(order);
	    
	    User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    user = userService.signIn(user);

	   
	    session.removeAttribute(AttributeList.ATTR_ORDER);
	    session.removeAttribute(AttributeList.ATTR_CART);
	    session.setAttribute(AttributeList.ATTR_USER, user);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return PageList.PG_INDEX;
    }
}
