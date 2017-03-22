package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.shop.dao.exception.DAOException;

public class DBConnector {
	private final static String url = "jdbc:mysql://127.0.0.1:3306/online_shop?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
	private final static String login = "root";
	private final static String password = "";
	private static Connection connection = null;

	private DBConnector() {

	}

	public static Connection getConnection() throws DAOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		}
		try {
			connection = DriverManager.getConnection(url, login, password);
			if (connection == null) {
				connection = DriverManager.getConnection(url, login, password);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return connection;
	}
	
	public static void closeConnection(PreparedStatement ps, Connection con) throws DAOException {
		if (con != null) {
			try {
				if (ps != null) {
					ps.close();
				}
				con.close();
			} catch (SQLException e) {
				throw new DAOException("Could not close connection", e);
			}
		}
	}

}
