package by.epam.shop.command.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class OrderEditPage implements Command {
    private final static Logger logger = Logger.getLogger(OrderEditPage.class);

    /* Get requested Order object, and set into request as an attribute  
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.ORDER_ID));
	if (id == 0) {
	    return PageList.PG_ADMIN_ORDER_R;
	}

	Order order = new Order();
	order.setId(id);

	try {
	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    Order result = orderService.getOrder(order);
	    request.setAttribute(AttributeList.ATTR_ORDER, result);

	    return PageList.PG_ORDER_INFO;
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during OrderEditPage command",e);
	}
    }

}
