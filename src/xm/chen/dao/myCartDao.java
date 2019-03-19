package xm.chen.dao;

import java.util.List;

import xm.chen.modal.MyCart;

public interface myCartDao {
	/**
	 * 将商品添加进购物车
	 * 
	 * @param cart
	 *            购物车实体
	 * @return 添加的行数
	 */
	public int addcart(MyCart cart);

	/**
	 * 跟新购物车
	 * 
	 * @param cart
	 *            购物车实体
	 * @return 添加行数
	 */
	public int updatecart(MyCart cart);

	/**
	 * 根据商品id删除购物车中的商品
	 * 
	 * @param gid
	 *            商品id
	 * @return 删除的行数
	 */
	public int delcart(int gid);

	/**
	 * 根据用户id删除购物车
	 * 
	 * @param uid
	 *            用户id
	 * @return 受影响的行数
	 */
	public int delcart1(int uid);
	
	/**
	 * 根据id删除购物车中对应商品信息
	 * 
	 * @param id
	 *            购物车id
	 * @return 受影响的行数
	 */
	public int delcart2(int id);

	/**
	 * 根据uid查询该用户购物车中的所有商品信息
	 * 
	 * @param uid
	 *            用户id
	 * @return 返回信息集合
	 */
	public List<MyCart> getAllCart(int uid);

	/**
	 * 根据购物车id获取购物车信息
	 * 
	 * @param id
	 *            购物车id
	 * @return
	 */
	public MyCart getCart(int id);

	/**
	 * 根据条件查询购物车中的商品信息
	 * 
	 * @param condition
	 *            条件
	 * @return 返回信息集合
	 */
	public List<MyCart> getAllCartByCondtion(String condition);

	/**
	 * 将查询出来的商品信息分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            是否排序true为排序
	 * @return 返回信息集合
	 */
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order);
}
