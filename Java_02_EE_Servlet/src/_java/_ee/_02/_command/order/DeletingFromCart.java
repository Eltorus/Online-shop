package _java._ee._02._command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _java._ee._02._bean.Cart;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class DeletingFromCart implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute(AttributeList.ATTR_CART);
		if(cart.getGoodList().size()>0) {
			int index = (Integer.parseInt(request.getParameter(ParameterList.CMD_GOOD_INDX)));
			cart.removeFromCartWithInd(index);
			System.out.println("delete from cart " + cart.getGoodList().isEmpty());
			session.setAttribute(AttributeList.ATTR_CART, cart);
		}
		return PageList.PG_CART;
	}

}
