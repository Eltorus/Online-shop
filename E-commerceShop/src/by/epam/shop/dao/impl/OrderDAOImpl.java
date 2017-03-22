package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Iterator;

import by.epam.shop.bean.CartLine;
import by.epam.shop.bean.Order;
import by.epam.shop.bean.Product;
import by.epam.shop.bean.User;
import by.epam.shop.dao.DBConnector;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.exception.DAOException;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public void addOrder(Order order) throws DAOException {
		Connection con = null;
		Savepoint svpt = null;
		
		try {
			con = DBConnector.getConnection();
			con.setAutoCommit(false);
			svpt = con.setSavepoint("Add Order");
			precompileAddOrderStatement(con, order);
			con.commit();
			
		} catch (SQLException e) {
			try {
				System.out.println("Rollback");
				e.printStackTrace();
				con.rollback(svpt);
			} catch (SQLException e1) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public boolean changeOrder(Order order) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void precompileAddOrderStatement(Connection con, Order order) throws SQLException {
		PreparedStatement ps = con.prepareStatement(QueryList.AddOrderQuery);
		
		ps.setInt(1, order.getUser().getId());
		ps.setTimestamp(2, order.getDeliveryDate());
		ps.setString(3, order.getAddress());
		ps.setDouble(4, order.getBill());
		ps.setBoolean(5, order.isOrderPaid());
		
		ps.executeUpdate();
		System.out.println("Order added");
		addOrderedProducts(con, ps, order);
		decreaseUserBalance(con, ps, order.getUser());
		decreaseProductAmount(con, ps, order);
	}
	
	private void addOrderedProducts(Connection con, PreparedStatement ps, Order order) throws SQLException {
		ps = con.prepareStatement(QueryList.AddOrderedProductsQuery);

		Iterator<CartLine> itr = order.getCart().getProductList().iterator();
		while(itr.hasNext()) {
			CartLine cartLine = itr.next();
			Product product = cartLine.getProduct();
			int quantity =  cartLine.getQuantity();
			ps.setInt(1, product.getId());
			ps.setInt(2, quantity);

			ps.executeUpdate();
			
			System.out.println("Ordered products added");
		}
	}
	
	private void decreaseUserBalance(Connection con, PreparedStatement ps, User user) throws SQLException {
		ps = con.prepareStatement(QueryList.UpdateUserQuery);
		
		ps.setDouble(1, user.getBalance());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPasswordHash());
		
		ps.executeUpdate();

		System.out.println("Ballance decreased");
	}
	
	private void decreaseProductAmount(Connection con, PreparedStatement ps, Order order) throws SQLException {
		ps = con.prepareStatement(QueryList.DecreaseProductAmountQuery);
		
		Iterator<CartLine> itr = order.getCart().getProductList().iterator();
		while(itr.hasNext()) {
			CartLine cartLine = itr.next();
			ps.setInt(1, cartLine.getQuantity());
			ps.setInt(2, cartLine.getProduct().getId());
			

			ps.executeUpdate();
		}

	}
}
