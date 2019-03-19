package xm.chen.service;

import java.util.List;

import xm.chen.modal.Goods;

public interface GoodService {
	/**
	 * �����Ʒ��Ϣ
	 * 
	 * @param good
	 *            ��Ʒ��Ϣʵ��
	 * @return ��Ӱ�������
	 */
	public int addGood(Goods good);

	/**
	 * ������Ʒ��Ϣ
	 * 
	 * @param good
	 *            ��Ʒʵ��
	 * @return ��Ӱ�������
	 */
	public int updateGood(Goods good);

	/**
	 * ������Ʒid��ȡ��Ʒ��Ϣ
	 * 
	 * @param id
	 *            ��Ʒid
	 * @return ��Ʒʵ��
	 */

	public Goods getGoodById(int id);

	/**
	 * ����������ȡ��Ʒ��Ϣ����
	 * 
	 * @param Condition
	 *            ����
	 * @return ��Ʒ��Ϣ����
	 */
	public List<Goods> getGoodByCondition(String Condition);

	/**
	 * ����id��Ʒɾ����Ʒ��Ϣ
	 * 
	 * @param id
	 *            ��Ʒid
	 * @return ��Ӱ�������
	 */
	public int deleteGood(int id);

	/**
	 * ����Ʒ��Ϣ��ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            true(����)
	 * @return ��Ʒ��Ϣ����
	 */
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order);

	/**
	 * ����key���в�ѯǰn������
	 * 
	 * @param key
	 *            ��ֵ
	 * @param n
	 *            ����
	 * @return ��Ʒ��Ϣ����
	 */
	public List<Goods> getGoodByKey(String key, int n);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ����
	 */
	public int Count(String condition);
}
