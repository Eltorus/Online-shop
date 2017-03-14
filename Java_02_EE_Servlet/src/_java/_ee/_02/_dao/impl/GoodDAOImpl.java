package _java._ee._02._dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import _java._ee._02._bean.Good;
import _java._ee._02._dao.DBConnector;
import _java._ee._02._dao.GoodDAO;
import _java._ee._02._dao.QueryList;
import _java._ee._02._dao.exception.DAOException;

public class GoodDAOImpl implements GoodDAO {

	@Override
	public boolean addGood(Good good) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteGood(Good good) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Good> getAllGoods() throws DAOException {
		List<Good> goods = null;
		Good good = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			goods = new ArrayList<Good>();
			con = DBConnector.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(QueryList.GetAllGoodsQuery);
			while (rs.next()) {
				good = fillUpGood(rs);
				goods.add(good);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null) {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return goods;
	}

	@Override
	public Good getGoodWithId(Good good) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		Good result = null;
		ResultSet rs = null;
		try {
			String query = QueryList.GetGoodQuery + QueryList.GetGoodWithIdQuery;
			con = DBConnector.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, good.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = fillUpGood(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null) {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return result;
	}

	@Override
	public List<Good> getGoodWithTitle(Good good) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Good> getGoodWithCategory(Good good) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Good fillUpGood(ResultSet rs) throws SQLException{
		Good good = new Good();
		good.setId(rs.getInt("g_id"));
		good.setTitle(rs.getString("g_title"));
		good.setCategory(rs.getString("category"));
		good.setPrice(rs.getDouble("g_price"));
		good.setDescription(rs.getString("g_description"));
		good.setAmount(rs.getInt("g_amount"));
		return good;
	}

}
