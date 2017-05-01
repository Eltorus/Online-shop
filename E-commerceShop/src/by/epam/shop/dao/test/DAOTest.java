package by.epam.shop.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.shop.dao.ProductDAO;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.dao.connectionpool.ConnectionPool;
import by.epam.shop.dao.connectionpool.ConnectionPoolException;
import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.dao.impl.ProductDAOImpl;
import by.epam.shop.dao.impl.UserDAOImpl;
import by.epam.shop.entity.bean.Product;
import by.epam.shop.entity.bean.User;
import by.epam.shop.util.HashTool;
import by.epam.shop.util.UtilException;

public class DAOTest {

    @Before
    public void init() throws ConnectionPoolException {
	ConnectionPool pool = ConnectionPool.getInstance();
	pool.initPool();
    }

    @Test
    public void getProductValidTest() throws DAOException {
	Product product = new Product();
	product.setId(1);

	ProductDAO productDAO = new ProductDAOImpl();
	product = productDAO.getProduct(product);
	assertEquals("Coffee Table", product.getTitle());
	assertEquals(1, product.getCategoryID());
	assertEquals("White coffee Table", product.getDescription());
	assertEquals(20, product.getAmount());
    }
    
    @Test
    public void getUserWithIdValidTest() throws DAOException {
	User user = new User();
	user.setId(1);
	
	UserDAO userDAO = new UserDAOImpl();
	user = userDAO.getUserWithId(user);
	
	assertEquals(1, user.getId());
	assertEquals("Jim", user.getName());
	assertEquals("Moore", user.getSurname());
	assertEquals("jmoor@gmail.com", user.getEmail());
	assertEquals("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae", user.getPasswordHash());
	assertEquals("+375 (29) 312-31-31", user.getPhonenumber());
	assertEquals(false, user.isAdmin());
	assertEquals(false, user.isBanned());
    }
    
    
    @Test
    public void getUserWithEmailValidTest() throws DAOException {
	User user = new User();
	user.setEmail("jmoor@gmail.com");
	
	UserDAO userDAO = new UserDAOImpl();
	user = userDAO.getUserWithEmail(user);

	assertEquals(1, user.getId());
	assertEquals("Jim", user.getName());
	assertEquals("Moore", user.getSurname());
	assertEquals("jmoor@gmail.com", user.getEmail());
	assertEquals("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae", user.getPasswordHash());
	assertEquals("+375 (29) 312-31-31", user.getPhonenumber());
	assertEquals(false, user.isAdmin());
	assertEquals(false, user.isBanned());
    }
    
    @Test
    public void getUserWithLogInfValidTest() throws DAOException, UtilException {
	User user = new User();
	user.setEmail("jmoor@gmail.com");
	user.setPasswordHash(HashTool.hashLine("111"));
	
	UserDAO userDAO = new UserDAOImpl();
	user = userDAO.getUserWithLoginInf(user);

	assertEquals(1, user.getId());
	assertEquals("Jim", user.getName());
	assertEquals("Moore", user.getSurname());
	assertEquals("jmoor@gmail.com", user.getEmail());
	assertEquals("f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae", user.getPasswordHash());
	assertEquals("+375 (29) 312-31-31", user.getPhonenumber());
	assertEquals(false, user.isAdmin());
	assertEquals(false, user.isBanned());
    }
    
    
    @After
    public void destroy() throws ConnectionPoolException {
	ConnectionPool pool = ConnectionPool.getInstance();
	pool.clearConnectionQueue();
    }
}
