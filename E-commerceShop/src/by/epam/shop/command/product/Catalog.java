package by.epam.shop.command.product;

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
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class Catalog implements Command {
    private final static Logger logger = Logger.getLogger(Catalog.class);
    private static final int limit = 4;
    
    /* Get part of all amount of products, put it into request
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	ProductService productService = ServiceFactory.getInstance().getProductService();
	List<Product> products = null;
	
	try {
	    int requestPage = NumberOperationTool.getIntFromString(request.getParameter(AttributeList.ATTR_PAGE_REQUEST));
	    if(requestPage == 0) {
		requestPage = 1;
	    }

	    double productTotal = productService.getTotalProductAmount();
	    int pageAmount = (int) Math.ceil(productTotal/limit);
	    
	    if(requestPage > pageAmount || requestPage < 1) {
		requestPage = 1;
	    }
	    
	    products = productService.getProducts(limit*(requestPage-1), limit);
	
	    request.setAttribute(AttributeList.ATTR_PRODUCTS, products);
	    request.setAttribute(AttributeList.ATTR_PAGE_AMOUNT, pageAmount);
	    request.setAttribute(AttributeList.ATTR_PAGE_REQUEST, requestPage);
	    
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during Catalog command",e);
	}
	return PageList.PG_CATALOG;
    }

}
