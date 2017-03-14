package _java._ee._02._command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _java._ee._02._bean.User;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class AdminPage implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(AttributeList.ATTR_USER);
		if(user != null) {
			if(user.isIs_admin()) {
				page = PageList.PG_ADMIN;
			}
		}
		return page;
	}

}
