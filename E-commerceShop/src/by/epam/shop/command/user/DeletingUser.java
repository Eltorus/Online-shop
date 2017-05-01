package by.epam.shop.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.PageList;

public class DeletingUser implements Command {
    private static final Logger logger = Logger.getLogger(DeletingUser.class);
    /* Get user from session, pass to service layer for deleting and invalidate session
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);
	
	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    userService.deleteUser(user);
	    session.invalidate();
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during DeletingUser command", e);
	}
	
	return PageList.PG_PROFILE;
    }

}
