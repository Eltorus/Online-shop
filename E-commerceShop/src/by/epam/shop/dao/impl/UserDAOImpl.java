package by.epam.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			ps = prepareGetUserStatement(ps, con, user);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = fillUpUser(rs);
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return result;
	}
	
	private PreparedStatement prepareGetUserStatement(PreparedStatement ps, Connection con, User user) throws SQLException {
		if(user.getEmail() != null && user.getPasswordHash()!=null) {
			if(!user.getEmail().isEmpty() && !user.getPasswordHash().isEmpty()) {
				ps = con.prepareStatement(QueryList.GetUserQuery+QueryList.GetUserQueryLogin);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswordHash());
			}
		} else if(user.getId()!=0) {
			ps = con.prepareStatement(QueryList.GetUserQuery+QueryList.GetUserQueryId);
			ps.setInt(1, user.getId());
		}
		return ps;
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

	@Override
	public List<User> getAllUsers() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			con = DBConnector.getConnection();
			ps = con.prepareStatement(QueryList.GetAllUsersQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = fillUpUser(rs);
				userList.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBConnector.closeConnection(ps, con);
		}
		return userList;
	}
	
	private User fillUpUser(ResultSet rs) throws SQLException {
		User result = new User();
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
		return result;
	}
}
