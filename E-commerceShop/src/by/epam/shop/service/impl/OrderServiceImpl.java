package by.epam.shop.service.impl;
 
import java.util.List;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.User;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.factory.DAOFactory;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.exception.ServiceException;
import by.epam.shop.util.NumberOperationTool;
/*
 * Class OrderServiceImpl implements OrderService interface OrderService 
 */
public class OrderServiceImpl implements OrderService {

    /* Creates Order object {@link by.epam.shop.bean.Order}
     * @param Cart object, which contains ordered Products
     * @param User object, represents User which made order
     * @throws by.epam.shop.service.exception.ServiceException if Cart object or User object equals null 
     * @return created by.epam.shop.bean.Order
     * */
    @Override
    public Order createOrder(Cart cart, User user) throws ServiceException {
	if(cart == null || user == null) {
	    throw new ServiceException("Order is empty");
	}
	
	Order order = new Order();
	order.setUser(user);
	order.setCart(cart);
	countOrderBill(cart, user, order);
	
	return order;
    }

    private void countOrderBill(Cart cart, User user, Order order) {
	double bill = 0;

	for(CartLine cartLine : cart.getProductList()) {
	    bill += cartLine.getProduct().getPrice() * cartLine.getQuantity();
	}
	order.setBill(bill);
	
	double discount = user.getDiscountCoefficient();
	order.setDiscount(discount);
	
	double total = bill;
	if (discount >= 0) {
	    total = bill - (bill * discount);
	    
	}
	order.setTotal(NumberOperationTool.getRoundedDouble(total));
	
    }

    /* Pass order to DAO layer for adding
     * @param by.epam.shop.bean.Order
     * @throws by.epam.shop.service.exception.ServiceException
     * */
    @Override
    public void addOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Order is empty");
	}
	OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	try {
	    orderDAO.addOrder(order);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during addOrder prodecure",e);
	}
    }
    
    /* Pass order to DAO layer for deleting procedure
     * @param by.epam.shop.bean.Order
     * @throws by.epam.shop.service.exception.ServiceException
     * */
    @Override
    public void deleteOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Object is null");
	}

	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderDAO.deleteOrder(order);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during deleteOrder prodecure",e);
	}
    }

    /* Pass order to DAO layer for updating
     * @param by.epam.shop.bean.Order
     * @throws by.epam.shop.service.exception.ServiceException
     * */
    @Override
    public void updateOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Object is null");
	}

	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderDAO.updateOrder(order);
	} catch (DAOException e) {
	    throw new ServiceException("Exception during updateOrder prodecure",e);
	}

    }

    /* Get Order object {@link by.epam.shop.bean.Order} from DAO layer
     * @param by.epam.shop.bean.Order
     * @throws by.epam.shop.service.exception.ServiceException
     * @return by.epam.shop.bean.Order
     * */
    @Override
    public Order getOrder(Order order) throws ServiceException {
	if (order == null) {
	    throw new ServiceException("Object is null");
	}

	Order result = null;

	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    result = orderDAO.getOrder(order);

	    User user = new User();
	    user.setId(result.getUser().getId());

	    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	    result.setUser(userDAO.getUserWithId(user));

	} catch (DAOException e) {
	    throw new ServiceException("Exception during getOrder prodecure",e);
	}
	return result;
    }

    /* Get Order objects {@link by.epam.shop.bean.Order} from DAO layer
     * @throws by.epam.shop.service.exception.ServiceException
     * @return List<by.epam.shop.bean.Order>
     * */
    @Override
    public List<Order> getAllOrders() throws ServiceException {
	List<Order> orderList = null;
	try {
	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderList = orderDAO.getAllOrders();

	    if (orderList.isEmpty()) {
		orderList = null;
	    }
	} catch (DAOException e) {
	    throw new ServiceException("Exception during getAllOrders prodecure",e);
	}
	return orderList;
    }

    /* Get Order objects {@link by.epam.shop.bean.Order} which have been made by User
     * @param by.epam.shop.bean.User, should contain id of User
     * @throws by.epam.shop.service.exception.ServiceException
     * @return List<by.epam.shop.bean.Order>
     * */
    @Override
    public List<Order> getUserOrders(User user) throws ServiceException {
	if(user == null || user.getId() == 0) {
	    throw new ServiceException("Object is null");
	}
	List<Order> orderList = null;

	try {
	    Order order = new Order();
	    order.setUser(user);

	    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	    orderList = orderDAO.getOrders(order);

	    if (orderList.isEmpty()) {
		return null;
	    }
	} catch (DAOException e) {
	    throw new ServiceException("Exception during getUserOrders prodecure",e);
	}
	return orderList;
    }

}
