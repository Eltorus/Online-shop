package by.epam.shop.command.products;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Product;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class Catalog implements Command {
    private final static Logger logger = Logger.getLogger(Catalog.class);
    private static final int limit = 4;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	ProductService productService = ServiceFactory.getInstance().getProductService();
	List<Product> products = null;
	
	try {
	    int requestPage;
	    String temp = request.getParameter(AttributeList.ATTR_PAGE_REQUEST);
	    
	    if(temp == null || temp.isEmpty()) {
		requestPage = 1;
	    } else {
		requestPage = Integer.parseInt(temp);
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
		    
	    /*products = productService.getAllProducts();
	    request.setAttribute(AttributeList.ATTR_PRODUCTS, products);*/
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return PageList.PG_CATALOG;
    }

}
