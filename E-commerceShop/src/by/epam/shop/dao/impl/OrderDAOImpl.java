package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.Cart;
import by.epam.shop.entity.bean.CartLine;
import by.epam.shop.entity.bean.Order;
import by.epam.shop.entity.bean.Product;
import by.epam.shop.entity.bean.User;
import by.epam.shop.util.DateFormatter;
import by.epam.shop.util.UtilException;

/*implements by.epam.shop.dao.OrderDAO*/
public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = Logger.getLogger(OrderDAOImpl.class);

    /*
     * Add order to database
     * 
     * @param by.epam.shop.bean.Order
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     */
    @Override
    public void addOrder(Order order) throws DAOException {
	Connection con = null;
	Savepoint svpt = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    con.setAutoCommit(false);
	    svpt = con.setSavepoint("Add Order");
	    compileAddOrderQuery(con, ps, order);
	    con.commit();

	} catch (SQLException | ConnectionPoolException e) {
	    try {
		logger.error(e);
		con.rollback(svpt);
	    } catch (SQLException e1) {
		throw new DAOException("Exception during addOrder procedure", e);
	    }
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in addOrder procedure during getting back connection", e);
	    }
	}
    }

    /*
     * Update order in database
     * 
     * @param by.epam.shop.bean.Order
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     */
    @Override
    public void updateOrder(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.UpdateOrderQuery);

	    ps.setString(1, DateFormatter.convertDateToString(order.getDeliveryDate()));
	    ps.setString(2, order.getAddress());
	    ps.setBoolean(3, order.isOrderCompleted());
	    ps.setInt(4, order.getId());

	    ps.executeUpdate();
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during updateOrder procedure", e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in addOrder procedure during getting back connection", e);
	    }
	}

    }

    /*
     * Get order from database with id
     * 
     * @param by.epam.shop.bean.Order
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     * 
     * @return by.epam.shop.bean.Order
     */
    @Override
    public Order getOrder(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	Order result = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    result = compileGetOrderQuery(con, ps, order);
	} catch (SQLException | ConnectionPoolException | UtilException e) {
	    throw new DAOException("Exception during getOrder procedure", e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getOrder procedure during getting back connection", e);
	    }
	}

	return result;
    }

    /*
     * Get all orders from database
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     * 
     * @return List<by.epam.shop.bean.Order>
     */
    @Override
    public List<Order> getAllOrders() throws DAOException {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	List<Order> orderList = new ArrayList<>();
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    st = con.createStatement();
	    rs = st.executeQuery(QueryList.GetAllOrdersQuery);

	    while (rs.next()) {
		Order order = fillUpOrder(rs);
		orderList.add(order);
	    }
	} catch (SQLException | ConnectionPoolException | UtilException e) {
	    throw new DAOException("Exception during getAllOrders procedure", e);
	} finally {
	    try {
		pool.getBackConnection(st, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getAllOrders procedure during getting back connection", e);
	    }
	}
	return orderList;
    }

    /*
     * Set "delete field" of order as "deleted"
     * 
     * @param by.epam.shop.bean.Order
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     */
    @Override
    public void deleteOrder(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	Savepoint svpt = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    con.setAutoCommit(false);
	    svpt = con.setSavepoint("Delete Order");
	    deleteOrderExecuteQuery(con, ps, order);

	    con.commit();
	} catch (SQLException | ConnectionPoolException e) {
	    try {
		logger.error(e);
		con.rollback(svpt);
	    } catch (SQLException e1) {
		throw new DAOException("Exception during deleteOrder procedure", e);
	    }
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in deleteOrder procedure during getting back connection", e);
	    }
	}

    }

    /*
     * Get orders which were made by user from database
     * 
     * @param by.epam.shop.bean.Order
     * 
     * @throws by.epam.shop.dao.exception.DAOException
     * 
     * @return List<by.epam.shop.bean.Order>
     */
    @Override
    public List<Order> getOrders(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	List<Order> orderList = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    orderList = excuteGetOrdersQuery(con, ps, order);
	} catch (SQLException | ConnectionPoolException | UtilException e) {
	    throw new DAOException("Exception during getOrders procedure", e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getOrders procedure during getting back connection", e);
	    }
	}

	return orderList;
    }

    private List<Order> excuteGetOrdersQuery(Connection con, PreparedStatement ps, Order order)
	    throws SQLException, UtilException {
	List<Order> orderList = new ArrayList<>();

	ps = con.prepareStatement(QueryList.GetOrderQuery_P + QueryList.GetOrderWhereConditionUserId_P);
	ps.setInt(1, order.getUser().getId());

	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Order result = fillUpOrder(rs);
	    result.setCart(getProductsToOrder(con, ps, result));
	    orderList.add(result);
	}

	return orderList;
    }

    private void compileAddOrderQuery(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.AddOrderQuery);

	ps.setInt(1, order.getUser().getId());
	ps.setString(2, DateFormatter.convertDateToString(order.getOrderDate()));
	ps.setString(3, order.getAddress());
	ps.setDouble(4, order.getBill());
	ps.setDouble(5, order.getDiscount());
	ps.setDouble(6, order.getTotal());
	ps.setBoolean(7, order.isOrderPaid());

	ps.executeUpdate();

	addOrderedProducts(con, ps, order);
	decreaseUserBalance(con, ps, order);
	decreaseProductAmount(con, ps, order);
    }

    private void addOrderedProducts(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.AddOrderedProductsQuery);

	for (CartLine cartLine : order.getCart().getProductList()) {
	    Product product = cartLine.getProduct();
	    int quantity = cartLine.getQuantity();

	    ps.setInt(1, product.getId());
	    ps.setInt(2, quantity);

	    ps.executeUpdate();
	}
    }

    private void decreaseUserBalance(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.DecreaseUserBalanceQuery_P + QueryList.UserLoginInfQuery_P);

	ps.setDouble(1, order.getTotal());
	ps.setString(2, order.getUser().getEmail());
	ps.setString(3, order.getUser().getPasswordHash());

	ps.executeUpdate();
    }

    private void decreaseProductAmount(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.DecreaseProductAmountQuery);

	for (CartLine cartLine : order.getCart().getProductList()) {
	    ps.setInt(1, cartLine.getQuantity());
	    ps.setInt(2, cartLine.getProduct().getId());

	    ps.executeUpdate();
	}

    }

    private Order compileGetOrderQuery(Connection con, PreparedStatement ps, Order order)
	    throws DAOException, SQLException, UtilException {
	Order result = null;

	ps = con.prepareStatement(QueryList.GetOrderQuery_P + QueryList.WhereConditionOrderId_P);
	ps.setInt(1, order.getId());
	ResultSet rs = ps.executeQuery();

	while (rs.next()) {
	    result = fillUpOrder(rs);
	}

	result.setCart(getProductsToOrder(con, ps, order));
	return result;
    }

    private Cart getProductsToOrder(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.GetOrderedProducts);
	ps.setInt(1, order.getId());

	Cart cart = new Cart();
	ResultSet rs = ps.executeQuery();

	while (rs.next()) {
	    CartLine cl = new CartLine();
	    Product product = new Product();
	    product.setId(rs.getInt(1));
	    product.setTitle(rs.getString(2));
	    product.setPrice(rs.getDouble(3));

	    cl.setProduct(product);
	    cl.setQuantity(rs.getInt(4));

	    cart.addToProductList(cl);
	}
	return cart;
    }

    private void deleteOrderExecuteQuery(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.DeleteOrderQuery);

	ps.setInt(1, order.getId());
	ps.executeUpdate();

	recoverUserBalanceQuery(con, ps, order);
	productAmountRecoveryQuery(con, ps, order);
    }

    private void recoverUserBalanceQuery(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.UserBalanceRecoveryQuery_P + QueryList.UserIdQuery_P);

	ps.setDouble(1, order.getTotal());
	ps.setInt(2, order.getUser().getId());

	ps.executeUpdate();
    }

    private void productAmountRecoveryQuery(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.ProductAmountRecoveryQuery);

	for (CartLine cartLine : order.getCart().getProductList()) {
	    ps.setInt(1, cartLine.getQuantity());
	    ps.setInt(2, cartLine.getProduct().getId());

	    ps.executeUpdate();
	}
    }

    private Order fillUpOrder(ResultSet rs) throws SQLException, UtilException {
	Order order = new Order();

	order.setId(rs.getInt(1));

	User user = new User();
	user.setId(rs.getInt(2));

	order.setUser(user);
	order.setOrderDate(DateFormatter.convertStringToDate(rs.getString(3)));
	order.setDeliveryDate(DateFormatter.convertStringToDate(rs.getString(4)));
	order.setAddress(rs.getString(5));
	order.setBill(rs.getDouble(6));
	order.setDiscount(rs.getDouble(7));
	order.setTotal(rs.getDouble(8));
	order.setOrderPaid(rs.getBoolean(9));
	order.setOrderCompleted(rs.getBoolean(10));

	return order;
    }
}
