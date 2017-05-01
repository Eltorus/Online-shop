package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.Cart;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.HashTool;
import by.epam.shop.util.MessageGenerator;
import by.epam.shop.util.PageList;
import by.epam.shop.util.UtilException;

public class SignIn implements Command {
    private final static Logger logger = Logger.getLogger(SignIn.class);
    
    /* Check if user has been registered with email and password
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	String email = request.getParameter(ParameterList.USER_EMAIL);
	String password = request.getParameter(ParameterList.USER_PSWRD);
	
	String page = null;
	try {
	    if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
		return PageList.PG_SIGNIN + MessageGenerator.generateError(1401);
	    }
	    User user = new User();
	    user.setEmail(email);
	    user.setPasswordHash(HashTool.hashLine(password));
	
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    HttpSession session = request.getSession();
	    user = userService.signIn(user);
	    if (user != null) {
		session.setAttribute(AttributeList.ATTR_USER, user);
		
		if(user.isBanned()) {
		    session.setAttribute(AttributeList.ATTR_CART, new Cart());
		}
		
		page = PageList.PG_SIGNIN;
	    } else {
		page = PageList.PG_SIGNIN + MessageGenerator.generateError(1401);
	    }
	} catch (ServiceException | UtilException e) {
	    logger.error(e);
	    throw new CommandException("Exception during SignIn command",e);
	}
	return page;
    }

}
