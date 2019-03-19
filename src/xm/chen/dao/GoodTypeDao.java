package xm.chen.dao;

import java.util.List;

import xm.chen.modal.GoodType;

public interface GoodTypeDao {
	/**
	 * �����Ʒ����
	 * 
	 * @param gt
	 *            ��Ʒ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int addGoodType(GoodType gt);

	/**
	 * �༭��Ʒ����
	 * 
	 * @param gt
	 *            ��Ʒ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int editGoodType(GoodType gt);

	/**
	 * ����idɾ����Ʒ����
	 * 
	 * @param id
	 *            ��Ʒ����id
	 * @return ��Ӱ�������
	 */
	public int delGoodType(int id);

	/**
	 * ͨ��id��ȡ������Ʒ����
	 * 
	 * @param id
	 *            ��Ʒ����id
	 * @return ������Ʒ����
	 */
	public GoodType getGoodTypeById(int id);

	/**
	 * ����������ѯ��Ʒ����
	 * 
	 * @param Condition
	 *            ����
	 * @return ��Ʒ���ͼ���
	 */
	public List<GoodType> getGoodType(String Condition);

	/**
	 * ����������ѯ����
	 * 
	 * @param condition
	 *            ����
	 * @return ����
	 */
	public int getcount(String condition);
}
