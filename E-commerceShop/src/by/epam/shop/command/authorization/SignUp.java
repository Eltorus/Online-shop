package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.HashTool;
import by.epam.shop.util.UtilException;

public class SignUp implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		// сделать это через js
		try {
			String passwordConfirm = request.getParameter("password_confirm");
			String password = request.getParameter("password");
			if (!passwordConfirm.equals(password)) {
				throw new CommandException("Password don't match");
			}
			User user = fillUpUser(request, response);
			UserService userService = ServiceFactory.getInstance().getUserService();
			boolean successfulRegister = userService.signUp(user);
			if (successfulRegister == true) {
				page = PageList.PG_INDEX; // вернуть страницу с сообщением об
											// успешной регистрацией
			} else {
				System.out.println("crap");
				// предложить пользователю войти заново или восстановить пароль
				page = PageList.PG_SIGNIN;
			}
		} catch (ServiceException | UtilException e) {
			throw new CommandException(e);
		}
		return page;
	}
	
	private User fillUpUser(HttpServletRequest request, HttpServletResponse response) throws UtilException {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setPhonenumber(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setPasswordHash(HashTool.hashLine(request.getParameter("password")));
		return user;
	}

}
