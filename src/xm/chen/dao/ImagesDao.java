package xm.chen.dao;

import java.util.List;

import xm.chen.modal.Images;

public interface ImagesDao {
	/**
	 * ���ͼƬ
	 * 
	 * @param img
	 *            ͼƬʵ��
	 * @return ��Ӱ������ 0(��ʾ��ͼ����һ��)1��ʾ��ͼ����7��,2��ʾ��ɫͼƬ����2��
	 */
	public int addImg(Images img);

	/**
	 * ������Ʒid��ȡͼƬ·������
	 * 
	 * @param goodsid
	 *            ��Ʒid
	 * @return ͼƬ·������
	 */
	public String[] goodsImg(int goodsid);

	/**
	 * ����id��ѯͼƬ����
	 * 
	 * @param id
	 *            ͼƬid
	 * @return ����ͼƬʵ��
	 */
	public Images getImgById(int id);

	/**
	 * ����idɾ��ͼƬ
	 * 
	 * @param id
	 *            ͼƬid
	 * @return ��Ӱ�������
	 */

	public int delImg(int id);

	/**
	 * ����ͼƬ���ͼ���ͼƬ����
	 * 
	 * @param goodsid
	 * @param type
	 * @return
	 */
	public int goodImgTypeCount(int goodsid);

	/**
	 * ����������ѯͼƬ����
	 * 
	 * @param Condition
	 *            ����
	 * @return ͼƬ����
	 */
	public List<Images> getImgByCondition(String Condition);
}
