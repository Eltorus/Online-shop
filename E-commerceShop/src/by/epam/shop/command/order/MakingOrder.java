package by.epam.shop.command.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.Cart;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.MessageGenerator;
import by.epam.shop.util.PageList;

public class MakingOrder implements Command {
    private final static Logger logger = Logger.getLogger(MakingOrder.class);

    /*
     * Create order object and pass it to service layer
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

	String address = request.getParameter(ParameterList.ORDER_ADDRESS);
	if (address == null || address.trim().isEmpty() || address.length() > 100) {
	    return PageList.PG_CART + MessageGenerator.generateError(1404);
	}

	HttpSession session = request.getSession();
	Order order = (Order) session.getAttribute(AttributeList.ATTR_ORDER);
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);

	try {
	    if (order != null) {
		UserService userService = ServiceFactory.getInstance().getUserService();
		user = userService.getUserWithId(user);

		if (user.getBalance() < order.getTotal()) {
		    return PageList.PG_CART + MessageGenerator.generateError(1403);
		}

		order.setUser(user);
		order.setAddress(address.trim());
		order.setOrderPaid(true);
		order.setOrderDate(new Date());
		OrderService orderService = ServiceFactory.getInstance().getOrderService();
		orderService.addOrder(order);

		user = userService.signIn(user);

		session.setAttribute(AttributeList.ATTR_CART, new Cart());
		session.setAttribute(AttributeList.ATTR_USER, user);
		return PageList.PG_CART
			+ MessageGenerator.generateSuccess(1202);
	    }
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during MakingOrder command",e);
	}
	return PageList.PG_ORDER;
    }
}
