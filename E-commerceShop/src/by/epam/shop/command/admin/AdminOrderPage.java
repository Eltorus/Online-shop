package by.epam.shop.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.PageList;

public class AdminOrderPage implements Command {
    private final static Logger logger = Logger.getLogger(AdminOrderPage.class);

    /*Get all orders and put them into request as attribute
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	try {
	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    List<Order> orderList = orderService.getAllOrders();

	    request.setAttribute(AttributeList.ATTR_ORDERLIST, orderList);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during AdminOrderPage command",e);
	}
	return PageList.PG_ADMIN_ORDER;
    }

}
