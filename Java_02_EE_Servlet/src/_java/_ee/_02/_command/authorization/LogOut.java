	package _java._ee._02._command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class LogOut implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		if(session.getAttribute(AttributeList.ATTR_USER)!=null) {
			session.removeAttribute(AttributeList.ATTR_USER);
			session.removeAttribute(AttributeList.ATTR_CART);
		}
		return PageList.PG_INDEX;
	}

}
