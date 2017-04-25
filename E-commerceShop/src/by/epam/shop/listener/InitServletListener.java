package by.epam.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;

public class InitServletListener implements ServletContextListener{
    private static final Logger log = Logger.getLogger(InitServletListener.class);
    
    public void contextInitialized(ServletContextEvent sce) {
	try {
	    ConnectionPool pool = ConnectionPool.getInstance();
	    pool.initPool();
	} catch (ConnectionPoolException e) {
	    log.error(e);
	}
    }
    
    public void contextDestroyed(ServletContextEvent sce) {
	try {
	    ConnectionPool pool = ConnectionPool.getInstance();
	    pool.clearConnectionQueue();
	} catch (ConnectionPoolException e) {
	    log.error(e);
	}
    }
}
