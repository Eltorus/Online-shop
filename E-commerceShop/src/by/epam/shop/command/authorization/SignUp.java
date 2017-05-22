package by.epam.shop.command.authorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import by.epam.shop.util.HashTool;
import by.epam.shop.util.MessageGenerator;
import by.epam.shop.util.MessageList;
import by.epam.shop.util.PageList;
import by.epam.shop.util.StringOperationTool;
import by.epam.shop.util.UtilException;

public class SignUp implements Command {
    public static String NUMBER_REGEX = "^\\+375\\s?(\\(?\\d{2}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2})$";
    public static String EMAIL_REGEX = "^(.[^@\\s]+)@(.[^@\\s]+)\\.([a-z]+)$";
    private final static Logger logger = Logger.getLogger(SignUp.class);

    /*
     * Get user params from request, creates new User object and pass to service
     * layer for adding
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
	try {
	    User user = fillUpUser(request, response);
	    UserService userService = ServiceFactory.getInstance().getUserService();
	    boolean successfulRegister = userService.addUser(user);

	    if (successfulRegister) {
		return PageList.PG_SIGNIN + MessageGenerator.generateSuccess(MessageList.SUCCESS_REG);
	    } else {
		return PageList.PG_SIGNIN + MessageGenerator.generateError(MessageList.ERR_USED_EMAIL);
	    }
	} catch (ServiceException | UtilException e) {
	    logger.error(e);
	    throw new CommandException("Exception during SignUp command", e);
	}
    }

    private User fillUpUser(HttpServletRequest request, HttpServletResponse response)
	    throws UtilException, CommandException {

	String passwordConfirm = request.getParameter(ParameterList.USER_PSWRD_CONFIRM);
	String password = request.getParameter(ParameterList.USER_PSWRD);
	if (!passwordConfirm.equals(password)) {
	    throw new CommandException("Passwords don't match");
	}

	String name = request.getParameter(ParameterList.USER_NAME);
	if (name == null || name.trim().isEmpty() || name.length() > 50) {
	    throw new CommandException("Inapropriate name format");
	}
	
	String surname = request.getParameter(ParameterList.USER_SURNAME);
	if (surname == null || surname.trim().isEmpty() || surname.length() > 50) {
	    throw new CommandException("Inapropriate surname format");
	}
	
	String email = request.getParameter(ParameterList.USER_EMAIL);
	if (!StringOperationTool.isStringValid(email) || email.trim().length() > 45 || email.trim().length() < 8) {
	    throw new CommandException("Inapropriate email format");
	}
	
	Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
	Matcher emailMatcher = emailPattern.matcher(email.trim());
	if (!emailMatcher.find()) {
	    throw new CommandException("Inapropriate email format");
	}

	String phonenumber = request.getParameter(ParameterList.USER_PHONE);
	if (phonenumber == null || phonenumber.trim().isEmpty() || phonenumber.length() > 20
		|| phonenumber.length() < 13) {
	    throw new CommandException("Inapropriate phonenumber format");
	}
	
	Pattern phonePattern = Pattern.compile(NUMBER_REGEX);
	Matcher phonematcher = phonePattern.matcher(phonenumber.trim());
	if (!phonematcher.find()) {
	    throw new CommandException("Inapropriate phonenumber format");
	}
	

	if (password == null || password.isEmpty() || password.length() < 5) {
	    throw new CommandException("Inapropriate password format");
	}

	User user = new User();
	user.setName(name.trim());
	user.setSurname(surname.trim());
	user.setPhonenumber(phonenumber.trim());
	user.setEmail(email.trim());
	user.setPasswordHash(HashTool.hashLine(password));

	return user;
    }

}
