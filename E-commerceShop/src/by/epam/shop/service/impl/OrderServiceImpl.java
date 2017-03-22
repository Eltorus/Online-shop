package by.epam.shop.service.impl;

import java.util.Iterator;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.service.validation.Validation;
import by.epam.shop.util.RoundDouble;

public class OrderServiceImpl implements OrderService{
	
	@Override
	public Order createOrder(Cart cart, User user) throws ServiceException {
		if(!Validation.userIsValid(user)) {
			throw new ServiceException("User doesn't valid");
		}
		
		Order order = new Order();
		order.setUser(user);
		order.setBill(countOrderBill(cart,user));
		order.setCart(cart);
		
		return order;
	}
	
	private double countOrderBill(Cart cart, User user) {
		double bill = 0;
		
		Iterator<CartLine> itr =  cart.getProductList().iterator();
		while(itr.hasNext()) {
			CartLine cartLine = (CartLine) itr.next();
			bill += cartLine.getProduct().getPrice()*cartLine.getQuantity();
		}
		
		double discount = user.getDiscountCoefficient();
		if(discount >= 0) {
			bill = bill - (bill * discount);	
		}
		
		return RoundDouble.getRoundedDouble(bill);
	}

	@Override
	public void addOrder(Order order) throws ServiceException {
		if(order == null) {
			throw new ServiceException("Order is empty");
		}
		OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
		try {
			System.out.println("orderservice: before");
			orderDAO.addOrder(order);
			System.out.println("orderservice: after");
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public boolean deleteOrder(Order order) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
