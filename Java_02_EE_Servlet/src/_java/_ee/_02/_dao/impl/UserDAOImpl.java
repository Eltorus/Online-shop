package _java._ee._02._dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import _java._ee._02._bean.User;
import _java._ee._02._dao.DBConnector;
import _java._ee._02._dao.QueryList;
import _java._ee._02._dao.UserDAO;
import _java._ee._02._dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
	@Override
	public User getUser(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User result = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.GetUserQuery);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPasswordHash());
			rs = ps.executeQuery();

			while (rs.next()) {
				result = new User();
				result.setId(rs.getInt(1));
				result.setName(rs.getString(2));
				result.setSurname(rs.getString(3));
				result.setEmail(rs.getString(4));
				result.setPhonenumber(rs.getString(5));
				result.setIs_banned(rs.getBoolean(6));
				result.setIs_admin(rs.getBoolean(7));
				result.setDiscountCoefficient(rs.getDouble(8));
				result.setBalance(rs.getDouble(9));
				result.setPasswordHash(rs.getString(10));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}

	@Override
	public boolean addUser(User user) throws DAOException {
		Connection con  = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.AddUserQuery);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPasswordHash());
			ps.setString(5, user.getPhonenumber());
			int t = ps.executeUpdate();
			if(t == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}

	@Override
	public boolean deleteUser(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result=false;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.DeleteUserQuery);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPasswordHash());
			int t = ps.executeUpdate();
			if (t == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}

	@Override
	public boolean rechargeBalance(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.RechargeBalanceQuery);
			ps.setDouble(1, user.getBalance());
			ps.setString(2,user.getEmail());
			ps.setString(3, user.getPasswordHash());
			int t = ps.executeUpdate();
			if(t==1) {
				result = true;
			} else {
				result = false;
			}
		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}

}
