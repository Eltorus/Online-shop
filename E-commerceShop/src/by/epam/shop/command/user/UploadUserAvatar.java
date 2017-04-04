package by.epam.shop.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.ImageUpload;
import by.epam.shop.util.UtilException;

public class UploadUserAvatar implements Command{
    private static final String relPath = "img/user-img/"; 
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if(!UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_SIGNIN;
	}
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	String fileName = user.getId()+"-avatar"+".jpg";
	String savePath = request.getServletContext().getRealPath(relPath);
	
	try {
	    ImageUpload.uploadFile(request.getParts(), savePath, fileName);
	    
	    user.setImgPath(relPath+fileName);
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    
	    user = userService.changeUser(user);
	    
	    session.setAttribute(AttributeList.ATTR_USER, user);
	} catch (UtilException | IOException | ServletException | ServiceException e) {
    	    throw new CommandException(e);
	}
	return PageList.PG_PROFILE;
    }

}
