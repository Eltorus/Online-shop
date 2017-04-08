package by.epam.shop.command.admin.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Product;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class DeletingProduct implements Command{
    private final static Logger logger = Logger.getLogger(DeletingProduct.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if(!UserValidation.isUserLoged(request, response) && !UserValidation.isUserAdmin(request, response)) {
	    return PageList.PG_SIGNIN;
	}
	
	Product product = new Product();
	
	int id = Integer.parseInt(request.getParameter(ParameterList.PRODUCT_ID));
	if(id == 0) {
	    return PageList.PG_ADMIN_PRODUCT_R;
	}
	
	product.setId(id);
	
	try {
	    ProductService productService = ServiceFactory.getInstance().getProductService();
	    productService.deleteProduct(product);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException();
	}
	
	return PageList.PG_ADMIN_PRODUCT_R;
    }

}
