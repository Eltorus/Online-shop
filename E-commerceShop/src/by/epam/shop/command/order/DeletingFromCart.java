package by.epam.shop.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.Cart;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class DeletingFromCart implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute(AttributeList.ATTR_CART);

	if (cart.getProductList().size() > 0) {
	    int index = (Integer.parseInt(request.getParameter(ParameterList.CMD_PRODUCT_INDX)));
	    cart.removeFromListWithIndx(index);

	    session.setAttribute(AttributeList.ATTR_CART, cart);
	}
	return PageList.PG_CART;
    }

}
