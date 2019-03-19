package xm.chen.service;

import java.util.List;

import xm.chen.modal.OrderDetail;

public interface OrderDetailService {
	/**
	 * 添加订单详情
	 * 
	 * @param od
	 *            订单详情实体
	 * @return 受影响的行数
	 */
	public int addOrderDetail(OrderDetail od);

	/**
	 * 跟新订单详情
	 * 
	 * @param od
	 *            订单详情实体
	 * @return 受影响的行数
	 */
	public int updateOrderDetail(OrderDetail od);

	/**
	 * 根据订单详情id删除订单详情
	 * 
	 * @param id
	 *            订单详情id
	 * @return 受影响的行数
	 */
	public int delOrderDetail(int id);

	/**
	 * 根据订单详情id查询单个订单详情
	 * 
	 * @param id
	 *            订单详情id
	 * @return 单个订单详情实体
	 */
	public OrderDetail findOrderDetail(int id);

	/**
	 * 根据添加查询订单详情
	 * 
	 * @param condition
	 *            条件
	 * @return 订单详情集合
	 */
	public List<OrderDetail> findODByCondition(String condition);
}
