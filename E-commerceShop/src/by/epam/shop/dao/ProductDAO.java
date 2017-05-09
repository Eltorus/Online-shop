package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.dao.exception.DAOException;
import by.epam.shop.entity.bean.Product;

public interface ProductDAO {
    void addProduct(Product product) throws DAOException;
    void deleteProduct(Product product) throws DAOException;
    List<Product> getAllProducts() throws DAOException;
    Product getProduct(Product product) throws DAOException;
    void updateProduct(Product product) throws DAOException;
    int getTotalProductAmount() throws DAOException;
    List<Product> getProducts(int offset, int limit) throws DAOException;
}
