package xm.chen.service;

import java.util.List;

import xm.chen.modal.Images;

public interface ImagesService {
	/**
	 * 添加图片
	 * 
	 * @param img
	 *            图片实体
	 * @return 首影响的行数
	 */
	public int addImg(Images img);

	/**
	 * 根据图片id删除图片
	 * 
	 * @param id
	 *            图片id
	 * @return 受影响的行数
	 */
	public int delImg(int id);

	/**
	 * 根据图片id查询图片信息
	 * 
	 * @param id
	 *            图片id
	 * @return 图片
	 */
	public Images getImgById(int id);

	/**
	 * 根据商品id获取对应图片
	 * 
	 * @param goodsid
	 *            商品id
	 * @return 图片路径
	 */
	public String[] goodsImg(int goodsid);

	/**
	 * 根据商品id查询对应图片的数量
	 * 
	 * @param goodsid
	 *            商品id
	 * @return 首映的行数
	 */
	public int goodImgTypeCount(int goodsid);

	/**
	 * 根据条件查询图片
	 * 
	 * @param Condition
	 *            条件
	 * @return 图片集合
	 */
	public List<Images> getImgByCondition(String Condition);
}
