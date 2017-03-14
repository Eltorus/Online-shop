package _java._ee._02._command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._bean.User;
import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;
import _java._ee._02._service.UserService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.factory.ServiceFactory;

public class RechargingBalance implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		if(user!=null) {
			double credit = Double.parseDouble(request.getParameter(ParameterList.CMD_CREDIT));
			if(credit <= 0) {
				throw new CommandException("Credit must be greater than 0");
			} else {
				credit += user.getBalance();
				user.setBalance(credit);
				UserService userService = ServiceFactory.getInstance().getUserService();
				try {
					if(userService.rechargeBalance(user)) {
						page = PageList.PG_PROFILE;
					} else {
						page = PageList.PG_ERROR;
					}
				} catch (ServiceException e) {
					throw new CommandException(e);
				}
			}
		}
		return page;
	}

}
