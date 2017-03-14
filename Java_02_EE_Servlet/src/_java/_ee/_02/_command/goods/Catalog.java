package _java._ee._02._command.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.Good;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.GoodService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;

public class Catalog implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		GoodService goodService = ServiceFactory.getInstance().getGoodService();
		List<Good> goods = null;
		try {
			
			goods = goodService.getAllGoods();
			request.setAttribute(AttributeList.ATTR_GOODS, goods);
			System.out.println("catalog");
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageList.PG_CATALOG;
	}

}
