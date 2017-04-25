package by.epam.shop.command.order;

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
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class AddingToCart implements Command {
    private final static Logger logger = Logger.getLogger(AddingToCart.class);

    /*
     * Get requested product from service layer, add to cart, and put it in
     * session
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
	int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.PRODUCT_ID));

	if (id == 0) {
	    return PageList.PG_CART;
	}

	Product requestProduct = new Product();
	requestProduct.setId(id);

	Cart cart = (Cart) request.getSession().getAttribute(AttributeList.ATTR_CART);
	try {
	    ProductService productService = ServiceFactory.getInstance().getProductService();

	    Product product = productService.getProductWithId(requestProduct);
	    if (product == null || product.getAmount() == 0) {
		return PageList.PG_CART;
	    }

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
	    throw new CommandException("Exception during AddingToCart command",e);
	}
	return PageList.PG_CART;
    }

    private CartLine getCartLineFromCart(Product product, Cart cart) {
	for (CartLine cartLine : cart.getProductList()) {
	    if (cartLine.getProduct().equals(product)) {
		return cartLine;
	    }
	}
	return null;
    }

}
