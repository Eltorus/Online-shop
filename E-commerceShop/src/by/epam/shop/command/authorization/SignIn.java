package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.HashTool;
import by.epam.shop.util.UtilException;

public class SignIn implements Command {
    private final static Logger logger = Logger.getLogger(SignIn.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_INDEX;
	}

	String email = request.getParameter(ParameterList.USER_EMAIL);
	String password = request.getParameter(ParameterList.USER_PSWRD);
	System.out.println(password);
	String page = null;
	try {
	    if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
		throw new CommandException("Empty fields");
	    }
	    User user = new User();
	    user.setEmail(email);
	    user.setPasswordHash(HashTool.hashLine(password));
	
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    HttpSession session = request.getSession();
	    user = userService.signIn(user);
	    if (user != null) {
		session.setAttribute(AttributeList.ATTR_USER, user);
		page = PageList.PG_SIGNIN;
	    } else {
		page = PageList.PG_SIGNIN;
		session.setAttribute("error", "Wrong login or password");
	    }
	} catch (ServiceException | UtilException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return page;
    }

}