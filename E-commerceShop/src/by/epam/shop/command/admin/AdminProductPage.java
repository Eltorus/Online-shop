package by.epam.shop.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.Product;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.PageList;

public class AdminProductPage implements Command {
    private final static Logger logger = Logger.getLogger(AdminProductPage.class);

    /*Get all products and put them into request as attribute
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	try {
	    ProductService productService = ServiceFactory.getInstance().getProductService();
	    List<Product> productList = productService.getAllProducts();
	    request.setAttribute(AttributeList.ATTR_PRODUCTS, productList);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during AdminProductPage command",e);
	}
	return PageList.PG_ADMIN_PRODUCT;
    }

}
