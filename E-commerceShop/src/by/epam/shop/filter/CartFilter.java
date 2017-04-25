package by.epam.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.Cart;
import by.epam.shop.command.AttributeList;

public class CartFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session =  request.getSession();
		
		Cart cart = (Cart) session.getAttribute(AttributeList.ATTR_CART);
		
		if(cart == null) {
			cart = new Cart();
			session.setAttribute(AttributeList.ATTR_CART, cart);
		}
		
		chain.doFilter(req, res);
	}
	
}
