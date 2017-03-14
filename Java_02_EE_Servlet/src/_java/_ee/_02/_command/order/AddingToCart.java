package _java._ee._02._command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.Cart;
import _java._ee._02._bean.Good;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.GoodService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;

public class AddingToCart implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		GoodService goodService = ServiceFactory.getInstance().getGoodService();
		Good requestGood = new Good();
		Cart cart = (Cart) request.getSession().getAttribute(AttributeList.ATTR_CART);
		Good good = null;
		requestGood.setId(Integer.parseInt(request.getParameter(ParameterList.CMD_GOOD_ID)));
		try {
			good = goodService.getGoodWithId(requestGood);
			cart.addToGoodList(good);
			request.getSession().setAttribute(AttributeList.ATTR_CART, cart);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageList.PG_CART;
	}

}
