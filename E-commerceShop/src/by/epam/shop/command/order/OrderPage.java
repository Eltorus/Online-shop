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
import by.epam.shop.service.OrderService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.MessageGenerator;
import by.epam.shop.util.PageList;

public class OrderPage implements Command {
    private final static Logger logger = Logger.getLogger(OrderPage.class);

    /*
     * Get cart and user from object, and put created order object in request
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
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	Cart cart = (Cart) session.getAttribute(AttributeList.ATTR_CART);
	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    user = userService.signIn(user);
	    
	    if (!user.isBanned()) {
		if (cart.getProductList().size() > 0) {
		    OrderService orderService = ServiceFactory.getInstance().getOrderService();
		    Order order = orderService.createOrder(cart, user);

		    session.setAttribute(AttributeList.ATTR_USER, user);
		    session.setAttribute(AttributeList.ATTR_ORDER, order);
		}
	    } else {
		session.setAttribute(AttributeList.ATTR_USER, user);
		return PageList.PG_CART+MessageGenerator.generateError(1406);
	    }
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during OrderPage command",e);
	}
	return PageList.PG_ORDER;
    }

}
