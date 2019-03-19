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
		int row = 0;// 定义结果变量
		try {
			BigDecimal totalmoney = null;
			double total = 0.0;
			for (int i = 0; i < count.size(); i++) {
				// 根据id查询商品信息
				Goods g = gdao.getGoodById(carts.get(i).getGoodid());
				total += (g.getPrice().multiply(new BigDecimal(count.get(i)))).doubleValue();
			}
			totalmoney = new BigDecimal(total);
			Orders order = new Orders(0, userid, common.StringtoTime(), totalmoney, OrderStatus.未付款.ordinal());
			row = add(order);
			Orders order1 = getOrdersByConditionPage(1, 1, "uid=" + userid, false).get(0);
			// 将数据添加到订单详情中
			for (int j = 0; j < count.size(); j++) {
				OrderDetail od = new OrderDetail(0, order1.getId(), carts.get(j).getGoodid(), count.get(j));
				oddao.addOrderDetail(od);
			}
			// 将购物车中的数据删除
			for (MyCart cart : carts) {
				cartdao.delcart2(cart.getId());
			}
			// 修改商品表中的数据
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
		// 定义总价
		BigDecimal Total = null;
		// 根据商品id查询商品信息
		Goods g = gdao.getGoodById(gid);
		// 计算订单总计
		Total = new BigDecimal(g.getPrice().doubleValue() * count);
		Orders o = new Orders(0, uid, common.StringtoTime(), Total, OrderStatus.未付款.ordinal());
		row = add(o);
		// 查询最新添加的订单
		Orders order1 = getOrdersByConditionPage(1, 1, "uid=" + uid, false).get(0);
		// 生成订单详情
		OrderDetail od = new OrderDetail(0, order1.getId(), gid, count);
		// 添加订单详情
		oddao.addOrderDetail(od);
		// 商品数量减少
		int lin = g.getCount() - count;
		if (lin >= 0) {
			g.setCount(lin);
			// 商品信息跟新
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
		int row = 0;// 默认是删除失败
		List<OrderDetail> odlist = oddao.findODByCondition("orderid=" + o.getId());
		// 如果商品未付款/已收货/已评价
		if (o.getOrderstatus() == OrderStatus.未付款.ordinal()) {
			for (int i = 0; i < odlist.size(); i++) {
				Goods g = gdao.getGoodById(odlist.get(i).getGoodid());
				int lin = g.getCount() + odlist.get(i).getCount();
			}
			// 将所有的订单详情删除
			for (OrderDetail orderDetail : odlist) {
				oddao.delOrderDetail(orderDetail.getId());
				delorders(orderDetail.getOrderid());
			}
			row = 1;// 代表未付款的订单详情删除
		} else if (o.getOrderstatus() == OrderStatus.已收货.ordinal() || o.getOrderstatus() == OrderStatus.已评价.ordinal()) {
			for (OrderDetail orderDetail : odlist) {
				oddao.delOrderDetail(orderDetail.getId());
				delorders(orderDetail.getOrderid());
			}
			row = 1;// 代表已收货或已评价的订单详情删除
		} else {
			row = 0;// 代表该商品订单信息不能删除
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
		// 根据条件的有无决定不同的sql语句
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
		int i = 0;// 默认值为0
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
