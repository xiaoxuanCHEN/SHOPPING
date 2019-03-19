package xm.chen.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.GoodDao;
import xm.chen.modal.Goods;

public class GoodDaoimpl implements GoodDao {

	@Override
	public int addGood(Goods good) {
		String sql = "insert into goods values(?,?,?,?,?,?,?,?)";
		Object[] params = { good.getTypeid(), good.getGoodName(), good.getPrice(), good.getConfig(),
				good.getProductDate(), good.getCount(), good.getIntroduction(), good.getDescription() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int updateGood(Goods good) {
		String sql = "update goods set typeid=?,goodname=?,price=?,config=?,productdate=?,count=?,introduction=?,description=? where id=?";
		Object[] params = { good.getTypeid(), good.getGoodName(), good.getPrice(), good.getConfig(),
				good.getProductDate(), good.getCount(), good.getIntroduction(), good.getDescription(), good.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public Goods getGoodById(int id) {
		String sql = "select * from goods where id=?";
		Object[] params = { id };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] maps = res.getRows();
		Goods g = new Goods();
		for (Map map : maps) {
			g.setId((Integer) map.get("id"));
			g.setTypeid((Integer) map.get("typeid"));
			g.setGoodName(map.get("goodName").toString());
			g.setConfig(map.get("config").toString());
			g.setPrice((BigDecimal) map.get("price"));
			g.setCount((Integer) map.get("count"));
			g.setProductDate(map.get("productDate").toString());
			g.setIntroduction(map.get("introduction").toString());
			g.setDescription(map.get("description").toString());
		}
		return g;
	}

	@Override
	public List<Goods> getGoodByCondition(String Condition) {
		String sql = "select * from goods where " + Condition;
		Result res = BaseDao.executeQuery(sql, null);
		Map[] maps = res.getRows();
		List<Goods> list = new ArrayList<Goods>();
		for (Map map : maps) {
			Goods g = new Goods();
			g.setId((Integer) map.get("id"));
			g.setTypeid((Integer) map.get("typeid"));
			g.setGoodName(map.get("goodName").toString());
			g.setConfig(map.get("config").toString());
			g.setPrice((BigDecimal) map.get("price"));
			g.setCount((Integer) map.get("count"));
			g.setProductDate(map.get("productDate").toString());
			g.setIntroduction(map.get("introduction").toString());
			g.setDescription(map.get("description").toString());
			list.add(g);
		}
		return list;
	}

	@Override
	public int deleteGood(int id) {
		String sql = "delete goods where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order) {
		int x = (page - 1) * pagesize;
		String sql = "";
		// 根据条件的有无决定不同的sql语句
		if (condition != null && condition.length() > 0) {
			if (!order) {
				sql = "select top " + pagesize + " * from  goods  where " + condition + " and id not in(select top " + x
						+ " id from goods where " + condition + "  order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from goods where  " + condition + " and id not in(select top " + x
						+ " id from goods where " + condition + "  )";
			}
		} else {
			if (!order) {
				sql = "select top " + pagesize + " * from goods where id not in(select top " + x
						+ " id from goods order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from goods  where id not in(select top " + x + " id from goods )";
			}
		}

		List<Goods> list = new ArrayList<Goods>();
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		for (Map map2 : map) {
			Goods g = new Goods();
			g.setId((Integer) map2.get("id"));
			g.setTypeid((Integer) map2.get("typeid"));
			g.setGoodName(map2.get("goodName").toString());
			g.setConfig(map2.get("config").toString());
			g.setPrice((BigDecimal) map2.get("price"));
			g.setCount((Integer) map2.get("count"));
			g.setProductDate(map2.get("productDate").toString());
			g.setIntroduction(map2.get("introduction").toString());
			g.setDescription(map2.get("description").toString());
			list.add(g);
		}
		return list;
	}

	@Override
	public List<Goods> getGoods(String condition, int n) {
		String sql = "select * from goods";
		if (n != 0) {
			sql = "select top " + n + " * from goods";
		}
		if (condition != null && !condition.isEmpty()) {
			sql += " " + condition;
		}
		Result res = BaseDao.executeQuery(sql, null);
		Map[] maps = res.getRows();
		List<Goods> list = new ArrayList<Goods>();
		for (Map map : maps) {
			Goods g = new Goods();
			g.setId((Integer) map.get("id"));
			g.setTypeid((Integer) map.get("typeid"));
			g.setGoodName(map.get("goodName").toString());
			g.setConfig(map.get("config").toString());
			g.setPrice((BigDecimal) map.get("price"));
			g.setCount((Integer) map.get("count"));
			g.setProductDate(map.get("productDate").toString());
			g.setIntroduction(map.get("introduction").toString());
			g.setDescription(map.get("description").toString());
			list.add(g);
		}
		return list;
	}

	@Override
	public int Count(String condition) {
		int i = 0;// 默认值为0
		String sql = "select count(*) row from Goods";
		if (!condition.isEmpty())
			sql += "where " + condition;
		Result rs = BaseDao.executeQuery(sql, null);
		Map[] maps = rs.getRows();
		if (maps != null && maps.length > 0)
			i = (Integer) maps[0].get("row");
		return i;
	}

	// public static void main(String[] args) {
	// GoodDao gdao = new GoodDaoimpl();
	// Goods good = new Goods(0, 1, "小米6", new BigDecimal(6500.00),
	// "3G+32G/4G+64G", "2017-6-6", 200, "最新的小米",
	// "全面屏手机");
	// int row = gdao.addGood(good);
	// // System.out.println(row.getId()+" "+row.getGoodName()+"
	// // "+row.getConfig());
	// // int row = gdao.deleteGood(1);
	// System.out.println(row);
	// Goods row=gdao.getGoodById("2");
	// System.out.println(row.getGoodName()+" "+row.getConfig());
	// List<Goods> list = gdao.getGoods("where goodname like '%6%'", 2);
	// for (Goods goods : list) {
	// System.out.println(goods.getGoodName());
	// int row=gdao.Count("");
	// System.out.println(row);
	// }
	// }

}
