package by.epam.shop.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.ImageUpload;
import by.epam.shop.util.PageList;
import by.epam.shop.util.UtilException;

public class UploadUserAvatar implements Command {
    private final static Logger logger = Logger.getLogger(UploadUserAvatar.class);
    private final static String relPath = "img/users/";
    private final static String imgPath = "img/users/default_avatar.jpg";
    
    /* Get image from request, save it, set path to this image, and pass changed user to service layer
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!ServletFileUpload.isMultipartContent(request)) {
	    throw new CommandException("File not uploaded");
	}

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	
	String appPath = request.getServletContext().getRealPath("");

	try {
	    Part part = request.getPart(ParameterList.USER_AVATAR);
	    
	    if (part.getSize() != 0) {
		String fileName = user.getId() + "-av-" + part.getName().hashCode()+ ".jpg";
		
		ImageUpload.uploadFile(part, appPath + relPath, fileName);
		user.setImgPath(relPath + fileName);
		
		session.setAttribute(AttributeList.ATTR_USER, user);
	    } else {
		user.setImgPath(imgPath);
	    }
	    
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    user = userService.updateUser(user);

	} catch (UtilException | IOException | ServletException | ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during UploadUserAvatar command", e);
	}
	return PageList.PG_PROFILE;
    }

}
