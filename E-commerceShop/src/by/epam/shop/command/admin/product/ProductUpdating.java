package by.epam.shop.command.admin.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Product;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.UserValidation;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class ProductUpdating implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response) || !UserValidation.isUserAdmin(request, response)) {
	    return PageList.PG_SIGNIN;
	}

	Product product = fillUpProduct(request, response);
	ProductService productService = ServiceFactory.getInstance().getProductService();

	try {
	    
	    if (product.getId() == 0) {
		
		productService.addProduct(product);
		
	    } else {
		
		productService.changeProduct(product);
		
	    }
	    
	} catch (ServiceException e) {
	    throw new CommandException(e);
	}

	return PageList.PG_ADMIN_PRODUCT_R;
    }

    private Product fillUpProduct(HttpServletRequest request, HttpServletResponse response) {
	Product product = new Product();
	
	String id = request.getParameter(ParameterList.PRODUCT_ID);
	if(id.equals("")) {
	    product.setId(0);
	} else {
	    product.setId(Integer.parseInt(id));
	}
	
	System.out.println(product.getId());
	product.setTitle(request.getParameter(ParameterList.PRODUCT_TITLE));
	System.out.println(product.getTitle());
	product.setCategoryID(Integer.parseInt((request.getParameter(ParameterList.PRODUCT_CATEGORY))));
	product.setPrice(Double.parseDouble(request.getParameter(ParameterList.PRODUCT_PRICE)));
	product.setDescription(request.getParameter(ParameterList.PRODUCT_DESCRIPTION));
	product.setAmount(Integer.parseInt(request.getParameter(ParameterList.PRODUCT_AMOUNT)));

	return product;
    }

}
