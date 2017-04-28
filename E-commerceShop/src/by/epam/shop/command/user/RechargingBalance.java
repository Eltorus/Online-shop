package by.epam.shop.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.MessageGenerator;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class RechargingBalance implements Command {
    private final static Logger logger = Logger.getLogger(RechargingBalance.class);

    /* Get requested credit and pass user object with changed balance to service layer
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(AttributeList.ATTR_USER);

	double credit = NumberOperationTool.getDoubleFromString(request.getParameter(ParameterList.USER_CREDIT));
	if (credit <= 0 || credit > 1000) {
	    return PageList.PG_PROFILE+MessageGenerator.generateError(1405);
	}

	credit += user.getBalance();
	user.setBalance(credit);

	UserService userService = ServiceFactory.getInstance().getUserService();
	try {
	    User result = userService.updateUser(user);
	    session.setAttribute(AttributeList.ATTR_USER, result);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during RechargingBalance command", e);
	}
	return PageList.PG_PROFILE;
    }

}
