package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.User;

/*implements by.epam.shop.dao.UserDAO*/
public class UserDAOImpl implements UserDAO {
    
    /* Get user from database with id
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * @return by.epam.shop.bean.User
     * */
    @Override
    public User getUserWithId(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	User result = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    ps = con.prepareStatement(QueryList.GetUserQuery + QueryList.UserIdQuery_P);
	    ps.setInt(1, user.getId());
	    
	    rs = ps.executeQuery();
	    while (rs.next()) {
		result = fillUpUser(rs);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getUserWithId procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getUserWithId procedure during getting back connection",e);
	    }
	}
	return result;
    }

    /* Get user from database with email and password 
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * @return by.epam.shop.bean.User
     * */
    @Override
    public User getUserWithLoginInf(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	User result = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.GetUserQuery + QueryList.UserLoginInfQuery_P);

	    ps.setString(1, user.getEmail());
	    ps.setString(2, user.getPasswordHash());

	    rs = ps.executeQuery();
	    while (rs.next()) {
		result = fillUpUser(rs);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getUserWithLoginInf procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getUserWithLoginInf procedure during getting back connection",e);
	    }
	}
	return result;
    }
    
    /* Add user to database
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void addUser(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.AddUserQuery);

	    ps.setString(1, user.getName());
	    ps.setString(2, user.getSurname());
	    ps.setString(3, user.getEmail());
	    ps.setString(4, user.getPasswordHash());
	    ps.setString(5, user.getPhonenumber());

	    ps.executeUpdate();
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during addUser procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in addUser procedure during getting back connection",e);
	    }
	}
    }

    /* Set "delete field" of user as "deleted" in database
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void deleteUser(User user) throws DAOException {
	Connection con = null;
	PreparedStatement ps = null;
	ConnectionPool pool = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = con.prepareStatement(QueryList.DeleteUserQuery);

	    ps.setString(1, user.getEmail());
	    ps.setString(2, user.getPasswordHash());
	    ps.executeUpdate();
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during deleteUser procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in deleteUser procedure during getting back connection",e);
	    }
	}
    }

    /* Update user in database
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * */
    @Override
    public void updateUser(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    updateUserInformation(ps, con, user);

	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during updateUser procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in updateUser procedure during getting back connection",e);
	    }
	}
    }

    private void updateUserInformation(PreparedStatement ps, Connection con, User user) throws SQLException {
	ps = con.prepareStatement(QueryList.UpdateUserQuery_P+ QueryList.UserLoginInfQuery_P);
	ps.setDouble(1, user.getBalance());
	ps.setString(2, user.getImgPath());
	ps.setBoolean(3, user.isBanned());
	ps.setDouble(4, user.getDiscountCoefficient());
	ps.setString(5, user.getEmail());
	ps.setString(6, user.getPasswordHash());
	ps.executeUpdate();
    }
    
    /* Gets all users from database
     * @throws by.epam.shop.dao.exception.DAOException
     * @return List<by.epam.shop.bean.User>
     * */
    @Override
    public List<User> getAllUsers() throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<User> userList = new ArrayList<>();
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    ps = con.prepareStatement(QueryList.GetAllUsersQuery);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		User user = fillUpUser(rs);
		userList.add(user);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getAllUsers procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getAllUsers procedure during getting back connection",e);
	    }
	}
	return userList;
    }

    private User fillUpUser(ResultSet rs) throws SQLException {
	User result = new User();

	result.setId(rs.getInt(1));
	result.setName(rs.getString(2));
	result.setSurname(rs.getString(3));
	result.setEmail(rs.getString(4));
	result.setPasswordHash(rs.getString(5));
	result.setPhonenumber(rs.getString(6));
	result.setBanned(rs.getBoolean(7));
	result.setAdmin(rs.getBoolean(8));
	result.setDiscountCoefficient(rs.getDouble(9));
	result.setBalance(rs.getDouble(10));
	result.setImgPath(rs.getString(11));
	return result;
    }

    /* Get user from database with email
     * @param by.epam.shop.bean.User
     * @throws by.epam.shop.dao.exception.DAOException
     * @return by.epam.shop.bean.User
     * */
    @Override
    public User getUserWithEmail(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	User result = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    ps = con.prepareStatement(QueryList.GetUserQuery + QueryList.UserEmailQuery_P);
	    ps.setString(1, user.getEmail());
	    
	    rs = ps.executeQuery();
	    while (rs.next()) {
		result = fillUpUser(rs);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException("Exception during getUserWithEmail procedure",e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException("Exception in getUserWithEmail procedure during getting back connection",e);
	    }
	}
	return result;
    }
}
