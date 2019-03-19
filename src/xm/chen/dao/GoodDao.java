package xm.chen.dao;

import java.util.List;

import xm.chen.modal.Goods;

public interface GoodDao {
	/**
	 * 添加商品
	 * 
	 * @param good
	 *            商品实体
	 * @return 受影响的行数
	 */
	public int addGood(Goods good);

	/**
	 * 跟新商品
	 * 
	 * @param good
	 *            商品实体
	 * @return 受影响的行数
	 */
	public int updateGood(Goods good);

	/**
	 * 根据id得到商品信息
	 * 
	 * @param id
	 *            商品id
	 * @return 返回商品实体
	 */
	public Goods getGoodById(int id);

	/**
	 * 通过条件查询商品信息
	 * 
	 * @param Condition
	 *            条件
	 * @return 返回商品实体
	 */
	public List<Goods> getGoodByCondition(String Condition);

	/**
	 * 根据删除商品信息
	 * 
	 * @param id
	 *            商品id
	 * 
	 * @return 返回受影响的行数
	 */
	public int deleteGood(int id);

	/**
	 * 根据条件进行商品分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            页码
	 * @param condition
	 *            条件
	 * @param order
	 *            是否排序
	 * @return 商品集合
	 */
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order);

	/**
	 * 根据条件查询商品
	 * 
	 * @param condition
	 *            条件
	 * @param n
	 *            商品条数
	 * @return 商品集合
	 */
	public List<Goods> getGoods(String condition, int n);

	/**
	 * 根据条件查询数据条数
	 * 
	 * @param condition
	 *            条件
	 * @return 条数
	 */
	public int Count(String condition);
}
