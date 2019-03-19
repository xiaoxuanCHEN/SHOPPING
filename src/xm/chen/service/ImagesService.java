package xm.chen.service;

import java.util.List;

import xm.chen.modal.Images;

public interface ImagesService {
	/**
	 * ���ͼƬ
	 * 
	 * @param img
	 *            ͼƬʵ��
	 * @return ��Ӱ�������
	 */
	public int addImg(Images img);

	/**
	 * ����ͼƬidɾ��ͼƬ
	 * 
	 * @param id
	 *            ͼƬid
	 * @return ��Ӱ�������
	 */
	public int delImg(int id);

	/**
	 * ����ͼƬid��ѯͼƬ��Ϣ
	 * 
	 * @param id
	 *            ͼƬid
	 * @return ͼƬ
	 */
	public Images getImgById(int id);

	/**
	 * ������Ʒid��ȡ��ӦͼƬ
	 * 
	 * @param goodsid
	 *            ��Ʒid
	 * @return ͼƬ·��
	 */
	public String[] goodsImg(int goodsid);

	/**
	 * ������Ʒid��ѯ��ӦͼƬ������
	 * 
	 * @param goodsid
	 *            ��Ʒid
	 * @return ��ӳ������
	 */
	public int goodImgTypeCount(int goodsid);

	/**
	 * ����������ѯͼƬ
	 * 
	 * @param Condition
	 *            ����
	 * @return ͼƬ����
	 */
	public List<Images> getImgByCondition(String Condition);
}
