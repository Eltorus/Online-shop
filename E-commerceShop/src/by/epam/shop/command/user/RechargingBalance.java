package by.epam.shop.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.UserValidation;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;

public class RechargingBalance implements Command{
	//// ПЕРЕПИСАААТЬ
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response)) {
			return PageList.PG_SIGNIN;
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(AttributeList.ATTR_USER);
		
		double credit = Double.parseDouble(request.getParameter(ParameterList.CMD_CREDIT));
		if (credit <= 0 || credit < user.getBalance()) {
			throw new CommandException("Inaproppriate value: credit must be greater than 0 and less than balance");
		}
		
		credit += user.getBalance();
		user.setBalance(credit);
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		try {
			User result = userService.changeUser(user);
			session.setAttribute(AttributeList.ATTR_USER, result);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageList.PG_PROFILE;
	}

}
