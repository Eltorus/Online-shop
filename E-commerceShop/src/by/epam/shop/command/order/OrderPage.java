package by.epam.shop.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class OrderPage implements Command {
    private final static Logger logger = Logger.getLogger(OrderPage.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_SIGNIN;
	}

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	Cart cart = (Cart) session.getAttribute(AttributeList.ATTR_CART);
	try {
	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    Order order = orderService.createOrder(cart, user);
	    
	    session.setAttribute(AttributeList.ATTR_ORDER, order);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return PageList.PG_ORDER;
    }

}
