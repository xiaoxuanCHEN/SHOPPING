package xm.chen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.myCartDao;
import xm.chen.modal.MyCart;

public class myCartDaoimpl implements myCartDao {

	@Override
	public int addcart(MyCart cart) {
		String sql = "insert into mycart values(?,?,?)";
		Object[] params = { cart.getUid(), cart.getGoodid(), cart.getCount() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int updatecart(MyCart cart) {
		String sql = "update mycart set uid=?,goodid=?,count=? where id=?";
		Object[] params = { cart.getUid(), cart.getGoodid(), cart.getCount(), cart.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delcart(int gid) {
		String sql = "delete mycart where goodid=?";
		Object[] params = { gid };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public List<MyCart> getAllCart(int uid) {
		String sql = "select * from mycart where uid=?";
		Object[] params = { uid };
		List<MyCart> list = new ArrayList<MyCart>();
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		for (Map map2 : map) {
			MyCart c = new MyCart();
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setGoodid((Integer) map2.get("goodid"));
			c.setCount((Integer) map2.get("count"));
			list.add(c);
		}
		return list;
	}

	@Override
	public List<MyCart> getAllCartByCondtion(String condition) {
		String sql = "select * from mycart where " + condition;
		List<MyCart> list = new ArrayList<MyCart>();
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		for (Map map2 : map) {
			MyCart c = new MyCart();
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setGoodid((Integer) map2.get("goodid"));
			c.setCount((Integer) map2.get("count"));
			list.add(c);
		}
		return list;
	}

	@Override
	public MyCart getCart(int id) {
		String sql = "select * from mycart where id='" + id + "'";
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		MyCart c = new MyCart();
		for (Map map2 : map) {
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setGoodid((Integer) map2.get("goodid"));
			c.setCount((Integer) map2.get("count"));
		}
		return c;
	}

	@Override
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order) {
		int x = (page - 1) * pagesize;
		String sql = "";
		// 根据条件的有无决定不同的sql语句
		if (condition != null && condition.length() > 0) {
			if (!order) {
				sql = "select top " + pagesize + " * from  mycart  where " + condition + " and id not in(select top "
						+ x + " id from mycart where " + condition + "  order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from mycart where  " + condition + " and id not in(select top " + x
						+ " id from mycart where " + condition + "  )";
			}
		} else {
			if (!order) {
				sql = "select top " + pagesize + " * from mycart where id not in(select top " + x
						+ " id from mycart order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from mycart  where id not in(select top " + x
						+ " id from mycart )";
			}
		}
		List<MyCart> list = new ArrayList<MyCart>();
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		for (Map map2 : map) {
			MyCart c = new MyCart();
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setGoodid((Integer) map2.get("goodid"));
			c.setCount((Integer) map2.get("count"));
			list.add(c);
		}
		return list;
	}

	@Override
	public int delcart1(int uid) {
		String sql = "delete mycart where uid=?";
		Object[] params = { uid };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delcart2(int id) {
		String sql = "delete mycart where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	public static void main(String[] args) {
		myCartDao cart = new myCartDaoimpl();
		List<MyCart> list = cart.getAllCartByCondtion("1=1");
		for (MyCart myCart : list) {
			System.out.println(myCart.getGoodid());
		}
		// int row = cart.delcart(5);
		// System.out.println(row);
	}

}
