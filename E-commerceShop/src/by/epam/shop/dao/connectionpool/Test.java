package by.epam.shop.dao.connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
	String query = "select * from clients where c_id = 1;";

	try {

	    ConnectionPool instance = ConnectionPool.getInstance();
	    Connection con = instance.takeConnection();
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    System.out.println("here");
	    while (rs.next()) {
		System.out.println(rs.getString(4));
	    }

	    instance.getBackConnection(st, con);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ConnectionPoolException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
