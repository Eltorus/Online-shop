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
	public boolean addProduct(Product product) throws DAOException {
		// TODO Auto-generated method stub
		return false;
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
			if (con != null) {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return products;
	}

	@Override
	public Product getProductWithId(Product product) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		Product result = null;
		ResultSet rs = null;
		try {
			String query = QueryList.GetProductQuery + QueryList.GetProductWithIdQuery;
			con = DBConnector.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, product.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = fillUpProduct(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null) {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return result;
	}

	@Override
	public List<Product> getProductWithTitle(Product product) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductWithCategory(Product product) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Product fillUpProduct(ResultSet rs) throws SQLException{
		Product product = new Product();
		product.setId(rs.getInt("p_id"));
		product.setTitle(rs.getString("p_title"));
		product.setCategory(rs.getString("category"));
		product.setPrice(rs.getDouble("p_price"));
		product.setDescription(rs.getString("p_description"));
		product.setAmount(rs.getInt("p_amount"));
		return product;
	}

}
