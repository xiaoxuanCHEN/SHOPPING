package xm.chen.service;

import java.util.List;

import xm.chen.modal.MyCart;

public interface mycartService {
	/**
	 * 添加购物车
	 * 
	 * @param cart
	 *            购物车实体
	 * @return 受影响的行数
	 */
	public int addcart(MyCart cart);

	/**
	 * 跟新购物车
	 * 
	 * @param cart
	 *            购物车实体
	 * @return 受影响的行数
	 */
	public int updatecart(MyCart cart);

	/**
	 * 根据商品id删除购物车中的数据
	 * 
	 * @param gid
	 *            商品id
	 * @return 受影响的行数
	 */
	public int delcart(int gid);

	/**
	 * 根据用户id删除购车中的所有信息
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
	 * 根据用户id获取对应的购物车所有商品信息
	 * 
	 * @param uid
	 *            用户id
	 * @return 购物车中商品集合
	 */
	public List<MyCart> getAllCart(int uid);

	/**
	 * 根据id查询购物车信息
	 * 
	 * @param id
	 * @return
	 */
	public MyCart getCart(int id);

	/**
	 * 根据条件获取购物车中商品的信息
	 * 
	 * @param condition
	 *            条件
	 * @return 购物车中商品信息集合
	 */
	public List<MyCart> getAllCartByCondtion(String condition);

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
	 * @return 购物车中商品信息集合
	 */
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order);
}
