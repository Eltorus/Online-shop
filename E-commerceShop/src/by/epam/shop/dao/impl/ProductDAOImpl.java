package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.bean.Product;
import by.epam.shop.dao.DBConnector;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.exception.DAOException;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public void addProduct(Product product) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.AddProductQuery);
			fillUpProductQuery(product, ps);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
	}

	@Override
	public boolean deleteProduct(Product product) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> getAllProducts() throws DAOException {
		List<Product> products = null;
		Product product = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			products = new ArrayList<Product>();
			con = DBConnector.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(QueryList.GetAllProductsQuery);
			while (rs.next()) {
				product = fillUpProduct(rs);
				products.add(product);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(st, con);
		}
		return products;
	}

	@Override
	public List<Product> getProduct(Product product) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		List<Product> result = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection();

			ps = precompileGetProductStatement(con,product);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product pr = fillUpProduct(rs);
				result.add(pr);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}

	
	private PreparedStatement precompileGetProductStatement(Connection con , Product product) throws SQLException {
		PreparedStatement ps = null;
		if(product.getId()!=0) {
			ps = con.prepareStatement(QueryList.GetProductQuery + QueryList.GetProductWithIdQuery);
			ps.setInt(1, product.getId());
		}
		////дописать
		if(product.getTitle()!=null && !product.getTitle().isEmpty()) {
			/*ps = con.prepareStatement(QueryList.GetProductQuery + "");
			ps.setInt(1, product.getId());*/
		}
		return ps;
	}

	
	private Product fillUpProduct(ResultSet rs) throws SQLException{
		Product product = new Product();
		
		product.setId(rs.getInt(1));
		product.setTitle(rs.getString(2));
		product.setCategory(rs.getString(3));
		product.setPrice(rs.getDouble(4));
		product.setDescription(rs.getString(5));
		product.setAmount(rs.getInt(6));
		product.setCategoryID(rs.getInt(7));
		
		return product;
	}
	
	private void fillUpProductQuery(Product product, PreparedStatement ps) throws SQLException {
		ps.setString(1, product.getTitle());
		ps.setInt(2, product.getCategoryID());
		ps.setDouble(3, product.getPrice());
		ps.setString(4, product.getDescription());
		ps.setInt(5, product.getAmount());
	}

	@Override
	public void updateProduct(Product product) throws DAOException {
	    Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.UpdateProductQuery);
			
			ps.setString(1, product.getTitle());
			ps.setInt(2, product.getCategoryID());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getDescription());
			ps.setInt(5, product.getAmount());
			ps.setInt(6, product.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
	}

}