package xm.chen.service;

import java.util.List;

import xm.chen.modal.GoodType;

public interface GoodTypeService {
	/**
	 * �����Ʒ������Ϣ
	 * 
	 * @param gt
	 *            ��Ʒ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int addGoodType(GoodType gt);

	/**
	 * �༭��Ʒ������Ϣ
	 * 
	 * @param gt
	 *            ��Ʒ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int editGoodType(GoodType gt);

	/**
	 * ����idɾ����Ʒ������Ϣ
	 * 
	 * @param id
	 *            ��Ʒ����id
	 * @return ��Ӱ�������
	 */
	public int delGoodType(int id);

	/**
	 * ����������ѯ��Ʒ���ͼ���
	 * 
	 * @param Condition
	 *            ����
	 * @return ��Ʒ���ͼ���
	 */
	public List<GoodType> getGoodType(String Condition);

	/**
	 * ������Ʒ����id��ѯ��Ʒ������Ϣ
	 * 
	 * @param id
	 *            ��Ʒ����id
	 * @return ��Ʒ������Ϣ
	 */
	public GoodType getGoodTypeById(int id);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ����
	 */
	public int getcount(String condition);
}
