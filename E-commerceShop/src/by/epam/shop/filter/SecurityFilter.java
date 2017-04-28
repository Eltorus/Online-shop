package by.epam.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.CommandList;
import by.epam.shop.util.PageList;

public class SecurityFilter implements Filter {

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
	return command.equals(CommandList.CMD_MAKE_ORDER) || command.equals(CommandList.CMD_USER_ORDER_CANCEL)
		|| command.equals(CommandList.CMD_ORDER_PG) || command.equals(CommandList.CMD_USER_DELETE_PROFILE)
		|| command.equals(CommandList.CMD_TOPBALANCE) || command.equals(CommandList.CMD_USER_AVATAR_UPLOAD)
		|| command.equals(CommandList.CMD_USER_ORDERS) || command.equals(CommandList.CMD_LOGOUT);
    }

    private boolean isAdminCommand(String command) {
	return command.equals(CommandList.CMD_ADMIN_ORDER_PG) || command.equals(CommandList.CMD_ADMIN_PRODUCT_PG)
		|| command.equals(CommandList.CMD_ADMIN_USER_PG) || command.equals(CommandList.CMD_ORDER_CHANGE)
		|| command.equals(CommandList.CMD_ORDER_INFORM) || command.equals(CommandList.CMD_PRODUCT_DELETE)
		|| command.equals(CommandList.CMD_PRODUCT_UPDATE) || command.equals(CommandList.CMD_USER_UPDATE_BAN)
		|| command.equals(CommandList.CMD_USER_UPDATE_DISCOUNT);
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

}
