package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.OrdersDao;
import xm.chen.dao.impl.OrdersDaoimpl;
import xm.chen.modal.MyCart;
import xm.chen.modal.Orders;
import xm.chen.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrdersDao odao = null;

	public OrderServiceImpl() {
		odao = new OrdersDaoimpl();
	}

	@Override
	public int add(Orders o) {
		int row = odao.add(o);
		return row;
	}

	@Override
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid) {
		int row = odao.addOrders(carts, count, userid);
		return row;
	}

	@Override
	public int addOrders1(int gid, int count, int uid) {
		int row = odao.addOrders1(gid, count, uid);
		return row;
	}

	@Override
	public int updateOrders(Orders o) {
		int row = odao.updateOrders(o);
		return row;
	}

	@Override
	public int deleteOrders(Orders o) {
		int row = odao.deleteOrders(o);
		return row;
	}

	@Override
	public Orders getOrders(int id) {
		Orders o = odao.getOrders(id);
		return o;
	}

	@Override
	public List<Orders> getOrdersByCondition(String condition) {
		List<Orders> list = odao.getOrdersByCondition(condition);
		return list;
	}

	@Override
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order) {
		List<Orders> list = odao.getOrdersByConditionPage(pagesize, page, condition, order);
		return list;
	}

	@Override
	public int getcount(String condition) {
		int row = odao.getcount(condition);
		return row;
	}

	@Override
	public List<Orders> getSumTotal() {
		List<Orders> list = odao.getSumTotal();
		return list;
	}

}
