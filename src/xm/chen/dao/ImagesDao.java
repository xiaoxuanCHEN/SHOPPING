package xm.chen.dao;

import java.util.List;

import xm.chen.modal.Images;

public interface ImagesDao {
	/**
	 * 添加图片
	 * 
	 * @param img
	 *            图片实体
	 * @return 受影响行数 0(表示主图超过一张)1表示附图超过7张,2表示颜色图片超过2张
	 */
	public int addImg(Images img);

	/**
	 * 根据商品id获取图片路径数组
	 * 
	 * @param goodsid
	 *            商品id
	 * @return 图片路径数组
	 */
	public String[] goodsImg(int goodsid);

	/**
	 * 根据id查询图片详情
	 * 
	 * @param id
	 *            图片id
	 * @return 单个图片实体
	 */
	public Images getImgById(int id);

	/**
	 * 根据id删除图片
	 * 
	 * @param id
	 *            图片id
	 * @return 受影响的行数
	 */

	public int delImg(int id);

	/**
	 * 根据图片类型计算图片数量
	 * 
	 * @param goodsid
	 * @param type
	 * @return
	 */
	public int goodImgTypeCount(int goodsid);

	/**
	 * 根据条件查询图片集合
	 * 
	 * @param Condition
	 *            条件
	 * @return 图片集合
	 */
	public List<Images> getImgByCondition(String Condition);
}
