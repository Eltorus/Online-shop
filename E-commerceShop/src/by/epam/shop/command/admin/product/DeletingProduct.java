package by.epam.shop.command.admin.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.Product;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class DeletingProduct implements Command {
    private final static Logger logger = Logger.getLogger(DeletingProduct.class);

    /*
     * Get Product id from request, create new Order object with this id and
     * pass to service layer for deleting
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
	    return PageList.PG_ADMIN_PRODUCT_R;
	}

	Product product = new Product();
	product.setId(id);

	try {
	    ProductService productService = ServiceFactory.getInstance().getProductService();
	    productService.deleteProduct(product);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during DeletingProduct command",e);
	}

	return PageList.PG_ADMIN_PRODUCT_R;
    }

}
