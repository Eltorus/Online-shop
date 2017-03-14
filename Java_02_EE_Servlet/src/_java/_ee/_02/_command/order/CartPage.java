package _java._ee._02._command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class CartPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		return PageList.PG_CART;
	}
	
}
