package by.epam.shop.command.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.entity.bean.User;
import by.epam.shop.service.UserService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.factory.ServiceFactory;
import by.epam.shop.util.NumberOperationTool;
import by.epam.shop.util.PageList;

public class UpdatingUserBanStatus implements Command {
    private final static Logger logger = Logger.getLogger(UpdatingUserBanStatus.class);

    /*
     * Get user status ban from request, create new User object and pass to
     * service layer for updating
     * 
     * @param javax.servlet.http.HttpServletRequest
     * 
     * @param javax.servlet.http.HttpServletResponse
     * 
     * @throws by.epam.shop.command.exception.CommandException
     * 
     * @return String page, which will be passed to client
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	int id = NumberOperationTool.getIntFromString(request.getParameter(ParameterList.USER_ID));
	if (id == 0) {
	    return PageList.PG_ADMIN_USER_R;
	}

	boolean userBanned = Boolean.valueOf(request.getParameter(ParameterList.USER_BANNED));

	User user = new User();
	user.setId(id);
	user.setBanned(!userBanned);

	try {
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    userService.updateUserBanStatus(user);
	} catch (ServiceException e) {
	    logger.error(e);
	    throw new CommandException("Exception during UpdatingUserBanStatus command",e);

	}
	return PageList.PG_ADMIN_USER_R;
    }

}
