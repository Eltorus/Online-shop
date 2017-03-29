package by.epam.shop.command.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.Product;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class ProductPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Product product = new Product();
			product.setId(Integer.parseInt(request.getParameter(ParameterList.PRODUCT_ID)));
			
			ProductService productService = ServiceFactory.getInstance().getProductService();
			Product result = productService.getProductWithId(product);
			
			request.setAttribute(AttributeList.ATTR_PRODUCT, result);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		
		return PageList.PG_GOOD;
	}

}
