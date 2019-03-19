package xm.chen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.OrderDetailDao;
import xm.chen.modal.OrderDetail;

public class OrderDetailDaoimpl implements OrderDetailDao {

	@Override
	public int addOrderDetail(OrderDetail od) {
		String sql = "insert into orderdetail values(?,?,?)";
		Object[] params = { od.getOrderid(), od.getGoodid(), od.getCount() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int updateOrderDetail(OrderDetail od) {
		String sql = "update orderdetail set orderid=?,goodid=?,count=? where id=?";
		Object[] params = { od.getOrderid(), od.getGoodid(), od.getCount(), od.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delOrderDetail(int id) {
		String sql = "delete orderdetail where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public OrderDetail findOrderDetail(int id) {
		String sql = "select * from orderdetail where id=?";
		Object[] params = { id };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		OrderDetail od = new OrderDetail();
		for (Map map2 : map) {
			od.setId((Integer) map2.get("id"));
			od.setOrderid((Integer) map2.get("orderid"));
			od.setGoodid((Integer) map2.get("goodid"));
			od.setCount((Integer) map2.get("count"));
		}
		return od;
	}

	@Override
	public List<OrderDetail> findODByCondition(String condition) {
		String sql = "select * from orderdetail where " + condition;
		Result res = BaseDao.executeQuery(sql, null);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Map[] map = res.getRows();
		for (Map map2 : map) {
			OrderDetail od = new OrderDetail();
			od.setId((Integer) map2.get("id"));
			od.setOrderid((Integer) map2.get("orderid"));
			od.setGoodid((Integer) map2.get("goodid"));
			od.setCount((Integer) map2.get("count"));
			list.add(od);
		}
		return list;
	}

	// public static void main(String[] args) {
	// OrderDetailDao oddao = new OrderDetailDaoimpl();
	// OrderDetail od = oddao.findOrderDetail(10);
	// System.out.println(od.getGoodid());
	// }
}
