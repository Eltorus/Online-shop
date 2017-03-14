package _java._ee._02._command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.User;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class SignInPage implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		if(user != null) {
			page = PageList.PG_INDEX;
		} else {
			page = PageList.PG_SIGNIN;
		}
		return page;
	}

}
