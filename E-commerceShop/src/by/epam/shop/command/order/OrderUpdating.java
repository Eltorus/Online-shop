package by.epam.shop.command.order;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Order;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.UserValidation;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.service.validation.Validation;
import by.epam.shop.util.DateFormatter;
import by.epam.shop.util.UtilException;

public class OrderUpdating implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response)) {
			///
		}
		
		try {
			
			String address  = request.getParameter(ParameterList.ADDRESS);
			Timestamp deliveryDate = DateFormatter.getTimeStamp(request.getParameter(ParameterList.DELIVERY_DATE));
			int orderID = Integer.parseInt(request.getParameter(ParameterList.CMD_ORDER_ID));
			
			if(Validation.isStringEmpty(address) || deliveryDate == null) {
				throw new CommandException("String is empty");
			}
			
			Order order = new Order();
			order.setId(orderID);
			order.setAddress(address);
			order.setDeliveryDate(deliveryDate);
			
			OrderService orderService = ServiceFactory.getInstance().getOrderService();
			orderService.updateOrder(order);
			
		} catch (UtilException | ServiceException e) {
			throw new CommandException(e);
		}
		return "/Controller?command=admin_order_page";
	}

}
