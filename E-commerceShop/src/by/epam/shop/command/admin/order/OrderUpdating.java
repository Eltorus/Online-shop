package by.epam.shop.command.admin.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.DateFormatter;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;
import by.epam.shop.util.UtilException;

public class OrderUpdating implements Command {
    private final static Logger logger = Logger.getLogger(OrderUpdating.class);

    /*
     * Get Order object params from request, create new Order object and pass to
     * service layer for updating
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
	try {

	    int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.ORDER_ID));
	    if (id == 0) {
		return PageList.PG_ADMIN_ORDER_R;
	    }

	    String address = request.getParameter(ParameterList.ORDER_ADDRESS);
	    if (address == null || address.isEmpty() || address.trim().isEmpty() || address.length() > 100) {
		throw new CommandException("Inapropriate address format");
	    }
	    
	    Date deliveryDate = DateFormatter.getDateRusFormat(request.getParameter(ParameterList.ORDER_DELIVERY_DATE));

	    String complitedString = request.getParameter(ParameterList.ORDER_IS_COMPLITED);
	    boolean complited = false;
	    
	    if (complitedString != null && complitedString.equals(ParameterList.ORDER_COMPLITED)) {
		complited = true;
		if (deliveryDate == null) {
		    deliveryDate = new Date();
		}
	    }

	    Order order = new Order();
	    order.setId(id);
	    order.setAddress(address);
	    order.setDeliveryDate(deliveryDate);
	    order.setOrderCompleted(complited);

	    OrderService orderService = ServiceFactory.getInstance().getOrderService();
	    orderService.updateOrder(order);

	} catch (UtilException | ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during OrderUpdating command", e);
	}
	return PageList.PG_ADMIN_ORDER_R;
    }

}
