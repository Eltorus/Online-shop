package _java._ee._02._service.impl;

import java.util.List;

import _java._ee._02._bean.Good;
import _java._ee._02._dao.GoodDAO;
import _java._ee._02._dao.exception.DAOException;
import _java._ee._02._dao.factory.DAOFactory;
import _java._ee._02._service.GoodService;
import _java._ee._02._service.exception.ServiceException;
import _java._ee._02._service.validation.Validation;

public class GoodServiceImpl implements GoodService {

	@Override
	public List<Good> getAllGoods() throws ServiceException {
		GoodDAO goodDAO = DAOFactory.getInstance().getGoodDAO();
		List<Good> goods = null;
		try {
			goods = goodDAO.getAllGoods();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return goods;
	}

	@Override
	public List<Good> getGood(Good good) throws ServiceException {
		List<Good> goods = null;
		if (good == null) {
			throw new ServiceException();
		}
		if (!Validation.isStringEmpty((good.getTitle()))) {
			goods = getGoodWithTitle(good);
		}
		/// to be continued...
		return goods;
	}
	
	@Override
	public Good getGoodWithId(Good good) throws ServiceException {
		GoodDAO goodDAO = DAOFactory.getInstance().getGoodDAO();
		Good result = null;
		if(good == null) {
			throw new ServiceException("Good is null");
		}
		if (good.getId() == 0) {
			throw new ServiceException("Id is null");
		}
		try {
			result = goodDAO.getGoodWithId(good);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<Good> getGoodWithTitle(Good good) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Good> getGoodWithCategory(Good good) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Good> getGoodWithPrice(Good good) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
