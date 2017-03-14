package _java._ee._02._command.authorization;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _java._ee._02._bean.User;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.UserService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;
import _java._ee._02._service.validation.Validation;
import _java._ee._02._util.HashTool;
import _java._ee._02._util.UtilException;

public class SingIn implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String email = request.getParameter(ParameterList.USER_EMAIL);
		String password = request.getParameter(ParameterList.USER_PSWRD);
		String page = null;
		try {
			if (Validation.isStringEmpty(email) || Validation.isStringEmpty(password)) {
				response.getWriter().print("Empty fields");
				page = PageList.PG_SIGNIN;
			}
			User user = new User();
			user.setEmail(email);
			user.setPasswordHash(HashTool.hashLine(password));
			UserService userService = ServiceFactory.getInstance().getUserService();
			HttpSession session = request.getSession();
			user = userService.signIn(user);
			if (user != null) {
				user.setLoged(true);
				
				session.setAttribute(AttributeList.ATTR_USER, user);
				page = PageList.PG_INDEX;
			} else {
				page = PageList.PG_SIGNIN;
				session.setAttribute("error", "Wrong login or password");
			}

		} catch (ServiceException | IOException | UtilException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
