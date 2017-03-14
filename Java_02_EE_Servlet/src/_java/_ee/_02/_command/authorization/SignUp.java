 package _java._ee._02._command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.User;
import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.UserService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;
import _java._ee._02._util.HashTool;
import _java._ee._02._util.UtilException;

public class SignUp implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		User user = new User();
		//сделать это через js
		try {
		String passwordConfirm = request.getParameter("password_confirm");
		String password = request.getParameter("password");
		if(!passwordConfirm.equals(password)) {
			return PageList.PG_SIGNUP;
		}
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setPhonenumber(request.getParameter("phonenumber"));
		user.setEmail(request.getParameter("email"));
		user.setPasswordHash(HashTool.hashLine(password));
		UserService userService = ServiceFactory.getInstance().getUserService();

			boolean successfulRegister = userService.signUp(user);
			if(successfulRegister == true) {
				System.out.println("ok");
				page =  PageList.PG_INDEX; //вернуть страницу с сообщением об успешной регистрацией
			} else {
				System.out.println("crap");
				// предложить пользователю войти заново или восстановить пароль
				page = PageList.PG_SIGNIN;
			}
		} catch (ServiceException | UtilException e) {
			System.out.println(e);
			throw new CommandException(e);
		} 
		return page;
	}

}
