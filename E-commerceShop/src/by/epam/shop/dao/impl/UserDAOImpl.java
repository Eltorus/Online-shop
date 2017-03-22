package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.shop.bean.User;
import by.epam.shop.dao.DBConnector;
import by.epam.shop.dao.QueryList;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.exception.DAOException;

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
	public void addUser(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.AddUserQuery);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPasswordHash());
			ps.setString(5, user.getPhonenumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
	}

	@Override
	public void deleteUser(User user) throws DAOException {
		// Мы не удаляем данные а присваиваем им поле deleted = true
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.DeleteUserQuery);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPasswordHash());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
	}

	@Override
	public void updateUser(User user) throws DAOException {
		/*Как будет быстрее: получить данные из бд, 
		сравнить их с обьеком для перезаписи и записать поля, 
		которые изменены или сразу перезаписать весь обьект?*/
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.UpdateUserQuery);
			precompileUpdateStatement(ps, user);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
	}
	
	private void precompileUpdateStatement(PreparedStatement ps, User user) throws SQLException {
		ps.setDouble(1, user.getBalance());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPasswordHash());
	}
}
