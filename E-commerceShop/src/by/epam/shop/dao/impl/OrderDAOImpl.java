package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.epam.shop.bean.Cart;
import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.Product;
import by.epam.shop.bean.User;
import by.epam.shop.dao.DBConnector;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.exception.DAOException;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) throws DAOException {
	Connection con = null;
	Savepoint svpt = null;

	try {
	    con = DBConnector.getConnection();
	    con.setAutoCommit(false);
	    svpt = con.setSavepoint("Add Order");
	    compileAddOrderQuery(con, order);
	    con.commit();

	} catch (SQLException e) {
	    try {
		con.rollback(svpt);
	    } catch (SQLException e1) {
		throw new DAOException(e);
	    }
	}
    }

    private void compileAddOrderQuery(Connection con, Order order) throws SQLException {
	PreparedStatement ps = con.prepareStatement(QueryList.AddOrderQuery);

	ps.setInt(1, order.getUser().getId());
	ps.setTimestamp(2, order.getDeliveryDate());
	ps.setString(3, order.getAddress());
	ps.setDouble(4, order.getBill());
	ps.setBoolean(5, order.isOrderPaid());

	ps.executeUpdate();
	addOrderedProducts(con, ps, order);
	decreaseUserBalance(con, ps, order.getUser());
	decreaseProductAmount(con, ps, order);
    }

    private void addOrderedProducts(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.AddOrderedProductsQuery);

	Iterator<CartLine> itr = order.getCart().getProductList().iterator();
	while (itr.hasNext()) {
	    CartLine cartLine = itr.next();
	    Product product = cartLine.getProduct();
	    int quantity = cartLine.getQuantity();
	    ps.setInt(1, product.getId());
	    ps.setInt(2, quantity);

	    ps.executeUpdate();
	}
    }

    private void decreaseUserBalance(Connection con, PreparedStatement ps, User user) throws SQLException {
	ps = con.prepareStatement(
		QueryList.UpdateUserQuery_P + QueryList.SetBalanceQuery_P + QueryList.GetUserQueryLogin_P);

	ps.setDouble(1, user.getBalance());
	ps.setString(2, user.getEmail());
	ps.setString(3, user.getPasswordHash());

	ps.executeUpdate();
    }

    private void decreaseProductAmount(Connection con, PreparedStatement ps, Order order) throws SQLException {
	ps = con.prepareStatement(QueryList.DecreaseProductAmountQuery);

	Iterator<CartLine> itr = order.getCart().getProductList().iterator();
	while (itr.hasNext()) {
	    CartLine cartLine = itr.next();
	    ps.setInt(1, cartLine.getQuantity());
	    ps.setInt(2, cartLine.getProduct().getId());

	    ps.executeUpdate();
	}

    }

    @Override
    public void updateOrder(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	try {
	    con = DBConnector.getConnection();
	    ps = con.prepareStatement(QueryList.UpdateOrderQuery);

	    ps.setTimestamp(1, order.getDeliveryDate());
	    ps.setString(2, order.getAddress());
	    ps.setBoolean(3, order.isOrderCompleted());
	    ps.setInt(4, order.getId());

	    ps.executeUpdate();
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DBConnector.closeConnection(ps, con);
	}

    }

    @Override
    public Order getOrder(Order order) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	Order result = null;

	try {
	    con = DBConnector.getConnection();
	    ps = con.prepareStatement(QueryList.GetOrderQuery);
	    result = compileGetOrderQuery(con, ps, order);
	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DBConnector.closeConnection(ps, con);
	}
	return result;
    }

    private Order compileGetOrderQuery(Connection con, PreparedStatement ps, Order order)
	    throws DAOException, SQLException {
	Order result = null;
	ps.setInt(1, order.getId());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    result = fillUpOrder(rs);
	}

	result.setCart(getProductsToOrder(con, order));
	return result;
    }

    private Cart getProductsToOrder(Connection con, Order order) throws SQLException {
	PreparedStatement ps = con.prepareStatement(QueryList.GetOrderedProducts);
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

    @Override
    public List<Order> getAllOrders() throws DAOException {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	List<Order> orderList = new ArrayList<>();

	try {
	    con = DBConnector.getConnection();
	    st = con.createStatement();
	    rs = st.executeQuery(QueryList.GetAllOrdersQuery);

	    while (rs.next()) {
		Order order = fillUpOrder(rs);
		orderList.add(order);
	    }

	} catch (SQLException e) {
	    throw new DAOException(e);
	} finally {
	    DBConnector.closeConnection(st, con);
	}
	return orderList;
    }

    @Override
    public void deleteOrder(Order order) throws DAOException {
	// TODO Auto-generated method stub

    }

    private Order fillUpOrder(ResultSet rs) throws SQLException {
	Order order = new Order();
	order.setId(rs.getInt(1));

	User user = new User();
	user.setId(rs.getInt(2));
	order.setUser(user);
	order.setOrderDate(rs.getTimestamp(3));
	order.setDeliveryDate(rs.getTimestamp(4));
	order.setAddress(rs.getString(5));
	order.setBill(rs.getDouble(6));
	order.setOrderPaid(rs.getBoolean(7));
	order.setOrderCompleted(rs.getBoolean(8));
	return order;
    }
}
