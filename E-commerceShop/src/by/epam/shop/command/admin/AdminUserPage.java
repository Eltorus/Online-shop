package by.epam.shop.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class AdminUserPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (!UserValidation.isUserLoged(request, response) || !UserValidation.isUserAdmin(request, response)) {
	    return PageList.PG_SIGNIN;
	}

	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    List<User> userList = userService.getAllUsers();
	    System.out.println("userList size" + userList.size());
	    request.setAttribute(AttributeList.ATTR_USERLIST, userList);
	} catch (ServiceException e) {
	    throw new CommandException(e);
	    /// нужен логгер
	}
	return PageList.PG_ADMIN_USER;
    }

}
