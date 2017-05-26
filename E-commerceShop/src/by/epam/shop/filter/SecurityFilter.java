package by.epam.shop.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.CommandList;
import by.epam.shop.entity.bean.User;
import by.epam.shop.util.PageList;

public class SecurityFilter implements Filter {
    private List<String> userCommands = null;
    private List<String> adminCommands = null;

    /*
     * Check if user has access level to requested command
     * 
     * @param javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain;
     * 
     * @throws java.io.IOException, javax.servlet.ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;

	User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
	String command = request.getParameter(CommandList.CMD);

	String page = null;
	if (command != null) {
	    if (isUserCommand(command)) {
		if (!isUserLoged(user)) {
		    page = PageList.PG_SIGNIN;
		}
	    } else if (isAdminCommand(command)) {
		if (!isUserAdmin(user)) {
		    page = PageList.PG_SIGNIN;
		}
	    } else if (command.equals(CommandList.CMD_SIGNIN) || command.equals(CommandList.CMD_SIGNUP)) {
		if (isUserLoged(user)) {
		    page = PageList.PG_INDEX;
		}
	    }
	}

	if (page != null) {
	    response.sendRedirect(request.getContextPath() + page);
	} else {
	    chain.doFilter(req, res);
	}
    }

    private boolean isUserCommand(String command) {
	for(String userCommand: userCommands) {
	    if(command.equals(userCommand)) {
		return true;
	    }
	}
	return false;
    }

    private boolean isAdminCommand(String command) {
	for(String adminCommand: adminCommands) {
	    if(command.equals(adminCommand)) {
		return true;
	    }
	}
	return false;
    }

    private boolean isUserLoged(User user) {
	if (user == null) {
	    return false;
	}
	if (user.getEmail() == null || user.getPasswordHash() == null) {
	    return false;
	}
	return true;
    }

    public boolean isUserAdmin(User user) {
	if (user == null) {
	    return false;
	}
	return user.isAdmin();
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
	userCommands = new ArrayList<String>();
	userCommands.add(CommandList.CMD_MAKE_ORDER);
	userCommands.add(CommandList.CMD_USER_ORDER_CANCEL);
	userCommands.add(CommandList.CMD_ORDER_PG);
	userCommands.add(CommandList.CMD_USER_DELETE_PROFILE);
	userCommands.add(CommandList.CMD_TOPBALANCE);
	userCommands.add(CommandList.CMD_USER_AVATAR_UPLOAD);
	userCommands.add(CommandList.CMD_USER_ORDERS);
	userCommands.add(CommandList.CMD_LOGOUT);
	
	adminCommands = new ArrayList<String>();
	adminCommands.add(CommandList.CMD_ADMIN_ORDER_PG);
	adminCommands.add(CommandList.CMD_ADMIN_PRODUCT_PG);
	adminCommands.add(CommandList.CMD_ADMIN_USER_PG);
	adminCommands.add(CommandList.CMD_ORDER_CHANGE);
	adminCommands.add(CommandList.CMD_ORDER_INFORM);
	adminCommands.add(CommandList.CMD_PRODUCT_DELETE);
	adminCommands.add(CommandList.CMD_PRODUCT_UPDATE); 
	adminCommands.add(CommandList.CMD_USER_UPDATE_BAN);
	adminCommands.add(CommandList.CMD_USER_UPDATE_DISCOUNT);
	
    }

}
