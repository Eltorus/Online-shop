package by.epam.shop.command.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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
    private final static Logger logger = Logger.getLogger(ProductUpdating.class);
    private final static String savePath="img/products/";
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
	    logger.error(e);
	    throw new CommandException(e);
	}

	return PageList.PG_ADMIN_PRODUCT_R;
    }

    private Product fillUpProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, UtilException{
	Product product = new Product();

	String id = request.getParameter(ParameterList.PRODUCT_ID);
	if (id.isEmpty()) {
	    product.setId(0);
	} else {
	    product.setId(Integer.parseInt(id));
	}

	product.setTitle(request.getParameter(ParameterList.PRODUCT_TITLE));
	product.setCategoryID(Integer.parseInt((request.getParameter(ParameterList.PRODUCT_CATEGORY))));
	product.setPrice(Double.parseDouble(request.getParameter(ParameterList.PRODUCT_PRICE)));
	product.setDescription(request.getParameter(ParameterList.PRODUCT_DESCRIPTION));
	product.setAmount(Integer.parseInt(request.getParameter(ParameterList.PRODUCT_AMOUNT)));

	Part part = request.getPart(ParameterList.PRODUCT_IMG);
	 if(part.getSize() != 0)  {
	    String fileName = product.getTitle() + "-" + product.getCategoryID() + "-" + product.getTitle().hashCode()
		    + ".jpg";
	    
	    ImageUpload.uploadFile(part, savePath, fileName);
	    
	    product.setImgPath(savePath + fileName);
	} else {
	    String imgPath = request.getParameter(ParameterList.PRODUCT_IMG_PATH);
	    product.setImgPath(imgPath);
	}

	return product;
    }

}
