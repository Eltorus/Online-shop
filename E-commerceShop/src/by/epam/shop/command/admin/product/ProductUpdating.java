package by.epam.shop.command.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import by.epam.shop.bean.Product;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.ProductService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.ImageUpload;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;
import by.epam.shop.util.UtilException;

public class ProductUpdating implements Command {
    private final static Logger logger = Logger.getLogger(ProductUpdating.class);
    private final static String relPath="img/products/";
    
    /* Get Product object params from request,
     * create new Product object and pass to service layer for updating
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
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
	    throw new CommandException("Exception during ProductUpdating command",e);
	}

	return PageList.PG_ADMIN_PRODUCT_R;
    }

    private Product fillUpProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, UtilException{
	Product product = new Product();

	int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.PRODUCT_ID));
	product.setId(id);
	product.setTitle(request.getParameter(ParameterList.PRODUCT_TITLE));
	
	int categoryID = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.PRODUCT_CATEGORY));
	product.setCategoryID(categoryID);
	
	double price = NumberOperationTool.getDoubleFromString(ParameterList.PRODUCT_PRICE);
	product.setPrice(price);
	
	product.setDescription(request.getParameter(ParameterList.PRODUCT_DESCRIPTION));
	
	int amount= NumberOperationTool.getIntFromString(request.getParameter(ParameterList.PRODUCT_AMOUNT));
	product.setAmount(amount);

	Part part = request.getPart(ParameterList.PRODUCT_IMG);
	 if(part.getSize() != 0)  {
	    String fileName = product.getTitle() + "-" + product.getCategoryID() + "-" + product.getTitle().hashCode()
		    + ".jpg";
	    
	    String appPath = request.getServletContext().getRealPath("");
	    ImageUpload.uploadFile(part, appPath+relPath, fileName);
	    
	    product.setImgPath(relPath + fileName);
	} else {
	    String imgPath = request.getParameter(ParameterList.PRODUCT_IMG_PATH);
	    product.setImgPath(imgPath);
	}

	return product;
    }

}
