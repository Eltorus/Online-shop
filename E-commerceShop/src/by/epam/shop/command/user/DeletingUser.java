package by.epam.shop.command.user;

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

public class DeletingUser implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if(!UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_SIGNIN;
	}
	
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	
	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    userService.deleteUser(user);
	    session.invalidate();
	} catch (ServiceException e) {
	    throw new CommandException(e);
	}
	
	return PageList.PG_INDEX;
    }

}
