package _java._ee._02._service;

import java.util.List;

import _java._ee._02._bean.Good;
import _java._ee._02._service.exception.ServiceException;

public interface GoodService {
	public List<Good> getAllGoods() throws ServiceException;
	public List<Good> getGood(Good good) throws ServiceException;
	public List<Good> getGoodWithTitle(Good good) throws ServiceException;
	public List<Good> getGoodWithCategory(Good good) throws ServiceException;
	public List<Good> getGoodWithPrice(Good good) throws ServiceException;
	public Good getGoodWithId(Good good) throws ServiceException;
}
