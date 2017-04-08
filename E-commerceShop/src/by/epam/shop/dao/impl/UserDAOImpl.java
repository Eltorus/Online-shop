package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.bean.User;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;
import by.epam.shop.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
    @Override
    public User getUser(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	User result = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();
	    ps = prepareGetUserStatement(ps, con, user);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		result = fillUpUser(rs);
	    }
	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException(e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException(e);
	    }
	}
	return result;
    }

    private PreparedStatement prepareGetUserStatement(PreparedStatement ps, Connection con, User user)
	    throws SQLException {
	if (user.getEmail() != null && user.getPasswordHash() != null) {

	    if (!user.getEmail().isEmpty() && !user.getPasswordHash().isEmpty()) {
		ps = con.prepareStatement(QueryList.GetUserQuery + QueryList.GetUserQueryLogin_P);
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getPasswordHash());
	    }

	} else if (user.getId() != 0) {

	    ps = con.prepareStatement(QueryList.GetUserQuery + QueryList.GetUserQueryId_P);
	    ps.setInt(1, user.getId());

	}
	return ps;
    }

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
	    throw new DAOException(e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException(e);
	    }
	}
    }

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
	    throw new DAOException(e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException(e);
	    }
	}
    }

    @Override
    public void updateUser(User user) throws DAOException {
	ConnectionPool pool = null;
	Connection con = null;
	PreparedStatement ps = null;
	try {
	    pool = ConnectionPool.getInstance();
	    con = pool.takeConnection();

	    ps = precompileUpdateStatement(con, user);
	    ps.executeUpdate();

	} catch (SQLException | ConnectionPoolException e) {
	    throw new DAOException(e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException(e);
	    }
	}
    }

    private PreparedStatement precompileUpdateStatement(Connection con, User user) throws SQLException {
	PreparedStatement ps = null;

	if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty() && user.getEmail() != null
		&& !user.getEmail().isEmpty()) {
	    ps = con.prepareStatement(
		    QueryList.UpdateUserQuery_P + QueryList.SetBalanceAndImageQuery_P + QueryList.GetUserQueryLogin_P);
	    ps.setDouble(1, user.getBalance());
	    ps.setString(2, user.getImgPath());
	    ps.setString(3, user.getEmail());
	    ps.setString(4, user.getPasswordHash());

	} else if (user.getId() != 0) {
	    ps = con.prepareStatement(
		    QueryList.UpdateUserQuery_P + QueryList.SetBannedQuery_P + QueryList.GetUserQueryId_P);
	    ps.setBoolean(1, user.isIs_banned());
	    ps.setInt(2, user.getId());
	}
	return ps;

    }

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
	    throw new DAOException(e);
	} finally {
	    try {
		pool.getBackConnection(ps, con);
	    } catch (ConnectionPoolException e) {
		throw new DAOException(e);
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
	result.setIs_banned(rs.getBoolean(7));
	result.setIs_admin(rs.getBoolean(8));
	result.setDiscountCoefficient(rs.getDouble(9));
	result.setBalance(rs.getDouble(10));
	result.setImgPath(rs.getString(11));

	return result;
    }
}
