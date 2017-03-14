package _java._ee._02._dao;

import java.util.List;

import _java._ee._02._bean.Good;
import _java._ee._02._dao.exception.DAOException;

public interface GoodDAO {
	public boolean addGood(Good good) throws DAOException;
	public boolean deleteGood(Good good) throws DAOException;
	public List<Good> getAllGoods() throws DAOException;
	public Good getGoodWithId(Good good) throws DAOException;
	public List<Good> getGoodWithTitle(Good good) throws DAOException;
	public List<Good> getGoodWithCategory(Good good) throws DAOException;
}
