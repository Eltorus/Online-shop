package by.epam.shop.command.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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

public class ProductPage implements Command {
    private final static Logger logger = Logger.getLogger(ProductPage.class);

    /* Get requested product from service and put into request
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	try {
	    Product product = new Product();
	    int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.PRODUCT_ID));
	    if(id == 0) {
		return PageList.PG_CATALOG_R;
	    }
	    product.setId(id);
	    
	    ProductService productService = ServiceFactory.getInstance().getProductService();
	    Product result = productService.getProductWithId(product);

	    request.setAttribute(AttributeList.ATTR_PRODUCT, result);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during ProductPage command",e);
	}

	return PageList.PG_GOOD;
    }

}
