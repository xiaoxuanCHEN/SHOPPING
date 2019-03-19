package xm.chen.service;

import java.util.List;

import xm.chen.modal.MyCart;
import xm.chen.modal.Orders;

public interface OrderService {
	/**
	 * 添加订单
	 * 
	 * @param o
	 *            订单实体
	 * @return 受影响的行数
	 */
	public int add(Orders o);

	/**
	 * 从购物车中购买商品，添加订单
	 * 
	 * @param carts
	 *            购物车集合
	 * @param count
	 *            数量集合
	 * @param userid
	 *            用户id
	 * @return 受影响的行数
	 */
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid);

	/**
	 * 从商品详情直接购买商品，生成订单
	 * 
	 * @param gid
	 *            商品id
	 * @param count
	 *            数量
	 * @param uid
	 *            用户id
	 * @return 受影响的行数
	 */
	public int addOrders1(int gid, int count, int uid);

	/**
	 * 跟新订单
	 * 
	 * @param o
	 *            订单实体
	 * @return 受影响的行数
	 */
	public int updateOrders(Orders o);

	/**
	 * 删除订单
	 * 
	 * @param o
	 *            订单实体
	 * @return 受影响的行数
	 */
	public int deleteOrders(Orders o);

	/**
	 * 根据订单id获取订单信息
	 * 
	 * @param id
	 *            订单id
	 * @return 受影响的行数
	 */
	public Orders getOrders(int id);

	/**
	 * 根据条件查询订单数量
	 * 
	 * @param condition
	 *            条件
	 * @return 受影响的行数
	 */
	public int getcount(String condition);

	/**
	 * 根据条件获取订单集合
	 * 
	 * @param condition
	 *            条件
	 * @return 订单集合
	 */
	public List<Orders> getOrdersByCondition(String condition);

	/**
	 * 根据条件进行分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            true(升序)
	 * @return 订单集合
	 */
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order);

	/**
	 * 计算每日订单总金额
	 * 
	 * @param star
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public List<Orders> getSumTotal();
}
