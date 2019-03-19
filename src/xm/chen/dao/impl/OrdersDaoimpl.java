package xm.chen.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.GoodDao;
import xm.chen.dao.OrderDetailDao;
import xm.chen.dao.OrdersDao;
import xm.chen.dao.common;
import xm.chen.dao.myCartDao;
import xm.chen.modal.Goods;
import xm.chen.modal.MyCart;
import xm.chen.modal.OrderDetail;
import xm.chen.modal.OrderStatus;
import xm.chen.modal.Orders;

public class OrdersDaoimpl implements OrdersDao {
	GoodDao gdao = null;
	OrderDetailDao oddao = null;
	myCartDao cartdao = null;

	public OrdersDaoimpl() {
		gdao = new GoodDaoimpl();
		oddao = new OrderDetailDaoimpl();
		cartdao = new myCartDaoimpl();
	}

	@Override
	public int add(Orders o) {
		String sql = "insert into orders values(?,?,?,?)";
		Object[] params = { o.getUid(), common.StringtoTime(), o.getTotalmoney(), o.getOrderstatus() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid) {
		int row = 0;// ����������
		try {
			BigDecimal totalmoney = null;
			double total = 0.0;
			for (int i = 0; i < count.size(); i++) {
				// ����id��ѯ��Ʒ��Ϣ
				Goods g = gdao.getGoodById(carts.get(i).getGoodid());
				total += (g.getPrice().multiply(new BigDecimal(count.get(i)))).doubleValue();
			}
			totalmoney = new BigDecimal(total);
			Orders order = new Orders(0, userid, common.StringtoTime(), totalmoney, OrderStatus.δ����.ordinal());
			row = add(order);
			Orders order1 = getOrdersByConditionPage(1, 1, "uid=" + userid, false).get(0);
			// ��������ӵ�����������
			for (int j = 0; j < count.size(); j++) {
				OrderDetail od = new OrderDetail(0, order1.getId(), carts.get(j).getGoodid(), count.get(j));
				oddao.addOrderDetail(od);
			}
			// �����ﳵ�е�����ɾ��
			for (MyCart cart : carts) {
				cartdao.delcart2(cart.getId());
			}
			// �޸���Ʒ���е�����
			for (int k = 0; k < count.size(); k++) {
				Goods g = gdao.getGoodById(carts.get(k).getGoodid());
				int lin = g.getCount() - count.get(k);
				g.setCount(lin);
				row = gdao.updateGood(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int addOrders1(int gid, int count, int uid) {
		int row = 0;
		// �����ܼ�
		BigDecimal Total = null;
		// ������Ʒid��ѯ��Ʒ��Ϣ
		Goods g = gdao.getGoodById(gid);
		// ���㶩���ܼ�
		Total = new BigDecimal(g.getPrice().doubleValue() * count);
		Orders o = new Orders(0, uid, common.StringtoTime(), Total, OrderStatus.δ����.ordinal());
		row = add(o);
		// ��ѯ������ӵĶ���
		Orders order1 = getOrdersByConditionPage(1, 1, "uid=" + uid, false).get(0);
		// ���ɶ�������
		OrderDetail od = new OrderDetail(0, order1.getId(), gid, count);
		// ��Ӷ�������
		oddao.addOrderDetail(od);
		// ��Ʒ��������
		int lin = g.getCount() - count;
		if (lin >= 0) {
			g.setCount(lin);
			// ��Ʒ��Ϣ����
			gdao.updateGood(g);
		} else {
			row = 0;
		}
		return row;
	}

	@Override
	public int updateOrders(Orders o) {
		String sql = "update orders set uid=?,orderTime=?,totalmoney=?,orderstatus=? where id=?";
		Object[] params = { o.getUid(), o.getOrderTime(), o.getTotalmoney(), o.getOrderstatus(), o.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delorders(int id) {
		String sql = "delete orders where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int deleteOrders(Orders o) {
		int row = 0;// Ĭ����ɾ��ʧ��
		List<OrderDetail> odlist = oddao.findODByCondition("orderid=" + o.getId());
		// �����Ʒδ����/���ջ�/������
		if (o.getOrderstatus() == OrderStatus.δ����.ordinal()) {
			for (int i = 0; i < odlist.size(); i++) {
				Goods g = gdao.getGoodById(odlist.get(i).getGoodid());
				int lin = g.getCount() + odlist.get(i).getCount();
			}
			// �����еĶ�������ɾ��
			for (OrderDetail orderDetail : odlist) {
				oddao.delOrderDetail(orderDetail.getId());
				delorders(orderDetail.getOrderid());
			}
			row = 1;// ����δ����Ķ�������ɾ��
		} else if (o.getOrderstatus() == OrderStatus.���ջ�.ordinal() || o.getOrderstatus() == OrderStatus.������.ordinal()) {
			for (OrderDetail orderDetail : odlist) {
				oddao.delOrderDetail(orderDetail.getId());
				delorders(orderDetail.getOrderid());
			}
			row = 1;// �������ջ��������۵Ķ�������ɾ��
		} else {
			row = 0;// �������Ʒ������Ϣ����ɾ��
		}
		return row;
	}

	@Override
	public Orders getOrders(int id) {
		String sql = "select * from orders where id=?";
		Object[] params = { id };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		Orders o = new Orders();
		if (map.length > 0) {
			for (Map map2 : map) {
				o.setId((Integer) map2.get("id"));
				o.setUid((Integer) map2.get("uid"));
				o.setOrderTime((Timestamp) map2.get("orderTime"));
				o.setTotalmoney((BigDecimal) map2.get("totalmoney"));
				o.setOrderstatus((Integer) map2.get("orderstatus"));
			}
		}
		return o;
	}

	@Override
	public List<Orders> getOrdersByCondition(String condition) {
		String sql = "select * from orders where " + condition;
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		List<Orders> list = new ArrayList<Orders>();
		if (map.length > 0) {
			for (Map map2 : map) {
				Orders o = new Orders();
				o.setId((Integer) map2.get("id"));
				o.setUid((Integer) map2.get("uid"));
				o.setOrderTime((Timestamp) map2.get("orderTime"));
				o.setTotalmoney((BigDecimal) map2.get("totalmoney"));
				o.setOrderstatus((Integer) map2.get("orderstatus"));
				list.add(o);
			}
		}
		return list;
	}

	@Override
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order) {
		int x = (page - 1) * pagesize;
		String sql = "";
		// �������������޾�����ͬ��sql���
		if (condition != null && condition.length() > 0) {
			if (!order) {
				sql = "select top " + pagesize + " * from  orders  where " + condition + " and id not in(select top "
						+ x + " id from orders where " + condition + "  order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from orders where  " + condition + " and id not in(select top " + x
						+ " id from orders where " + condition + "  )";
			}
		} else {
			if (!order) {
				sql = "select top " + pagesize + " * from orders where id not in(select top " + x
						+ " id from orders order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from orders  where id not in(select top " + x
						+ " id from orders )";
			}
		}
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		List<Orders> list = new ArrayList<Orders>();
		if (map.length > 0) {
			for (Map map2 : map) {
				Orders o = new Orders();
				o.setId((Integer) map2.get("id"));
				o.setUid((Integer) map2.get("uid"));
				o.setOrderTime((Timestamp) map2.get("orderTime"));
				o.setTotalmoney((BigDecimal) map2.get("totalmoney"));
				o.setOrderstatus((Integer) map2.get("orderstatus"));
				list.add(o);
			}
		}
		return list;
	}

	@Override
	public int getcount(String condition) {
		int i = 0;// Ĭ��ֵΪ0
		String sql = "select count(*) row from orders";
		if (!condition.isEmpty())
			sql = " " + condition;
		Result rs = BaseDao.executeQuery(sql, null);
		Map[] maps = rs.getRows();
		if (maps != null && maps.length > 0)
			i = (Integer) maps[0].get("row");
		return i;
	}

	public static void main(String[] args) {
		OrdersDao o = new OrdersDaoimpl();
		// int row = o.addOrders1(5, 1, 1);
		// System.out.println(row);
		// Orders os = o.getOrders(3);
		// Orders order = new Orders(os.getId(), os.getUid(), os.getOrderTime(),
		// os.getTotalmoney(), os.getOrderstatus());
		// int row = o.deleteOrders(order);
		// int row = o.getcount("");
		List<Orders> list = o.getSumTotal();
		for (Orders orders : list) {
			System.out.println(orders.getZje() + " " + orders.getTime());
		}

	}

	@Override
	public List<Orders> getSumTotal() {		
		String sql = "select convert(char(11),ordertime,120) dt, sum(totalmoney) zje"
				+ " from orders where ordertime between '2017-12-01' and '2018-02-01'"
				+ " group by convert(char(11),ordertime,120)";
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		List<Orders> list = new ArrayList<Orders>();
		if (map.length > 0) {
			for (Map map2 : map) {
				Orders o = new Orders();
				o.setZje((BigDecimal)map2.get("zje"));
				o.setTime(map2.get("dt").toString());
				list.add(o);
			}
		}
		return list;
	}

}
