package by.epam.shop.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.PageList;

public class AdminUserPage implements Command {
    private final static Logger logger = Logger.getLogger(AdminUserPage.class);
    
    /*Get all users and put them into request as attribute
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    List<User> userList = userService.getAllUsers();
	    request.setAttribute(AttributeList.ATTR_USERLIST, userList);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during AdminUserPage command",e);
	}
	return PageList.PG_ADMIN_USER;
    }

}
