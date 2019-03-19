package xm.chen.service;

import java.util.List;

import xm.chen.modal.Goods;

public interface GoodService {
	/**
	 * 添加商品信息
	 * 
	 * @param good
	 *            商品信息实体
	 * @return 受影响的行数
	 */
	public int addGood(Goods good);

	/**
	 * 跟新商品信息
	 * 
	 * @param good
	 *            商品实体
	 * @return 受影响的行数
	 */
	public int updateGood(Goods good);

	/**
	 * 根据商品id获取商品信息
	 * 
	 * @param id
	 *            商品id
	 * @return 商品实体
	 */

	public Goods getGoodById(int id);

	/**
	 * 根据条件获取商品信息集合
	 * 
	 * @param Condition
	 *            条件
	 * @return 商品信息集合
	 */
	public List<Goods> getGoodByCondition(String Condition);

	/**
	 * 根据id商品删除商品信息
	 * 
	 * @param id
	 *            商品id
	 * @return 受影响的行数
	 */
	public int deleteGood(int id);

	/**
	 * 对商品信息分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            true(升序)
	 * @return 商品信息集合
	 */
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order);

	/**
	 * 根据key进行查询前n条数据
	 * 
	 * @param key
	 *            键值
	 * @param n
	 *            条数
	 * @return 商品信息集合
	 */
	public List<Goods> getGoodByKey(String key, int n);

	/**
	 * 根据条件查询数据条数
	 * 
	 * @param condition
	 *            条件
	 * @return 条数
	 */
	public int Count(String condition);
}
