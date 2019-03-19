package xm.chen.dao;

import java.util.List;

import xm.chen.modal.MyCart;
import xm.chen.modal.Orders;

public interface OrdersDao {

	public int add(Orders o);

	/**
	 * 从购物车中下订单
	 * 
	 * @param carts
	 *            购物车实体
	 * @param count
	 *            数量
	 * @param userid
	 *            用户id
	 * @return 返回受影响的行数
	 */
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid);

	/**
	 * 直接下单
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

	public int delorders(int id);

	/**
	 * 删除订单
	 * 
	 * @param id
	 *            订单id
	 * @return 受影响的行数
	 */
	public int deleteOrders(Orders o);

	/**
	 * 根据id得到订单实体
	 * 
	 * @param id
	 *            订单id
	 * @return 返回订单实体
	 */
	public Orders getOrders(int id);

	/**
	 * 根据条件查询订单集合
	 * 
	 * @param condition
	 *            条件
	 * @return 返回订单集合
	 */
	public List<Orders> getOrdersByCondition(String condition);

	/**
	 * 根据条件获取数据条数
	 * 
	 * @param condition
	 *            条件
	 * @return 受影响的行数
	 */

	public int getcount(String condition);

	/**
	 * 根据条件查询数据并分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            true为升序
	 * @return 订单集合
	 */
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order);

	/**
	 * 统计每日订单总金额
	 * 
	 * @param star
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public List<Orders> getSumTotal();
}
