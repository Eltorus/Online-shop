package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.bean.User;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.admin.AdminOrderPage;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.validation.UserValidation;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.HashTool;
import by.epam.shop.util.UtilException;

public class SignUp implements Command {
    private final static Logger logger = Logger.getLogger(SignUp.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	if (UserValidation.isUserLoged(request, response)) {
	    return PageList.PG_INDEX;
	}
	
	String page = null;
	// сделать это через js
	try {
	    String passwordConfirm = request.getParameter(ParameterList.USER_PSWRD_CONFIRM);
	    String password = request.getParameter(ParameterList.USER_PSWRD);
	    if (!passwordConfirm.equals(password)) {
		throw new CommandException("Password don't match");
	    }

	    User user = fillUpUser(request, response);
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    boolean successfulRegister = userService.addUser(user);

	    if (successfulRegister == true) {
		page = PageList.PG_SIGNIN; // вернуть страницу с сообщением об
					   // успешной регистрацией
	    } else {
		// предложить пользователю войти заново или восстановить пароль
		page = PageList.PG_SIGNIN;
	    }
	} catch (ServiceException | UtilException e) {
	    logger.error(e);
	    throw new CommandException(e);
	}
	return page;
    }

    private User fillUpUser(HttpServletRequest request, HttpServletResponse response) throws UtilException {
	User user = new User();
	user.setName(request.getParameter(ParameterList.USER_NAME));
	user.setSurname(request.getParameter(ParameterList.USER_SURNAME));
	user.setPhonenumber(request.getParameter(ParameterList.USER_PHONE));
	user.setEmail(request.getParameter(ParameterList.USER_EMAIL));
	user.setPasswordHash(HashTool.hashLine(request.getParameter(ParameterList.USER_PSWRD)));
	return user;
    }

}
