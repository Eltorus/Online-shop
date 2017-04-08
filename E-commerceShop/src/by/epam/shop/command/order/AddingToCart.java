package by.epam.shop.command.order;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Product;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class AddingToCart implements Command {
    private final static Logger logger = Logger.getLogger(AddingToCart.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	Product requestProduct = new Product();
	requestProduct.setId(Integer.parseInt(request.getParameter(ParameterList.PRODUCT_ID)));

	Cart cart = (Cart) request.getSession().getAttribute(AttributeList.ATTR_CART);
	try {
	    ProductService productService = ServiceFactory.getInstance().getProductService();

	    Product product = productService.getProductWithId(requestProduct);

	    CartLine cartLine = getCartLineFromCart(product, cart);

	    if (cartLine != null) {
		cart.removeFromProductList(cartLine);
		int quantity = cartLine.getQuantity() + 1;
		cartLine.setQuantity(quantity);

	    } else {
		cartLine = new CartLine();
		cartLine.setProduct(product);
		cartLine.setQuantity(1);
	    }

	    cart.addToProductList(cartLine);
	    request.getSession().setAttribute(AttributeList.ATTR_CART, cart);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return PageList.PG_CART;
    }

    public CartLine getCartLineFromCart(Product product, Cart cart) {
	Iterator<CartLine> itr = cart.getProductList().iterator();

	while (itr.hasNext()) {
	    CartLine cartLine = (CartLine) itr.next();
	    if (cartLine.getProduct().equals(product)) {
		return cartLine;
	    }
	}
	return null;
    }

}
