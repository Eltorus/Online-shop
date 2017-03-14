package _java._ee._02._command.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.Good;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.GoodService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;

public class ProductPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		GoodService goodService = ServiceFactory.getInstance().getGoodService();
		Good result = null;
		try {
			Good good = new Good();
			good.setId(Integer.parseInt(request.getParameter(ParameterList.CMD_GOOD_ID)));
			result = goodService.getGoodWithId(good);
			request.setAttribute(AttributeList.ATTR_GOOD, result);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageList.PG_GOOD;
	}

}
