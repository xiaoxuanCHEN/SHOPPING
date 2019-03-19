package xm.chen.service;

import java.util.List;

import xm.chen.modal.GoodType;

public interface GoodTypeService {
	/**
	 * 添加商品类型信息
	 * 
	 * @param gt
	 *            商品类型实体
	 * @return 受影响的行数
	 */
	public int addGoodType(GoodType gt);

	/**
	 * 编辑商品类型信息
	 * 
	 * @param gt
	 *            商品类型实体
	 * @return 受影响的行数
	 */
	public int editGoodType(GoodType gt);

	/**
	 * 根据id删除商品类型信息
	 * 
	 * @param id
	 *            商品类型id
	 * @return 受影响的行数
	 */
	public int delGoodType(int id);

	/**
	 * 根据条件查询商品类型集合
	 * 
	 * @param Condition
	 *            条件
	 * @return 商品类型集合
	 */
	public List<GoodType> getGoodType(String Condition);

	/**
	 * 根据商品类型id查询商品类型信息
	 * 
	 * @param id
	 *            商品类型id
	 * @return 商品类型信息
	 */
	public GoodType getGoodTypeById(int id);

	/**
	 * 根据条件查询数据条数
	 * 
	 * @param condition
	 *            条件
	 * @return 数量
	 */
	public int getcount(String condition);
}
