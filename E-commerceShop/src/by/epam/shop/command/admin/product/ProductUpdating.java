package by.epam.shop.command.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.Product;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.ImageUpload;
import by.epam.shop.util.UtilException;

public class ProductUpdating implements Command {
    private static final String relPath = "img/products/";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response) || !UserValidation.isUserAdmin(request, response)) {
	    return PageList.PG_SIGNIN;
	}

	try {

	    Product product = fillUpProduct(request, response);
	    ProductService productService = ServiceFactory.getInstance().getProductService();

	    if (product.getId() == 0) {
		productService.addProduct(product);
	    } else {
		productService.changeProduct(product);
	    }

	} catch (ServiceException | UtilException | IOException | ServletException e) {
	    throw new CommandException(e);
	}

	return PageList.PG_ADMIN_PRODUCT_R;
    }

    private Product fillUpProduct(HttpServletRequest request, HttpServletResponse response)
	    throws UtilException, IOException, ServletException {
	Product product = new Product();

	String id = request.getParameter(ParameterList.PRODUCT_ID);
	if (id.equals("")) {
	    product.setId(0);
	} else {
	    product.setId(Integer.parseInt(id));
	}

	product.setTitle(request.getParameter(ParameterList.PRODUCT_TITLE));
	product.setCategoryID(Integer.parseInt((request.getParameter(ParameterList.PRODUCT_CATEGORY))));
	product.setPrice(Double.parseDouble(request.getParameter(ParameterList.PRODUCT_PRICE)));
	product.setDescription(request.getParameter(ParameterList.PRODUCT_DESCRIPTION));
	product.setAmount(Integer.parseInt(request.getParameter(ParameterList.PRODUCT_AMOUNT)));

	String fileName = product.getTitle() + "-" + product.getCategory() + "-" + product.getTitle().hashCode()
		+ ".jpg";
	String savePath = request.getServletContext().getRealPath(relPath);

	ImageUpload.uploadFile(request.getParts(), savePath, fileName);

	product.setImgPath(relPath + fileName);

	return product;
    }

}
