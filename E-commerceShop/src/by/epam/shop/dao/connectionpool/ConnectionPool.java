package by.epam.shop.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private BlockingQueue<Connection> givenAwayConQueue;
    private BlockingQueue<Connection> connectionQueue;

    private static volatile ConnectionPool instance;

    private String url;
    private String password;
    private String login;
    private int poolSize;

    private ConnectionPool() {
	DBResourceManager resourceManager = DBResourceManager.getInstance();
	this.url = resourceManager.getValue(DBParameter.DB_URL);
	System.out.println(url);
	this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
	this.login = resourceManager.getValue(DBParameter.DB_LOGIN);
	try {
	    this.poolSize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
	    System.out.println(poolSize);
	} catch (NumberFormatException e) {
	    poolSize = 10;
	}
    }

    public void initPool() throws ConnectionPoolException {
	try {
	    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
	    connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

	    for (int i = 0; i < poolSize; i++) {
		Connection connection = DriverManager.getConnection(url, login, password);
		System.out.println("Creating some conenction");
		connectionQueue.add(connection);
	    }
	} catch (SQLException e) {
	    throw new ConnectionPoolException(e);
	}
    }

    public Connection takeConnection() throws ConnectionPoolException {
	Connection con = null;
	try {
	    con = connectionQueue.take();
	    givenAwayConQueue.add(con);
	} catch (InterruptedException e) {
	    throw new ConnectionPoolException(e);
	}
	return con;
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
	ConnectionPool localInstance = instance;
	if (localInstance == null) {
	    synchronized (ConnectionPool.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = new ConnectionPool();
		    localInstance = instance;
		}
	    }
	}
	return localInstance;
    }

    public void clearConnectionQueue() throws ConnectionPoolException {
	try {
	    closeConnectionQueue(givenAwayConQueue);
	    closeConnectionQueue(connectionQueue);
	} catch (SQLException e) {
	    throw new ConnectionPoolException(e);
	}
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
	Connection con = null;
	while ((con = queue.poll()) != null) {
	    if (!con.getAutoCommit()) {
		con.commit();
	    }
	    con.close();
	}
    }

    public void getBackConnection(Statement st, Connection con) throws ConnectionPoolException {
	try {
	    if (st != null) {
		st.close();
	    }

	    givenAwayConQueue.remove(con);
	    connectionQueue.add(con);
	    System.out.println("givenAwayConQueue size: " + givenAwayConQueue.size());
	    System.out.println("connectionQueue size: " + connectionQueue.size());
	} catch (SQLException e) {
	    throw new ConnectionPoolException(e);
	}
    }

}
