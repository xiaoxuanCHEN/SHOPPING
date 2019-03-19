package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.OrderDetailDao;
import xm.chen.dao.impl.OrderDetailDaoimpl;
import xm.chen.modal.OrderDetail;
import xm.chen.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	OrderDetailDao oddao = null;

	public OrderDetailServiceImpl() {
		oddao = new OrderDetailDaoimpl();
	}

	@Override
	public int addOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrderDetail(OrderDetail od) {
		return oddao.updateOrderDetail(od);
	}

	@Override
	public int delOrderDetail(int id) {
		return oddao.delOrderDetail(id);
	}

	@Override
	public OrderDetail findOrderDetail(int id) {
		OrderDetail od = oddao.findOrderDetail(id);
		return od;
	}

	@Override
	public List<OrderDetail> findODByCondition(String condition) {
		List<OrderDetail> list = oddao.findODByCondition(condition);
		return list;
	}

}
