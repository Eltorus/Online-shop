package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.Product;

/*implements by.epam.shop.dao.OrderDAO*/
public class ProductDAOImpl implements ProductDAO {

    /* Add product to database
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void addProduct(Product product) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.AddProductQuery);
	    
	    fillUpProductQuery(product, ps);
	    
	    ps.executeUpdate();
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during addProduct procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in addProduct procedure during getting back connection",e);
	    }
	}
    }

    /* Set "delete field" of product as "deleted" in database
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void deleteProduct(Product product) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;

	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.DeleteProductQuery);

	    ps.setInt(1, product.getId());
	    ps.executeUpdate();

	} catch (ConnectionPoolException | SQLException e) {
	    throw new DAOException("Exception during deleteProduct procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in deleteProduct procedure during getting back connection",e);
	    }
	}
    }

    /* Gets all products from database
     * @throws by.epam.shop.dao.exception.DAOException
     * @return List<by.epam.shop.bean.Product>
     * */
    @Override
    public List<Product> getAllProducts() throws DAOException {
	List<Product> products = null;
	Product product = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ConnectionPool pool = null;
	
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    st = con.createStatement();
	    rs = st.executeQuery(QueryList.GetAllProductsQuery);
	    products = new ArrayList<Product>();
	    
	    while (rs.next()) {
		product = fillUpProduct(rs);
		products.add(product);
	    }
	    
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getAllProducts procedure",e);
	} finally {
	    try {
		pool.getBackConnection(st, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getAllProducts procedure during getting back connection",e);
	    }
	}
	return products;
    }

    /* Gets product from database with id
     * @throws by.epam.shop.dao.exception.DAOException
     * @return by.epam.shop.bean.Product
     * */
    @Override
    public Product getProduct(Product product) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	Product result = null;
	ResultSet rs = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    ps = con.prepareStatement(QueryList.GetProductQuery + QueryList.GetProductWithIdQuery);
	    ps.setInt(1, product.getId());
	    
	    rs = ps.executeQuery();
	    while (rs.next()) {
		result = fillUpProduct(rs);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getProduct procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getProduct procedure during getting back connection",e);
	    }
	}
	return result;
    }

    private Product fillUpProduct(ResultSet rs) throws SQLException {
	Product product = new Product();

	product.setId(rs.getInt(1));
	product.setTitle(rs.getString(2));
	product.setCategory(rs.getString(3));
	product.setPrice(rs.getDouble(4));
	product.setDescription(rs.getString(5));
	product.setAmount(rs.getInt(6));
	product.setCategoryID(rs.getInt(7));
	product.setImgPath(rs.getString(8));

	return product;
    }

    private void fillUpProductQuery(Product product, PreparedStatement ps) throws SQLException {
	ps.setString(1, product.getTitle());
	ps.setInt(2, product.getCategoryID());
	ps.setDouble(3, product.getPrice());
	ps.setString(4, product.getDescription());
	ps.setInt(5, product.getAmount());
	ps.setString(6, product.getImgPath());
    }

    /* Update product in database
     * @param by.epam.shop.bean.Product
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void updateProduct(Product product) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.UpdateProductQuery);

	    ps.setString(1, product.getTitle());
	    ps.setInt(2, product.getCategoryID());
	    ps.setDouble(3, product.getPrice());
	    ps.setString(4, product.getDescription());
	    ps.setInt(5, product.getAmount());
	    ps.setString(6, product.getImgPath());
	    ps.setInt(7, product.getId());

	    ps.executeUpdate();
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during updateProduct procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in updateProduct procedure during getting back connection",e);
	    }
	}
    }

    /* Get total product amount from database
     * @throws by.epam.shop.dao.exception.DAOException
     * @return integer amount
     * */
    @Override
    public int getTotalProductAmount() throws DAOException {
	Connection con = null;
	Statement st = null;
	ConnectionPool pool = null;
	int amount = 0;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(QueryList.GetTotalProductAmount);
	    
	    while(rs.next()) {
		amount = rs.getInt(1);
	    }
	} catch (ConnectionPoolException | SQLException e) {
	    throw new DAOException("Exception during getTotalProductAmount procedure",e);
	} finally {
	    try {
		pool.getBackConnection(st, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getTotalProductAmount procedure during getting back connection",e);
	    }
	}
	return amount;
    }

    /* Get part of total amount products from database
     * @param int offset - index of first product to get
     * @param int limit - amount of products to get 
     * @throws by.epam.shop.dao.exception.DAOException
     * @return List<by.epam.shop.bean.Product>
     * */
    @Override
    public List<Product> getProducts(int offset, int limit) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	List<Product> productList = new ArrayList<Product>();
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    
	    ps = con.prepareStatement(QueryList.GetAllProductsQuery+QueryList.GetLimitProductsQuery_P);
	    ps.setInt(1, offset);
	    ps.setInt(2, limit);
	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
		Product product = fillUpProduct(rs);
		productList.add(product);
	    }
	    
	} catch (ConnectionPoolException | SQLException e) {
	    throw new DAOException("Exception during getProducts procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getProducts procedure during getting back connection",e);
	    }
	}
	
	return productList;
    }

}