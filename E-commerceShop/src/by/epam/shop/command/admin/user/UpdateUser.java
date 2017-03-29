package by.epam.shop.command.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.User;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class UpdateUser implements Command{
    private final static Logger logger = Logger.getLogger(UpdateUser.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response) || !UserValidation.isUserAdmin(request, response)) {
	    return PageList.PG_SIGNIN;
	}
	
	int id = Integer.valueOf(request.getParameter(ParameterList.USER_ID));
	boolean userBanned = Boolean.valueOf(request.getParameter(ParameterList.USER_BANNED));
	
	User user = new User();
	user.setId(id);
	user.setIs_banned(!userBanned);
	UserService userService = ServiceFactory.getInstance().getUserService();
	try {
	    userService.changeUser(user);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException(e);
	    
	}
	return PageList.PG_ADMIN_USER_R;
    }

}
